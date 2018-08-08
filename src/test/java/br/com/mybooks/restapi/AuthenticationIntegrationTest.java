/*
 * mybooks 1.0 9 de abr de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.restapi;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;

import br.com.mybooks.configuration.AbstractIntegrationTest;
import br.com.mybooks.domain.items.Category;
import br.com.mybooks.domain.items.Item;
import br.com.mybooks.domain.items.ItemFactory;
import br.com.mybooks.restapi.errorhandling.LibraryErrorResponse;
import br.com.mybooks.restapi.wrapper.ItemWrapper;

/**
 * A classe <code>AuthenticationIntegrationTest</code> contém
 * os testes de integração para o processo de autenticação
 * da aplicação.
 *
 * @author Augusto dos Santos
 * @version 1.0 9 de abr de 2017
 */
@RunWith(SpringRunner.class)
public class AuthenticationIntegrationTest extends AbstractIntegrationTest {
	
	@Autowired
	private ItemFactory itemFactory;

	@Test
	public void shouldGetAuthorizationJwt() throws Exception {
		
		final String jsonCredentials = "{"
					+ "\"username\" : \"augusto\","
					+ "\"password\" : \"spring\""
				+ "}";
		
		final MvcResult result =  performRESTLogin(jsonCredentials);
		final MockHttpServletResponse response = result.getResponse();
		final int status = response.getStatus();
		final String jwt = response.getHeader("Authorization");
		
		assertThat(status, is(200));
		assertThat(jwt, notNullValue());
	}
	
	@Test
	public void shouldNotProvideAuthorizationIfCredentialsAreInvalid() throws Exception {
		
		final String jsonCredentials = "{"
				+ "\"username\" : \"any value\","
				+ "\"password\" : \"any value\""
			+ "}";
	
		final MvcResult result =  performRESTLogin(jsonCredentials);
		final MockHttpServletResponse response = result.getResponse();
		final int status = response.getStatus();
		final String jwt = response.getHeader("Authorization");
		
		final LibraryErrorResponse errorResponse = getAuthenticationErrorResponse(response);
		
		assertThat(status, is(401));
		assertThat(errorResponse.getMessage(), is("Authentication Failed. Username or Password not valid."));
		assertThat(jwt, is(nullValue()));
	}
	
	@Test
	public void shouldNotProvideAuthorizationIfHeadersAreInvalid() throws Exception {
		
		final String jsonCredentials = "{"
				+ "\"username\" : \"augusto\","
				+ "\"password\" : \"spring\""
			+ "}";
		
		mockMvc = webAppContextSetup(webApplicationContext).addFilter(loginFilter, "/login").build();
		final MvcResult result = mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON)
        		.content(jsonCredentials).header("Invalid Header", "Invalid Value")).andReturn();
		
		final MockHttpServletResponse response = result.getResponse();
		final int status = response.getStatus();
		final String jwt = response.getHeader("Authorization");
		
		final LibraryErrorResponse errorResponse = getAuthenticationErrorResponse(response);
		
		assertThat(status, is(401));
		assertThat(errorResponse.getMessage(), is("Invalid Request! Check the http method and request headers."));
		assertThat(jwt, is(nullValue()));
	}
	
	@Test
	public void shouldNotProvideAuthorizationIfJsonWithCredentialsWasNotInformed() throws Exception {
		
		final String jsonCredentials = "";
		
		final MvcResult result =  performRESTLogin(jsonCredentials);
		final MockHttpServletResponse response = result.getResponse();
		final int status = response.getStatus();
		final String jwt = response.getHeader("Authorization");
		
		final LibraryErrorResponse errorResponse = getAuthenticationErrorResponse(response);
		
		assertThat(status, is(401));
		assertThat(errorResponse.getMessage(), is("JSON with credentials was not informed."));
		assertThat(jwt, is(nullValue()));
	}
	
	@Test
	public void shouldNotProvideAuthorizationIfUniformedUserOrPassword() throws Exception {
		
		final String jsonCredentials = "{"
				+ "\"username\" : \"augusto\","
				+ "\"password\" : \"\""
			+ "}";
		
		final MvcResult result =  performRESTLogin(jsonCredentials);
		final MockHttpServletResponse response = result.getResponse();
		final int status = response.getStatus();
		final String jwt = response.getHeader("Authorization");
		
		final LibraryErrorResponse errorResponse = getAuthenticationErrorResponse(response);
		
		assertThat(status, is(401));
		assertThat(errorResponse.getMessage(), is("Uninformed user or password."));
		assertThat(jwt, is(nullValue()));
	}
	
	@Test
	public void shouldNotPerformDomainOperationWithAInvalidAuthorizationToken() throws Exception {
		
		final Item book = itemFactory.createBookWithCategory(Category.HYSTORY);
		final ItemWrapper bookWrapper = ItemWrapper.of(book);
		final String bookJson = serializeObject(bookWrapper);
		
		final String jwt = "Invalid Token";
		
		final MvcResult result = performRESTPost("/items", jwt, bookJson);
		final MockHttpServletResponse response = result.getResponse();
		final int status = response.getStatus();
		
		final LibraryErrorResponse errorResponse = getAuthenticationErrorResponse(response);
		
		assertThat(status, is(401));
		assertThat(errorResponse.getMessage(), startsWith("Invalid JWT token: "));
	}
	
	@Test
	public void shouldNotPerformDomainOperationWithAExpiredAuthorizationToken() throws Exception {
		
		final Item book = itemFactory.createBookWithCategory(Category.HYSTORY);
		final ItemWrapper bookWrapper = ItemWrapper.of(book);
		final String bookJson = serializeObject(bookWrapper);
		
		final String expiredJwt = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsInNjb3BlcyI6WyJST0xFX"
				+ "0xJQlJBUklBTiJdLCJpc3MiOiJsaWJyYXJ5bWFuYWdlci1hcHAiLCJqdGkiOiI3M2RkYjU1YS00MTI"
				+ "1LTQ3MjItYWEwMC05MzhkNzg2YzFiMTciLCJpYXQiOjE0OTEyNzc0NDMsImV4cCI6MTQ5MTI3NzUwM"
				+ "30.TkTkCsE936GOK9hyjuV3cw-tqoOonj_bS_CWH3VTr6A0q2lMDr-fqc8zwFjx7N5dMvclwEIPuE3"
				+ "2oB5vaVEXdA";
		
		final MvcResult result = performRESTPost("/items", expiredJwt, bookJson);
		final MockHttpServletResponse response = result.getResponse();
		final int status = response.getStatus();
		
		final LibraryErrorResponse errorResponse = getAuthenticationErrorResponse(response);
		
		assertThat(status, is(401));
		assertThat(errorResponse.getMessage(), startsWith("JWT Token expired: "));
	}
	
}
