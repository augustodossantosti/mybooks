/*
 * mybooks 1.0 25 de jun de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.restapi.wrapper;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.mybooks.domain.shelf.Shelf;
import br.com.mybooks.restapi.serializer.ShelfSerializer;

/**
 * A classe <code>ShelfWrapper</code> encapsula as informações
 * referentes a <code<Shelf</code> para o processo de serialização 
 * e deserialização.
 *
 * @author Augusto dos Santos
 * @version 1.0 25 de jun de 2017
 */
@JsonSerialize(using = ShelfSerializer.class)
public class ShelfWrapper {

	private final Shelf shelf;
	
	private ShelfWrapper(final Shelf shelf) {
		this.shelf = shelf;
	}
	
	public static ShelfWrapper toWrapper(final Shelf shelf) {
		return new ShelfWrapper(shelf);
	}
	
	public static List<ShelfWrapper> toWrapperList(final List<Shelf> shelfs) {
		return shelfs.stream().map(ShelfWrapper::new).collect(Collectors.toList());
	}
	
	public Shelf getShelf() {
		return shelf;
	}
}
