/*
 * mybooks 1.0 19 de jun de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.infra.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mybooks.domain.items.Category;
import br.com.mybooks.domain.items.Item;
import br.com.mybooks.domain.library.events.Event;
import br.com.mybooks.domain.library.events.EventType;
import br.com.mybooks.domain.library.exceptions.LibraryException;
import br.com.mybooks.domain.shelf.Shelf;
import br.com.mybooks.infra.persistence.ItemRepository;
import br.com.mybooks.infra.persistence.ShelfRepository;

/**
 * A classe <code>LibraryService</code> contém todos os serviços
 * referentes as operações com <code>Shelf</code> e <code>Item</code>.
 *
 * @author Augusto dos Santos
 * @version 1.0 19 de jun de 2017
 */
@Service
public class LibraryService {
	
	@Autowired
	private ShelfRepository shelfRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private EventsService eventsService;
	
	public Shelf saveShelf(final Shelf shelf) {
		return shelfRepository.save(shelf);
	}
	
	public void removeShelfById(final Long id) {
		shelfRepository.delete(id);
	}
	
	public void removeShelfByCategory(final Category category) {
		shelfRepository.deleteByCategory(category);
	}
	
	public boolean existsShelfByCategory(final Category category) {
		return shelfRepository.existsByCategory(category);
	}
	
	public Shelf findShelfById(final Long id) {
		return shelfRepository.findById(id);
	}
	
	public Shelf findShelfByCategory(final Category category) {
		return shelfRepository.findByCategory(category);
	}
	
	public List<Shelf> findAllShelfs() {
		final List<Shelf> shelfs = new ArrayList<>();
		final Iterable<Shelf> shelfsIterable = shelfRepository.findAll();
		shelfsIterable.forEach(shelfs::add);
		return shelfs;
	}
	
	public Item saveItem(final Item item) {
		return itemRepository.save(item);
	}
	
	public void deleteItem(final Item item) {
		itemRepository.delete(item);
	}
	
	public Item findItemById(final Long id) {
		return itemRepository.findById(id);
	}
	
	public List<Item> findItemByTitle(final String title) {
		return itemRepository.findByFeaturesTitle(title);
	}
	
	public List<Item> findItemByCategory(final Category category) {
		return itemRepository.findByFeaturesCategory(category);
	}
	
	public List<Item> findItemByPublisher(final String publisher) {
		return itemRepository.findByFeaturesPublisher(publisher);
	}
	
	public Item findItemByIdentification(final String identification) {
		return itemRepository.findByFeaturesIdentification(identification);
	}
	
	public List<Item> findAllItems() {
		final Iterable<Item> itemsIterable = itemRepository.findAll();
		final List<Item> items = new ArrayList<>();
		itemsIterable.forEach(items::add);
		return items;
	}
	
	public Event registerEvent(final EventType type, final Item item) {
		return eventsService.registerEvent(type, item);
	}
	
	public Event getRegisterItemEvent(final String title) throws LibraryException {
		return eventsService.getRegisterItemEvent(title);
	}
	
	public List<Event> getAllEvents() {
		return eventsService.getAllEvents();
	}
	
}
