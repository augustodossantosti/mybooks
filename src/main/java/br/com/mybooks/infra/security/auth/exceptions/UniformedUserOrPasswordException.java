/*
 * mybooks 1.0 12 de jan de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.infra.security.auth.exceptions;

/**
 * A exceção <code>UniformedUserOrPasswordException</code> indica 
 * que as credenciais passadas durante o processo de autenticação
 * não possuem o campo referente ao nome de usuário ou a senha.
 *
 * @author Augusto dos Santos
 * @version 1.0 12 de jan de 2017
 */
public class UniformedUserOrPasswordException extends LibraryAuthenticationErrorException {

	private static final long serialVersionUID = 1L;

	public UniformedUserOrPasswordException(String msg) {
		super(msg);
	}

}
