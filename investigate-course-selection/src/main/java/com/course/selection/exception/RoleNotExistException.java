package com.course.selection.exception;

public class RoleNotExistException extends Exception {

	private static final long serialVersionUID = 2799254614682529761L;

	public RoleNotExistException() {
	}

	public RoleNotExistException(String message) {
		super(message);
	}

	public RoleNotExistException(Throwable cause) {
		super(cause);
	}

	public RoleNotExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public RoleNotExistException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
