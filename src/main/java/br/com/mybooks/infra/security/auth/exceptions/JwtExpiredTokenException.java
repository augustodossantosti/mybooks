/*
 * mybooks 1.0 13 de jan de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.infra.security.auth.exceptions;

import br.com.mybooks.infra.security.auth.jwt.JwtBase64;

/**
 * A exceção <code>JwtExpiredTokenException</code> indica 
 * que o JWT passado no processo de autenticação já expirou.
 *
 * @author Augusto dos Santos
 * @version 1.0 13 de jan de 2017
 */
public class JwtExpiredTokenException extends LibraryAuthenticationErrorException {

	private static final long serialVersionUID = -5959543783324224864L;
    
    private JwtBase64 token;

    public JwtExpiredTokenException(String msg) {
        super(msg);
    }
    
    public JwtExpiredTokenException(JwtBase64 token, String msg) {
        super(msg);
        this.token = token;
    }

    public JwtExpiredTokenException(JwtBase64 token, String msg, Throwable t) {
        super(msg, t);
        this.token = token;
    }

    public String token() {
        return token.getToken();
    }

}
