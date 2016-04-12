package com.course.selection.exception;

public class TeacherNotExistException extends Exception {

	private static final long serialVersionUID = 9074657175161852506L;

	public TeacherNotExistException() {
	}

	public TeacherNotExistException(String message) {
		super(message);
	}

	public TeacherNotExistException(Throwable cause) {
		super(cause);
	}

	public TeacherNotExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public TeacherNotExistException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
