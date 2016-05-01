package com.roldan.currencyconverter.web;

import com.roldan.currencyconverter.domain.model.PostalAddress;
import com.roldan.currencyconverter.domain.model.User;

public class UserTranslator {

	public User translate(UserForm userForm) {
		PostalAddress postalAddress = new PostalAddress(
				userForm.getStreet(), 
				userForm.getZipCode(), 
				userForm.getCity(), 
				userForm.getCountry());
		User user = new User(
				userForm.getEmail(), 
				userForm.getPassword(), 
				userForm.getDateOfBirth(), 
				postalAddress);
		return user;
	}

}
