/*
 * mybooks 1.0 12 de jan de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.infra.security.auth.login;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A classe <code>UserCredentialsFromRequest</code> encapsula as credenciais 
 * fornecidas pela aplicação cliente durante o pedido de autenticação.
 *
 * @author Augusto dos Santos
 * @version 1.0 12 de jan de 2017
 */
public class UserCredentialsFromRequest {
	
	private final String userName;
	private final String password;
	
	@JsonCreator
	public UserCredentialsFromRequest(@JsonProperty("username") String username, 
			@JsonProperty("password") String password) {
		
		this.userName = username;
		this.password = password;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}

}
