/*
 * mybooks 1.0 13 de out de 2016
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.domain.library;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mybooks.domain.items.Category;
import br.com.mybooks.domain.items.Item;
import br.com.mybooks.domain.library.events.Event;
import br.com.mybooks.domain.library.events.EventType;
import br.com.mybooks.domain.library.exceptions.LibraryException;
import br.com.mybooks.domain.library.exceptions.NoOperationsException;
import br.com.mybooks.domain.library.exceptions.ShelfAlreadyRegisteredException;
import br.com.mybooks.domain.shelf.Shelf;
import br.com.mybooks.infra.services.LibraryService;

/**
 * A classe <code>Library</code> centraliza as operações de
 * domínio relacionadas a manipulação de prateleiras e itens
 * gerenciáveis pela aplicação.
 *
 * @author Augusto dos Santos
 * @version 1.0 13 de out de 2016
 */
@Component
public class Library {

	private final LibraryService libraryService;

	@Autowired
	public Library(final LibraryService libraryService) {
		this.libraryService = libraryService;
	}
	
	public Shelf registerShelf(final Category category) throws ShelfAlreadyRegisteredException {
		if(shelfAlreadyExists(category)) {
			throw new ShelfAlreadyRegisteredException();
		}
		return libraryService.saveShelf(new Shelf(category));
	}
	
	public void removeShelf(final Category category) {
		if(shelfAlreadyExists(category)){
			libraryService.removeShelfByCategory(category);
		}
	}
	
	public Shelf searchShelfById(final Long id) {
		return libraryService.findShelfById(id);
	}
	
	public Shelf searchShelfByCategory(final Category category) {
		return libraryService.findShelfByCategory(category);
	}
	
	public List<Shelf> listAllShelfs() {
		final List<Shelf> shelfs = libraryService.findAllShelfs();
		return Collections.unmodifiableList(shelfs);
	}

	public Item registerItem(final Item newItem) throws LibraryException {
		final Category itemCategory = newItem.getCategory();
		
		final Shelf shelf = shelfAlreadyExists(itemCategory) ? 
				libraryService.findShelfByCategory(itemCategory) : registerShelf(itemCategory);
				
		shelf.addNewItem(newItem);
		libraryService.saveItem(newItem);
		libraryService.saveShelf(shelf);
		libraryService.registerEvent(EventType.REGISTERED_ITEM, newItem);
		return newItem;
	}
	
	public Item updateItem(final Item item) {
		final Item original = libraryService.findItemById(item.getId());
		original.setTitle(item.getTitle());
		original.setEdition(item.getEdition());
		original.setCategory(item.getCategory());
		original.setType(item.getType());
		original.setPublisher(item.getPublisher());
		original.setDescription(item.getDescription());
		original.setIdentification(item.getIdentification());
		original.setFileInfo(item.getFileInfo());
		return libraryService.saveItem(original);
	}
	
	public Item searchItem(final String title) throws LibraryException {
		final Event registerEvent = libraryService.getRegisterItemEvent(title);
		final Shelf shelf = libraryService.findShelfByCategory(registerEvent.getItemCategory());
		return shelf.searchItem(title);
	}
	
	public Item searchItemById(final Long id) {
		return libraryService.findItemById(id);
	}
	
	public List<Item> searchItemsByCategory(final Category category) {
		return libraryService.findItemByCategory(category);
	}
	
	public List<Item> listAllItems() {
		final List<Item> items = libraryService.findAllItems();
		return Collections.unmodifiableList(items);
	}
	
	public List<Event> report() throws NoOperationsException {
		return libraryService.getAllEvents();
	}
	
	private boolean shelfAlreadyExists(Category category) {
		return libraryService.existsShelfByCategory(category);
	}
	
}
