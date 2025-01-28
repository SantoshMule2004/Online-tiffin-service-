package com.homeybites.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

	@Bean
	protected ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
