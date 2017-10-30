/*
 * mybooks 1.0 14 de jan de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.restapi.errorhandling;

import java.util.Date;

/**
 * A classe <code>LibraryErrorResponse</code> é responsável por
 * encapsular mensagens de erros que podem ser geradas durante o
 * processo de autenticação.
 *
 * @author Augusto dos Santos
 * @version 1.0 14 de jan de 2017
 */
public class LibraryErrorResponse {
	
	private final String message;
	private final String url;
	private final Date timestamp;
	
	private LibraryErrorResponse(String message, String url) {
		this.message = message;
		this.url = url;
		timestamp = new Date();
	}
	
	public static LibraryErrorResponse of(final String message, final String url) {
        return new LibraryErrorResponse(message, url);
    }

    public String getMessage() {
        return message;
    }
    
    public String getUrl() {
    	return url;
    }

    public Date getTimestamp() {
        return timestamp;
    }

}
