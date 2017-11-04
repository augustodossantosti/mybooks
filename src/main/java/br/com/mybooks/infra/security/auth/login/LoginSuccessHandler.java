/*
 * mybooks 1.0 13 de jan de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.infra.security.auth.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.mybooks.infra.security.auth.AuthenticatedUser;

/**
 * A classe <code>LoginSuccessHandler</code> é responsável pela execução de 
 * procedimentos que devem suceder o processo de autentucação bem sucedido.
 *
 * @author Augusto dos Santos
 * @version 1.0 13 de jan de 2017
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private JwtTokenFactory tokenFactory;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	/**
	 * Cria o JWT e o adiciona ao cabeçalho da resposta para a aplicação cliente.
	 * Também é adicionado no corpo da resposta algumas informações uteis referentes 
	 * ao usuário autenticado.
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		final AuthenticatedUser authenticatedUser = (AuthenticatedUser) authentication.getPrincipal();
		final String userInformation = objectMapper.writeValueAsString(authenticatedUser.getUserInformation());
		final AccessJwt accessJWT = tokenFactory.createAccessJwt(authenticatedUser);
		
		response.setStatus(HttpStatus.OK.value());
		response.setHeader(HttpHeaders.AUTHORIZATION, accessJWT.getToken());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(userInformation);
		
		clearAuthenticationAttributes(request);
		
	}
	
	/**
     * Remove dados relacionados a autenticação que podem ter sido
     * armazenados na sessão.
     */
    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
        	session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }

}
