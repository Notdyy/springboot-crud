package com.example.crud.service;

import java.util.List;

import com.example.crud.exceptions.ProductAlreadyExistsException;
import com.example.crud.models.ProductRequest;
import com.example.crud.models.ProductResponse;

public interface ProductServiceable {

	List<ProductResponse> getAllProduct();

	ProductResponse getOneProduct(String id);

	ProductResponse createProduct(ProductRequest product) throws ProductAlreadyExistsException;
	
	ProductResponse updateProduct(ProductRequest product);

	boolean deleteProduct(String id);

}
