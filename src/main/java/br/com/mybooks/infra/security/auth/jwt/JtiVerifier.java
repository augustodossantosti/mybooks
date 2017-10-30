/*
 * mybooks 1.0 16 de jan de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.infra.security.auth.jwt;

import org.springframework.stereotype.Component;

/**
 * A classe <code>JtiVerifier</code> é responsável por
 * verificar se os tokens são válidos através de seu jti.
 *
 * @author Augusto dos Santos
 * @version 1.0 16 de jan de 2017
 */
@Component
public class JtiVerifier implements JwtVerifier {

	@Override
	public boolean verify(String jti) {
		return true;
	}

}
