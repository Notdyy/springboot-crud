package com.example.crud.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.crud.entities.Product;
import com.example.crud.exceptions.ProductAlreadyExistsException;
import com.example.crud.mapper.ProductMapper;
import com.example.crud.models.ProductRequest;
import com.example.crud.models.ProductResponse;
import com.example.crud.repository.ProductRepository;
import com.example.crud.service.ProductServiceable;

@Service
public class ProductService implements ProductServiceable {

	private final ProductRepository repository;

	private final ProductMapper productMapper;

	public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
		this.repository = productRepository;
		this.productMapper = productMapper;
	}

	@Override
	public List<ProductResponse> getAllProduct() {
		List<Product> products = this.repository.findAll();
		return products.stream().map(this.productMapper::toResponse).collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public ProductResponse getOneProduct(String id) {

		Product product = this.repository.findById(id).orElse(null);

		return product != null ? this.productMapper.toResponse(product) : null;
	}
	
	@Override
	public ProductResponse createProduct(ProductRequest product) throws ProductAlreadyExistsException {
	    Product entity = this.productMapper.toEntity(product);

	    // ถ้า id เป็น null แปลว่าเป็นของใหม่ ไม่ต้องเช็ค existsById
	    if (entity.getId() != null && this.repository.existsById(entity.getId())) {
	        throw new ProductAlreadyExistsException(entity.getId());
	    }

	    Product savedProduct = this.repository.save(entity);
	    return this.productMapper.toResponse(savedProduct);
	}

	
	@Override
	public ProductResponse updateProduct(ProductRequest product) {
		Product entity = this.productMapper.toEntity(product);
		boolean exists = this.repository.existsById(entity.getId());
		if (!exists) {
			throw new IllegalArgumentException("Product with ID " + entity.getId() + " does not exist.");
		}
		Product updatedProduct = this.repository.save(entity);
		return this.productMapper.toResponse(updatedProduct);
	}

	@Override
	public boolean deleteProduct(String id) {
		this.repository.deleteById(id);
		return !this.repository.existsById(id);
	}

}
