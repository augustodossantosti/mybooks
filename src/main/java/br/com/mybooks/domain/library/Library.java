/*
 * mybooks 1.0 13 de out de 2016
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.domain.library;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mybooks.domain.items.Category;
import br.com.mybooks.domain.items.Item;
import br.com.mybooks.domain.library.events.Event;
import br.com.mybooks.domain.library.events.EventType;
import br.com.mybooks.domain.library.events.RegisteredItemEvent;
import br.com.mybooks.domain.library.exceptions.ItemNotRegisteredException;
import br.com.mybooks.domain.library.exceptions.LibraryException;
import br.com.mybooks.domain.library.exceptions.NoDomainEventOfThisTypeException;
import br.com.mybooks.domain.library.exceptions.NoOperationsException;
import br.com.mybooks.domain.library.exceptions.ShelfAlreadyRegisteredException;
import br.com.mybooks.domain.shelf.Shelf;
import br.com.mybooks.infra.services.AuthenticationService;
import br.com.mybooks.infra.services.LibraryService;

/**
 * A classe <code>Library</code> contém todos os métodos de dominio
 * da aplicação, como permitir cadastro itens, empréstimos e devoluções.
 *
 * @author Augusto dos Santos
 * @version 1.0 13 de out de 2016
 */
@Component
public class Library {
	
	@Autowired
	private LibraryService libraryService;
	
	@Autowired
	private AuthenticationService authenticationService;
	
	private final List<Event> eventHistory = new LinkedList<>();
	
	public void registerShelf(Category category) throws ShelfAlreadyRegisteredException {
		if(shelfAlreadyExists(category)) {
			throw new ShelfAlreadyRegisteredException();
		}
		libraryService.registerShelf(new Shelf(category));
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

	public void registerItem(final Item newItem) throws LibraryException {
		if (shelfAlreadyExists(newItem.getCategory())) {
			final Shelf shelf = libraryService.findShelfByCategory(newItem.getCategory());
			shelf.addNewItem(newItem);
			libraryService.saveItem(newItem);
			libraryService.registerShelf(shelf);
			eventHistory.add(new RegisteredItemEvent(newItem, authenticationService.getAuthenticatedUser().getUsername()));
		}
		else {
			registerShelf(newItem.getCategory());
			registerItem(newItem);
		}
	}
	
	public Item searchItem(final String title) throws LibraryException {
		final Event registerEvent = getRegisterItemEvent(title);
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
	
	/**
	 * Retorna todos os eventos de domínio que ocorreram na aplicação.
	 * @throws NoOperationsException Nenhuma operação de domínio realizada.
	 */
	public List<Event> report() throws NoOperationsException {
		if(!eventHistory.isEmpty()) {
			return Collections.unmodifiableList(eventHistory);
		}
		throw new NoOperationsException();
	}
	
	private boolean shelfAlreadyExists(Category category) {
		return libraryService.findShelfByCategory(category) != null;
	}
	
	/**
	 * Retorna o evento de registro de um determinado item.
	 * @throws ItemNotRegisteredException O item nunca foi registrado.
	 */
	private Event getRegisterItemEvent(final String title) throws LibraryException {
		
		try {
			List<Event> registerEvents = eventsByType(EventType.REGISTERED_ITEM);
			for(Event event : registerEvents) {
				if(event.getItemTitle().equalsIgnoreCase(title)) {
					return event;
				}
			}
			throw new ItemNotRegisteredException();
		} catch (NoDomainEventOfThisTypeException ex) {
			throw new ItemNotRegisteredException();
		}
		
	}
	
	/**
	 * Filtra os eventos ocorridos na aplicação.
	 * @param type O tipo de evento utilizado para o filtro.
	 * @return Todos os eventos do tipo específicado.
	 * @throws NoDomainEventOfThisTypeException Indica que não ocorreram eventos do tipo informado.
	 */
	private List<Event> eventsByType(EventType type) throws NoDomainEventOfThisTypeException {
		LinkedList<Event> events = new LinkedList<>();
		for(Event event : eventHistory) {
			if(event.getType().equals(type)) {
				events.add(event);
			}
		}
		if(events.isEmpty()) {
			throw new NoDomainEventOfThisTypeException();
		}
		Collections.sort(events);
		return Collections.unmodifiableList(events);
	}
	
}
