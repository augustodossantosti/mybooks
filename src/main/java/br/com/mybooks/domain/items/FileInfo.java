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
import javax.persistence.Lob;

/**
 * A classe <code>FileInfo</code> encapsula as informações referentes 
 * ao arquivo associado a um determinado item.
 *
 * @author Augusto dos Santos
 * @version 1.0 11 de out de 2017
 */
@Embeddable
public class FileInfo {
	
	@Column(name = "CREATED_DATE")
	private LocalDate createdDate;

	@Column(name = "FILE_NAME")
	private String fileName;
	
	@Column(name = "FILE_PATH")
	private String filePath;
	
	@Lob
	@Column(name = "COVER_BASE_64")
	private String coverBase64;
	
	public FileInfo() {}
	
	public FileInfo(final LocalDate createdDate, final String fileName, final String filePath, final String coverBase64) {
		this.createdDate = createdDate;
		this.fileName = fileName;
		this.filePath = filePath;
		this.coverBase64 = coverBase64;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getCoverBase64() {
		return coverBase64;
	}

	public void setCoverBase64(String coverBase64) {
		this.coverBase64 = coverBase64;
	}

	@Override
	public String toString() {
		return "FileInfo: [name: " + fileName + ", createdDate: " 
				+ createdDate.format(DateTimeFormatter.ofPattern("dd MMM uuuu")) + ", path: " + filePath + "]";
	}

}
