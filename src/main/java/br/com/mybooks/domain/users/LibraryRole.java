/*
 * mybooks 1.0 12 de jan de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.domain.users;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 * A classe <code>LibraryRole</code> é uma entidade que 
 *
 * @author Augusto dos Santos
 * @version 1.0 12 de jan de 2017
 */
@Entity
@Table(name="LIBRARY_ROLE")
public class LibraryRole {
	
	@Embeddable
	public static class Id implements Serializable {
		
		private static final long serialVersionUID = 1322120000551624359L;
		
		@Column(name="LIBRARY_USER_ID")
		private Long userId;
		
		@Enumerated(EnumType.STRING)
		@Column(name="ROLE")
		protected Role role;
		
		public Id() { }
		public Id(final Long userId, final Role role) {
			this.userId = userId;
			this.role = role;
		}
		
	}
	
	@EmbeddedId
	private Id id = new Id();
	
	@Enumerated(EnumType.STRING)
    @Column(name = "ROLE", insertable=false, updatable=false)
    protected Role role;

    public Role getRole() {
        return role;
    }

}
