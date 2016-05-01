package com.roldan.currencyconverter.infrastructure;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.roldan.currencyconverter.domain.model.User;
import com.roldan.currencyconverter.domain.model.UserRepository;

@Repository
public class JdbcUserRepository implements UserRepository {

	private JdbcTemplate jdbcTemplate;

	public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;		
	}
	
	@Override	
	public User save(User user) {
		Long id = user.getId();
		if (id == null) {
			long userId = insertAndReturnUserId(user);
			return new User(userId, user.getEmail(), user.getPassword(), user.getDateOfBirth(), user.getPostalAddress());
		} else {
			jdbcTemplate.update("update Spitter set email=?, password=?, dateOfBirth=?, street=?, zipCode=?, city=?, country=? where id=?",					
					user.getEmail(),
					user.getPassword(),
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
		args.put("email", user.getEmail());
		args.put("password", user.getPassword());
		args.put("dateOfBirth", user.getDateOfBirth());
		args.put("street", user.getPostalAddress().getStreet());
		args.put("zipCode", user.getPostalAddress().getZipCode());
		args.put("city", user.getPostalAddress().getCity());
		args.put("country", user.getPostalAddress().getCountry());
		long spitterId = jdbcInsert.executeAndReturnKey(args).longValue();
		return spitterId;
	}
}
