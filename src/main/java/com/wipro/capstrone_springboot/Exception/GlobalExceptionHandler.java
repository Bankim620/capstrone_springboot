package com.wipro.capstrone_springboot.Exception;


import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.server.ResponseStatusException;
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e){
		return new ResponseEntity<>("Request Body Needed to add",HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<?> handleRequestMethodNotFoundException(HttpRequestMethodNotSupportedException e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	/*
	 * @ExceptionHandler(java.lang.Exception.class) public ResponseEntity<?>
	 * handlejavalangException(java.lang.Exception e){ return new
	 * ResponseEntity<>("Employee Not Found",HttpStatus.NOT_FOUND); }
	 */
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException e){
		return new ResponseEntity<>("No Data present by The provided Id",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<?> handleNullPointerException(NullPointerException e){
		return new ResponseEntity<>("No Data Present",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<?> handleCustomerNotFoundException(CustomerNotFoundException e){
		return new ResponseEntity<>("No Customer Found",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(FundTransferException.class)
	public ResponseEntity<?> handleFundTransferException(FundTransferException e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<?> handleAccountNotFoundException(AccountNotFoundException e){
		return new ResponseEntity<>("Account Not Found By the Given Account Number",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AccountCreationException.class)
	public ResponseEntity<?> handleAccountCreationException(AccountCreationException e){
		return new ResponseEntity<>("Account Not Created",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	

}
