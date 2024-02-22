package com.shashwat.order.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {

	private String orderNumber;
	
	private LocalDateTime createdAt;
	
	private String billingAddress;
	
	private String deliveryAddress;
	
	//private float orderValue;
	
	private List<OrderItemResponse> orderItems;
}
