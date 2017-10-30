/*
 * mybooks 1.0 5 de mar de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.mybooks.domain.items.ItemFactory;
import br.com.mybooks.infra.services.LibraryService;

/**
 * A classe <code>TestContextConfiguration</code> disponibiliza
 * os beans necessários para os testes de unidade.
 * 
 * Esta classe é utilizada principalmente para evitar o auto scan 
 * do Spring e o carregamento de todo o contexto da aplicação e 
 * consequentemente do banco de dados e outros recursos que não
 * são necessários para os testes de unidade.
 *
 * @author Augusto dos Santos
 * @version 1.0 5 de mar de 2017
 */
@Configuration
public class UnitTestConfiguration {
	
	@Bean
	public ItemFactory libraryItemFactory() {
		return new ItemFactory();
	}
	
	@Bean
	public LibraryService libraryService() {
		return Mockito.mock(LibraryService.class);
	}
	
}
