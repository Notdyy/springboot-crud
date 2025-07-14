package com.example.crud.models;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import org.springframework.http.HttpStatusCode;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class ResponseStatus implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private HttpStatusCode status;
	private String message;
	private String code;
	private List<String> description;

}
