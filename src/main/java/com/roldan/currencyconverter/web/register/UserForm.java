package com.roldan.currencyconverter.web.register;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.format.annotation.DateTimeFormat;

public class UserForm {
	
	@Size(min=6, max=10, message="{username.size}")
	private String username;
	@Size(min=6, max=10, message="{password.size}")
	private String password;
	@Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message="{email.pattern}")
	private String email;
	@Past(message="{dateOfBirth.past}")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date dateOfBirth;
	@Size(min=1, max=50, message="{street.size}")
	private String street;
	@Pattern(regexp="^\\d{5}$", message="{zipCode.pattern}")
	private String zipCode;
	@Size(min=1, max=50, message="{city.size}")
	private String city;
	@Size(min=1, max=50, message="{country.size}")
	private String country;
	
	public UserForm() {}

	public UserForm(String username, String password, String email, Date dateOfBirth, String street, String zipCode, String city, String country) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.street = street;
		this.zipCode = zipCode;
		this.city = city;
		this.country = country;
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
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	@Override
	public boolean equals(Object that) {
		return EqualsBuilder.reflectionEquals(this, that, "password", "email", "dateOfBirth", "street", "zipCode", "city", "country");
	}
  
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "password", "email", "dateOfBirth", "street", "zipCode", "city", "country");
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}