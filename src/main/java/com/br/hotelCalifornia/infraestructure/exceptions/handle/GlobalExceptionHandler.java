package com.br.hotelCalifornia.infraestructure.exceptions.handle;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.br.hotelCalifornia.infraestructure.exceptions.BusinessException;
import com.br.hotelCalifornia.infraestructure.exceptions.ConflictException;
import com.br.hotelCalifornia.infraestructure.exceptions.NotFoundException;
import com.br.hotelCalifornia.infraestructure.exceptions.UnprocessableEntityException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorResponse> handleBusinessResponseEntity(BusinessException BusEx, HttpServletRequest request){
		return response(BusEx.getMessage(), request, HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
	}
	
	@ExceptionHandler(ConflictException.class)
	public ResponseEntity<ErrorResponse> handleConflictResponseEntity(ConflictException ConEx, HttpServletRequest request){
		return response(ConEx.getMessage(), request, HttpStatus.CONFLICT, LocalDateTime.now());
	}
	
	@ExceptionHandler(UnprocessableEntityException.class)
	public ResponseEntity<ErrorResponse> handleUnprocessableResponseEntity(UnprocessableEntityException UnEx, HttpServletRequest request){
		return response(UnEx.getMessage(), request, HttpStatus.UNPROCESSABLE_ENTITY, LocalDateTime.now());
	}
	
	@ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException Nfex, HttpServletRequest request){
        return response(Nfex.getMessage(), request, HttpStatus.NOT_FOUND, LocalDateTime.now());
    }
	
	private ResponseEntity<ErrorResponse> response(final String message, final HttpServletRequest request,
		final HttpStatus status, LocalDateTime data){
		return ResponseEntity.status(status).body(new ErrorResponse(message, data, status.value(), request.getRequestURI()));
	}
	
}
