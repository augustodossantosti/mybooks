/*
 * mybooks 1.0 18 de jan de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.infra.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.mybooks.domain.items.Category;
import br.com.mybooks.domain.items.Item;

/**
 * A interface <code>ItemRepository</code> atua como repositório
 * de qualquer item gerenciável pela aplicação.
 *
 * @author Augusto dos Santos
 * @version 1.0 18 de jan de 2017
 */
@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
	
	Item findById(Long id);
	
	List<Item> findByFeaturesTitle(String title);
	
	List<Item> findByFeaturesCategory(Category category);
	
	List<Item> findByFeaturesPublisher(String publisher);
	
	Item findByFeaturesIdentification(String identification);
}
