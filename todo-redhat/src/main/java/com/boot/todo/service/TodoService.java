package com.boot.todo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.boot.todo.exception.DuplicateUserException;
import com.boot.todo.model.Role;
import com.boot.todo.model.User;
import com.boot.todo.repository.RoleRepository;
import com.boot.todo.repository.UserRepository;

@Service
public class TodoService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository rolesRepository;

	public User findUser(String name) {
		return userRepository.findByUsername(name);

	}

	public HashSet<GrantedAuthority> getRoles(String name) {
		ArrayList<Role> roles = rolesRepository.findByUsername(name);
		HashSet<GrantedAuthority> authorities = new HashSet<GrantedAuthority>(roles.size());

		for (Role role : roles)
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole()));
		return authorities;
	}

	public User saveUserDetails(User user) throws DuplicateUserException {
		User userByUserName = userRepository.findByUsername(user.getUsername());
		if (userByUserName != null) {
			throw new DuplicateUserException("User already exists");
		}
		User savedUser = userRepository.save(user);
		if (savedUser.getRole().equalsIgnoreCase("ADMIN")) {
			rolesRepository.save(new Role().setRole("USER").setUsername(user.getUsername()).build());
			rolesRepository.save(new Role().setRole("ADMIN").setUsername(user.getUsername()).build());
		}
		else
		{
			rolesRepository.save(new Role().setRole("USER").setUsername(user.getUsername()).build());
		}

		return savedUser;
	}

	public List<User> getAllUsers(String role) {
		if(role.equals("Admin"))
		{
			return userRepository.findAll();
		}
		else
		{
			return userRepository.findByRole("User");
		}
		
	}

	public void deleteUser(String userName) {
		userRepository.delete(userRepository.findByUsername(userName));
		rolesRepository.deleteAll(rolesRepository.findByUsername(userName));
		
	}

	public void updateDetails(User user) throws DuplicateUserException {
		try{
			
			User userOld = userRepository.findById(user.getId()).get();
			User findByUsername = userRepository.findByUsername(user.getUsername());
			if(findByUsername!=null && findByUsername.getId()!=userOld.getId())
			{
				throw new DuplicateUserException("User already exists.");
			}
			if(userOld.getRole().equalsIgnoreCase("Admin")&&user.getRole().contentEquals("User"))
			{
				List<Role> userDelete = rolesRepository.findByUsernameAndRole(user.getUsername(),"ADMIN");
				rolesRepository.deleteAll(userDelete);
			}
		user.setPassword(userOld.getPassword());
		userRepository.save(user);
		}
		catch(Exception e) {
			throw new DuplicateUserException(e.getMessage());
		}
	}
		

}
