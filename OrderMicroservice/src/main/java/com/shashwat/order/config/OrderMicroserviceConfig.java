package com.shashwat.order.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderMicroserviceConfig {

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
