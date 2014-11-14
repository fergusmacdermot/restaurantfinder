package com.mweft.domain.exception;

public class TooManyEmailRemindersException extends ApplicationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3193370100261611512L;

	public TooManyEmailRemindersException(ErrorResponse errorResponse) {

		super(errorResponse);
	}
}
