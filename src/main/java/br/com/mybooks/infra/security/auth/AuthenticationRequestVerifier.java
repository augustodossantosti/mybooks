/*
 * mybooks 1.0 12 de jan de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.infra.security.auth;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

/**
 * A classe <code>AuthenticationRequestVerifier</code> é responsável
 * por validar requisições de login.
 *
 * @author Augusto dos Santos
 * @version 1.0 12 de jan de 2017
 */
public class AuthenticationRequestVerifier {
	
	private static final String X_REQUESTED_WITH = "X-Requested-With";
	private static final String XML_HTTP_REQUEST = "XMLHttpRequest";
    
    public static boolean isValidLoginRequest(final HttpServletRequest request) {
        if(!HttpMethod.POST.name().equals(request.getMethod()) 
        		|| !hasValidLoginHeaders(request)
        		|| !request.getHeader(X_REQUESTED_WITH).equals(XML_HTTP_REQUEST)
        		|| !request.getHeader(HttpHeaders.CONTENT_TYPE).contains(MediaType.APPLICATION_JSON_VALUE)) {
			return false;
		}
        return true;
    }
    
    public static boolean isValidJwtRequest(final HttpServletRequest request) {
    	return request.getHeader(HttpHeaders.AUTHORIZATION) != null ? true : false;
    }

    private static boolean hasValidLoginHeaders(final HttpServletRequest request) {
    	return request.getHeader(X_REQUESTED_WITH) != null && request.getHeader(HttpHeaders.CONTENT_TYPE) != null;
    }
}
