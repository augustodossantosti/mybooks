/*
 * mybooks 1.0 12 de jan de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.infra.security.auth.login;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.mybooks.domain.users.User;
import br.com.mybooks.infra.security.auth.AuthenticatedUser;
import br.com.mybooks.infra.security.auth.exceptions.InvalidCredentialsException;
import br.com.mybooks.infra.services.UserService;

/**
 * A classe <code>LoginAuthenticationProvider</code> verifica se as credenciais
 * fornecidas pela aplicação cliente batem com as credenciais armazenadas no
 * banco de dados. Em caso de sucesso delega a <code>LoginSuccessHandler</code>
 * e em caso de falha delega a <code>DefaultAuthenticationFailureHandler</code>.
 *
 * @author Augusto dos Santos
 * @version 1.0 12 de jan de 2017
 */
@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {
	
	private final BCryptPasswordEncoder passwordEncoder;
	private final UserService userService;
	
	@Autowired
	public LoginAuthenticationProvider(final BCryptPasswordEncoder passwordEncoder, final UserService userService) {
		this.passwordEncoder = passwordEncoder;
		this.userService = userService;
	}

	/**
	 * Verifica as credenciais fornecidas comparando-as com as armazenadas no banco
	 * de dados. Caso as credenciais sejam válidas o usuário é autenticado e o
	 * processo de criação do token é delegado para <code>LoginSuccessHandler</code>.
	 * @throws InvalidCredentialsException As credenciais fornecidas são inválidas.
	 * @throws InsufficientAuthenticationException O usuário não possui roles associadas.
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
        final String username = (String) authentication.getPrincipal();
        final String password = (String) authentication.getCredentials();

        final User user = userService.getByUsername(username).orElseThrow(() 
        		-> new InvalidCredentialsException("Authentication Failed. Username or Password not valid."));
        
        verifyPassword(password, user);

        verifyRoles(user);
        
        final List<GrantedAuthority> authorities = getAuthority(user);
        
        final AuthenticatedUser authenticatedUser = AuthenticatedUser.create(user.getUsername(), authorities);
        
        return new UsernamePasswordAuthenticationToken(authenticatedUser, null, authenticatedUser.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
	
    private void verifyPassword(final String password, final User user) {
		if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException("Authentication Failed. Username or Password not valid.");
        }
	}
    
    private void verifyRoles(final User user) {
		if (user.getRoles() == null || user.getRoles().isEmpty()) {
        	throw new InvalidCredentialsException("Credentials has no roles assigned");
        }
	}
    
    private List<GrantedAuthority> getAuthority(final User user) {
		return user.getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRole().authority()))
                .collect(Collectors.toList());
	}
}
