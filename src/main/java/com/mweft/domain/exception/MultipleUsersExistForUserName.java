package com.mweft.domain.exception;

public class MultipleUsersExistForUserName extends ApplicationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3193370100261611514L;

	public MultipleUsersExistForUserName(ErrorResponse errorResponse) {

		super(errorResponse);
	}
}
