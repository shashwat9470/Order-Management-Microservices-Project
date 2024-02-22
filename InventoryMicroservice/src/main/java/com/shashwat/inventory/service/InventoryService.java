package com.shashwat.inventory.service;

import java.util.List;

import com.shashwat.inventory.dto.InventoryRequest;
import com.shashwat.inventory.dto.InventoryResponse;
import com.shashwat.inventory.dto.UpdateInventoryRequest;

public interface InventoryService {

	InventoryResponse addToInventory(InventoryRequest inventoryRequest);
	
	List<InventoryResponse> getStockAvailability(List<String> productId);
	
	void updateInventory(UpdateInventoryRequest updateInventoryRequest);
}
