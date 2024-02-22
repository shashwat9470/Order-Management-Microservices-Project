package com.shashwat.product.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductMicroserviceConfig {

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
