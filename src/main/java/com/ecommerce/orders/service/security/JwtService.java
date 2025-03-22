package com.ecommerce.orders.service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JwtService {

  private static final long ACCESS_TOKEN_EXPIRATION = 1000 * 60 * 15; // 15 minutes
  private static final long REFRESH_TOKEN_EXPIRATION = 1000L * 60 * 60 * 24 * 7; // 7 days
  private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

  public String generateAccessToken(UserDetails userDetails) {
    return Jwts.builder()
        .setSubject(userDetails.getUsername())
        .claim("roles", extractRoles(userDetails.getAuthorities()))
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION))
        .signWith(key)
        .compact();
  }

  public String generateRefreshToken(UserDetails userDetails) {
    return Jwts.builder()
        .setSubject(userDetails.getUsername())
        .claim("roles", extractRoles(userDetails.getAuthorities()))
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION))
        .signWith(key)
        .compact();
  }

  public String extractUsername(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }

  public boolean isTokenValid(String token, String username) {
    return extractUsername(token).equals(username);
  }

  public Date getAccessTokenExpiration(String token) {
    return getExpirationDate(token);
  }

  public Date getRefreshTokenExpiration(String token) {
    return getExpirationDate(token);
  }

  private Date getExpirationDate(String token) {
    return parseAllClaims(token).getExpiration();
  }

  private Claims parseAllClaims(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  private String extractRoles(Collection<? extends GrantedAuthority> authorities) {
    return authorities.stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(","));
  }

  public Collection<? extends GrantedAuthority> extractRoles(String token) {

    Claims claims = Jwts.parserBuilder()
        .setSigningKey(key) // Asegúrate de tener tu clave secreta correctamente configurada
        .build()
        .parseClaimsJws(token)
        .getBody();

    List<String> roles = claims.get("roles", List.class);

    return roles.stream()
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
  }

}