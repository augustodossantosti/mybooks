/*
 * mybooks 1.0 11 de out de 2016
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.restapi.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import br.com.mybooks.domain.items.Category;
import br.com.mybooks.domain.items.Item;
import br.com.mybooks.domain.items.ItemBuilder;
import br.com.mybooks.domain.items.Type;
import br.com.mybooks.restapi.wrapper.ItemWrapper;

/**
 * A classe <code>ItemDeserializer</code> é responsável por
 * gerar uma classe contendo as informações do livro a partir de um json.
 *
 * @author Augusto dos Santos
 * @version 1.0 11 de out de 2016
 */
public class ItemDeserializer extends JsonDeserializer<ItemWrapper> {
	
	private ItemBuilder itemBuilder;
	
	public ItemDeserializer() {
		itemBuilder = new ItemBuilder();
	}

	@Override
	public ItemWrapper deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
			throws IOException, JsonProcessingException {
		
		final JsonNode jsonNode = jsonParser.readValueAsTree();
		
		final String title = jsonNode.get("title").asText();
		final int edition =jsonNode.get("edition").asInt();
		final String publisher =jsonNode.get("publisher").asText();
		final String description =jsonNode.get("description").asText();
		final String identification = jsonNode.get("identification").asText();
		final Category category = Category.valueOf(jsonNode.get("category").asText().toUpperCase());
		final Type type = Type.valueOf(jsonNode.get("type").asText().toUpperCase());
		
		final Item item = itemBuilder.createItem()
				.title(title)
				.edition(edition)
				.publisher(publisher)
				.description(description)
				.identification(identification)
				.category(category)
				.type(type)
				.build();
		
		return ItemWrapper.of(item);
	}

}
