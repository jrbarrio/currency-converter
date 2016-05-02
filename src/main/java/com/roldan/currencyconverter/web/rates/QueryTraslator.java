package com.roldan.currencyconverter.web.rates;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.roldan.currencyconverter.domain.model.Query;

@Component
public class QueryTraslator {
	
	public Query traslate(QueryForm queryForm) {		
		return new Query(
				queryForm.getUsername(), 
				queryForm.getFrom(), 
				queryForm.getTo(), 
				queryForm.getDate(), 
				queryForm.getRate(), 
				new Date());
	}

	public List<QueryForm> traslate(List<Query> queries) {
		List<QueryForm> queryFormList = new ArrayList<>();
		for (Query query: queries) {
			queryFormList.add(traslate(query));
		}
		return queryFormList;
	}
	
	public QueryForm traslate(Query query) {		
		QueryForm queryForm = new QueryForm();
		queryForm.setUsername(query.getUsername());
		queryForm.setFrom(query.getFrom());
		queryForm.setTo(query.getTo());
		queryForm.setDate(query.getDate());
		queryForm.setRate(query.getRate());
		return queryForm;
	}
}
