package com.training.agora.authenticate;

import com.training.agora.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY="7638792F423F4528482B4B6250655368566D597133743677397A24432646294A";

    public String generateToken(User user){
        Map<String,Object>claims=new HashMap<>();
        claims.put("user_id", user.getId());
        claims.put("role",user.getAuthorities());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24))
                .signWith(signInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key signInKey() {
        byte[]keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public boolean isValidToken(String token,UserDetails userDetails){
        String userName = extractUsername(token);
        return (userName.equals(userDetails.getUsername())&&!isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    public String extractUsername(String token) {
        return extractClaim(token,Claims::getSubject);
    }
    private  <T> T extractClaim(String token, Function<Claims,T>claimsResolver){
        final Claims claims=extractClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractClaims(String token) {
       return Jwts.parserBuilder()
               .setSigningKey(signInKey())
               .build()
               .parseClaimsJws(token)
               .getBody();
    }
}
