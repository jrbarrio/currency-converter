package com.roldan.currencyconverter.domain.model;

import java.util.Date;

public class User {
	
	private String username;
	private String password;
	private String email;
	private Date dateOfBirth;
	private PostalAddress postalAddress;

	public User(String username, String password, String email, Date dateOfBirth, PostalAddress postalAddress) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.postalAddress = postalAddress;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public PostalAddress getPostalAddress() {
		return postalAddress;
	}
	public void setPostalAddress(PostalAddress postalAddress) {
		this.postalAddress = postalAddress;
	}
}
