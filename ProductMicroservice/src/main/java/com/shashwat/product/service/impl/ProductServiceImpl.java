package com.shashwat.product.service.impl;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shashwat.product.dto.InventoryRequest;
import com.shashwat.product.dto.ProductRequest;
import com.shashwat.product.dto.ProductResponse;
import com.shashwat.product.feignclient.InventoryClient;
import com.shashwat.product.model.Product;
import com.shashwat.product.repository.ProductRepository;
import com.shashwat.product.service.ProductService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor															//this will help in constructor injection
public class ProductServiceImpl implements ProductService{
	
	private final ProductRepository productRepository;
	private final InventoryClient inventoryClient;
	private final ModelMapper modelMapper;

	@Transactional
	@Override
	public ProductResponse createProduct(ProductRequest productRequest) throws Exception {
		String id = UUID.randomUUID().toString();

		Product product = Product.builder()
				.productId(id)
				.productName(productRequest.getProductName())
				.productDescription(productRequest.getProductDescription())
				.price(productRequest.getPrice())
				.build();
		
		Product savedProduct = this.productRepository.save(product);
		
		InventoryRequest inventoryRequest = InventoryRequest.builder()
		.productId(savedProduct.getProductId())
		.quantity(productRequest.getQuantity())
		.build();
		
		HttpStatusCode invnetoryStatusCode = this.inventoryClient.addToInventory(inventoryRequest).getStatusCode();
		
		if(!invnetoryStatusCode.equals(HttpStatus.CREATED)) throw new Exception("Inventory service not responding"); 
		
		ProductResponse productResponse = this.modelMapper.map(savedProduct, ProductResponse.class);
		
		return productResponse;
	}

	@Override
	public List<ProductResponse> getAllProducts() {
		List<Product> allProducts = this.productRepository.findAll();
		List<ProductResponse> list = allProducts.stream().map(product -> (this.modelMapper.map(product, ProductResponse.class))).toList();
		return list;
	}

}
