package com.roldan.currencyconverter.infrastructure.rest;

import java.text.SimpleDateFormat;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.roldan.currencyconverter.domain.model.ExchangeRateService;
import com.roldan.currencyconverter.domain.model.Query;

@Service
public class RestExchangeRateService implements ExchangeRateService {

	private static final String URL = "https://openexchangerates.org/api/historical/{date}.json?app_id=cc792ddf1b7c4412b3dee49c5fa73ead&base={base}";
	
	@Override
	public float getExchangeRate(Query query) {
		RestTemplate restTemplate = new RestTemplate();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		String formattedDate = sdf.format(query.getDate());		
		Response response = restTemplate.getForObject(URL, Response.class, formattedDate, query.getFrom());
		return response.getRates().get(query.getTo());
	}
}
