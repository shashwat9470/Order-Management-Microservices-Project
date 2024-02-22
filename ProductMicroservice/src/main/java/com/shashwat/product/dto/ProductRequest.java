package com.shashwat.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {

	private String productName;
	
	private String productDescription;
	
	private float price;
	
	private int quantity;
}
