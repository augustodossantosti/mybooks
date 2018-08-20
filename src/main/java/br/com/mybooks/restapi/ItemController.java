/*
 * mybooks 1.0 14 de out de 2017
 * 
 * MIT License - Copyright (c) 2017
 */
package br.com.mybooks.restapi;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.mybooks.applayer.LibraryFacade;
import br.com.mybooks.domain.items.Category;
import br.com.mybooks.domain.items.Type;
import br.com.mybooks.domain.library.exceptions.LibraryException;
import br.com.mybooks.domain.library.exceptions.NoOperationsException;
import br.com.mybooks.domain.library.exceptions.StorageFileNotFoundException;
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
	
	@GetMapping(path = "/{id}")
	public ItemWrapper findItem(@PathVariable(name = "id") final Long id) {
		return ItemWrapper.of(libraryFacade.searchItemById(id));
	}
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void registerItem(@RequestPart("item") final ItemWrapper wrapper, 
			@RequestPart("file") final MultipartFile file, @RequestPart("cover") final MultipartFile coverFile)
			throws LibraryException, IOException  {
		
		libraryFacade.registerItem(wrapper.getItem(), file, coverFile);
	}
	
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ItemWrapper updateItem(@RequestPart("item") final ItemWrapper wrapper, 
			@RequestPart("file") final MultipartFile file, @RequestPart("cover") final MultipartFile coverFile) throws IOException {
		
		return ItemWrapper.of(libraryFacade.updateItem(wrapper.getItem(), file, coverFile));
	}
	
	@GetMapping
	public List<ItemWrapper> listAllItems(@RequestParam(name = "category", required = false) final Category category, 
			@RequestParam(name = "type", required = false) final Type type) throws LibraryException {
		
		return ItemWrapper.listOf(libraryFacade.listAllItems(category));
	}
	
	@PostMapping(path = "/file")
	public ResponseEntity<Resource> downloadItemFile(@RequestBody Map<String, String> filePath) throws StorageFileNotFoundException {
		final Resource resource = libraryFacade.loadAsResource(filePath.get("filePath"));
		return ResponseEntity
				.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
				.body(resource);
	}
	
	@GetMapping(path = "/categories")
	public Map<String, Category> availableCategories() {
		return Arrays.stream(Category.values()).collect(Collectors.toMap(Category::toString, category -> category));
	}
	
	@GetMapping(path = "/types")
	public Map<String, Type> availableTypes() {
		return Arrays.stream(Type.values()).collect(Collectors.toMap(Type::toString, type -> type));
	}
	
	@RequestMapping(path= "/search", method = RequestMethod.POST)
	public ItemWrapper searchItem(@RequestParam(name = "title") final String title) throws LibraryException {
		return ItemWrapper.of(libraryFacade.searchItem(title));
	}
	
	@GetMapping(path= "/report")
	public ReportWrapper report() throws NoOperationsException {
		return new ReportWrapper(libraryFacade.report());
	}

}
