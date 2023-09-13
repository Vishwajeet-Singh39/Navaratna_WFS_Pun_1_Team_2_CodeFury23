package com.navaratna.exception;

public class IncorrectDetailsException extends Exception {
	public IncorrectDetailsException(String err) {
		super(""+err);
	}
}
