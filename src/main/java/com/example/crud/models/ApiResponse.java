package com.example.crud.models;

import java.io.Serial;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T extends Serializable> implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 1L;
	
	private T data;
	private ResponseStatus status;

}
