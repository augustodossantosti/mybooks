/*
 * mybooks 1.0 17 de jan de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.infra.security.auth.exceptions;

/**
 * A exceção <code>InvalidCredentialsException</code> indica que as
 * credenciais passadas durante o processo de autenticação não são
 * válidas.
 *
 * @author Augusto dos Santos
 * @version 1.0 17 de jan de 2017
 */
public class InvalidCredentialsException extends LibraryAuthenticationErrorException {

	private static final long serialVersionUID = 1L;
	
	public InvalidCredentialsException(String msg) {
		super(msg);
	}

}
