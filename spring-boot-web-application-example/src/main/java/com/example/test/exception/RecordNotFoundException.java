package com.example.test.exception;


public class RecordNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public RecordNotFoundException(String message) {
		super(message);
		System.out.println("messgae recordnotfound exception :" + message);
	}

	
}
