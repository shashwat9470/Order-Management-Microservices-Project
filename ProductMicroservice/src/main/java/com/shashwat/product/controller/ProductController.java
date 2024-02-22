package com.shashwat.product.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shashwat.product.dto.ProductRequest;
import com.shashwat.product.dto.ProductResponse;
import com.shashwat.product.service.ProductService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1")
@AllArgsConstructor
public class ProductController {

	private final ProductService productService;
	
	@PostMapping(path = "/products")
	public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) throws Exception{
		ProductResponse response = this.productService.createProduct(productRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping(path = "/products")
	public ResponseEntity<List<ProductResponse>> getAllProducts(){
		List<ProductResponse> allProducts = this.productService.getAllProducts();
		return ResponseEntity.status(HttpStatus.OK).body(allProducts);
	}
}
