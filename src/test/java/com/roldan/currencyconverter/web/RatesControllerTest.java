package com.roldan.currencyconverter.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

public class RatesControllerTest {
	
	@Test
	public void testLoginPage() throws Exception {
		RatesController ratesController = new RatesController();
		MockMvc mockMvc = standaloneSetup(ratesController).build();
		mockMvc.perform(get("/")).andExpect(view().name("ratesForm"));
	}

}
