package com.shashwat.order.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.shashwat.order.dto.InventoryResponse;
import com.shashwat.order.dto.UpdateInventoryRequest;

@FeignClient(name = "inventory-service")
public interface InventoryClient {

	@GetMapping(path = "/api/v1/inventory")
	public ResponseEntity<List<InventoryResponse>> getStockAvailability(@RequestParam List<String> productId);
	
	@PutMapping(path = "api/v1/inventory")
	@ResponseStatus(code = HttpStatus.OK)
	public void updateInventory(@RequestBody UpdateInventoryRequest updateInventoryRequest);
}
