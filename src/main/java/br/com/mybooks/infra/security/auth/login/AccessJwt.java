/*
 * mybooks 1.0 13 de jan de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.infra.security.auth.login;

/**
 * A classe <code>AccessJwt</code> representa um JWT válido 
 * para o processo de autenticação na aplicação.
 *
 * @author Augusto dos Santos
 * @version 1.0 13 de jan de 2017
 */
public class AccessJwt {

	private final String token;

    public AccessJwt(final String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

}
