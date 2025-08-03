package com.emranhss.project.jwt;

import com.emranhss.project.entity.User;
import com.emranhss.project.repository.ITokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    @Autowired
    private ITokenRepository  tokenRepository;

    private final String SECURITY_KEY ="6SeHdW3EuTusarT0PxoJJRt9YOH1sadiar4C5uhmv0O3zF30YaD";

    //get all part from token
    private Claims extraAllClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(SECURITY_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(User user) {
        return Jwts
                .builder()
                .setSubject(user.getEmail())
                .claim("role",user.getRole())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                .signWith(getSigningKey())
                .compact();
    }

    public String extraUserName(String token) {
        return extratClaim(token,Claims::getSubject);
    }

    // Extract a specific Claim from the Token Claims
    public <T> T extratClaim(String token, Function<Claims, T> resolver) {
        Claims claims=extraAllClaims(token);
        return resolver.apply(claims);
    }

    private Date extractExpiration(String token) {
        return extratClaim(token,Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return extratClaim(token,Claims::getExpiration).before(new Date());
    }

    public boolean isValidToken(String token, UserDetails user) {
        String userName=extraUserName(token);

        boolean validToken =tokenRepository
                .findByToken(token)
                .map(t -> !t.isLogout())
                .orElse(false);

        return (userName.equals(user.getUsername()) && validToken);
    }

    //get user roll from token
    public String extractUserRole(String token) {
        return extratClaim(token,claims ->claims.get("role",String.class));
    }

}
