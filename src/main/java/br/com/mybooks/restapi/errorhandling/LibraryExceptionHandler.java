/*
 * mybooks 1.0 8 de mai de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.restapi.errorhandling;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.mybooks.domain.library.exceptions.LibraryException;

/**
 * A classe <code>LibraryExceptionHandler</code> é responsável
 * por interceptar todas as exceções de domínio ocorridas na 
 * aplicação e retornar no corpo da resposta as informações
 * referentes ao erro.
 *
 * @author Augusto dos Santos
 * @version 1.0 8 de mai de 2017
 */
@ControllerAdvice
public class LibraryExceptionHandler {
	
	private static final Logger logger = Logger.getLogger(LibraryExceptionHandler.class);
	
	private String message;
	private String uri;
	private LibraryErrorResponse errorResponse;
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<LibraryErrorResponse> handleException(final HttpServletRequest request, final Exception exception) {
		
		uri = request.getRequestURI();
		
		if (exception instanceof LibraryException) {
			final ResponseStatus responseStatus = exception.getClass().getAnnotation(ResponseStatus.class);
			message = responseStatus.reason();
			logger.error("Failed a domain operation");
			logger.error("Cause of error: " + message);
			logger.error("URI: " + uri);
			errorResponse = LibraryErrorResponse.of(message, uri);
			final ResponseEntity<LibraryErrorResponse> response = new ResponseEntity<>(errorResponse, responseStatus.value());
			return response;
		}
		
		message = exception.getMessage() != null ? exception.getMessage() : exception.toString();
		logger.error("Internal application error.");
		logger.error("Cause of error: " + message);
		logger.error("URI: " + uri);
		errorResponse = LibraryErrorResponse.of(message, uri);
		final ResponseEntity<LibraryErrorResponse> response = new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		return response;
	}
}
