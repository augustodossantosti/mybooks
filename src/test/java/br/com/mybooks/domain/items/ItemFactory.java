/*
 * mybooks 1.0 5 de mar de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.domain.items;

import org.springframework.stereotype.Component;

import br.com.mybooks.domain.items.Category;
import br.com.mybooks.domain.items.Features;
import br.com.mybooks.domain.items.Item;
import br.com.mybooks.domain.items.Type;

/**
 * A classe <code>ItemFactory</code> é responsável por
 * construir itens gerenciáveis pela aplicação para a 
 * realização dos testes.
 *
 * @author Augusto dos Santos
 * @version 1.0 5 de mar de 2017
 */
@Component
public class ItemFactory {
	
	private Item item;
	
	/**
	 * Cria um novo livro com a categoria especificada.
	 */
	public Item createBookWithCategory(final Category category) {
		item = new Item("Book for test", 1,"Test publisher", "Book for test.",
				"888-888", category, Type.BOOK);
		return item;
	}
	
	/**
	 * Cria um novo manga com a categoria especificada.
	 */
	public Item createMangaWithCategory(final Category category) {
		item = new Item("Manga for test", 1,"Test publisher", "Book for test.",
				"888-888", category, Type.MANGA);
		return item;
	}
	
	/**
	 * Cria uma nova revista com a categoria especificada.
	 */
	public Item createMagazineWithCategory(final Category category) {
		item = new Item("Magazine for test", 1,"Test publisher","Book for test.",
				"888-888", category, Type.MAGAZINE);
		return item;
	}

}
