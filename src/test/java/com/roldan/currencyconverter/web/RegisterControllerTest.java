package com.roldan.currencyconverter.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

public class RegisterControllerTest {
	
	@Test
	public void testLoginPage() throws Exception {
		RegisterController registerController = new RegisterController();
		MockMvc mockMvc = standaloneSetup(registerController).build();
		mockMvc.perform(get("/register")).andExpect(view().name("registerForm"));
	}
}
