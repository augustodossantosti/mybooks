/*
 * mybooks 1.0 19 de jun de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.infra.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.mybooks.domain.items.Category;
import br.com.mybooks.domain.shelf.Shelf;

/**
 * A classe <code>ShelfRepository<code> atua como o
 * repositório de <code>Shelf</code>.
 *
 * @author Augusto dos Santos
 * @version 1.0 19 de jun de 2017
 */
@Repository
public interface ShelfRepository extends CrudRepository<Shelf, Long> {
	
	Shelf findById(Long id);

	Shelf findByCategory(Category category);
	
	Shelf deleteByCategory(Category category);
	
	boolean existsByCategory(Category category);
}
