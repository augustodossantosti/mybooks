/*
 * mybooks 1.0 11 de out de 2016
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.restapi.wrapper;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.mybooks.domain.items.Item;
import br.com.mybooks.restapi.deserializer.ItemDeserializer;
import br.com.mybooks.restapi.serializer.ItemSerializer;

/**
 * A classe <code>ItemWrapper</code> encapsula as informações
 * referentes a itens gerenciáveis pela aplicação para o 
 * processo de serialização e deserialização.
 *
 * @author Augusto dos Santos
 * @version 1.0 11 de out de 2016
 */
@JsonSerialize(using = ItemSerializer.class)
@JsonDeserialize(using = ItemDeserializer.class)
public class ItemWrapper {
	
	private final Item item;
	
	private ItemWrapper(final Item item) {
		this.item = item;
	}
	
	public static ItemWrapper of(final Item item) {
		return new ItemWrapper(item);
	}
	
	public static List<ItemWrapper> listOf(final List<Item> items) {
		return items.stream().map(ItemWrapper::new).collect(Collectors.toList());
	}
	
	public Item getItem() {
		return item;
	}
}
