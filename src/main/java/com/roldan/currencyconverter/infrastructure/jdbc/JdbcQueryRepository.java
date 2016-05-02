package com.roldan.currencyconverter.infrastructure.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.roldan.currencyconverter.domain.model.Query;
import com.roldan.currencyconverter.domain.model.QueryRepository;

@Repository
public class JdbcQueryRepository implements QueryRepository {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcQueryRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;		
	}
	
	public JdbcQueryRepository() {}
	
	@Override	
	public Query save(Query query) {
		SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("Query");
		Map<String, Object> args = new HashMap<String, Object>();
		jdbcInsert.setGeneratedKeyName("id");
		args.put("username", query.getUsername());
		args.put("fromCurrency", query.getFrom());
		args.put("toCurrency", query.getTo());
		args.put("queriedDate", query.getDate());
		args.put("rate", query.getRate());
		args.put("postedTime", query.getPostedTime());
		jdbcInsert.execute(args);
		return query;
	}
	
	@Override	
	public List<Query> findByUserName(String username) {
		return jdbcTemplate.query("select username, fromCurrency, toCurrency, queriedDate, rate, postedTime from Query where username=? order by postedTime", new QueryRowMapper(), username);
	}

	private static final class QueryRowMapper implements RowMapper<Query> {
		public Query mapRow(ResultSet rs, int rowNum) throws SQLException {			
			String username = rs.getString("username");
			String from = rs.getString("fromCurrency");
			String to = rs.getString("toCurrency");
			Date date = rs.getDate("queriedDate");
			float rate = rs.getFloat("rate");
			Date postedTime = rs.getDate("postedTime");
			return new Query(username, from, to, date, rate, postedTime);
		}		
	}

}
