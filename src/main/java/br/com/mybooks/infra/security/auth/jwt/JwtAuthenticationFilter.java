/*
 * mybooks 1.0 15 de jan de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.infra.security.auth.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import br.com.mybooks.infra.security.auth.AuthenticationRequestVerifier;
import br.com.mybooks.infra.security.auth.exceptions.InvalidRequestException;

/**
 * A classe <code>JwtAuthenticationFilter</code> atua como filtro no
 * processo de autenticação com JWT.
 *
 * @author Augusto dos Santos
 * @version 1.0 15 de jan de 2017
 */
public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
	
	private final JwtHeaderExtractor tokenExtractor;

	public JwtAuthenticationFilter(RequestMatcher requestMatcher, JwtHeaderExtractor tokenExtractor) {
		super(requestMatcher);
		this.tokenExtractor = tokenExtractor;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {

		validateRequest(request);
		final String token = tokenExtractor.extract(request);
        final JwtBase64 jwt = new JwtBase64(token);
        return getAuthenticationManager().authenticate(new JwtAuthenticationToken(jwt));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(authResult);
		SecurityContextHolder.setContext(context);
		chain.doFilter(request, response);
	}
	
	/**
	 * Verifica se a requisição é válida para o processo de autenticação.
	 * @throws InvalidRequestException indica que a requisição não é válida.
	 */
	private void validateRequest(final HttpServletRequest request) {
		if (!AuthenticationRequestVerifier.isValidJwtRequest(request)) {
			throw new InvalidRequestException("Invalid Request! Check the request headers.");
		}
	}
}
