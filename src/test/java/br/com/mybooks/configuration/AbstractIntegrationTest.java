/*
 * mybooks 1.0 8 de abr de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.configuration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.mybooks.infra.security.auth.jwt.JwtAuthenticationFilter;
import br.com.mybooks.infra.security.auth.login.LoginFilter;
import br.com.mybooks.restapi.errorhandling.LibraryErrorResponse;

/**
 * A classe <code>AbstractIntegrationTest</code> contém as
 * configurações necessárias para execução dos testes de
 * integração e o correto carregamento do contexto do Spring
 * para a injeção de dependências.
 *
 * @author Augusto dos Santos
 * @version 1.0 8 de abr de 2017
 */
@SpringBootTest
@WebAppConfiguration
public class AbstractIntegrationTest {
	
	@Autowired
	protected WebApplicationContext webApplicationContext;
	
	@Autowired
	protected JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Autowired
	protected LoginFilter loginFilter;
	
	@Autowired
	protected ObjectMapper jsonMapper;

	protected MockMvc mockMvc;
    
    protected MvcResult performRESTLogin(final String jsonCredentials) throws Exception {
    	
        mockMvc = webAppContextSetup(webApplicationContext)
        		.addFilter(loginFilter, "/login").build();
        return mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON)
        		.content(jsonCredentials).header("X-Requested-With", "XMLHttpRequest")).andReturn();
    }
    
    protected MvcResult performRESTGet(final String restURI, final String authenticationToken, 
    		final Object... urlVariable) throws UnsupportedEncodingException, Exception {
    	
        mockMvc = webAppContextSetup(webApplicationContext)
        		.addFilter(jwtAuthenticationFilter, "/*").build();
        return mockMvc.perform(get(restURI, urlVariable).header("Authorization", "Bearer " + authenticationToken)).andReturn();
    }

    protected MvcResult performRESTPost(final String restURI, final String authenticationToken, 
    		final String json, final Object... urlVariable) throws Exception {
    	
        mockMvc = webAppContextSetup(webApplicationContext)
        		.addFilter(jwtAuthenticationFilter, "/*").build();
        return mockMvc.perform(post(restURI, urlVariable).contentType(MediaType.APPLICATION_JSON)
        		.content(json).header("Authorization", "Bearer " + authenticationToken)).andReturn();
    }
    
    protected LibraryErrorResponse getAuthenticationErrorResponse(final MockHttpServletResponse httpResponse) 
    		throws JsonProcessingException, IOException {
    	
    	final String content = httpResponse.getContentAsString();
    	final JsonNode node = jsonMapper.readTree(content);
    	final String message = node.get("message").asText();
    	final String url = node.get("url").asText();
    	return LibraryErrorResponse.of(message, url);
    }
    
    protected String serializeObject(final Object object) throws JsonProcessingException {
    	return jsonMapper.writeValueAsString(object);
    }
    
    protected Object deserializeJson(final String json, final Object objectResult) 
    		throws JsonParseException, JsonMappingException, IOException {
    	
    	return jsonMapper.readValue(json, objectResult.getClass());
    }

}
