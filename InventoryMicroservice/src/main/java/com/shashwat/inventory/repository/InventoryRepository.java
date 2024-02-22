package com.shashwat.inventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shashwat.inventory.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Integer>{
	
	List<Inventory> findByProductIdIn(List<String> productIdList);

}
