package com.roldan.currencyconverter.infrastructure.rest;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.roldan.currencyconverter.domain.model.Query;

public class RestExchangeRateServiceTest {
	
	@Test
	public void testgetExchangeRate() throws Exception {
		RestExchangeRateService service = new RestExchangeRateService();
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(2010, 06, 03);
		Date date = calendar.getTime();		
		
		Query query = new Query("jrbarrio", "USD", "EUR", date, (float) 0.9, new Date());
		float rate = service.getExchangeRate(query);
		System.out.println("Rate: " + rate);
	}
}