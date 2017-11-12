/*
 * mybooks 1.0 13 de jan de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.infra.security.auth.jwt;

import br.com.mybooks.infra.security.auth.exceptions.InvalidJwtTokenException;
import br.com.mybooks.infra.security.auth.exceptions.JwtExpiredTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * A classe <code>JwtBase64</code> encapsula o token fornecido no header 
 * da requisição pela aplicação cliente ainda codificado em Base64 para 
 * que sua assinatura seja verificada.
 *
 * @author Augusto dos Santos
 * @version 1.0 13 de jan de 2017
 */
public class JwtBase64 {
	
	private final String token;
	
	public JwtBase64(final String token) {
		this.token = token;
	}
	
	/**
	 * Analisa e valida a assinatura do token.
	 * @param signingKey A chave de assinatura do JWT
	 * @return O token validado e pronto para uso.
	 */
    public Jws<Claims> parseClaims(String signingKey) {
        try {
            return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token);
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException ex) {
            throw new InvalidJwtTokenException("Invalid JWT token: " + ex.getMessage());
        } catch (ExpiredJwtException expiredEx) {
            throw new JwtExpiredTokenException(this, "JWT expired: " +  expiredEx.getMessage());
        }
    }

	public String getToken() {
		return token;
	}

}
