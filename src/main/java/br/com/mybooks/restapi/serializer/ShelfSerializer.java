/*
 * mybooks 1.0 25 de jun de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.restapi.serializer;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.com.mybooks.domain.items.Item;
import br.com.mybooks.domain.shelf.Shelf;
import br.com.mybooks.restapi.wrapper.ShelfWrapper;

/**
 * A classe <code>ShelfSerializer</code> é responsável pela
 * serialização de prateleiras de itens.
 *
 * @author Augusto dos Santos
 * @version 1.0 25 de jun de 2017
 */
public class ShelfSerializer extends JsonSerializer<ShelfWrapper> {

	@Override
	public void serialize(final ShelfWrapper shelfWrapper, final JsonGenerator jGen, 
			final SerializerProvider serializers) throws IOException, JsonProcessingException {
		
		final Shelf shelf = shelfWrapper.getShelf();

		jGen.writeStartObject();
		jGen.writeNumberField("id", shelf.getId());
		jGen.writeStringField("category", shelf.getCategory().toString());
		serializeItemsBasicInformation(shelf.getAvailableItems(), jGen);
		jGen.writeEndObject();
	}
	
	private void serializeItemsBasicInformation(final List<Item> items, final JsonGenerator jGen) throws IOException {
		jGen.writeArrayFieldStart("items");
		for(final Item item : items) {
			jGen.writeStartObject();
			jGen.writeNumberField("id", item.getId());
			jGen.writeStringField("title", item.getTitle());
			jGen.writeNumberField("edition", item.getEdition());
			jGen.writeStringField("publisher", item.getPublisher());
			jGen.writeStringField("type", item.getType().toString());
			jGen.writeStringField("filePath", item.getFilePath());
			jGen.writeStringField("coverBase64", item.getFileCover());
			jGen.writeEndObject();
		}
		jGen.writeEndArray();
	}

}
