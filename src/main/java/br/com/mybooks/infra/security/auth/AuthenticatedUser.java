/*
 * mybooks 1.0 13 de jan de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.infra.security.auth;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;

/**
 * A classe <code>AuthenticatedUser</code> representa um usuário
 * autenticado.
 * Ela se difere da classe <code>User</code> provida pelo Spring no
 * fato de que ela não precisa de senha no método construtor.
 *
 * @author Augusto dos Santos
 * @version 1.0 13 de jan de 2017
 */
public class AuthenticatedUser {
	
	private final String username;
	private final List<GrantedAuthority> authorities;
	
	
	private AuthenticatedUser(final String username, final List<GrantedAuthority> authorities) {
		this.username = username;
		this.authorities = authorities;
	}
	
	public static AuthenticatedUser create(final String username, final List<GrantedAuthority> authorities) {
        if (StringUtils.isBlank(username)) {
        	throw new IllegalArgumentException("Username is blank.");
        }
        return new AuthenticatedUser(username, authorities);
    }

    public String getUsername() {
        return username;
    }
    
    public List<GrantedAuthority> getAuthorities() {
        return Collections.unmodifiableList(authorities);
    }
    
}
