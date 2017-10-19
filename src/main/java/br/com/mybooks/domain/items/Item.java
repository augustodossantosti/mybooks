/*
 * mybooks 1.0 29 de out de 2016
 * 
 * MIT License - Copyright (c) 2017
 */
package br.com.mybooks.domain.items;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A classe <code>Item</code> representa um item gerenciável 
 * pela aplicação (livros, revistas, mangás, etc).
 *
 * @author Augusto dos Santos
 * @version 1.0 29 de out de 2016
 */
@Entity
@Table(name = "ITEM")
public final class Item implements Comparable<Item> {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Embedded
	private Features features;
	
	@Embedded
	private FileInfo fileInfo;
	
	@Column(name = "SHELF_ID")
	private Long shelfId;
	
	public Item() {}
	
	public Item(final Features features) {
		this.features = features;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(final Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return features.getTitle();
	}
	
	public void setTitle(final String title) {
		features.setTitle(title);
	}
	
	public int getEdition() {
		return features.getEdition();
	}
	
	public void setEdition(final int edition) {
		features.setEdition(edition);
	}
	
	public String getPublisher() {
		return features.getPublisher();
	}
	
	public void setPublisher(final String publisher) {
		features.setPublisher(publisher);
	}
	
	public String getDescription() {
		return features.getDescription();
	}
	
	public void setDescription(final String description) {
		features.setDescription(description);
	}
	
	public Category getCategory() {
		return features.getCategory();
	}
	
	public void setCategory(final Category category) {
		features.setCategory(category);
	}
	
	public Type getType() {
		return features.getType();
	}
	
	public void setType(final Type type) {
		features.setType(type);
	}
	
	public String getIdentification() {
		return features.getIdentification();
	}
	
	public void setIdentification(final String identification) {
		features.setIdentification(identification);
	}
	
	public void setFileInfo(final FileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}
	
	public FileInfo getFileInfo() {
		return fileInfo;
	}
	
	public String getFileName() {
		return fileInfo.getName();
	}
	
	public LocalDate getFileCreatedDate() {
		return fileInfo.getCreatedDate();
	}
	
	public String getFilePath() {
		return fileInfo.getPath();
	}
	
	public Long getShelfId() {
		return shelfId;
	}
	
	@Override
	public int compareTo(Item o) {
		return String.CASE_INSENSITIVE_ORDER.compare(this.getTitle(), o.getTitle());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((features == null) ? 0 : features.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (features == null) {
			if (other.features != null)
				return false;
		} else if (!features.equals(other.features))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", features=" + features + "]";
	}

}
