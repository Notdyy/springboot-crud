package com.example.crud.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.crud.models.ProductResponse;
import com.example.crud.models.ResponseBase;

@Mapper(componentModel = "spring")
public interface ResponseMapper { // ✅ ต้องเป็น interface

	@Mapping(target = "id", ignore = true)
    @Mapping(target = "productName", ignore = true)
    @Mapping(target = "price", ignore = true)
    ProductResponse toProductResponse(ResponseBase base); // ✅ method interface ไม่ต้องมี body

}