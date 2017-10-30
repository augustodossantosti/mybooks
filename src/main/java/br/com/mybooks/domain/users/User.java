/*
 * mybooks 1.0 18 de mar de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.domain.users;

import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * A classe <code>User</code> representa cada usuário
 * dentro do contexto da aplicação.
 *
 * @author Augusto dos Santos
 * @version 1.0 18 de mar de 2017
 */
@Entity
@Table(name="USER")
public class User {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="USERNAME")
    private String username;
    
    @Column(name="PASSWORD")
    private String password;
	
	@Embedded
	private PersonalInformation information;

	@OneToMany
    @JoinColumn(name="LIBRARY_USER_ID", referencedColumnName="ID")
	private List<LibraryRole> roles;
	
	public User() {}
	
	public User(final PersonalInformation information, final String username, 
			final String password, final List<LibraryRole> roles) {
		
		this.information = information;
		this.username = username;
		this.password = password;
		this.roles = roles;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(final Long id) {
		this.id = id;
	}
	
    public String getUsername() {
        return username;
    }
    
    public void setUsername(final String username) {
    	this.username = username;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(final String password) {
    	this.password = password;
    }

	public List<LibraryRole> getRoles() {
		if(roles == null) {
			return Collections.emptyList();
		}
		return Collections.unmodifiableList(roles);
	}

	public void setRoles(final List<LibraryRole> roles) {
		this.roles = roles;
	}
	
	public String getName() {
		return information.getName();
	}

	public void setName(final String name) {
		information.setName(name);
	}

	public Genre getGenre() {
		return information.getGenre();
	}

	public void setGenre(Genre genre) {
		information.setGenre(genre);
	}

	public String getIdNumber() {
		return information.getIdNumber();
	}

	public void setIdNumber(String idNumber) {
		information.setIdNumber(idNumber);
	}

	public String getCellPhone() {
		return information.getCellPhone();
	}

	public void setCellPhone(String cellPhone) {
		information.setCellPhone(cellPhone);
	}

	public String getState() {
		return information.getState();
	}

	public void setState(String state) {
		information.setState(state);
	}

	public String getCity() {
		return information.getCity();
	}

	public void setCity(String city) {
		information.setCity(city);
	}

	public String getStreetAddress() {
		return information.getStreetAddress();
	}

	public void setStreetAddress(String streetAddress) {
		information.setStreetAddress(streetAddress);
	}
}
