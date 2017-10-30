/*
 * mybooks 1.0 12 de jan de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.infra.security.auth.exceptions;

/**
 * A exceção <code>InvalidRequestException</code> indica que a requisição
 * enviada pela aplixação cliente é inválida.
 *
 * @author Augusto dos Santos
 * @version 1.0 12 de jan de 2017
 */
public class InvalidRequestException extends LibraryAuthenticationErrorException {

	private static final long serialVersionUID = 3705043083010304496L;
	
	public InvalidRequestException(String msg) {
		super(msg);
	}

}
