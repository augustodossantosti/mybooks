/*
 * mybooks 1.0 28 de out de 2016
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.domain.library.events;

import br.com.mybooks.domain.items.Item;

/**
 * A classe<code>RegisteredItemEvent</code> representa o evento de
 * registro de um novo item gerenciável pela aplicação. 
 *
 * @author Augusto dos Santos
 * @version 1.0 28 de out de 2016
 */
public class RegisteredItemEvent extends Event {
	
	private static final long serialVersionUID = 1L;

	public RegisteredItemEvent(final Item item, final String username) {
		super(new OperationContext(EventType.REGISTERED_ITEM, item, username));
	}

}
