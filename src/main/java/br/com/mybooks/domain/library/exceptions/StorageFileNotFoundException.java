/*
 * mybooks 1.0 20 de out de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.domain.library.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção responsável por indicar que 
 *
 * @author Augusto dos Santos
 * @version 1.0 20 de out de 2017
 */
@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Could not read file.")
public class StorageFileNotFoundException extends LibraryException {

	private static final long serialVersionUID = 1L;

}
