package com.br.hotelCalifornia.infraestructure.exceptions.handle;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public class ErrorResponse {
	
	
	public ErrorResponse(String message, LocalDateTime data, int status, String path) {
		super();
		this.message = message;
		this.data = data;
		this.status = status;
		this.path = path;
	}
	private String message;
	private LocalDateTime data;
	private int status;
	private String path;
}
