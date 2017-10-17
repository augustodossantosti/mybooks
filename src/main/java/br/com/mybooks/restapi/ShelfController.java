/*
 * mybooks 1.0 11 de out de 2016
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.mybooks.applayer.LibraryFacade;
import br.com.mybooks.domain.items.Category;
import br.com.mybooks.domain.library.exceptions.LibraryException;
import br.com.mybooks.restapi.wrapper.ShelfWrapper;

/**
 * A classe <code>ShelfController</code> contém as rotas 
 * das operações sobre <code>Shelf</code>.
 *
 * @author Augusto dos Santos
 * @version 1.0 11 de out de 2016
 */
@RestController
@RequestMapping(value = "/shelfs")
public class ShelfController {
	
	@Autowired
	private LibraryFacade libraryFacade;
	
	@GetMapping
	public List<ShelfWrapper> listShelfs() {
		return ShelfWrapper.toWrapperList(libraryFacade.listAllShelfs());
	}
	
	@GetMapping(path = "/{id}")
	public ShelfWrapper findShelf(@PathVariable(name = "id") final Long id) {
		return ShelfWrapper.toWrapper(libraryFacade.searchShelfById(id));
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void registerShelf(@RequestParam(name = "category") final Category category) throws LibraryException {
		libraryFacade.registerShelf(category);
	}
	
}