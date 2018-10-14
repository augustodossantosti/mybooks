/*
 * mybooks 1.0 11 de out de 2016
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.domain.items;

/**
 * O enum <code>Category</code> contém as categorias dos 
 * itens gerenciáveis pela aplicação.
 *
 * @author Augusto dos Santos
 * @version 1.0 11 de out de 2016
 */
public enum Category {
	
	HYSTORY("History"),
	ROMANCE("Romance"), 
	ADVENTURE("Adventure"), 
	SCI_FI("Sci-fi"), 
	BIOGRAPHIES("Biographies"), 
	TECNOLOGY("Tecnology");
	
	private final String name;
	
	Category(final String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
