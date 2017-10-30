/*
 * mybooks 1.0 27 de mar de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.infra.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.mybooks.infra.security.auth.AuthenticatedUser;

/**
 * A classe <code>AuthenticationService</code> fornce serviços
 * referentes a autenticação de usuários.
 *
 * @author Augusto dos Santos
 * @version 1.0 27 de mar de 2017
 */
@Service
public class AuthenticationService {
	
	public AuthenticatedUser getAuthenticatedUser() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		final AuthenticatedUser authenticatedUser = (AuthenticatedUser) authentication.getPrincipal();
		return authenticatedUser;
	}

}
