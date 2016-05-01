package com.roldan.currencyconverter.web;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import com.roldan.currencyconverter.domain.model.PostalAddress;
import com.roldan.currencyconverter.domain.model.User;
import com.roldan.currencyconverter.domain.model.UserRepository;

public class RegisterControllerTest {
	
	@Test
	public void testLoginPage() throws Exception {
		UserRepository userRepository = mock(UserRepository.class);
		UserTranslator userTranslator = mock(UserTranslator.class);
		RegisterController registerController = new RegisterController(userRepository, userTranslator);
		MockMvc mockMvc = standaloneSetup(registerController).build();
		mockMvc.perform(get("/register")).andExpect(view().name("registerForm"));
	}
	
	@Test
	public void testProcessRegistration() throws Exception {
		UserTranslator userTranslator = mock(UserTranslator.class);
		UserForm userForm = new UserForm("jrbarrio", "password", "jorge.roldan@gmail.com", "1978/06/03", "Fermin Caballero", "28035", "Madrid", "Spain");
		PostalAddress postalAddress = new PostalAddress("Fermin Caballero", "28035", "Madrid", "Spain");
		User unsavedUser = new User("jrbarrio", "password", "jorge.roldan@gmail.com", "1978/06/03", postalAddress);
		when(userTranslator.translate(userForm)).thenReturn(unsavedUser);
		
		UserRepository userRepository = mock(UserRepository.class);
		User savedUser = new User(24L, "jrbarrio", "password", "jorge.roldan@gmail.com", "1978/06/03", postalAddress);		
		when(userRepository.save(unsavedUser)).thenReturn(savedUser);
		
		RegisterController registerController = new RegisterController(userRepository, userTranslator);
		MockMvc mockMvc = standaloneSetup(registerController).build();
		
		mockMvc.perform(post("/register")
				.param("username", "jrbarrio")
				.param("password", "password")
				.param("email", "jorge.roldan@gmail.com")
				.param("dateOfBirth", "1978/06/03")
				.param("street", "Fermin Caballero")
				.param("zipCode", "28035")
				.param("city", "Madrid")
				.param("country", "Spain"))
				.andExpect(view().name("loginForm"));
		
		verify(userRepository, atLeastOnce()).save(unsavedUser);
	}
	
	@Test
	public void testUserTranslator() throws Exception {
		UserTranslator userTranslator = new UserTranslator();
		UserForm userForm = new UserForm("jrbarrio", "password", "jorge.roldan@gmail.com", "1978/06/03", "Fermin Caballero", "28035", "Madrid", "Spain");
		User user = userTranslator.translate(userForm);
		
		assertEquals("jrbarrio", user.getUsername());
		assertEquals("password", user.getPassword());
		assertEquals("jorge.roldan@gmail.com", user.getEmail());
		assertEquals("1978/06/03", user.getDateOfBirth());
		assertEquals("Fermin Caballero", user.getPostalAddress().getStreet());
		assertEquals("28035", user.getPostalAddress().getZipCode());
		assertEquals("Madrid", user.getPostalAddress().getCity());
		assertEquals("Spain", user.getPostalAddress().getCountry());
	}
}
