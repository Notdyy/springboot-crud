package com.example.crud.controllers.base;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.crud.models.ResponseBase;
import com.example.crud.utils.LocaleUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@RestControllerAdvice
public class ApiBase<T extends Serializable> {
	
	private Class<T> clazz;
	
	public ResponseEntity<Void> ok() {
        return ResponseEntity.ok().build();
    }
	
	public ResponseEntity<Void> build(HttpStatus status) {
        return ResponseEntity.status(status).build();
    }
	
	public ResponseEntity<T> buildEntity(Object o, HttpStatus status) {
		return ResponseEntity.status(status).body(this.clazz.cast(o));
	}
	
	public ResponseEntity<?> messageError(final Exception e) {
	    ResponseBase responseBase = new ResponseBase(e, LocaleUtils.getLocaleThTH());

	    return ResponseEntity
	            .status(HttpStatus.BAD_REQUEST)
	            .body(responseBase);
	}
}