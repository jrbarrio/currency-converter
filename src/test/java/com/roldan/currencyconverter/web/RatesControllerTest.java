package com.roldan.currencyconverter.web;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import com.roldan.currencyconverter.domain.model.ExchangeRateService;
import com.roldan.currencyconverter.web.rates.RatesController;

public class RatesControllerTest {
	
	@Test
	public void testLoginPage() throws Exception {
		ExchangeRateService exchangeRateService = mock(ExchangeRateService.class);
		RatesController ratesController = new RatesController(exchangeRateService);
		MockMvc mockMvc = standaloneSetup(ratesController).build();
		mockMvc.perform(get("/")).andExpect(view().name("ratesForm"));
	}

}
