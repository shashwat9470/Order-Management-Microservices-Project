package com.shashwat.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {

	private String productId;
	
	private String productName;
	
	private String productDescription;
	
	private float price;
	
}
