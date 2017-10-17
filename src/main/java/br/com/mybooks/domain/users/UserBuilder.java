/*
 * mybooks 1.0 24 de mar de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.domain.users;

import java.util.ArrayList;
import java.util.List;

/**
 * A classe <code>UserBuilder</code> é responsável por fornecer
 * um método mais simples e ligível para a criação de usuários.
 * @see User
 *
 * @author Augusto dos Santos
 * @version 1.0 24 de mar de 2017
 */
public class UserBuilder {

	private User user;
	
	public UserBuilder createUser() {
		final ArrayList<LibraryRole> roles = new ArrayList<>();
		user = new User(new PersonalInformation(), "default", "default", roles);
		return this;
	}

	public UserBuilder id(final Long id) {
		user.setId(id);
		return this;
	}
	
	public UserBuilder username(final String username) {
		user.setUsername(username);
		return this;
	}
	
	public UserBuilder password(final String password) {
		user.setPassword(password);
		return this;
	}

	public UserBuilder role(final LibraryRole role) {
		List<LibraryRole> userRoles = new ArrayList<>();
		userRoles.addAll(user.getRoles());
		if(!userRoles.contains(role)) {
			userRoles.add(role);
		}
		user.setRoles(userRoles);
		return this;
	}
	
	public UserBuilder roles(final List<LibraryRole> roles) {
		user.setRoles(roles);
		return this;
	}

	public UserBuilder name(final String name) {
		user.setName(name);
		return this;
	}

	public UserBuilder genre(final Genre genre) {
		user.setGenre(genre);
		return this;
	}

	public UserBuilder idNumber(final String idNumber) {
		user.setIdNumber(idNumber);
		return this;
	}

	public UserBuilder cellPhone(final String cellPhone) {
		user.setCellPhone(cellPhone);
		return this;
	}

	public UserBuilder state(final String state) {
		user.setState(state);
		return this;
	}

	public UserBuilder city(final String city) {
		user.setCity(city);
		return this;
	}

	public UserBuilder streetAddress(final String streetAddress) {
		user.setStreetAddress(streetAddress);
		return this;
	}

	public User build() {
		return user;
	}
}
