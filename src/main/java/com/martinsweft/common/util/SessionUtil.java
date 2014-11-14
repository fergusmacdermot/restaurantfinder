package com.martinsweft.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {
	
	public static final String ERROR_MESSAGE = "errorMessage";
	public static void saveErrorMessageToSession(HttpServletRequest request, String errorMessage)
	{
		request.getSession().setAttribute(ERROR_MESSAGE, errorMessage);
	}
	public static void saveErrorMessageToSession(HttpSession session, String errorMessage)
	{
		session.setAttribute(ERROR_MESSAGE, errorMessage);
	}
	public static String obtainErrorMessageFromSession(HttpSession session)
	{
		return (String)session.getAttribute(ERROR_MESSAGE);
	}
	public static void removeErrorMessageFromSession(HttpSession session)
	{
		session.removeAttribute(ERROR_MESSAGE);
	}

}
