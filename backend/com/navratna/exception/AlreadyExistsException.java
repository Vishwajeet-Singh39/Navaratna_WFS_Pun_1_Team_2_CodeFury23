package com.navratna.exception;

public class AlreadyExistsException extends Exception{
	public AlreadyExistsException() {
		super();
	}
	public AlreadyExistsException(Throwable cause) {
		super(cause);
	}
	public AlreadyExistsException(String msg) {
		super(msg);
	}
	public AlreadyExistsException(String msg,Throwable cause) {
		super(msg,cause);
	}
}
