/*
 * mybooks 1.0 2 de abr de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.infra.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mybooks.domain.users.User;
import br.com.mybooks.infra.persistence.UserRepository;

/**
 * A classe <code>UserService</code> contém as operações
 * referentes a usuários da aplicação.
 *
 * @author Augusto dos Santos
 * @version 1.0 2 de abr de 2017
 */
@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public Optional<User> getByUsername(String username) {
		return repository.findByUsername(username);
	}
	
	public User registerUser(User user) {
		validateUser(user);
		return repository.save(user);
	}
	
	private void validateUser(final User user) {
		// TODO Adicionar validação das informações do usuário
		if(user != null) {
			return;
		}
	}

}
