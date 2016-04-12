package com.course.selection.exception;

public class UserExistException extends Exception {

	private static final long serialVersionUID = -5422577931631467997L;

	public UserExistException() {
	}

	public UserExistException(String message) {
		super(message);
	}

	public UserExistException(Throwable cause) {
		super(cause);
	}

	public UserExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserExistException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
