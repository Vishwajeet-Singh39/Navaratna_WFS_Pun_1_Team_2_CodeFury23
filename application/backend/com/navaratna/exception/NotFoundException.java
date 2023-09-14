package com.navaratna.exception;

public class NotFoundException extends Exception{
	public NotFoundException() {
		super();
	}
	public NotFoundException(Throwable cause) {
		super(cause);
	}
	public NotFoundException(String msg) {
		super(msg);
	}
	public NotFoundException(String msg,Throwable cause) {
		super(msg,cause);
	}
}
