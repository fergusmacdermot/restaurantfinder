package com.mweft.domain.exception;

public class UserNameExistsException extends ApplicationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3193370100261611515L;

	public UserNameExistsException(ErrorResponse errorResponse) {

		super(errorResponse);
	}
}
