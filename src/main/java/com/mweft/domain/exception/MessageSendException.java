package com.mweft.domain.exception;

public class MessageSendException extends ApplicationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3193370100261611512L;

	public MessageSendException(ErrorResponse errorResponse) {

		super(errorResponse);
	}
}
