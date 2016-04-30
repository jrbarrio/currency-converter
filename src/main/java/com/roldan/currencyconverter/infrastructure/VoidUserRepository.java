package com.roldan.currencyconverter.infrastructure;

import org.springframework.stereotype.Component;

import com.roldan.currencyconverter.domain.model.UserRepository;
import com.roldan.currencyconverter.web.UserForm;

@Component
public class VoidUserRepository implements UserRepository {

	@Override
	public UserForm save(UserForm user) {
		return user;
	}
}
