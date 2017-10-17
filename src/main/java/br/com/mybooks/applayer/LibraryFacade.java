/*
 * mybooks 1.0 27 de mar de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.applayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mybooks.domain.items.Category;
import br.com.mybooks.domain.items.Item;
import br.com.mybooks.domain.library.Library;
import br.com.mybooks.domain.library.events.Event;
import br.com.mybooks.domain.library.exceptions.LibraryException;
import br.com.mybooks.domain.library.exceptions.NoOperationsException;
import br.com.mybooks.domain.shelf.Shelf;

/**
 * A classe <code>LibraryFacade</code> interliga as diferentes 
 * partes da arquitetura da aplicação disponibilizando sua API.
 *
 * @author Augusto dos Santos
 * @version 1.0 27 de mar de 2017
 */
@Component
public class LibraryFacade {
	
	@Autowired
	private Library library;
	
	public void registerShelf(final Category category) throws LibraryException {
		library.registerShelf(category);
	}
	
	public Shelf searchShelfById(final Long id) {
		return library.searchShelfById(id);
	}
	
	public Shelf searchShelf(final Category category) {
		return library.searchShelfByCategory(category);
	}
	
	public List<Shelf> listAllShelfs() {
		return library.listAllShelfs();
	}
	
	public void registerItem(final Item item) throws LibraryException {
		library.registerItem(item);
	}
	
	public Item searchItemById(final Long id) {
		return library.searchItemById(id);
	}

	public Item searchItem(final String title) throws LibraryException {
		return library.searchItem(title);
	}
	
	public List<Item> listAllItems(final Category category) {
		return category != null ? library.searchItemsByCategory(category) : library.listAllItems();
	}
	
	public List<Event> report() throws NoOperationsException {
		return library.report();
	}
	
}
