/*
 * mybooks 1.0 16 de jan de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.infra.security.auth.exceptions;

/**
 * A exceção <code>InvalidJwtTokenException</code> indica que o token fornecido
 * pela aplicação cliente não é válido.
 *
 * @author Augusto dos Santos
 * @version 1.0 16 de jan de 2017
 */
public class InvalidJwtTokenException extends LibraryAuthenticationErrorException {

	public InvalidJwtTokenException(String msg) {
		super(msg);
	}
	
	public InvalidJwtTokenException(String msg, Throwable t) {
		super(msg, t);
	}

	private static final long serialVersionUID = 1L;

}
