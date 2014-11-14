package com.martinsweft.web.controller;

import java.net.UnknownHostException;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.martinsweft.common.util.PasswordUtil;
import com.martinsweft.domain.IJSONObject;
import com.martinsweft.domain.user.Member;
import com.mweft.domain.exception.BadRequestException;
@Controller
public class BaseController {
	
	@Autowired
	private Validator validator;
	
	@Resource
	private ReloadableResourceBundleMessageSource messageSource;
	
//	@Resource
//	private RestConnection restConnection;
//	
//	protected WebResource buildWebResource(String url) throws UnknownHostException
//	{
//		return restConnection.getWebResourceForLocal(url);
//	}

	@RequestMapping(value="/home", method = RequestMethod.GET)
	public String home() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Member member = (Member)authentication.getPrincipal();
		return "redirect:/web/account/"+member.getMemberId();
	}
	
//	protected RestAuthenticationCredentials getRestAuthenticationCredentials(Authentication authentication, HttpSession session)
//	{
//		  RestAuthenticationCredentials credentials = new RestAuthenticationCredentials();
//		  credentials.setUsername(authentication.getName());
//		  credentials.setPassword(obtainPasswordFromSession(session));
//		  return credentials;
//	}
	/**
	 * 
	 * @param object
	 */
	protected final void validate(com.martinsweft.domain.IJSONObject object) {		
		Set<ConstraintViolation<com.martinsweft.domain.IJSONObject>> constraintViolations = validator.validate(object);
		if (constraintViolations.size()>0) {
			throw new BadRequestException(messageToString(constraintViolations));
		}	
	}	
	
	/**
	 * 
	 * @param object
	 */
	protected final void validateSingleProperty(com.martinsweft.domain.IJSONObject object, String propertyName) {		
		Set<ConstraintViolation<com.martinsweft.domain.IJSONObject>> constraintViolations = validator.validateProperty(object, propertyName);
		if (constraintViolations.size()>0) {
			throw new BadRequestException(messageToString(constraintViolations));
		}	
	}	
	/**
	 * Formats error messages in to a string
	 * @param constraintViolations
	 * @return
	 */
	private String messageToString(Set<ConstraintViolation<IJSONObject>> constraintViolations) {
		StringBuilder sb = new StringBuilder();
		
		for (ConstraintViolation<IJSONObject> viol: constraintViolations) {
			sb.append(viol.getMessage());
			if (constraintViolations.isEmpty()){
				break;
			}
			sb.append(",\n");
		}
		if(sb.length() > 2) {
			sb.setLength(sb.length()-2);
		}
		return sb.toString();
	}
	protected String loadMessage(int code, Object[] args)
	{
		return messageSource.getMessage(code+"", args, null);
	}
	protected String obtainPasswordFromSession(HttpSession session)
	{
		return PasswordUtil.obtainPasswordFromSession(session);
	}
}
