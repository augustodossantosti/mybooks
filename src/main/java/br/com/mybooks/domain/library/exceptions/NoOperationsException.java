/*
 * mybooks 1.0 22 de fev de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.domain.library.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção responsável por indicar que não ocorreram
 * operações de dominio na biblioteca.
 *
 * @author Augusto dos Santos
 * @version 1.0 22 de fev de 2017
 */
@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No operations.")
public class NoOperationsException extends LibraryException {

	private static final long serialVersionUID = 1L;

}
