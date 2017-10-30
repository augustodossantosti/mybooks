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
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import br.com.mybooks.infra.security.auth.AuthenticatedUser;

/**
 * A classe <code>LoginSuccessHandler</code> é responsável por
 * adicionar os JWTs de acesso e de atualização na resposta da
 * requisição após o processo de autenticação ter sido bem sucedido.
 *
 * @author Augusto dos Santos
 * @version 1.0 13 de jan de 2017
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	private final JwtTokenFactory tokenFactory;
	
	@Autowired
	public LoginSuccessHandler(final JwtTokenFactory tokenFactory) {
		this.tokenFactory = tokenFactory;
	}
	
	/**
	 * Cria os tokens de acesso e atualização para usuários pré autenticados
	 * e os envia no cabeçalho da resposta para a aplicação cliente.
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		final AuthenticatedUser authenticatedUser = (AuthenticatedUser) authentication.getPrincipal();
		final AccessJwt accessJWT = tokenFactory.createAccessJwt(authenticatedUser);
		
		response.setStatus(HttpStatus.OK.value());
		response.setHeader("Authorization", accessJWT.getToken());
		
		clearAuthenticationAttributes(request);
		
	}
	
	/**
     * Remove os dados relacionados a autenticação temporária que podem ter sido
     * armazenados na sessão durante o processo de autenticação.
     * 
     */
    protected final void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
        	session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }

}
