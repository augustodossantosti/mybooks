/*
 * mybooks 1.0 26 de jan de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.infra.security.auth.exceptions;

/**
 * A exceção <code>InvalidAuthorizationHeaderException</code> indica que o
 * header de autorização enviado pela aplicação cliente é inválido.
 *
 * @author Augusto dos Santos
 * @version 1.0 26 de jan de 2017
 */
public class InvalidAuthorizationHeaderException extends LibraryAuthenticationErrorException {

	private static final long serialVersionUID = 1L;
	
	public InvalidAuthorizationHeaderException(String msg) {
		super(msg);
	}
	
	public InvalidAuthorizationHeaderException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

}
