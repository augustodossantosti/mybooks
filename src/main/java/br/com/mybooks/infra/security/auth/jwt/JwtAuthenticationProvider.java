/*
 * mybooks 1.0 15 de jan de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.infra.security.auth.jwt;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import br.com.mybooks.infra.security.auth.AuthenticatedUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

/**
 * A classe <code>JwtAuthenticationProvider</code> verifica a assinatura do token
 * e extrai identidade e autorizações do token de acesso para criar um usuário
 * pré autenticado.
 *
 * @author Augusto dos Santos
 * @version 1.0 15 de jan de 2017
 */
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private JwtSettings jwtSettings;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		final JwtBase64 jtw = (JwtBase64) authentication.getCredentials();
		final Jws<Claims> jwsClaims = jtw.parseClaims(jwtSettings.getTokenSigningKey());
        final String username = jwsClaims.getBody().getSubject();
        
        @SuppressWarnings("unchecked")
		final List<String> scopes = jwsClaims.getBody().get("scopes", List.class);
        
        final List<GrantedAuthority> authorities = getAuthority(scopes);
        
        final AuthenticatedUser authenticatedUser = AuthenticatedUser.create(username, authorities);
        
        return new JwtAuthenticationToken(authenticatedUser, authenticatedUser.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
	}
	
	private List<GrantedAuthority> getAuthority(final List<String> scopes) {
		return scopes.stream()
                .map(authority -> new SimpleGrantedAuthority(authority))
                .collect(Collectors.toList());
	}

}
