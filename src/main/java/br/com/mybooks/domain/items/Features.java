/*
 * mybooks 1.0 11 de out de 2016
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.domain.items;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;

/**
 * A classe <code>Features</code> contém as informações de cada item
 * gerenciável pela aplicação.
 *
 * @author Augusto dos Santos
 * @version 1.0 11 de out de 2016
 */
@Embeddable
public class Features {
	
	@Column(name = "TITLE")
	private String title;
	@Column(name = "EDITION")
	private Integer edition;
	@Column(name = "PUBLISHER")
	private String publisher;
	@Lob
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "IDENTIFICATION", unique = true)
	private String identification;
	@Column(name = "CATEGORY")
	@Enumerated(EnumType.STRING)
	private Category category;
	@Column(name = "TYPE")
	@Enumerated(EnumType.STRING)
	private Type type;
	
	public Features() {}

	public Features(final String title, final Integer edition, final String publisher, final String description,
					final String identification, final Category category, final Type type) {
		
		this.title = title;
		this.edition = edition;
		this.publisher = publisher;
		this.description = description;
		this.category = category;
		this.type = type;
		this.identification = identification;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Integer getEdition() {
		return edition;
	}
	
	public void setEdition(int edition) {
		this.edition = edition;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public String getIdentification() {
		return identification;
	}
	
	public void setIdentification(String identification) {
		this.identification = identification;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + edition;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Features other = (Features) obj;
		if (category != other.category)
			return false;
		if (edition != other.edition)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Features [title=" + title + ", edition=" + edition + ", identification=" + identification
				+ ", category=" + category + ", type=" + type + "]";
	}
}
