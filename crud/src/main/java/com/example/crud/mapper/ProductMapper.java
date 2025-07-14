package com.example.crud.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.crud.entities.Product;
import com.example.crud.models.ProductRequest;
import com.example.crud.models.ProductResponse;

@Mapper(componentModel = "spring")
public interface ProductMapper {

	@Mapping(source = "id", target = "id")
    @Mapping(source = "productName", target = "productName")
    @Mapping(source = "price", target = "price")
	ProductResponse toResponse(Product product);

	Product toEntity(ProductRequest product);

}
