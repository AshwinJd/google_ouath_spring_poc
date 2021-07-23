package com.stackroute.userservice.util;

import com.stackroute.userservice.users.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class JwtUtil {
    static final long EXPIRATIONTIME = 3600000;// 1 hour in milliseconds
    static String SIGNINGKEY;
    static final String PREFIX = "Bearer";

    @Value("${SIGNING_KEY}")
    public void setSigningkey(String signingkey) {
        SIGNINGKEY = signingkey;
    }

    // Add token to Authorization header
    public static String addToken(HttpServletResponse res, Users user) {
        Claims claims = Jwts.claims();
        System.out.println("SIGNINGKEY" + SIGNINGKEY);
        claims.put("username", user.getUserName()); // username
        claims.put("name", user.getName()); // given name or name of user
        claims.put("avatar", user.getAvatarURL()); // avatar url
        String jwtToken = Jwts.builder().setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SIGNINGKEY).compact();
        return jwtToken;
    }
}
