/*
 * mybooks 1.0 14 de out de 2017
 * 
 * MIT License - Copyright (c) 2017
 */
package br.com.mybooks.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.mybooks.applayer.LibraryFacade;
import br.com.mybooks.domain.items.Category;
import br.com.mybooks.domain.items.Type;
import br.com.mybooks.domain.library.exceptions.LibraryException;
import br.com.mybooks.domain.library.exceptions.NoOperationsException;
import br.com.mybooks.restapi.wrapper.ItemWrapper;
import br.com.mybooks.restapi.wrapper.ReportWrapper;

/**
 * A classe <code>ItemController</code> contém as rotas 
 * das operações sobre <code>Item</code>.
 *
 * @author Augusto dos Santos
 * @version 1.0 14 de out de 2017
 */
@RestController
@RequestMapping("/items")
public class ItemController {

	@Autowired
	private LibraryFacade libraryFacade;
	
	@GetMapping
	public List<ItemWrapper> listAllItems(@RequestParam(name = "category", required = false) final Category category, 
			@RequestParam(name = "type", required = false) final Type type) throws LibraryException {
		
		return ItemWrapper.toWrapperList(libraryFacade.listAllItems(category));
	}
	
	@GetMapping(path = "/{id}")
	public ItemWrapper findItem(@PathVariable(name = "id") final Long id) {
		return ItemWrapper.toWrapper(libraryFacade.searchItemById(id));
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void registerItem(@RequestBody final ItemWrapper itemInformation) throws LibraryException  {
		libraryFacade.registerItem(itemInformation.getItem());
	}
	
	@RequestMapping(path= "/search", method = RequestMethod.POST)
	public ItemWrapper searchItem(@RequestParam(name = "title") final String title) throws LibraryException {
		return ItemWrapper.toWrapper(libraryFacade.searchItem(title));
	}
	
	@GetMapping(path= "/report")
	public ReportWrapper report() throws NoOperationsException {
		return new ReportWrapper(libraryFacade.report());
	}

}
