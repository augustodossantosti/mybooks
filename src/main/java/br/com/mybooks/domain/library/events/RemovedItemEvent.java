/*
 * mybooks 1.0 14 de out de 2017
 * 
 * MIT License - Copyright (c) 2017
 */
package br.com.mybooks.domain.library.events;

import br.com.mybooks.domain.items.Item;

/**
 * A classe<code>RemovedItemEvent</code> representa o evento de
 * remoção de um item gerenciável pela aplicação. 
 *
 * @author Augusto dos Santos
 * @version 1.0 14 de out de 2017
 */
public class RemovedItemEvent extends Event {

	private static final long serialVersionUID = 1L;

	public RemovedItemEvent(final Item item, final String username) {
		super(new OperationContext(EventType.REMOVED_ITEM, item, username));
	}

}
