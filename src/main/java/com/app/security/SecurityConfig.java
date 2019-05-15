package com.app.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//			.withUser("admin").password("{noop}pass").roles("ADMIN")
//			.and()
//			.withUser("manu").password("{noop}pass").roles("USER")
//		;
		
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(new BCryptPasswordEncoder());
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/noticia").hasAuthority("ADMIN")
				.antMatchers("/h2-console/**").permitAll()
				.antMatchers("/", "/**").permitAll()
			.and()
				.formLogin()
				.loginPage("/login")
			.and().csrf().ignoringAntMatchers("/h2-console/**")//don't apply CSRF protection to /h2-console
            .and().headers().frameOptions().sameOrigin();//allow use of frame to same origin urls
		;	
	}
}
