package com.mweft.domain.exception;

public class TooManyRemindersException extends ApplicationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3193370100261611512L;

	public TooManyRemindersException(ErrorResponse errorResponse) {
		super(errorResponse);
	}

}
