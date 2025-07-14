package com.example.crud.models;

import java.io.Serial;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "code")
public class SystemMessage implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
	
	private String code;

	
}
