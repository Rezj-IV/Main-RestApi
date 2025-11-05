package com.api.eshop.security;

import com.api.eshop.domain.Users;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {

    // 🔹 استفاده از کلید امن و باینری برای جلوگیری از ارور Base64
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SecurityConstants.SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    // step 1 : generate the token
    public String generateToken(Authentication authentication) {
        Users users = (Users) authentication.getPrincipal();
        Date now = new Date(System.currentTimeMillis());
        Date expiryDate = new Date(now.getTime() + SecurityConstants.EXPIRATION_TIME);

        String userId = Long.toString(users.getId());
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userId);
        claims.put("username", users.getUsername());
        claims.put("name", users.getName());
        claims.put("lastname", users.getLastname());

        return Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SECRET_KEY, SignatureAlgorithm.HS512)
                .compact();
    }

    // step 2 : validate the token
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            System.out.println("invalid JWT signature");
        } catch (MalformedJwtException ex) {
            System.out.println("invalid JWT token");
        } catch (ExpiredJwtException ex) {
            System.out.println("expired JWT token");
        } catch (UnsupportedJwtException ex) {
            System.out.println("unsupported JWT token \n" + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            System.out.println("JWT claims string is empty");
        }
        return false;
    }

    // step 3 : get user id from token
    public int getUserIdFormJWT(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
        String id = (String) claims.get("id");
        return Integer.parseInt(id);
    }
}
