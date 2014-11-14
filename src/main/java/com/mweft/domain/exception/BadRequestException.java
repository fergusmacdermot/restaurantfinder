package com.mweft.domain.exception;

public class BadRequestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -445581102627904789L;

	public BadRequestException(String message) {
		super(message);
	}

}