package com.roldan.currencyconverter.domain.model;

import java.util.Date;

public interface ExchangeRateService {
	
	float getExchangeRate(String from, String to, Date date);

}
