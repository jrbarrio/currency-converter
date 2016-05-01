package com.roldan.currencyconverter.web.register;

import org.springframework.stereotype.Component;

import com.roldan.currencyconverter.domain.model.PostalAddress;
import com.roldan.currencyconverter.domain.model.User;

@Component
public class UserTranslator {

	public User translate(UserForm userForm) {
		PostalAddress postalAddress = new PostalAddress(
				userForm.getStreet(), 
				userForm.getZipCode(), 
				userForm.getCity(), 
				userForm.getCountry());
		User user = new User(
				userForm.getUsername(), 
				userForm.getPassword(),
				userForm.getEmail(), 
				userForm.getDateOfBirth(), 
				postalAddress);
		return user;
	}

}
