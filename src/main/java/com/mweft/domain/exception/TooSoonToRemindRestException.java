package com.mweft.domain.exception;

public class TooSoonToRemindRestException extends ApplicationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3193370100261611512L;

	public TooSoonToRemindRestException(ErrorResponse errorResponse) {

		super(errorResponse);
	}
}
