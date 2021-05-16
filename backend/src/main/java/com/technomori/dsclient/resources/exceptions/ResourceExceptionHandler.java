package com.technomori.dsclient.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.technomori.dsclient.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<StandardError> illegalArgumentException(IllegalArgumentException e,
			HttpServletRequest request) {
		return createStandardError(e, request, HttpStatus.BAD_REQUEST, "Illegal argument");
	}

	@ExceptionHandler(PropertyReferenceException.class)
	public ResponseEntity<StandardError> propertyReferenceException(PropertyReferenceException e,
			HttpServletRequest request) {
		return createStandardError(e, request, HttpStatus.BAD_REQUEST, "Property not found");
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFoundException(ResourceNotFoundException e,
			HttpServletRequest request) {
		return createStandardError(e, request, HttpStatus.NOT_FOUND, "Resource not found");
	}

	private ResponseEntity<StandardError> createStandardError(Exception e, HttpServletRequest request,
			HttpStatus status, String error) {
		StandardError err = new StandardError();
		err.setStatus(status.value());
		err.setError(error);
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(err.getStatus()).body(err);
	}

}