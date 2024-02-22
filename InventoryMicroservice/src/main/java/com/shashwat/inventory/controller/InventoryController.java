package com.shashwat.inventory.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.shashwat.inventory.dto.InventoryRequest;
import com.shashwat.inventory.dto.InventoryResponse;
import com.shashwat.inventory.dto.UpdateInventoryRequest;
import com.shashwat.inventory.service.InventoryService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1")
@AllArgsConstructor
public class InventoryController {

	private final InventoryService inventoryService;
	
	@PostMapping(path = "/inventory")
	public ResponseEntity<InventoryResponse> addToInventory(@RequestBody InventoryRequest inventoryRequest){
		InventoryResponse inventoryResponse = this.inventoryService.addToInventory(inventoryRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(inventoryResponse);
	}
	
	@GetMapping(path = "/inventory")
	public ResponseEntity<List<InventoryResponse>> getStockAvailability(@RequestParam List<String> productId){
		List<InventoryResponse> stockAvailability = this.inventoryService.getStockAvailability(productId);
		return ResponseEntity.status(HttpStatus.OK).body(stockAvailability);
	}
	
	@PutMapping(path = "/inventory")
	@ResponseStatus(code = HttpStatus.OK)
	public void updateInventory(@RequestBody UpdateInventoryRequest updateInventoryRequest) {
		this.inventoryService.updateInventory(updateInventoryRequest);
	}
}
