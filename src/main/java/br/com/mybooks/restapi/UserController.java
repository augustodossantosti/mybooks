/*
 * mybooks 1.0 12 de mai de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.mybooks.infra.services.UserService;
import br.com.mybooks.restapi.wrapper.UserWrapper;

/**
 * A classe <code>UserController</code> contém as rotas de acesso
 * para as operações com usuários dentro aplicação.
 *
 * @author Augusto dos Santos
 * @version 1.0 12 de mai de 2017
 */
@Controller
@RequestMapping("/")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(path = "users", method = RequestMethod.POST)
	public @ResponseBody void registerUser(@RequestBody UserWrapper userWrapper) {
		userService.registerUser(userWrapper.getUser());
	}

}
