/*
 * mybooks 1.0 12 de jan de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.infra.security.auth;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpMethod;

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
    private static final String CONTENT_TYPE = "Content-type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String JWT_TOKEN_HEADER_PARAM = "Authorization";
    
    public static boolean isValidLoginRequest(HttpServletRequest request) {
        if(!HttpMethod.POST.name().equals(request.getMethod()) 
        		|| !hasValidLoginHeaders(request)
        		|| !request.getHeader(X_REQUESTED_WITH).equals(XML_HTTP_REQUEST)
        		|| !request.getHeader(CONTENT_TYPE).contains(CONTENT_TYPE_JSON)) {
			return false;
		}
        return true;
    }
    
    public static boolean isValidJwtRequest(HttpServletRequest request) {
    	return request.getHeader(JWT_TOKEN_HEADER_PARAM) != null ? true : false;
    }

    private static boolean hasValidLoginHeaders(final HttpServletRequest request) {
    	return request.getHeader(X_REQUESTED_WITH) != null && request.getHeader(CONTENT_TYPE) != null;
    }
}
