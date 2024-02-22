package com.shashwat.order.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shashwat.order.dto.InventoryRequest;
import com.shashwat.order.dto.InventoryResponse;
import com.shashwat.order.dto.OrderItemRequest;
import com.shashwat.order.dto.OrderRequest;
import com.shashwat.order.dto.OrderResponse;
import com.shashwat.order.dto.UpdateInventoryRequest;
import com.shashwat.order.feignclient.InventoryClient;
import com.shashwat.order.model.Order;
import com.shashwat.order.model.OrderItem;
import com.shashwat.order.repository.OrderRepository;
import com.shashwat.order.service.OrderService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService{
	
	private final OrderRepository orderRepository;
	
	private final InventoryClient inventoryClient;
	
	private final ModelMapper modelMapper;

	@Transactional
	@Override
	public OrderResponse createOrder(OrderRequest orderRequest) throws Exception {
		
		String orderNumber = UUID.randomUUID().toString();
		
		Order order = Order.builder()
				.orderNumber(orderNumber)
				.createdAt(LocalDateTime.now())
				.billingAddress(orderRequest.getBillingAddress())
				.deliveryAddress(orderRequest.getDeliveryAddress())
				.build();
		
		List<OrderItemRequest> orderItemRequests = orderRequest.getOrderItemRequests();
		
		List<String> productIdList = orderItemRequests.stream().map(orderItemRequest -> orderItemRequest.getProductId()).toList();	
		
		List<InventoryResponse> stockAvailability = this.inventoryClient.getStockAvailability(productIdList).getBody();
		
		List<OrderItem> list = this.getOrderItemList(orderItemRequests, stockAvailability, order);
		
		if(list.size() < orderItemRequests.size()) throw new Exception("Products are not in stock, please try again later");
		
		order.setOrderItems(list);
		
		List<InventoryRequest> updateInventoryList = orderItemRequests.stream().map(orderItemRequest -> this.modelMapper.map(orderItemRequest, InventoryRequest.class)).toList();
		
		UpdateInventoryRequest updateInventoryRequest = UpdateInventoryRequest.builder()
		.inventoryRequests(updateInventoryList)
		.build();
		
		this.inventoryClient.updateInventory(updateInventoryRequest);
		
		Order savedOrder = this.orderRepository.save(order);
		
		OrderResponse OrderResponse = this.modelMapper.map(savedOrder, OrderResponse.class);
		
		return OrderResponse;
	}
	
	private List<OrderItem> getOrderItemList(List<OrderItemRequest> orderItemRequests, List<InventoryResponse> inventoryResponses, Order order) {
		List<OrderItem> orderItems = new ArrayList<>();
		for(OrderItemRequest item : orderItemRequests) {
			for(InventoryResponse inventoryResponse : inventoryResponses) {
				if((item.getProductId().equals(inventoryResponse.getProductId())) && item.getQuantity() <= inventoryResponse.getQuantity()) {
					OrderItem orderItem = OrderItem.builder()
							.productId(item.getProductId())
							.quantity(item.getQuantity())
							.order(order)
							.build();
					orderItems.add(orderItem);
				}
			}
		}
		return orderItems;
	}

	@Override
	public List<OrderResponse> getAllOrders() {
		List<OrderResponse> orderResponses = this.orderRepository.findAll().stream().map(order -> this.modelMapper.map(order, OrderResponse.class)).toList();
		return orderResponses;
	}

}
