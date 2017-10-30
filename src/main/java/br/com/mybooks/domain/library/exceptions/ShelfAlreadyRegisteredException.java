/*
 * mybooks 1.0 30 de out de 2016
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.domain.library.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção responsável por indicar, no processo de registro de uma
 * nova prateleira, que já existe uma prateleira registrada para
 * a categoria informada.
 *
 * @author Augusto dos Santos
 * @version 1.0 30 de out de 2016
 */
@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE, reason="Shelf already registered.")
public class ShelfAlreadyRegisteredException extends LibraryException {

	private static final long serialVersionUID = 1L;

}
