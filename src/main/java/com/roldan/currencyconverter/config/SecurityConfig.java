package com.roldan.currencyconverter.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	protected void configure(HttpSecurity http) throws Exception {
		http
			.formLogin().loginPage("/login")
			.and()
			.rememberMe().tokenValiditySeconds(2419200)
			.and()
			.logout().logoutSuccessUrl("/login")
			.and()
			.authorizeRequests()
			.antMatchers("/").authenticated()
			.anyRequest().permitAll()
			.and()
			.csrf().disable();
	}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("select username, password, true from User where username=?")
			.authoritiesByUsernameQuery("select username, 'ROLE_USER' from User where username=?");
	}
	
	@Bean
	public SpringSecurityDialect securityDialect() {
	    return new SpringSecurityDialect();
	}
}
