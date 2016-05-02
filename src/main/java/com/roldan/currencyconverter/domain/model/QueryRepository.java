package com.roldan.currencyconverter.domain.model;

import java.util.List;

public interface QueryRepository {
	
	Query save(Query query);
	public List<Query> findByUserName(String username);

}
