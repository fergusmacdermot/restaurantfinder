package com.martinsweft.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class PasswordUtil {
	
	public static void savePasswordToSession(HttpServletRequest request, String password)
	{
		request.getSession().setAttribute("password", password);
	}
	
	public static String obtainPasswordFromSession(HttpSession session)
	{
		return (String)session.getAttribute("password");
	}
	public static boolean isPasswordSaved(HttpServletRequest request)
	{
		return null != request.getSession().getAttribute("password");
	}

}
