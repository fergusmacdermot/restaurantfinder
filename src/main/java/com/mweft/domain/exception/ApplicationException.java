package com.mweft.domain.exception;

public class ApplicationException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3193370100261611512L;

	public ApplicationException(ErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
	}

	private ErrorResponse errorResponse;

	public ErrorResponse getErrorResponse() {
		return errorResponse;
	}

	public void setErrorResponse(ErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
	}

}
