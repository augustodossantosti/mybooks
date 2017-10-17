/*
 * mybooks 1.0 18 de mar de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.domain.users;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * A classe <code>PersonalInformation</code> contém todas as 
 * informações de localização e/ou contato de clientes que
 * intessam a aplicação.
 *
 * @author Augusto dos Santos
 * @version 1.0 18 de mar de 2017
 */
@Embeddable
public class PersonalInformation {
	
	@Column(name = "NAME")
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "GENRE")
	private Genre genre;
	
	@Column(name = "ID_NUMBER")
	private String idNumber;
	
	@Column(name = "CELL_PHONE")
	private String cellPhone;
	
	@Column(name = "STATE")
	private String state;
	
	@Column(name = "CITY")
	private String city;
	
	@Column(name = "STREET_ADDRESS")
	private String streetAddress;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	
}