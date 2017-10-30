/*
 * mybooks 1.0 19 de out de 2016
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.domain.library.exceptions;

/**
 * A exceção <code>LibraryException</code> é a exceção de
 * nível mais alto referente ao dominio da aplicação.
 * Cabe as exceções que a estendem serem mais específicas
 * quanto ao problema que representam.
 *
 * @author Augusto dos Santos
 * @version 1.0 19 de out de 2016
 */
public abstract class LibraryException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public LibraryException() {}
	
	public LibraryException(final String message) {
		super(message);
	}

}
