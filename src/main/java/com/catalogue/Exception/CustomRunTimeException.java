package com.catalogue.Exception;

public class CustomRunTimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 public CustomRunTimeException(String s) { 
        super(s); 
	 }

	@Override
	public String getMessage() {
		return super.getMessage();
	}
	 
	 
}
