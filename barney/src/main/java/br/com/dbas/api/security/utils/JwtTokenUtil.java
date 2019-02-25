package br.com.dbas.api.security.utils;

import br.com.dbas.api.security.entities.Endpoint;
import br.com.dbas.api.security.entities.Page;
import br.com.dbas.api.security.entities.User;
import br.com.dbas.api.security.enums.HttpMethod;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil {

    static final String CLAIM_KEY_USERNAME = "sub"; // Username

    static final String CLAIM_KEY_ROLE = "role"; //authorities

    static final String CLAIM_KEY_PAGE = "page";

    static final String CLAIM_KEY_CREATED = "created"; // data

//    static final String CLAIM_KEY_AUDIENCE = "audience";

    /**
     * Chave para autenticacao do token vem do arquivo de propiedades
     *
     */
    @Value("${jwt.secret}")
    private String secret;

    /**
     * Tempo de duracao do token vem do arquivo de propiedades
     *
     */
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * Obtém o username contido no token JWT.
     *
     * @param token
     * @return String
     */
    public String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * Retorna a data de expiração de um token JWT.
     *
     * @param token
     * @return Date
     */
    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    /**
     * Retorna um novo token JWT com base nos dados do usuários.
     *
     * @param user
     * @return String
     */
    public String obterToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, user.getUsername());
        user.getAuthorities().forEach(authority -> claims.put(CLAIM_KEY_ROLE, authority.getAuthority()));

        Map<String, List<HttpMethod>> pages = new HashMap<>();
        if (!user.getAuthorities().isEmpty()) {
            user.getAuthorities().forEach( authorities -> {
                if (!authorities.getEndpoints().isEmpty()) {
                    authorities.getEndpoints().stream().collect(Collectors.groupingBy(Endpoint::getPage)).forEach((page, endpoints) -> {
                        List<HttpMethod> httpMethods = new ArrayList<>();
                        endpoints.forEach(endpoint -> httpMethods.add(endpoint.getHttpMethod()));
                        pages.put(page.getName(), httpMethods);
                    });
                }
            });
        }
        claims.put(CLAIM_KEY_PAGE, pages);

        claims.put(CLAIM_KEY_CREATED, new Date());

        return gerarToken(claims);
    }

    /**
     * Gera um novo token JWT contendo os dados (claims) fornecidos.
     *
     * @param claims
     * @return String
     */
    private String gerarToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims).setExpiration(gerarDataExpiracao())
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    /**
     * Renova um token (refresh).
     *
     * @param token
     * @return String
     */
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = gerarToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }


    /**
     * Realiza o parse do token JWT para extrair as informações contidas no
     * corpo dele.
     *
     * @param token
     * @return Claims
     */
    private Claims getClaimsFromToken(String token) {
        // Usa chave do token para descriptografar
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * Retorna a data de expiração com base na data atual.
     *
     * @return Date
     */
    private Date gerarDataExpiracao() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }


    /**
     * Verifica e retorna se um token JWT é válido.
     *
     * @param token
     * @return boolean
     */
    public boolean tokenValido(String token) {
        return !tokenExpirado(token);
    }

    /**
     * Verifica se um token JTW está expirado.
     *
     * @param token
     * @return boolean
     */
    private boolean tokenExpirado(String token) {
        Date dataExpiracao = this.getExpirationDateFromToken(token);
        if (dataExpiracao == null) {
            return false;
        }
        return dataExpiracao.before(new Date());
    }

}