package com.mweft.domain.exception;

public class TooSoonToReminderException extends ApplicationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3193370100261611512L;

	public TooSoonToReminderException(ErrorResponse errorResponse) {
		super(errorResponse);
	}

}
