/*
 * mybooks 1.0 27 de mar de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.applayer;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.mybooks.domain.items.Category;
import br.com.mybooks.domain.items.FileInfo;
import br.com.mybooks.domain.items.Item;
import br.com.mybooks.domain.library.Library;
import br.com.mybooks.domain.library.events.Event;
import br.com.mybooks.domain.library.exceptions.LibraryException;
import br.com.mybooks.domain.library.exceptions.NoOperationsException;
import br.com.mybooks.domain.library.exceptions.StorageFileNotFoundException;
import br.com.mybooks.domain.users.User;
import br.com.mybooks.infra.services.StorageService;
import br.com.mybooks.infra.services.UserService;

/**
 * A classe <code>LibraryFacade</code> interliga as diferentes 
 * partes da arquitetura da aplicação disponibilizando sua API.
 *
 * @author Augusto dos Santos
 * @version 1.0 27 de mar de 2017
 */
@Component
public class LibraryFacade {

	private final Library library;
	private final StorageService storageService;
	private final UserService userService;

	@Autowired
	public LibraryFacade(final Library library, final StorageService storageService, final UserService userService) {
		this.library = library;
		this.storageService = storageService;
		this.userService = userService;
	}
	
	@PreAuthorize("isFullyAuthenticated()")
	public Item registerItem(final Item item, final MultipartFile file, final MultipartFile cover)
			throws LibraryException, IOException {
		final FileInfo fileInfo = storageService.store(file, cover);
		item.setFileInfo(fileInfo);
		return library.registerItem(item);
	}
	
	@PreAuthorize("isFullyAuthenticated()")
	public Item updateItem(final Item item, final MultipartFile file, final MultipartFile cover) throws IOException {
		final FileInfo fileInfo = storageService.store(file, cover);
		item.setFileInfo(fileInfo);
		return library.updateItem(item);
	}
	
	@PreAuthorize("isFullyAuthenticated()")
	public Resource loadAsResource(final String filePath) throws StorageFileNotFoundException {
		return storageService.loadAsResource(filePath);
	}
	
	@PreAuthorize("isFullyAuthenticated()")
	public Item searchItemById(final Long id) {
		return library.searchItemById(id);
	}

	@PreAuthorize("isFullyAuthenticated()")
	public List<Item> searchItem(final String title) throws LibraryException {
		return library.searchItem(title);
	}
	
	@PreAuthorize("isFullyAuthenticated()")
	public List<Item> listItems(final Category category) {
		return category != null ? library.searchItemsByCategory(category) : library.listAllItems();
	}
	
	@PreAuthorize("isFullyAuthenticated()")
	public List<Event> report() throws NoOperationsException {
		return library.report();
	}
	
	@PreAuthorize("isFullyAuthenticated()")
	public User registerUser(final User user) {
		return userService.registerUser(user);
	}
	
}
