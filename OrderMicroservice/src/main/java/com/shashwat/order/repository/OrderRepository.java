package com.shashwat.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shashwat.order.model.Order;

public interface OrderRepository extends JpaRepository<Order, String>{

}
