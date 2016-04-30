package com.roldan.currencyconverter.web;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import com.roldan.currencyconverter.domain.model.UserRepository;

public class RegisterControllerTest {
	
	@Test
	public void testLoginPage() throws Exception {
		UserRepository userRepository = mock(UserRepository.class);
		RegisterController registerController = new RegisterController(userRepository);
		MockMvc mockMvc = standaloneSetup(registerController).build();
		mockMvc.perform(get("/register")).andExpect(view().name("registerForm"));
	}
	
	@Test
	public void testProcessRegistration() throws Exception {
		UserRepository userRepository = mock(UserRepository.class);
	
		String dateOfBirth ="1978/06/03";
		
		UserForm unsavedUser = new UserForm("jorge.roldan@gmail.com", "password", dateOfBirth, "Fermin Caballero", "28035", "Madrid", "Spain");
		UserForm savedUser = new UserForm(24L, "jorge.roldan@gmail.com", "password", dateOfBirth, "Fermin Caballero", "28035", "Madrid", "Spain");
		
		when(userRepository.save(unsavedUser)).thenReturn(savedUser);
		
		RegisterController registerController = new RegisterController(userRepository);
		MockMvc mockMvc = standaloneSetup(registerController).build();
		
		mockMvc.perform(post("/register")
				.param("email", "jorge.roldan@gmail.com")
				.param("password", "password")
				.param("dateOfBirth", "1978/06/03")
				.param("street", "Fermin Caballero")
				.param("zipCode", "28035")
				.param("city", "Madrid")
				.param("country", "Spain"))
				.andExpect(view().name("loginForm"));
		
		verify(userRepository, atLeastOnce()).save(unsavedUser);
	}
}
