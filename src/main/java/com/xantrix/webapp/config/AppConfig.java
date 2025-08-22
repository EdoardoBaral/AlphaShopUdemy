package com.xantrix.webapp.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration("application")
@Getter
@Setter
public class AppConfig {
	
	private String listino;
	private String lingua;
}
