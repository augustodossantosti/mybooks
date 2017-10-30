/*
 * mybooks 1.0 28 de out de 2016
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.restapi.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.com.mybooks.domain.library.events.Event;
import br.com.mybooks.restapi.wrapper.ReportWrapper;

/**
 * A classe <code>ReportSerializer</code> é responsável pela serialização
 * de todos os eventos ocorridos na aplicação na forma de um relatório.
 *
 * @author Augusto dos Santos
 * @version 1.0 28 de out de 2016
 */
public class ReportSerializer extends JsonSerializer<ReportWrapper>{

	@Override
	public void serialize(ReportWrapper reportWrapper, JsonGenerator jsonGenerator, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		
		jsonGenerator.writeStartArray();
		for(final Event event : reportWrapper.events()) {
			jsonGenerator.writeStartObject();
			serializeInformation(event, jsonGenerator);
			jsonGenerator.writeEndObject();
		}
		jsonGenerator.writeEndArray();
		
	}
	
	protected void serializeInformation(Event event, JsonGenerator jsonGenerator) throws IOException {
		
		jsonGenerator.writeStringField("Operation", event.getType().name());
		jsonGenerator.writeNumberField("Date", event.getDate().toEpochDay());
		
		jsonGenerator.writeObjectFieldStart("Item information");
		jsonGenerator.writeStringField("Title", event.getItemTitle());
		jsonGenerator.writeStringField("Type", event.getItemType().name());
		jsonGenerator.writeStringField("Identification", event.getItemIdentification());
		jsonGenerator.writeEndObject();
		
		jsonGenerator.writeObjectFieldStart("User information");
		jsonGenerator.writeStringField("Name", event.getUserName());
		jsonGenerator.writeEndObject();
		
	}
	
}