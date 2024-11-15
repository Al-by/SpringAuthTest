package com.alby.springauthtest.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Collections;
import java.util.Date;

public class Token {

    private final static String TOKEN_SECRETO = "/cK7=ra{Cp{Z6]=!FBHJcC#2ySz#Ad3a";
    private final static Long TOKEN_DURACION = 1_800_000L;

    // Modificar para aceptar nombre y email
    public static String crearToken(String nombre, String email) {
        Date expirationDate = new Date(System.currentTimeMillis() + TOKEN_DURACION);

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .claim("nombre", nombre) // Añadir el nombre al payload del JWT
                .signWith(Keys.hmacShaKeyFor(TOKEN_SECRETO.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuth(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(TOKEN_SECRETO.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String email = claims.getSubject();
            return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
        } catch (Exception e) {
            return null;
        }
    }
}
