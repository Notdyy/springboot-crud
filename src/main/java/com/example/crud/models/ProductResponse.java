package com.example.crud.models;

import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse implements Serializable{

	@Serial
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String productName;
	private String price;

}
