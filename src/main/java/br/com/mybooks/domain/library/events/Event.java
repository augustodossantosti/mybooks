/*
 * mybooks 1.0 20 de out de 2016
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.domain.library.events;

import java.time.LocalDate;
import java.util.EventObject;

import br.com.mybooks.domain.items.Category;
import br.com.mybooks.domain.items.Type;

/**
 * A classe <code>Event</code> define os eventos de domínio que
 * podem ocorrer na aplicação. Cabe as classes que a estendem 
 * serem mais específicas quanto ao evento que representam.
 *
 * @author Augusto dos Santos
 * @version 1.0 20 de out de 2016
 */
public abstract class Event extends EventObject implements Comparable<Event> {

	private static final long serialVersionUID = 1L;

	public Event(final OperationContext context) {
		super(context);
	}
	
	public EventType getType() {
		return getSource().getType();
	}
	
	public String getUserName() {
		return getSource().getUserName();
	}
	
	public String getItemTitle() {
		return getSource().getItemTitle();
	}
	
	public String getItemDescription() {
		return getSource().getItemDescription();
	}
	
	public String getItemIdentification() {
		return getSource().getItemIdentification();
	}
	
	public Type getItemType() {
		return getSource().getItemType();
	}
	
	public Category getItemCategory() {
		return getSource().getItemCategory();
	}
	
	public LocalDate getDate() {
		return getSource().getDate();
	}

	@Override
	public int compareTo(final Event otherEvent) {
		return getDate().compareTo(otherEvent.getDate());
	}

	@Override
	public OperationContext getSource() {
		return (OperationContext) super.getSource();
	}
	
}
