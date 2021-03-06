/*
 * mybooks 1.0 29 de out de 2016
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.domain.items;

/**
 * O enum <code>Type</code> contém os tipos de itens gerenciáveis
 * pela aplicação.
 *
 * @author Augusto dos Santos
 * @version 1.0 29 de out de 2016
 */
public enum Type {

	BOOK("Book"), 
	MAGAZINE("Magazine"), 
	MANGA("Manga");
	
	private final String name;
	
	Type(final String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
