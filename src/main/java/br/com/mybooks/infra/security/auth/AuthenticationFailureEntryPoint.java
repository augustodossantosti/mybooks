/*
 * mybooks 1.0 16 de jan de 2017
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
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * A classe <code>AuthenticationFailureEntryPoint</code> é responsável
 * por indicar que o acesso a uma determinado recurso foi negado.
 *
 * @author Augusto dos Santos
 * @version 1.0 16 de jan de 2017
 */
@Component
public class AuthenticationFailureEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex)
			throws IOException, ServletException {
		response.sendError(HttpStatus.FORBIDDEN.value(), "Forbidden.");
	}
	
}
