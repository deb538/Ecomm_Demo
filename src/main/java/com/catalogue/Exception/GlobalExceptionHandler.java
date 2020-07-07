package com.catalogue.Exception;

import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.catalogue.Model.ControllerInputOutput;

@ControllerAdvice
public class GlobalExceptionHandler<T> {

	@ExceptionHandler(value = { CustomRunTimeException.class })
	public ResponseEntity<Object> handleCustomException(RuntimeException ex){
		return new ResponseEntity<Object>(this.buildOutputEntity(ex), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = { NoContentException.class })
	public ResponseEntity<Object> handleInvalidContent(RuntimeException ex){
		return new ResponseEntity<Object>(this.buildOutputEntity(ex), HttpStatus.NO_CONTENT);
	}
	
	@ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleConflict(RuntimeException ex) {
		return new ResponseEntity<Object>(this.buildOutputEntity(ex), HttpStatus.CONFLICT);
    }
	
	@ExceptionHandler({SQLException.class, DataAccessException.class})
    public ResponseEntity<Object> databaseError(RuntimeException ex) {
		return new ResponseEntity<Object>(this.buildOutputEntity(ex), HttpStatus.FORBIDDEN);
    }
	
	@ExceptionHandler(value = { RuntimeException.class })
	public ResponseEntity<Object> handleAllException(RuntimeException ex){
		return new ResponseEntity<Object>(this.buildOutputEntity(ex), HttpStatus.FORBIDDEN);
	}
	
	private ControllerInputOutput buildOutputEntity(RuntimeException ex) {
		return new ControllerInputOutput.ControllerBuilder()
				.message(ex.getMessage())
				.build();
	}
}
