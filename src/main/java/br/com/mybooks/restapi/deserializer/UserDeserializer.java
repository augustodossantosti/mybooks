/*
 * mybooks 1.0 12 de mai de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.restapi.deserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

import br.com.mybooks.domain.users.Genre;
import br.com.mybooks.domain.users.LibraryRole;
import br.com.mybooks.domain.users.Role;
import br.com.mybooks.domain.users.User;
import br.com.mybooks.domain.users.UserBuilder;
import br.com.mybooks.restapi.wrapper.UserWrapper;

/**
 * A claasse <code>UserDeserializer</code> é responsável
 * pela deserialização das informações de usuários.
 *
 * @author Augusto dos Santos
 * @version 1.0 12 de mai de 2017
 */
public class UserDeserializer extends JsonDeserializer<UserWrapper> {
	
	private User user;
	private UserBuilder userBuilder;
	
	public UserDeserializer() {
		userBuilder = new UserBuilder();
	}

	@Override
	public UserWrapper deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		
		JsonNode node = jp.readValueAsTree();
		
		final Long id = node.get("id").asLong();
		final String username = node.get("username").asText();
		final String password = node.get("password").asText();
		final List<LibraryRole> roles = deserializeRoles(node);
		final String name = node.get("name").asText();
		final Genre genre = Genre.valueOf(node.get("genre").asText().toUpperCase());
		final String idNumber = node.get("idNumber").asText();
		final String cellPhone = node.get("cellPhone").asText();
		final String state = node.get("state").asText();
		final String city = node.get("city").asText();
		final String streetAddress = node.get("streetAddress").asText();
		
		user = userBuilder.createUser()
						.id(id)
						.username(username)
						.password(password)
						.roles(roles)
						.name(name)
						.genre(genre)
						.idNumber(idNumber)
						.cellPhone(cellPhone)
						.state(state)
						.city(city)
						.streetAddress(streetAddress)
						.build();
		
		return new UserWrapper(user);
	}
	
	private List<LibraryRole> deserializeRoles(final JsonNode node) 
			throws JsonParseException, JsonMappingException, IOException {
		
		List<Role> roles = new ArrayList<>();
		
		final JsonNode rolesNode = node.get("roles");
		Iterator<JsonNode> nodeIterator = rolesNode.iterator();
		while(nodeIterator.hasNext()) {
			Role role = Role.valueOf(nodeIterator.next().asText().toUpperCase());
			roles.add(role);
		}
		return null;
	}

}
