package com.roldan.currencyconverter.infrastructure;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.roldan.currencyconverter.domain.model.PostalAddress;
import com.roldan.currencyconverter.domain.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JdbcTestConfig.class)
public class JdbcSpitterRepositoryTest {

	@Autowired
	JdbcUserRepository userRepository;

	@Test
	@Transactional
	public void saveNewUser() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(1978, 06, 03);
		Date dateOfBirth = calendar.getTime();
		
		PostalAddress postalAddress = new PostalAddress("Fermin Caballero", "28035", "Madrid", "Spain");
		User unsavedUser = new User("jrbarrio", "password", "jorge.roldan@gmail.com", dateOfBirth, postalAddress);
		User savedUser = userRepository.save(unsavedUser);
		assertEquals(unsavedUser.getEmail(), savedUser.getEmail());
	}
}
