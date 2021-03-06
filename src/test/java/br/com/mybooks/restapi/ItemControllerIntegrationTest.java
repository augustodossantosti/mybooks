/*
 * mybooks 1.0 19 de abr de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.restapi;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;

import br.com.mybooks.configuration.AbstractIntegrationTest;
import br.com.mybooks.domain.items.Category;
import br.com.mybooks.domain.items.Item;
import br.com.mybooks.domain.items.ItemFactory;
import br.com.mybooks.restapi.wrapper.ItemWrapper;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * A classe <code>ItemControllerIntegrationTest</code> contém os
 * testes de integração para as operações com <code>Item</code>.
 *
 * @author Augusto dos Santos
 * @version 1.0 19 de abr de 2017
 */
@RunWith(SpringRunner.class)
public final class ItemControllerIntegrationTest extends AbstractIntegrationTest {
	
	@Autowired
	private ItemFactory itemFactory;
	
	private String jwt;
	private boolean initialized = false;
	
	@Before
	public void userAuthentication() throws Exception {
		if(!initialized) {
			final String jsonCredentials = "{"
					+ "\"username\" : \"augusto\","
					+ "\"password\" : \"spring\""
				+ "}";
		
			final MvcResult result =  performRESTLogin(jsonCredentials);
			final MockHttpServletResponse response = result.getResponse();
			jwt = response.getHeader("Authorization");
			initialized = true;
		}
	}

	@Test
	public void shouldRegisterItem() throws Exception {
		
		final Item magazine = itemFactory.createMagazineWithCategory(Category.TECNOLOGY);
		final ItemWrapper magazineWrapper = ItemWrapper.of(magazine);
		final String magazineJson = serializeObject(magazineWrapper);

		final List<File> files = Arrays.asList(new File(getClass().getResource("/cover.jpg").toURI()),
				new File(getClass().getResource("/file.pdf").toURI()));

		final MvcResult result = performRESTUpload("/items", jwt, magazineJson, files);
		final MockHttpServletResponse response = result.getResponse();
		final int status = response.getStatus();
		
		assertThat(status, is(201));
	}
	
	@Test
	public void shouldFindARegisteredItem() throws Exception {

		final Item book = itemFactory.createBookWithCategory(Category.SCI_FI);
		final ItemWrapper bookWrapper = ItemWrapper.of(book);
		final String bookJson = serializeObject(bookWrapper);

		final List<File> files = Arrays.asList(new File(getClass().getResource("/cover.jpg").toURI()),
				new File(getClass().getResource("/file.pdf").toURI()));

		performRESTUpload("/items", jwt, bookJson, files);

		final MvcResult result = performRESTGet("/items/search?title=" + book.getTitle(), jwt);
		final MockHttpServletResponse response = result.getResponse();
		final String content = response.getContentAsString();
		final int status = response.getStatus();

		assertThat(status, is(200));
		assertThat(content, is(bookJson));
	}
	
	@Test
	public void shouldNotFindUnregisteredItem() throws Exception {
		
		final String itemTitle = "Any item";
		
		final MvcResult result = performRESTPost("/search?item=" + itemTitle, jwt, "");
		final MockHttpServletResponse response = result.getResponse();
		final int status = response.getStatus();
		
		assertThat(status, is(404));
	}
	
	@Test
	public void shouldGenerateReport() throws Exception {
		
		final Item book = itemFactory.createMangaWithCategory(Category.ROMANCE);
		final ItemWrapper bookWrapper = ItemWrapper.of(book);
		final String bookJson = serializeObject(bookWrapper);

		final List<File> files = Arrays.asList(new File(getClass().getResource("/cover.jpg").toURI()),
				new File(getClass().getResource("/file.pdf").toURI()));

		performRESTUpload("/items", jwt, bookJson, files);
		
		final MvcResult result = performRESTGet("/items/report", jwt);
		final MockHttpServletResponse response = result.getResponse();
		final int status = response.getStatus();
		assertThat(status, is(200));
	}
}
