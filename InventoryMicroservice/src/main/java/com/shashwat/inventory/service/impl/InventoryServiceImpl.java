package com.shashwat.inventory.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shashwat.inventory.dto.InventoryRequest;
import com.shashwat.inventory.dto.InventoryResponse;
import com.shashwat.inventory.dto.UpdateInventoryRequest;
import com.shashwat.inventory.model.Inventory;
import com.shashwat.inventory.repository.InventoryRepository;
import com.shashwat.inventory.service.InventoryService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService{

	private final InventoryRepository inventoryRepository;
	
	private final ModelMapper modelMapper;
	
	@Override
	public InventoryResponse addToInventory(InventoryRequest inventoryRequest) {
		Inventory inventory = Inventory.builder()
				.productId(inventoryRequest.getProductId())
				.quantity(inventoryRequest.getQuantity())
				.build();
		
		Inventory savedInventory = this.inventoryRepository.save(inventory);
		return this.modelMapper.map(savedInventory, InventoryResponse.class);
	}

	@Transactional(readOnly = true)
	@Override
	public List<InventoryResponse> getStockAvailability(List<String> productIdList) {
		List<Inventory> list = this.inventoryRepository.findByProductIdIn(productIdList);
		List<InventoryResponse> inventoryResponses = list.stream().map(inventory -> this.modelMapper.map(inventory, InventoryResponse.class)).toList();
		return inventoryResponses;
	}

	@Transactional
	@Override
	public void updateInventory(UpdateInventoryRequest updateInventoryRequest) {
		List<InventoryRequest> inventoryRequests = updateInventoryRequest.getInventoryRequests();
		List<String> productIdList = inventoryRequests.stream().map(inventoryRequest -> inventoryRequest.getProductId()).toList(); 
		List<Inventory> inventories = this.inventoryRepository.findByProductIdIn(productIdList);
		for(Inventory inventory : inventories) {
			for(InventoryRequest inventoryRequest : inventoryRequests) {
				if(inventoryRequest.getProductId().equals(inventory.getProductId())) {
					inventory.setQuantity(inventory.getQuantity() - inventoryRequest.getQuantity());
				}
			}
		}
		
		this.inventoryRepository.saveAll(inventories);
	}

}
