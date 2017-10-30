/*
 * mybooks 1.0 13 de out de 2016
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.restapi.wrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.mybooks.domain.library.events.Event;
import br.com.mybooks.restapi.serializer.ReportSerializer;

/**
 * A classe <code>ReportWrapper</code> encapsula as informações
 * referentes a todos os eventos ocorridos nas aplicação.
 *
 * @author Augusto dos Santos
 * @version 1.0 28 de out de 2016
 */
@JsonSerialize(using= ReportSerializer.class)
public class ReportWrapper {
	
	private final List<Event> events = new ArrayList<>();
	
	public ReportWrapper(final List<Event> events) {
		this.events.addAll(events);
	}
	
	public List<Event> events() {
		return Collections.unmodifiableList(events);
	}

}
