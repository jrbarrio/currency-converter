package com.roldan.currencyconverter.domain.model;

public class User {
	
	private Long id;
	private String email;
	private String password;
	private String dateOfBirth;
	private PostalAddress postalAddress;
	
	public User(Long id, String email, String password, String dateOfBirth, PostalAddress postalAddress) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.postalAddress = postalAddress;
	}

	public User(String email, String password, String dateOfBirth, PostalAddress postalAddress) {
		super();
		this.email = email;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.postalAddress = postalAddress;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public PostalAddress getPostalAddress() {
		return postalAddress;
	}
	public void setPostalAddress(PostalAddress postalAddress) {
		this.postalAddress = postalAddress;
	}
}
