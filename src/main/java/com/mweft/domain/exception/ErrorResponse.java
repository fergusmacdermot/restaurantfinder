package com.mweft.domain.exception;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * List of error codes so the front end can map error codes to user friendly
 * messages. Note that rest should still send the correct http response code.
 * The {@link ErrorResponseCode} is an json message in the body of the response
 * to give the user more information.
 * 
 * @author fergusmacdermot
 * 
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "response")
public class ErrorResponse {
	public ErrorResponse() {

	}

	public ErrorResponse(Errors error, String message) {
		this.errorCode = error.getCode();
		this.message = message;
	}

	@XmlElement(name = "error-code")
	private int errorCode;
	@XmlElement(name = "error-message")
	private String message;

	public int getErrorCode() {
		return errorCode;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "ErrorResponse [errorCode=" + errorCode + ", message=" + message
				+ "]";
	}

}
