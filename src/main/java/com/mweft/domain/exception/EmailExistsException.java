package com.mweft.domain.exception;


public class EmailExistsException extends ApplicationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3193370100261611515L;



    public EmailExistsException(ErrorResponse errorResponse) {
 	  
    	super(errorResponse);
    }
}
