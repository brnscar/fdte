package br.com.dbas.api.security.filters;

import br.com.dbas.api.security.entities.Authorities;
import br.com.dbas.api.security.entities.Endpoint;
import br.com.dbas.api.security.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = request.getHeader(AUTH_HEADER);
        if (token != null && token.startsWith(BEARER_PREFIX)) {
            token = token.substring(7);
        }
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if (jwtTokenUtil.tokenValido(token)) {
                AtomicBoolean forbidden = new AtomicBoolean(true);
                userDetails.getAuthorities().forEach(authorit -> {
                    ((Authorities) authorit).getEndpoints().forEach(endpoint -> {
                        if(endpoint.getHttpMethod().toString().equalsIgnoreCase(request.getMethod())) {
                            validUrl(request, forbidden, endpoint);
                        }
                    });
                });
                if (forbidden.get()) {
                    response.setStatus(403);
                } else {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }

    private void validUrl(HttpServletRequest request, AtomicBoolean forbidden, Endpoint endpoint) {
        String[] urlEndpoint = endpoint.getUrl().split("/");
        String[] uri = request.getRequestURI().split("/");
        for (int i = 0; i < urlEndpoint.length && i < uri.length; i++) {
            if (urlEndpoint[i].contains("{")) {
                uri[i] = "{}";
                urlEndpoint[i] = "{}";
            }
        }
        if (Arrays.toString(urlEndpoint).equalsIgnoreCase(Arrays.toString(uri))) {
            forbidden.set(false);
        }
    }

}
