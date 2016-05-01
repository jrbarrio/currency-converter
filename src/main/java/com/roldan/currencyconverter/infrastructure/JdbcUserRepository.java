package com.roldan.currencyconverter.infrastructure;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.roldan.currencyconverter.domain.model.User;
import com.roldan.currencyconverter.domain.model.UserRepository;

@Repository
public class JdbcUserRepository implements UserRepository {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;		
	}
	
	public JdbcUserRepository() {}
	
	@Override	
	public User save(User user) {
		Long id = user.getId();
		if (id == null) {
			long userId = insertAndReturnUserId(user);
			return new User(userId, user.getUsername(), user.getPassword(), user.getEmail(), user.getDateOfBirth(), user.getPostalAddress());
		} else {
			jdbcTemplate.update("update Spitter set username=?, password=?, email=?, dateOfBirth=?, street=?, zipCode=?, city=?, country=? where id=?",					
					user.getUsername(),
					user.getPassword(),
					user.getEmail(),
					user.getDateOfBirth(),
					user.getPostalAddress().getStreet(),
					user.getPostalAddress().getZipCode(),
					user.getPostalAddress().getCity(),
					user.getPostalAddress().getCountry(),
					id);
		}
		return user;
	}	
	
	private long insertAndReturnUserId(User user) {
		SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("User");
		jdbcInsert.setGeneratedKeyName("id");
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("username", user.getUsername());
		args.put("password", user.getPassword());
		args.put("email", user.getEmail());
		args.put("dateOfBirth", user.getDateOfBirth());
		args.put("street", user.getPostalAddress().getStreet());
		args.put("zipCode", user.getPostalAddress().getZipCode());
		args.put("city", user.getPostalAddress().getCity());
		args.put("country", user.getPostalAddress().getCountry());
		long spitterId = jdbcInsert.executeAndReturnKey(args).longValue();
		return spitterId;
	}
}
