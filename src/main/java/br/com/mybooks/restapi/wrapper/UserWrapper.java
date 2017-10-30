/*
 * mybooks 1.0 12 de mai de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.restapi.wrapper;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.mybooks.domain.users.LibraryRole;
import br.com.mybooks.domain.users.User;
import br.com.mybooks.restapi.deserializer.UserDeserializer;
import br.com.mybooks.restapi.serializer.UserSerializer;

/**
 * A classe <code>UserWrapper</code> encapsula informações
 * referentes a usuários.
 *
 * @author Augusto dos Santos
 * @version 1.0 12 de mai de 2017
 */
@JsonSerialize(using = UserSerializer.class)
@JsonDeserialize(using = UserDeserializer.class)
public class UserWrapper {

	private final User user;
	
	public UserWrapper(final User user) {
		this.user = user;
	}
	
	public Long id() {
		return user.getId();
	}
	
	public String name() {
		return user.getName();
	}
	
	public List<LibraryRole> roles() {
		return user.getRoles();
	}
	
	public String genre() {
		return user.getGenre().name();
	}
	
	public String idNumber() {
		return user.getIdNumber();
	}
	
	public String cellPhone() {
		return user.getCellPhone();
	}
	
	public String state() {
		return user.getState();
	}
	
	public String city() {
		return user.getCity();
	}
	
	public String streetAddress() {
		return user.getStreetAddress();
	}
	
	public User getUser() {
		return user;
	}
}
