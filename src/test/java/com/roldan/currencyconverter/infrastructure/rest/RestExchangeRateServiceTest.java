package com.roldan.currencyconverter.infrastructure.rest;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class RestExchangeRateServiceTest {
	
	@Test
	public void testgetExchangeRate() throws Exception {
		RestExchangeRateService service = new RestExchangeRateService();
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(2010, 06, 03);
		Date date = calendar.getTime();		
		
		float rate = service.getExchangeRate("USD", "EUR", date);
		System.out.println("Rate: " + rate);
	}
}