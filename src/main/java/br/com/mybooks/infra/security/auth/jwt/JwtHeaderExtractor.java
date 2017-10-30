/*
 * mybooks 1.0 15 de jan de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.infra.security.auth.jwt;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.mybooks.infra.security.auth.exceptions.InvalidAuthorizationHeaderException;

/**
 * A classe <code>JwtHeaderExtractor</code> é responsável por
 * extrair o token do cabeçalho da requisição.
 *
 * @author Augusto dos Santos
 * @version 1.0 15 de jan de 2017
 */
@Component
public class JwtHeaderExtractor {
	
	private static final String JWT_TOKEN_HEADER_PARAM = "Authorization";
	private static final String HEADER_PREFIX = "Bearer ";

	/**
	 * Extrai o token do campo X-Authorization do header da requisição.
	 * @throws InvalidAuthorizationHeaderException Erro ao processar o campo X-Authorization.
	 */
	public String extract(HttpServletRequest request) {
		
		final String token = request.getHeader(JWT_TOKEN_HEADER_PARAM);
		
		verifyToken(token);

        return token.substring(HEADER_PREFIX.length(), token.length());
	}

	private void verifyToken(final String token) {
		if (StringUtils.isBlank(token)) {
            throw new InvalidAuthorizationHeaderException("Authorization header cannot be blank!");
        }

        if (!token.startsWith(HEADER_PREFIX)) {
            throw new InvalidAuthorizationHeaderException("No JWT found! Check the authentication prefix.");
        }
	}

}
