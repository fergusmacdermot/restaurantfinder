package com.mweft.domain.exception;

public class MemberRelationAlreadyExists extends ApplicationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3193370100261611512L;

	public MemberRelationAlreadyExists(ErrorResponse errorResponse) {
		super(errorResponse);
	}
}
