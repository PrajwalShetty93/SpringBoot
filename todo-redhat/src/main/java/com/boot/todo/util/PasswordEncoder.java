package com.boot.todo.util;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
	static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  public static String encode(String input) {
		
		
		String hashedPassword = passwordEncoder.encode(input);

		System.out.println(hashedPassword);
		
		return hashedPassword;
	}
  
  public static boolean isPasswordValid(String inputPass,String hashedPassword)
  {
	  return passwordEncoder.matches(inputPass,hashedPassword);
  }

  }

