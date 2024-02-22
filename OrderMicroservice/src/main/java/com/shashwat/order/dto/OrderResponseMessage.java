package com.shashwat.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseMessage {

	private String orderStatus;
	
	private OrderResponse orderResponse;
}
