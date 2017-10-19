/*
 * mybooks 1.0 11 de out de 2017
 * 
 * MIT License - Copyright (c) 2017
 */
package br.com.mybooks.domain.items;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * A classe <code>FileInfo</code> encapsula as informações referentes 
 * ao arquivo associado a um determinado item.
 *
 * @author Augusto dos Santos
 * @version 1.0 11 de out de 2017
 */
@Embeddable
public class FileInfo {

	@Column(name = "FILE_NAME")
	private String name;
	
	@Column(name = "CREATED_DATE")
	private LocalDate createdDate;
	
	@Column(name = "FILE_PATH")
	private String path;
	
	public FileInfo() {}
	
	public FileInfo(final String fileName, final LocalDate createdDate, final String absolutePath) {
		this.name = fileName;
		this.createdDate = createdDate;
		this.path = absolutePath;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the createdDate
	 */
	public LocalDate getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "FileInfo: [name: " + name + ", createdDate: " 
				+ createdDate.format(DateTimeFormatter.ofPattern("dd MMM uuuu")) + ", path: " + path + "]";
	}

}
