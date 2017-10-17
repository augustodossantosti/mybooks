/*
 * mybooks 1.0 11 de out de 2016
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.restapi.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.com.mybooks.domain.items.Item;
import br.com.mybooks.restapi.wrapper.ItemWrapper;

/**
 * A classe <code>ItemSerializer</code> é responsável pela
 * serialização de itens gerenciáveis pela aplicação.
 *
 * @author Augusto dos Santos
 * @version 1.0 11 de out de 2016
 */
public class ItemSerializer extends AbstractSerializer<ItemWrapper> {

	@Override
	public void serialize(final ItemWrapper itemWrapper, final JsonGenerator jGen, 
			final SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
		
		final Item item = itemWrapper.getItem();
		
		jGen.writeStartObject();
		writeNumber(jGen, "id", item.getId());
		jGen.writeStringField("title", item.getTitle());
		writeNumber(jGen, "edition", item.getEdition());
		jGen.writeStringField("publisher", item.getPublisher());
		jGen.writeStringField("description", item.getDescription());
		jGen.writeStringField("category", item.getCategory().name());
		jGen.writeStringField("type", item.getType().name());
		jGen.writeStringField("identification", item.getIdentification());
		jGen.writeEndObject();
	}

}
