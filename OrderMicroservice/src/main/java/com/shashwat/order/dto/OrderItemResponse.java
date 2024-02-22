package com.shashwat.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemResponse {

	private int itemNumber;
	
	private String productId;
	
	private int quantity;
	
	//private int purchaseValue;
	
}
