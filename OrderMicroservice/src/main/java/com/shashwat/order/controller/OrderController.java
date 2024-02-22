package com.shashwat.order.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shashwat.order.dto.OrderRequest;
import com.shashwat.order.dto.OrderResponse;
import com.shashwat.order.dto.OrderResponseMessage;
import com.shashwat.order.service.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1")
@AllArgsConstructor
public class OrderController {

	private final OrderService orderService;
	
	@PostMapping(path = "/orders")
	@CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
	public ResponseEntity<OrderResponseMessage> createOrder(@RequestBody OrderRequest orderRequest) throws Exception{
		OrderResponse createdOrder = this.orderService.createOrder(orderRequest);
		OrderResponseMessage orderResponseMessage = OrderResponseMessage.builder()
																			.orderStatus("Order placed successfully!")
																			.orderResponse(createdOrder)
																			.build();
		return ResponseEntity.status(HttpStatus.CREATED).body(orderResponseMessage);
	}
	
	@GetMapping(path = "/orders")
	public ResponseEntity<List<OrderResponse>> getAllOrders(){
		List<OrderResponse> allOrders = this.orderService.getAllOrders();
		return ResponseEntity.status(HttpStatus.OK).body(allOrders);
	}
	
	private ResponseEntity<OrderResponseMessage> fallbackMethod(OrderRequest orderRequest, Exception ex){
		OrderResponseMessage orderResponseMessage = OrderResponseMessage.builder()
																			.orderStatus("OOPS!! Something went wrong, please try again later.")
																			.orderResponse(null)
																			.build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(orderResponseMessage);
	}
	
}
