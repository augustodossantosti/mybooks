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
import br.com.mybooks.infra.persistence.ItemRepository;

/**
 * A classe <code>LibraryService</code> contém todos os serviços
 * referentes as operações com <code>Shelf</code> e <code>Item</code>.
 *
 * @author Augusto dos Santos
 * @version 1.0 19 de jun de 2017
 */
@Service
public class LibraryService {

	private final ItemRepository itemRepository;
	private final EventsService eventsService;

	@Autowired
	public LibraryService(final ItemRepository itemRepository, final EventsService eventsService) {
		this.itemRepository = itemRepository;
		this.eventsService = eventsService;
	}
	
	public Item saveItem(final Item item) {
		registerEvent(EventType.REGISTERED_ITEM, item);
		return itemRepository.save(item);
	}
	
	public void deleteItem(final Item item) {
		registerEvent(EventType.REMOVED_ITEM, item);
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
		final List<Item> items = new ArrayList<>();
		itemRepository.findAll().forEach(items::add);
		return items;
	}

	public Event getRegisterItemEvent(final String title) throws LibraryException {
		return eventsService.getRegisterItemEvent(title);
	}
	
	public List<Event> getAllEvents() {
		return eventsService.getAllEvents();
	}

	private void registerEvent(final EventType type, final Item item) {
		eventsService.registerEvent(type, item);
	}
	
}
