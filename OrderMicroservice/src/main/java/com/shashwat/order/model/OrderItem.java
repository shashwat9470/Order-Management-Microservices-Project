package com.shashwat.order.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "Order_items")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "item_number")
	private int itemNumber;
	
	@Column(name = "product_id")
	private String productId;
	
	private int quantity;
	/*
	@Column(name = "purchase_value")
	private int purchaseValue;
	*/
	@ManyToOne
	@JoinColumn(name = "order_number", nullable = false)
	private Order order;
	
}
