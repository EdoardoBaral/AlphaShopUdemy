package com.xantrix.webapp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
@RequiredArgsConstructor
public class MessageConfig {
	
	private final AppConfig configuration;
	
	@Bean(name = "validator")
	LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());

		return bean;
	}
	
	@Bean
	MessageSource messageSource() {
		ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
		resource.setBasename("messages");
		resource.setUseCodeAsDefaultMessage(true);

		return resource;
	}

    @Bean
    SessionLocaleResolver localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        //sessionLocaleResolver.setDefaultLocale(LocaleContextHolder.getLocale());
        sessionLocaleResolver.setDefaultLocale(new Locale(configuration.getLingua()));

        return sessionLocaleResolver;
    }
}
