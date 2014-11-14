package com.mweft.domain.exception;

public class MemberNotFoundException extends ApplicationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3193370100261611515L;

	public MemberNotFoundException(ErrorResponse errorResponse) {

		super(errorResponse);
	}
}
