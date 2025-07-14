package com.example.crud.exceptions.base;

import java.io.Serial;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ApiException extends Exception{

	@Serial
	private static final long serialVersionUID = 1L;
	
	private final transient Object[] params;
	private final Throwable cause;

	public ApiException(final Object... params) {
		this.params = params;
		this.cause = null;
	}

	public ApiException(final Exception e, final Object... params) {
		this.params = params;
		this.cause = e.getCause();
	}

	public ApiException(final Throwable t, final Object... params) {
		this.params = params;
		this.cause = t;
	}
	

}
