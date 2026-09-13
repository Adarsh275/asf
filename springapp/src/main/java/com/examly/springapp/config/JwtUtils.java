package com.examly.springapp.config;
 
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
 
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
 
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
 
@Service
public class JwtUtils {
   
    @Value("${spring.security.jwt.secret-key}")
    private String secretKey;
 
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
 
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
 
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
 
    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
 
    // generate the token
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        String role = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("ADMIN");
        claims.put("role", role);
        claims.put("user_id", ((UserPrinciple) userDetails).getUserId()); // Store as Long
        claims.put("username", userDetails.getUsername());
        return generateToken(userDetails, claims);
    }
    
    public Long extractUserId(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("user_id", Long.class); // Extract as Long
    }
    

    public String generateToken(UserDetails userDetails, Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuer("Book Heaven")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }
 
    // validate the token
    public boolean isValidateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
 
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
 
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
