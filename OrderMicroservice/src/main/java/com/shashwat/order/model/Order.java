package com.shashwat.order.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "orders")
public class Order {

	@Id
	@Column(name = "order_number")
	private String orderNumber;
	
	@Column(name = "order_date_time")
	private LocalDateTime createdAt;
	
	@Column(name = "billing_address")
	private String billingAddress;

	@Column(name = "delivery_address")
	private String deliveryAddress;
	/*
	@Column(name = "payable_amount")
	private float orderValue;
	*/
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
	private List<OrderItem> orderItems;
}
