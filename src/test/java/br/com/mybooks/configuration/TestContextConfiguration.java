/*
 * mybooks 1.0 5 de mar de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.mybooks.domain.items.LibraryItemFactory;

/**
 * A classe <code>TestContextConfiguration</code> disponibiliza
 * os beans de teste para o contexto do Spring.
 *
 * @author Augusto dos Santos
 * @version 1.0 5 de mar de 2017
 */
@Configuration
public class TestContextConfiguration {
	
	@Bean
	public LibraryItemFactory libraryItemFactory() {
		return new LibraryItemFactory();
	}
	
}
