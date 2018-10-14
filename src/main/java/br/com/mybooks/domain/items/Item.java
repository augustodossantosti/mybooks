/*
 * mybooks 1.0 29 de out de 2016
 * 
 * MIT License - Copyright (c) 2017
 */
package br.com.mybooks.domain.items;

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
	
	public Item() {
		features = new Features();
	}

	public Item(final String title, final Integer edition, final String publisher, final String description,
				final String identification, final Category category, final Type type) {
		features = new Features(title, edition, publisher, description, identification, category, type);
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(final Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return features != null ? features.getTitle() : null;
	}
	
	public void setTitle(final String title) {
		features.setTitle(title);
	}
	
	public Integer getEdition() {
		return features != null ? features.getEdition() : null;
	}
	
	public void setEdition(final Integer edition) {
		features.setEdition(edition);
	}
	
	public String getPublisher() {
		return features != null ? features.getPublisher() : null;
	}
	
	public void setPublisher(final String publisher) {
		features.setPublisher(publisher);
	}
	
	public String getDescription() {
		return features != null ? features.getDescription() : null;
	}
	
	public void setDescription(final String description) {
		features.setDescription(description);
	}
	
	public Category getCategory() {
		return features != null ? features.getCategory() : null;
	}
	
	public void setCategory(final Category category) {
		features.setCategory(category);
	}
	
	public Type getType() {
		return features != null ? features.getType() : null;
	}
	
	public void setType(final Type type) {
		features.setType(type);
	}
	
	public String getIdentification() {
		return features != null ? features.getIdentification() : null;
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
		return fileInfo != null ? fileInfo.getFileName() : null;
	}
	
	public String getFilePath() {
		return fileInfo != null ? fileInfo.getFilePath() : null;
	}
	
	public String getFileCover() {
		return fileInfo != null ? fileInfo.getCoverBase64() : null;
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
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Item other = (Item) obj;
		if (features == null) {
			return other.features == null;
		} else return features.equals(other.features);
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", features=" + features + "]";
	}

}
