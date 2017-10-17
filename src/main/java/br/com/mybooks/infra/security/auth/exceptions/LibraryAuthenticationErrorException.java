/*
 * mybooks 1.0 17 de jan de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.infra.security.auth.exceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * A exceção <code>LibraryAuthenticationErrorException</code> indica 
 * erros no processo de autenticação. Ela é genérica e cabe as exceções
 * filhas especificarem o erro que representam.
 *
 * @author Augusto dos Santos
 * @version 1.0 17 de jan de 2017
 */
public class LibraryAuthenticationErrorException extends AuthenticationException {
	
	private static final long serialVersionUID = 1L;

	public LibraryAuthenticationErrorException(String msg) {
		super(msg);
	}
	
	public LibraryAuthenticationErrorException(String msg, Throwable t) {
		super(msg, t);
	}
}
