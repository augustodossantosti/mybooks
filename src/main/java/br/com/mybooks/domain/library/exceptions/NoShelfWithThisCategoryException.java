/*
 * mybooks 1.0 1 de nov de 2016
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.domain.library.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção responsável por indicar que não há prateleiras
 * pertencentes a categoria informada.
 *
 * @author Augusto dos Santos
 * @version 1.0 1 de nov de 2016
 */
@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No shelf with this category.")
public class NoShelfWithThisCategoryException extends LibraryException {

	private static final long serialVersionUID = 1L;

}
