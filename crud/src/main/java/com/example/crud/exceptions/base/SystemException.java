package com.example.crud.exceptions.base;

import java.io.Serial;

import com.example.crud.models.ResponseBase;
import com.example.crud.models.SystemMessage;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SystemException extends Exception {

	@Serial
	private static final long serialVersionUID = 1L;

	private final transient Object[] params;
	private final SystemMessage systemMessage;
	private final Throwable cause;
	private final ResponseBase response;

	public SystemException(final Object... params) {
		this.params = params;
		this.systemMessage = null;
		this.cause = null;
		this.response = this.initResponseStatus("ERR999", "Unknown error", params);
	}

	public SystemException(final SystemMessage systemMessage, final Object... params) {
		this.systemMessage = systemMessage;
		this.params = params;
		this.cause = null;
		this.response = this.initResponseStatus("ERR999", "Unknown error", params);
	}

	public SystemException(final Exception e, final Object... params) {
		this.params = params;
		this.systemMessage = null;
		this.cause = e.getCause();
		this.response = this.initResponseStatus("ERR999", "Unknown error", params);
	}

	public SystemException(final Throwable t, final Object... params) {
		this.params = params;
		this.systemMessage = null;
		this.cause = t;
		this.response = this.initResponseStatus("ERR999", "Unknown error", params);
	}
	
	private ResponseBase initResponseStatus(String code, String message, Object[] params) {
		return response;
	}
}