package com.boot.todo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/welcome")
	 * .hasAnyRole("USER", "ADMIN") .antMatchers("/getEmployees").hasAnyRole("USER",
	 * "ADMIN").antMatchers("/addNewEmployee")
	 * .hasAnyRole("ADMIN").anyRequest().authenticated().and().formLogin().loginPage
	 * ("/login").permitAll() .and().logout().permitAll();
	 * 
	 * http.csrf().disable(); }
	 */

	@Autowired
	private CustomAuthenticationProvider authProvider;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/register").permitAll().antMatchers("/processRegistration").permitAll()
				.anyRequest().fullyAuthenticated().and().formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/welcome").and().logout()
				.permitAll();
		http.csrf().disable();
	}

	/*
	 * @Autowired public void configureGlobal(AuthenticationManagerBuilder
	 * authenticationMgr) throws Exception {
	 * authenticationMgr.inMemoryAuthentication().withUser("admin").password(
	 * "{noop}admin").authorities("ROLE_USER")
	 * .and().withUser("user").password("{noop}user").authorities("ROLE_USER",
	 * "ROLE_ADMIN"); }
	 */

}
