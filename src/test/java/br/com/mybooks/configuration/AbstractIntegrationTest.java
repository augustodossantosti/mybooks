/*
 * mybooks 1.0 8 de abr de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.configuration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.*;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
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
        return mockMvc.perform(get(restURI, urlVariable).header("Authorization", "Bearer "
				+ authenticationToken)).andReturn();
    }

    protected MvcResult performRESTPost(final String restURI, final String authenticationToken, 
    		final String json, final Object... urlVariable) throws Exception {
    	
        mockMvc = webAppContextSetup(webApplicationContext)
        		.addFilter(jwtAuthenticationFilter, "/*").build();
        return mockMvc.perform(post(restURI, urlVariable).contentType(MediaType.APPLICATION_JSON)
        		.content(json).header("Authorization", "Bearer " + authenticationToken)).andReturn();
    }

    protected MvcResult performRESTUpload(final String restURI, final String authenticationToken,
										  final String json, final List<File> files, final Object... urlVariables) throws Exception {

    	mockMvc = webAppContextSetup(webApplicationContext)
				.addFilter(jwtAuthenticationFilter, "/*").build();

		final MockMultipartHttpServletRequestBuilder uploadBuilder = fileUpload(restURI, urlVariables);
		uploadBuilder.file(new MockMultipartFile("item", "item", MediaType.APPLICATION_JSON_VALUE, json.getBytes()));

		for (final File file : files) {
			uploadBuilder.file(new MockMultipartFile(file.getName().substring(0, file.getName().lastIndexOf(".")),
					file.getName(), MediaType.MULTIPART_FORM_DATA_VALUE, FileUtils.openInputStream(file)));
		}

		uploadBuilder.header("Authorization", "Bearer " + authenticationToken).contentType(MediaType.MULTIPART_FORM_DATA);

    	return mockMvc
				.perform(uploadBuilder)
				.andReturn();
	}
    
    protected LibraryErrorResponse getAuthenticationErrorResponse(final MockHttpServletResponse httpResponse) 
    		throws IOException {
    	
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
    		throws IOException {
    	
    	return jsonMapper.readValue(json, objectResult.getClass());
    }

}
