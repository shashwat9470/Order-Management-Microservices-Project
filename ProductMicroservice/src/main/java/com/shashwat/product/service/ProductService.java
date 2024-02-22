package com.shashwat.product.service;

import java.util.List;

import com.shashwat.product.dto.ProductRequest;
import com.shashwat.product.dto.ProductResponse;

public interface ProductService {

	ProductResponse createProduct(ProductRequest productRequest) throws Exception;
	
	List<ProductResponse> getAllProducts();
}
