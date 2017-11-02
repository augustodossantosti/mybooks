/*
 * mybooks 1.0 18 de out de 2017
 * 
 * MIT License - Copyright (c) 2017
 */
package br.com.mybooks.infra.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mybooks.domain.items.Item;
import br.com.mybooks.domain.library.events.Event;
import br.com.mybooks.domain.library.events.EventType;
import br.com.mybooks.domain.library.events.OperationContext;
import br.com.mybooks.domain.library.exceptions.ItemNotRegisteredException;
import br.com.mybooks.domain.library.exceptions.LibraryException;
import br.com.mybooks.domain.library.exceptions.NoDomainEventOfThisTypeException;

/**
 * 
 *
 * @author Augusto dos Santos
 * @version 1.0 18 de out de 2017
 */
@Service
public class EventsService {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	private final List<Event> eventHistory = new ArrayList<>();
	
	public Event registerEvent(final EventType type, final Item item) {
		final Event event = new Event(new OperationContext(type, item, authenticationService.getAuthenticatedUser().getUsername()));
		eventHistory.add(event);
		return event;
	}
	
	public List<Event> getAllEvents() {
		return Collections.unmodifiableList(eventHistory);
	}
	
	/**
	 * Retorna o evento de registro de um determinado item.
	 * @throws ItemNotRegisteredException O item nunca foi registrado.
	 */
	public Event getRegisterItemEvent(final String title) throws LibraryException {
		
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
	private List<Event> eventsByType(final EventType type) throws NoDomainEventOfThisTypeException {
		final List<Event> foundEvents = new ArrayList<>();
		for(Event event : eventHistory) {
			if(event.getType().equals(type)) {
				foundEvents.add(event);
			}
		}
		if(foundEvents.isEmpty()) {
			throw new NoDomainEventOfThisTypeException();
		}
		Collections.sort(foundEvents);
		return Collections.unmodifiableList(foundEvents);
	}

}
