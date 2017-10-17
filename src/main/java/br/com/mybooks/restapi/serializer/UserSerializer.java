/*
 * mybooks 1.0 12 de mai de 2017
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

import br.com.mybooks.domain.users.LibraryRole;
import br.com.mybooks.restapi.wrapper.UserWrapper;

/**
 * A claasse <code>UserSerializer</code> é responsável
 * pela serialização das informações de usuários.
 *
 * @author Augusto dos Santos
 * @version 1.0 12 de mai de 2017
 */
public class UserSerializer extends JsonSerializer<UserWrapper> {

	@Override
	public void serialize(UserWrapper wrapper, JsonGenerator jGen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
		
		jGen.writeNumberField("id", wrapper.id());
		jGen.writeStringField("name", wrapper.name());
		serializeRoles(wrapper.roles(), jGen);
		jGen.writeStringField("genre", wrapper.genre());
		jGen.writeStringField("idNumber", wrapper.idNumber());
		jGen.writeStringField("cellPhone", wrapper.cellPhone());
		jGen.writeStringField("state", wrapper.state());
		jGen.writeStringField("city", wrapper.city());
		jGen.writeStringField("streetAddress", wrapper.streetAddress());
	}
	
	private void serializeRoles(final List<LibraryRole> roles, 
			final JsonGenerator jsonGenerator) throws IOException {
		
		jsonGenerator.writeArrayFieldStart("Roles");
		for(final LibraryRole role : roles) {
			jsonGenerator.writeString(role.getRole().name());
		}
		jsonGenerator.writeEndArray();
	}

}
