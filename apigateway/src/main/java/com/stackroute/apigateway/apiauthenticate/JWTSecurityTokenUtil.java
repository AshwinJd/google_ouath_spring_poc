package com.stackroute.apigateway.apiauthenticate;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class JWTSecurityTokenUtil implements Serializable {
    private static final long serialVersionUID = -2550185165626007488L;
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    @Value("${jwt.secret}")
    private String jwtSecretKey;

    //retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        Claims claims = getClaimFromToken(token);
        return (String)claims.get("username");
    }

    public Claims getClaimFromToken(String token) {
        final Claims claims = getAllClaimsFromToken(token);
        return claims;
    }

    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token).getBody();
    }


    //validate token
    public Boolean validateToken(String token) {
        System.out.println("secret" + jwtSecretKey);
        final String username = getUsernameFromToken(token);
        if(username != null) {
            return true;
        }
        return false;
    }
}
