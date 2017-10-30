/*
 * mybooks 1.0 8 de mar de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.domain.shelf.exceptions;

import br.com.mybooks.domain.library.exceptions.LibraryException;

/**
 * Exceção responsável por indicar que um determinado item não está
 * armazenado na prateleira.
 *
 * @author Augusto dos Santos
 * @version 1.0 8 de mar de 2017
 */
public class ItemNotFoundException extends LibraryException {

	private static final long serialVersionUID = 1L;

}
