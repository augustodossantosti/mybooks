/*
 * mybooks 1.0 8 de ago de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.restapi.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;

/**
 * A classe <code>AbstractSerializer</code> contém métodos úteis 
 * para o processo de serialização de objetos.
 *
 * @author Augusto dos Santos
 * @version 1.0 8 de ago de 2017
 */
public abstract class AbstractSerializer<T> extends JsonSerializer<T> {
	
	protected void writeNumber(final JsonGenerator jsonGenerator, final String fieldName, final Integer value) throws IOException {
		if(value == null) {
			jsonGenerator.writeNullField(fieldName);
		} else {
			jsonGenerator.writeNumberField(fieldName, value);
		}
	}
	
	protected void writeNumber(final JsonGenerator jsonGenerator, final String fieldName, final Long value) throws IOException {
		if(value == null) {
			jsonGenerator.writeNullField(fieldName);
		} else {
			jsonGenerator.writeNumberField(fieldName, value);
		}
	}
	
	protected void writeNumber(final JsonGenerator jsonGenerator, final String fieldName, final Double value) throws IOException {
		if(value == null) {
			jsonGenerator.writeNullField(fieldName);
		} else {
			jsonGenerator.writeNumberField(fieldName, value);
		}
	}
}
