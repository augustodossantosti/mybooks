/*
 * mybooks 1.0 5 de mar de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.domain.items;

import br.com.mybooks.domain.items.Category;
import br.com.mybooks.domain.items.Features;
import br.com.mybooks.domain.items.Item;
import br.com.mybooks.domain.items.Type;

/**
 * A classe <code>LibraryItemFactory</code> é responsável por
 * construir itens gerenciáveis pela biblioteca para a realização
 * dos testes.
 *
 * @author Augusto dos Santos
 * @version 1.0 5 de mar de 2017
 */
public class LibraryItemFactory {
	
	private Item item;
	
	/**
	 * Cria um novo livro com a categoria especificada.
	 */
	public Item createBookWithCategory(final Category category) {
		item = new Item(new Features("Book for test", 1,"Test publisher", 
				"Book for test.", category, Type.BOOK, "888-888"));
		
		return item;
	}
	
	/**
	 * Cria um novo manga com a categoria especificada.
	 */
	public Item createMangaWithCategory(final Category category) {
		item = new Item(new Features("Manga for test", 1,"Test publisher", 
				"Book for test.", category, Type.MANGA, "888-888"));
		
		return item;
	}
	
	/**
	 * Cria uma nova revista com a categoria especificada.
	 */
	public Item createMagazineWithCategory(final Category category) {
		item = new Item(new Features("Magazine for test", 1,"Test publisher", 
				"Book for test.", category, Type.MAGAZINE, "888-888"));
		
		return item;
	}

}
