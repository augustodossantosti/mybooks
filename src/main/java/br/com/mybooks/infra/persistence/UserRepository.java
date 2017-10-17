/*
 * mybooks 1.0 2 de abr de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.infra.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.mybooks.domain.users.User;

/**
 * A classe <code>UserRepository</code> atua como um
 * repositório para a entidade <code>User</code>.
 *
 * @see User
 * 
 * @author Augusto dos Santos
 * @version 1.0 2 de abr de 2017
 */
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("select u from User u left join fetch u.roles r where u.username=:username")
    public Optional<User> findByUsername(@Param("username") String username);

}
