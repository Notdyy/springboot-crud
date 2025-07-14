package com.example.crud.exceptions.base;

import java.io.Serial;

import com.example.crud.models.SystemMessage;

public class CrudError extends SystemMessage{

	@Serial
	private static final long serialVersionUID = 1L;
	
	public static final CrudError PRODUCT_EXCPTION = new CrudError("com.example.crud.exceptions.ProductException");
	
	public CrudError(String code) {
        super(code);
    }

    public static CrudError valueOf(String str) {
        return new CrudError(str);
    }

}
