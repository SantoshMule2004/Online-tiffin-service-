package com.homeybites.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.homeybites.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	//handles user not found exception
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
		String message = ex.getMessage();
		ApiResponse response = new ApiResponse(message, false, null);
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	//handles data validation exceptions
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex)
	{
		Map<String, String> response = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName = ((FieldError)error).getField();
			String message = error.getDefaultMessage();
			response.put(fieldName, message);
		});
		return new ResponseEntity<Map<String,String>>(response, HttpStatus.BAD_REQUEST);
	}
	//handler for bad request method
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ApiResponse> HttpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException ex)
	{
		String message = ex.getMessage();
		ApiResponse response = new ApiResponse(message, false, null);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	//handler for resource not found
	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<ApiResponse> NoResourceFoundExceptionHandler(NoResourceFoundException ex)
	{
		String message = ex.getMessage();
		ApiResponse response = new ApiResponse(message, false, null);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);
	}
	
	//handler for wrong credentials
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ApiResponse> BadCredentialsExceptionHandler(BadCredentialsException ex)
	{
		String message = ex.getMessage();
		ApiResponse response = new ApiResponse(message, false, null);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.UNAUTHORIZED);
	}
}

