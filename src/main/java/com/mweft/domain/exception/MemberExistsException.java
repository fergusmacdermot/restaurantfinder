package com.mweft.domain.exception;

public class MemberExistsException extends ApplicationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3193370100261611515L;

	public MemberExistsException(ErrorResponse errorResponse) {

		super(errorResponse);
	}
}
