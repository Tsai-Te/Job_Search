package com.te.extend.security;

import com.te.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    static final String CLAIM_KEY_USERNAME = "sub"; //todo ask this part
    static final String CLAIM_KEY_AUDIENCE = "audience";
    static final String CLAIM_KEY_CREATED = "created";

    @Value("#{shareProperties['jwt.expiration']}")
    private Long expiration; // L: Long type

    @Value("#{shareProperties['jwt.secret']}")
    private String secret;

    public String generateToken(UserDetails userDetails) { //todo ask why 2 generateToken? Map?
        Map<String, Object> claims=new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    private String generateToken(Map<String, Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
            logger.error("cannot find claims");
        }
        return claims;
    }


    public String getUsernameFromToken(String token){ //get username from claims, so we need to get claims from token first
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username=null;
            logger.error("cannot find username from token");
        }
        return username;
    }

    public Date getExpirationDateFromToken(String token){
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration=null;
            logger.error("cannot find date from token");
        }
        return expiration;
    }

    private Date getExpirationDate(String token){
        return new Date(System.currentTimeMillis());
    }

    private Boolean isTokenExpired(String token){
        final Date expiration = getExpirationDate(token);
        return expiration.before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        User user = (User) userDetails;
        final String username = getUsernameFromToken(token);
        return (
                username.equals(user.getUsername())
                        && !isTokenExpired(token));

    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }
}
