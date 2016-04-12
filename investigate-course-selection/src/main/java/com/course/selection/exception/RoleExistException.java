package com.course.selection.exception;

public class RoleExistException extends Exception {

	private static final long serialVersionUID = 2370164815414912645L;

	public RoleExistException() {
		super();
	}

	public RoleExistException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RoleExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public RoleExistException(String message) {
		super(message);
	}

	public RoleExistException(Throwable cause) {
		super(cause);
	}

}
