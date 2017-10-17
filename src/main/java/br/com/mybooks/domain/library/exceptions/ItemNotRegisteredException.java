/*
 * mybooks 1.0 31 de out de 2016
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.domain.library.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção responsável por indicar que um determinado item
 * não foi registrado na biblioteca.
 *
 * @author Augusto dos Santos
 * @version 1.0 31 de out de 2016
 */
@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Item not registered.")
public class ItemNotRegisteredException extends LibraryException {

	private static final long serialVersionUID = 1L;

}
