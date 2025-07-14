package com.example.crud.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.controllers.base.ApiBase;
import com.example.crud.models.ProductRequest;
import com.example.crud.models.ProductResponse;
import com.example.crud.service.ProductServiceable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController extends ApiBase<ProductResponse> {

	private final ProductServiceable service;

	public ProductController(ProductServiceable productService) {
		this.setClazz(ProductResponse.class);
		this.service = productService;
	}

	@GetMapping("/list")
	public ResponseEntity<List<ProductResponse>> getAllProduct() {
		return ResponseEntity.status(HttpStatus.OK).body(this.service.getAllProduct());
	}

	@GetMapping("/list/{id}")
	public ResponseEntity<ProductResponse> getProductById(@PathVariable String id) {
		return this.buildEntity(this.service.getOneProduct(id), HttpStatus.OK);
	}

	@PostMapping(value = "/add")
	public ResponseEntity<?> createProduct(@RequestBody ProductRequest product) {
		ProductResponse response;
		try {
			response = this.service.createProduct(product);
			log.info("Product created successfully: {}", response);
			return this.buildEntity(response, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("Error creating product: {}", e.getMessage());
			return this.messageError(e);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequest product) {
		return ResponseEntity.status(HttpStatus.OK).body(this.service.updateProduct(product));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
		log.info("Attempting to delete product with ID: {}", id);
		boolean isDeleted = service.deleteProduct(id);
		log.info("Product deletion status for ID {}: {}", id, isDeleted);
		return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}

}
