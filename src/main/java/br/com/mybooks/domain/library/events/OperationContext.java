/*
 * mybooks 1.0 20 de out de 2016
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.domain.library.events;

import java.time.LocalDate;

import br.com.mybooks.domain.items.Category;
import br.com.mybooks.domain.items.Item;
import br.com.mybooks.domain.items.Type;

/**
 * A classe <code>OperationContext</code> contém os dados das
 * operações de domínio realizadas na aplicação.
 * 
 * @author Augusto dos Santos
 * @version 1.0 20 de out de 2016
 */
public class OperationContext {
	
	private final EventType type;
	private final LocalDate date;
	private final Item item;
	private final String username;
	
	public OperationContext(final EventType eventType, 
			final Item item, final String username) {
		
		this.date = LocalDate.now();
		this.type = eventType;
		this.item = item;
		this.username = username;
	}
	
	public EventType getType() {
		return type;
	}
	
	public String getUserName() {
		return username;
	}
	
	public String getItemTitle() {
		return item.getTitle();
	}
	
	public String getItemDescription() {
		return item.getDescription();
	}
	
	public String getItemIdentification() {
		return item.getIdentification();
	}
	
	public Type getItemType() {
		return item.getType();
	}
	
	public Category getItemCategory() {
		return item.getCategory();
	}
	 
	public LocalDate getDate() {
		return date;
	}

}
