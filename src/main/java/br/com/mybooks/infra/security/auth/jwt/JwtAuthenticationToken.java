/*
 * mybooks 1.0 15 de jan de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.infra.security.auth.jwt;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import br.com.mybooks.infra.security.auth.AuthenticatedUser;

/**
 * A classe <code>JwtAuthenticationToken</code> atua como implementação de 
 * <code>Authentication</code> e é utilizada pelo Spring Security para 
 * armazenar o usuário logado através de um JWT no contexto de segurança.
 *
 * @author Augusto dos Santos
 * @version 1.0 15 de jan de 2017
 */
public class JwtAuthenticationToken extends AbstractAuthenticationToken {
	
	private static final long serialVersionUID = 2877954820905567501L;
	
	private JwtBase64 jwt;
	private AuthenticatedUser authenticatedUser;

	public JwtAuthenticationToken(final JwtBase64 jwt) {
        super(null);
        this.jwt = jwt;
        this.setAuthenticated(false);
    }

    public JwtAuthenticationToken(final AuthenticatedUser authenticatedUser, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.eraseCredentials();
        this.authenticatedUser = authenticatedUser;
        super.setAuthenticated(true);
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        if (authenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return jwt;
    }

    @Override
    public Object getPrincipal() {
        return this.authenticatedUser;
    }

    @Override
    public void eraseCredentials() {        
        super.eraseCredentials();
        this.jwt = null;
    }

}
