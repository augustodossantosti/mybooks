/*
 * mybooks 1.0 16 de jan de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.infra.security.auth.jwt;

/**
 * A interface <code>JwtVerifier</code> define os métodos de
 * verificação de JWTs.
 *
 * @author Augusto dos Santos
 * @version 1.0 16 de jan de 2017
 */
public interface JwtVerifier {

	public boolean verify(String value);
}
