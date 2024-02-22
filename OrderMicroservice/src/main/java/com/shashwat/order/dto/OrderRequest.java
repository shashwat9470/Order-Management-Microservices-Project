package com.shashwat.order.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {

	private String billingAddress;
	
	private String deliveryAddress;
	
	private List<OrderItemRequest> orderItemRequests;

}
