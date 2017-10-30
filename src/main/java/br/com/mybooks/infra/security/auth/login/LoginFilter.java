/*
 * mybooks 1.0 12 de jan de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.infra.security.auth.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.mybooks.infra.security.auth.AuthenticationRequestVerifier;
import br.com.mybooks.infra.security.auth.exceptions.InvalidCredentialsException;
import br.com.mybooks.infra.security.auth.exceptions.InvalidRequestException;
import br.com.mybooks.infra.security.auth.exceptions.UniformedUserOrPasswordException;

/**
 * A classe <code>LoginFilter</code> trata os pedidos de autenticação,
 * verificando as credenciais enviadas no corpo da request pela
 * aplicação cliente.
 *
 * @author Augusto dos Santos
 * @version 1.0 12 de jan de 2017
 */
public class LoginFilter extends AbstractAuthenticationProcessingFilter {
	
	@Autowired
	private ObjectMapper jsonMapper;
	
	public LoginFilter(final String defaultFilterProcessesUrl) {
		super(defaultFilterProcessesUrl);
	}

	/**
	 * Realiza o processo de autenticação através das credenciais 
	 * enviadas pela aplicação cliente.
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		validateRequest(request);
		
		UserCredentialsFromRequest credentials = deserializeCredentials(request);
			
		validateCredentials(credentials);
		
		final UsernamePasswordAuthenticationToken token = 
				new UsernamePasswordAuthenticationToken(credentials.getUserName(), credentials.getPassword());
		
		return getAuthenticationManager().authenticate(token);
	}
	
	/**
	 * Verifica se a requisição é válida para o processo de autenticação.
	 * @throws InvalidRequestException indica que a requisição não é válida.
	 */
	private void validateRequest(final HttpServletRequest request) {
		if (!AuthenticationRequestVerifier.isValidLoginRequest(request)) {
			throw new InvalidRequestException("Invalid Request! Check the http method and request headers.");
		}
	}
	
	/**
	 * Deserializa o JSON com as credenciais enviadas pela aplicação cliente.
	 * @return Uma instância de <code>UserCredentialsFromRequest</code> contendo as credenciais.
	 */
	private UserCredentialsFromRequest deserializeCredentials(final HttpServletRequest request) {
		UserCredentialsFromRequest credentials;
		try {
			credentials = jsonMapper.readValue(request.getReader(), UserCredentialsFromRequest.class);
		} catch (Exception deserializeException) {
			throw new InvalidCredentialsException("JSON with credentials was not informed.");
		}
		return credentials;
	}
	
	/**
	 * Realiza o processo de validação das credenciais.
	 * @throws UniformedUserOrPasswordException Indica que o nome de usuário ou a senha não foram informados.
	 */
	private void validateCredentials(final UserCredentialsFromRequest credentials) {
		if(StringUtils.isBlank(credentials.getUserName()) || StringUtils.isBlank(credentials.getPassword())) {
			throw new UniformedUserOrPasswordException("Uninformed user or password.");
		}
	}
}
