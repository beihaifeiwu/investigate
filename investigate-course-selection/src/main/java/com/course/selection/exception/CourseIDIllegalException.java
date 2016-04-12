package com.course.selection.exception;

public class CourseIDIllegalException extends Exception {

	private static final long serialVersionUID = 765681411336639644L;

	public CourseIDIllegalException() {
	}

	public CourseIDIllegalException(String message) {
		super(message);
	}

	public CourseIDIllegalException(Throwable cause) {
		super(cause);
	}

	public CourseIDIllegalException(String message, Throwable cause) {
		super(message, cause);
	}

	public CourseIDIllegalException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
