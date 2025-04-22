package com.xantrix.webapp.controllers;

import org.springframework.stereotype.Component;

@Component
public class AuthenticationService {
	
	public boolean auth(String username, String password) {
		boolean isValidUserName = username.equalsIgnoreCase("Edoardo");
		boolean isValidPassword = password.equalsIgnoreCase("password");
		
		return isValidUserName && isValidPassword;
	}
}