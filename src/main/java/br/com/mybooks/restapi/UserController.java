/*
 * mybooks 1.0 12 de mai de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.mybooks.applayer.LibraryFacade;
import br.com.mybooks.restapi.wrapper.UserWrapper;

/**
 * A classe <code>UserController</code> contém as rotas de acesso
 * para as operações com usuários dentro aplicação.
 *
 * @author Augusto dos Santos
 * @version 1.0 12 de mai de 2017
 */
@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private LibraryFacade libraryFacade;
	
	@PostMapping("/signup")
	public @ResponseBody void registerUser(@RequestBody UserWrapper userWrapper) {
		libraryFacade.registerUser(userWrapper.getUser());
	}
	
	@GetMapping
	public @ResponseBody UserWrapper userInformation() {
		return null;
	}

}
