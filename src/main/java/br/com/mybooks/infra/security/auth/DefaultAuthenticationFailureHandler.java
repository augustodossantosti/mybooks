/*
 * mybooks 1.0 14 de jan de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.infra.security.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.mybooks.restapi.errorhandling.LibraryErrorResponse;

/**
 * A classe <code>DefaultAuthenticationFailureHandler</code> é reponsável
 * por informar a aplicação cliente que o processo de autenticação não 
 * foi bem sucedido.
 *
 * @author Augusto dos Santos
 * @version 1.0 14 de jan de 2017
 */
@Component
public class DefaultAuthenticationFailureHandler implements AuthenticationFailureHandler {
	
	private final ObjectMapper jsonMapper;
	
	public DefaultAuthenticationFailureHandler(final ObjectMapper mapper) {
		this.jsonMapper = mapper;
	}

	/**
	 * Envia como resposta a aplicação cliente o erro ocorrido no
	 * processo de autenticação.
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authenticationException)
			throws IOException, ServletException {
		
		final LibraryErrorResponse errorResponse = 
				LibraryErrorResponse.of(authenticationException.getMessage(), request.getRequestURI());

		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		
		jsonMapper.writeValue(response.getWriter(), errorResponse);
	}

}
