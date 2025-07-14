package com.example.crud.exceptions;

import java.io.Serial;

import com.example.crud.exceptions.base.SystemException;

public class ProductException extends SystemException {

	@Serial
	private static final long serialVersionUID = 1L;
	
	public ProductException(Object... params) {
		super(params);
	}

}
