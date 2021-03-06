/*
 * mybooks 1.0 13 de jan de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.infra.security.auth.login;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mybooks.infra.security.auth.AuthenticatedUser;
import br.com.mybooks.infra.security.auth.jwt.JwtSettings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * A classe <code>JwtTokenFactory</code> é responsável pela 
 * criação de JWTs para o processo de autenticação.
 *
 * @author Augusto dos Santos
 * @version 1.0 13 de jan de 2017
 */
@Component
public class JwtTokenFactory {
	
	@Autowired
	private JwtSettings jwtSettings;
	
	/**
	 * Cria um novo JWT de acesso.
	 * @throws IllegalArgumentException Neste caso indica username ou privilégios em branco.
	 */
	public AccessJwt createAccessJwt(final AuthenticatedUser user) {
        
        final Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("scopes", user.getAuthorities().stream().map(s -> s.toString()).collect(Collectors.toList()));

        final Instant currentTime = Instant.now();

        String token = Jwts.builder()
          .setClaims(claims)
          .setIssuer(jwtSettings.getTokenIssuer())
          .setId(UUID.randomUUID().toString())
          .setIssuedAt(Date.from(currentTime))
          .setExpiration(Date.from(currentTime.plus(jwtSettings.getTokenExpirationTime(), ChronoUnit.MINUTES)))
          .signWith(SignatureAlgorithm.HS512, jwtSettings.getTokenSigningKey())
          .compact();

        return new AccessJwt(token);
    }

}
