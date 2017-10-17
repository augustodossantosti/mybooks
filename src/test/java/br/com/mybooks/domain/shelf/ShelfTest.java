/*
 * mybooks 1.0 5 de mar de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.domain.shelf;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.mybooks.configuration.TestContextConfiguration;
import br.com.mybooks.domain.items.Category;
import br.com.mybooks.domain.items.Item;
import br.com.mybooks.domain.items.LibraryItemFactory;
import br.com.mybooks.domain.shelf.Shelf;
import br.com.mybooks.domain.shelf.exceptions.InvalidCategoryOfItemException;
import br.com.mybooks.domain.shelf.exceptions.ItemNotFoundException;

/**
 * A classe <code>ShelfTest</code> contém os testes de unidade
 * para a classe <code>Shelf</code>.
 * @see Shelf
 *
 * @author Augusto dos Santos
 * @version 1.0 5 de mar de 2017
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TestContextConfiguration.class)
public class ShelfTest {
	
	@Autowired
	private LibraryItemFactory itemFactory;
	
	private Shelf shelf;
	private Item item;
	
	@Before
	public void createShelf() {
		shelf = new Shelf(Category.ADVENTURE);
	}
	
	@Test
	public void shouldAddItemsWithSameCategory() throws InvalidCategoryOfItemException {
		item = itemFactory.createBookWithCategory(Category.ADVENTURE);
		shelf.addNewItem(item);
		
		assertThat(shelf.getAvailableItems(), hasItems(item));
	}
	
	@Test(expected = InvalidCategoryOfItemException.class)
	public void shouldNotAddItemsFromDifferentCategories() throws InvalidCategoryOfItemException {
		item = itemFactory.createBookWithCategory(Category.BIOGRAPHIES);
		shelf.addNewItem(item);
	}
	
	@Test
	public void shouldRemoveItemPreviouslyAdded() throws InvalidCategoryOfItemException {
		
		assertThat( shelf.getAvailableItems(), is(empty()) );
		
		item = itemFactory.createMagazineWithCategory(Category.ADVENTURE);
		shelf.addNewItem(item);
		assertThat(shelf.getAvailableItems(), hasItem(item));
		
		shelf.removeItem(item);
		assertThat( shelf.getAvailableItems(), not(hasItem(item)) );
	}
	
	@Test
	public void shouldReturnItemStoredInTitleSearch() throws InvalidCategoryOfItemException, ItemNotFoundException {
		item = itemFactory.createMangaWithCategory(Category.ADVENTURE);
		shelf.addNewItem(item);
		assertThat(shelf.searchItem("Manga for test"), is(item));
	}
	
	@Test(expected = ItemNotFoundException.class)
	public void shouldIndicateThatAnItemIsNotPresentInTitleSearch() throws ItemNotFoundException {
		shelf.searchItem("Any Item");
	}

}
