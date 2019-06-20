package com.boot.todo.config;

import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.boot.todo.model.User;
import com.boot.todo.service.TodoService;
import com.boot.todo.util.PasswordEncoder;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	TodoService todoService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		if (isUserAuthenticated(name,password)) {
			HashSet<GrantedAuthority> authorities=todoService.getRoles(name);
			return new UsernamePasswordAuthenticationToken(name, password, authorities);
		} else {
			throw new BadCredentialsException("Authentication Failed");
		}
	}

	

	private boolean isUserAuthenticated(String name, String password) {
		User userInfo = todoService.findUser(name);
		if(userInfo!=null)
		{
			return PasswordEncoder.isPasswordValid(password, userInfo.getPassword());
			
		}
		return false;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
