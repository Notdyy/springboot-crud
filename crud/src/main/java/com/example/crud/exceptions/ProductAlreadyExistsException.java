package com.example.crud.exceptions;

import java.io.Serial;

import com.example.crud.exceptions.base.ApiException;

/**
 * Exception สำหรับกรณีที่มีการพยายามสร้าง Product ที่มีอยู่แล้วในระบบ
 */
public class ProductAlreadyExistsException extends ApiException {

	@Serial
	private static final long serialVersionUID = 1L;

	public ProductAlreadyExistsException(String args0) {
		super(args0);
	}

}
