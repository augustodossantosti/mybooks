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
import br.com.mybooks.domain.shelf.Shelf;
import br.com.mybooks.infra.services.StorageService;

/**
 * A classe <code>LibraryFacade</code> interliga as diferentes 
 * partes da arquitetura da aplicação disponibilizando sua API.
 *
 * @author Augusto dos Santos
 * @version 1.0 27 de mar de 2017
 */
@Component
public class LibraryFacade {
	
	@Autowired
	private Library library;
	
	@Autowired
	private StorageService storageService;
	
	public void registerShelf(final Category category) throws LibraryException {
		library.registerShelf(category);
	}
	
	public Shelf searchShelfById(final Long id) {
		return library.searchShelfById(id);
	}
	
	public Shelf searchShelf(final Category category) {
		return library.searchShelfByCategory(category);
	}
	
	public List<Shelf> listAllShelfs() {
		return library.listAllShelfs();
	}
	
	public void registerItem(final Item item, final MultipartFile file, final MultipartFile cover) throws LibraryException, IOException {
		final FileInfo fileInfo = storageService.store(file, cover);
		item.setFileInfo(fileInfo);
		library.registerItem(item);
	}
	
	public Item updateItem(final Item item, final MultipartFile file, final MultipartFile cover) throws IOException {
		final FileInfo fileInfo = storageService.store(file, cover);
		item.setFileInfo(fileInfo);
		return library.updateItem(item);
	}
	
	public Resource loadAsResource(final String filePath) throws StorageFileNotFoundException {
		return storageService.loadAsResource(filePath);
	}
	
	public Item searchItemById(final Long id) {
		return library.searchItemById(id);
	}

	public Item searchItem(final String title) throws LibraryException {
		return library.searchItem(title);
	}
	
	public List<Item> listAllItems(final Category category) {
		return category != null ? library.searchItemsByCategory(category) : library.listAllItems();
	}
	
	public List<Event> report() throws NoOperationsException {
		return library.report();
	}
	
}
