/*
 * mybooks 1.0 16 de jan de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.infra.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.mybooks.infra.security.auth.AuthenticationFailureEntryPoint;
import br.com.mybooks.infra.security.auth.StrategyRequestMatcher;
import br.com.mybooks.infra.security.auth.jwt.JwtAuthenticationFilter;
import br.com.mybooks.infra.security.auth.jwt.JwtAuthenticationProvider;
import br.com.mybooks.infra.security.auth.jwt.JwtHeaderExtractor;
import br.com.mybooks.infra.security.auth.login.LoginAuthenticationProvider;
import br.com.mybooks.infra.security.auth.login.LoginFilter;

/**
 * A classe <code>WebSecurityConfig</code> define as configurações do Spring security
 * bem como a instanciação dos beans que controlam filtros e outros objetos que
 * tem participação no processo de autenticação de usuários.
 *
 * @author Augusto dos Santos
 * @version 1.0 16 de jan de 2017
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String CREDENTIALS_LOGIN_ENTRY_POINT = "/login";
    private static final String TOKEN_BASED_AUTH_ENTRY_POINT = "/**";
    
    @Autowired
    private AuthenticationSuccessHandler successHandler;
    
    @Autowired
    private AuthenticationFailureHandler failureHandler;
    
    @Autowired
    private LoginAuthenticationProvider loginAuthenticationProvider;
    
    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;
    
    @Autowired
    private AuthenticationFailureEntryPoint authenticationFailureEntryPoint;
    
    @Autowired
    private JwtHeaderExtractor tokenExtractor;
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
    	return super.authenticationManagerBean();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.authenticationProvider(loginAuthenticationProvider);
    	auth.authenticationProvider(jwtAuthenticationProvider);
    }
    
    @Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final CorsConfiguration configuration = new CorsConfiguration();
		configuration.applyPermitDefaultValues();
		configuration.addExposedHeader("Authorization");
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
    
    @Bean
    public LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter(CREDENTIALS_LOGIN_ENTRY_POINT);
        loginFilter.setAuthenticationManager(authenticationManagerBean());
        loginFilter.setAuthenticationSuccessHandler(successHandler);
        loginFilter.setAuthenticationFailureHandler(failureHandler);
        return loginFilter;
    }
    
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
    	final StrategyRequestMatcher strategyRequestMatcher = 
    			new StrategyRequestMatcher(CREDENTIALS_LOGIN_ENTRY_POINT, TOKEN_BASED_AUTH_ENTRY_POINT);
        JwtAuthenticationFilter jwtFilter = new JwtAuthenticationFilter(strategyRequestMatcher, tokenExtractor);
        jwtFilter.setAuthenticationManager(authenticationManagerBean());
        jwtFilter.setAuthenticationFailureHandler(failureHandler);
        return jwtFilter;
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public ObjectMapper jsonMapper() {
    	return new ObjectMapper();
    }

	@Override
    protected void configure(HttpSecurity http) throws Exception {
		
		http
			.cors().and()
	    	.addFilterBefore(loginFilter(), AbstractPreAuthenticatedProcessingFilter.class)
	    	.addFilterAfter(jwtAuthenticationFilter(), LoginFilter.class);
		http
    		.headers().cacheControl();
        http
        	.csrf().disable()
        	.exceptionHandling()
        	.authenticationEntryPoint(authenticationFailureEntryPoint)
        .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
            .authorizeRequests()
            	/*
            	 * URI para processo de autenticação de obtenção do JWT.
            	 */
                .antMatchers(CREDENTIALS_LOGIN_ENTRY_POINT).permitAll()
                /*
                 * Todas as outras rotas necessitarão de autenticação.
                 */
		    	.anyRequest().authenticated();
    }
}