package br.com.dbas.api.security.controllers;

import br.com.dbas.api.response.Response;
import br.com.dbas.api.security.dtos.TokenDto;
import br.com.dbas.api.security.entities.User;
import br.com.dbas.api.security.utils.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);
    private static final String TOKEN_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    private static final String CONSUMER_KEY = "consumer_key";
    private static final String CONSUMER_SECRET = "consumer_secret";


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping
    public ResponseEntity<Response<TokenDto>> gerarTokenJwt(HttpServletRequest request) throws AuthenticationException {
        Response<TokenDto> response = new Response<TokenDto>();

        Optional<String> userName = Optional.ofNullable(request.getHeader(CONSUMER_KEY));
        Optional<String> password = Optional.ofNullable(request.getHeader(CONSUMER_SECRET));

        if (!userName.isPresent() || !password.isPresent()){
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body(response);
        }

        log.info("Gerando token username =[" + userName);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userName.get(), password.get()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) userDetailsService.loadUserByUsername(userName.get());
        String token = jwtTokenUtil.obterToken(user);

        response.setData(new TokenDto(token));
        return ResponseEntity.ok(response);
    }

//    @PostMapping(value = "/refresh")
//    public ResponseEntity<Response<TokenDto>> gerarRefreshTokenJwt(HttpServletRequest request) {
//        log.info("Gerando refresh token JWT.");
//        Response<TokenDto> response = new Response<TokenDto>();
//        Optional<String> token = Optional.ofNullable(request.getHeader(TOKEN_HEADER));
//
//        if (token.isPresent() && token.get().startsWith(BEARER_PREFIX)) {
//            token = Optional.of(token.get().substring(7));
//        }
//
//        if (!token.isPresent()) {
//            response.getErrors().add("Token não informado.");
//        } else if (!jwtTokenUtil.tokenValido(token.get())) {
//            response.getErrors().add("Token inválido ou expirado.");
//        }
//
//        if (!response.getErrors().isEmpty()) {
//            return ResponseEntity.badRequest().body(response);
//        }
//
//        String refreshedToken = jwtTokenUtil.refreshToken(token.get());
//        response.setData(new TokenDto(refreshedToken));
//        return ResponseEntity.ok(response);
//    }

}
