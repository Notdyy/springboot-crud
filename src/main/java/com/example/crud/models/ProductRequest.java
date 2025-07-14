package com.example.crud.models;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductRequest implements Serializable{
	
	@Serial
	private static final long serialVersionUID = 1L;

	private String id;
	private String productName;
	private String price;

}
