package com.roldan.currencyconverter.web;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import com.roldan.currencyconverter.domain.model.PostalAddress;
import com.roldan.currencyconverter.domain.model.User;
import com.roldan.currencyconverter.domain.model.UserRepository;
import com.roldan.currencyconverter.web.register.RegisterController;
import com.roldan.currencyconverter.web.register.UserForm;
import com.roldan.currencyconverter.web.register.UserTranslator;

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
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(1978, 06, 03);
		Date dateOfBirth = calendar.getTime();
		
		UserForm userForm = new UserForm("jrbarrio", "password", "jorge.roldan@gmail.com", dateOfBirth, "Fermin Caballero", "28035", "Madrid", "Spain");
		PostalAddress postalAddress = new PostalAddress("Fermin Caballero", "28035", "Madrid", "Spain");
		User unsavedUser = new User("jrbarrio", "password", "jorge.roldan@gmail.com", dateOfBirth, postalAddress);
		when(userTranslator.translate(userForm)).thenReturn(unsavedUser);
		
		UserRepository userRepository = mock(UserRepository.class);
		User savedUser = new User("jrbarrio", "password", "jorge.roldan@gmail.com", dateOfBirth, postalAddress);		
		when(userRepository.save(unsavedUser)).thenReturn(savedUser);
		
		RegisterController registerController = new RegisterController(userRepository, userTranslator);
		MockMvc mockMvc = standaloneSetup(registerController).build();
		
		mockMvc.perform(post("/register")
				.param("username", "jrbarrio")
				.param("password", "password")
				.param("email", "jorge.roldan@gmail.com")
				.param("dateOfBirth", "1978-06-03")
				.param("street", "Fermin Caballero")
				.param("zipCode", "28035")
				.param("city", "Madrid")
				.param("country", "Spain"))
				.andExpect(view().name("loginForm"));
		
		verify(userRepository, atLeastOnce()).save(unsavedUser);
	}
	
	@Test
	public void testUserTranslator() throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.set(1978, 06, 03);
		Date dateOfBirth = calendar.getTime();
		
		UserTranslator userTranslator = new UserTranslator();
		UserForm userForm = new UserForm("jrbarrio", "password", "jorge.roldan@gmail.com", dateOfBirth, "Fermin Caballero", "28035", "Madrid", "Spain");
		User user = userTranslator.translate(userForm);
		
		assertEquals("jrbarrio", user.getUsername());
		assertEquals("password", user.getPassword());
		assertEquals("jorge.roldan@gmail.com", user.getEmail());
		assertEquals(dateOfBirth, user.getDateOfBirth());
		assertEquals("Fermin Caballero", user.getPostalAddress().getStreet());
		assertEquals("28035", user.getPostalAddress().getZipCode());
		assertEquals("Madrid", user.getPostalAddress().getCity());
		assertEquals("Spain", user.getPostalAddress().getCountry());
	}
}
