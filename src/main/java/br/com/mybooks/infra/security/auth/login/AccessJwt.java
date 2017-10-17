/*
 * mybooks 1.0 13 de jan de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.infra.security.auth.login;

import io.jsonwebtoken.Claims;

/**
 * A classe <code>AccessJwt</code> representa o JWT de
 * autenticação e acesso.
 *
 * @author Augusto dos Santos
 * @version 1.0 13 de jan de 2017
 */
public class AccessJwt {

	private final String rawToken;
	
    private final Claims claims;

    public AccessJwt(final String token, Claims claims) {
        this.rawToken = token;
        this.claims = claims;
    }

    public String getToken() {
        return rawToken;
    }

    public Claims getClaims() {
        return claims;
    }

}
