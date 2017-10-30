/*
 * mybooks 1.0 11 de out de 2016
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.domain.shelf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.mybooks.domain.items.Category;
import br.com.mybooks.domain.items.Item;
import br.com.mybooks.domain.shelf.exceptions.InvalidCategoryOfItemException;
import br.com.mybooks.domain.shelf.exceptions.ItemNotFoundException;

/**
 * A classe <code>Shelf</code> representa cada prateleira que agrupa
 * itens na biblioteca.
 *
 * @author Augusto dos Santos
 * @version 1.0 11 de out de 2016
 */
@Entity
@Table(name = "SHELF")
public class Shelf {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "CATEGORY", unique = true)
	private Category category;
	
	@OneToMany(orphanRemoval = true)
	@JoinColumn(name = "SHELF_ID")
	private List<Item> availableItems;
	
	public Shelf() {
		this.availableItems = new ArrayList<>();
	}
	
	public Shelf(final Category category) {
		this.category = category;
		this.availableItems = new ArrayList<>();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(final Long id) {
		this.id = id;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(final Category category) {
		this.category = category;
	}
	
	public List<Item> getAvailableItems() {
		return Collections.unmodifiableList(availableItems);
	}
	
	public void addNewItem(final Item item) throws InvalidCategoryOfItemException {
		if(item.getCategory() != category) {
			throw new InvalidCategoryOfItemException();
		}
		availableItems.add(item);
	}
	
	public boolean removeItem(final Item item) {
		return availableItems.remove(item);
	}
	
	public Item searchItem(final String title) throws ItemNotFoundException {
		for(final Item item : availableItems) {
			if (item.getTitle().equalsIgnoreCase(title)) {
				return item;
			}
		}
		throw new ItemNotFoundException();
	}

	@Override
	public String toString() {
		return "Shelf [id=" + id + ", category=" + category + "]";
	}
	
}
