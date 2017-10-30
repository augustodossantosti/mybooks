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
 * Exceção responsável por indicar que não ocorreram
 * eventos de domínio do tipo especificado.
 *
 * @author Augusto dos Santos
 * @version 1.0 31 de out de 2016
 */
@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No domain event of this type.")
public class NoDomainEventOfThisTypeException extends LibraryException {

	private static final long serialVersionUID = 1L;

}
