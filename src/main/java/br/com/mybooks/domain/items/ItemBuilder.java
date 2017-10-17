/*
 * mybooks 1.0 16 de abr de 2017
 * 
 * MIT License - Copyright (c) 2017
 */
package br.com.mybooks.domain.items;

/**
 * A classe <code>ItemBuilder</code> fornece uma maneira mais simples 
 * e legível para a construção de itens gerenciáveis pela aplicação.
 *
 * @author Augusto dos Santos
 * @version 1.0 16 de abr de 2017
 */
public class ItemBuilder {
	
	private Item item;
	
	public ItemBuilder createItem() {
		item = new Item(new Features());
		return this;
	}
	
	public ItemBuilder id(final Long id) {
		item.setId(id);
		return this;
	}
	
	public ItemBuilder title(final String title) {
		item.setTitle(title);
		return this;
	}
	
	public ItemBuilder edition(final int edition) {
		item.setEdition(edition);
		return this;
	}
	
	public ItemBuilder publisher(final String publisher) {
		item.setPublisher(publisher);
		return this;
	}
	
	public ItemBuilder description(final String description) {
		item.setDescription(description);
		return this;
	}
	
	public ItemBuilder category(final Category category) {
		item.setCategory(category);
		return this;
	}
	
	public ItemBuilder type(final Type type) {
		item.setType(type);
		return this;
	}
	
	public ItemBuilder identification(final String identification) {
		item.setIdentification(identification);
		return this;
	}
	
	public Item build() {
		return item;
	}
}
