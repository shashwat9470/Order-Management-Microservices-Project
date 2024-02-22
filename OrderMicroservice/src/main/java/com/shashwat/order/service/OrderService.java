package com.shashwat.order.service;

import java.util.List;

import com.shashwat.order.dto.OrderRequest;
import com.shashwat.order.dto.OrderResponse;

public interface OrderService {
	
	OrderResponse createOrder(OrderRequest orderRequest) throws Exception;
	
	List<OrderResponse> getAllOrders();

}
