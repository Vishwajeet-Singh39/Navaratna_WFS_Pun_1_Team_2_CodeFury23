package com.navaratna.exception;

public class ValidationException extends Exception {
	public ValidationException() {
		super();
	}
	public ValidationException(Throwable cause) {
		super(cause);
	}
	public ValidationException(String msg) {
		super(msg);
	}
	public ValidationException(String msg,Throwable cause) {
		super(msg,cause);
	}
}
