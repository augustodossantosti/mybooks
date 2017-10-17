/*
 * mybooks 1.0 26 de mar de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.domain.library;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.mybooks.configuration.TestContextConfiguration;
import br.com.mybooks.domain.items.Category;
import br.com.mybooks.domain.library.Library;
import br.com.mybooks.domain.library.exceptions.NoShelfWithThisCategoryException;
import br.com.mybooks.domain.library.exceptions.ShelfAlreadyRegisteredException;

/**
 * A classe <code>LibraryTest</code> contém os testes
 * de unidade para a classe <code>Library</code>.
 *
 * @author Augusto dos Santos
 * @version 1.0 26 de mar de 2017
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TestContextConfiguration.class)
public class LibraryTest {

	private Library library;
	
	@Before
	public void createInventory() {
		library = new Library();
	}
	
	@Test
	public void shouldRegisterANewShelf() throws ShelfAlreadyRegisteredException, NoShelfWithThisCategoryException {
		library.registerShelf(Category.ADVENTURE);
	}
	
	@Test(expected = ShelfAlreadyRegisteredException.class)
	public void shouldIndicateThatAShelfAlreadyExists() throws ShelfAlreadyRegisteredException {
		library.registerShelf(Category.BIOGRAPHIES);
		library.registerShelf(Category.BIOGRAPHIES);
	}
}
