package com.example.crud.exceptions.base;

import java.io.Serial;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class CrudException extends SystemException{

	@Serial
	private static final long serialVersionUID = 1L;
	
	private final CrudError crudError;
	private final transient Object[] params;
	
	public CrudException(CrudError crudError, Object... param){
		this.crudError = crudError;
		this.params = param;
	}

}
