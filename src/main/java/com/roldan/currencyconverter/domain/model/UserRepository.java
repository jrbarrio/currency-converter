package com.roldan.currencyconverter.domain.model;

import com.roldan.currencyconverter.web.UserForm;

public interface UserRepository {

	UserForm save(UserForm user);

}
