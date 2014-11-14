package com.martinsweft.web.controller.registration;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.martinsweft.domain.user.Member;
import com.mweft.domain.exception.EmailExistsException;
import com.mweft.domain.exception.MultipleUsersExistForUserName;
import com.mweft.domain.exception.UserNameExistsException;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	
    /*
     * logger
     */
	private static final Logger LOG = Logger.getLogger("RegistrationController");
	@Resource
	private RegistrationControllerDelegate registrationControllerDelegate;
	@Autowired
	private AuthenticationManager authenticationManager;
	

	
	@RequestMapping(method = RequestMethod.GET)
	public String prepareRegister(Model model) throws UnknownHostException {
		LOG.debug("Incoming prepare register URI:"+InetAddress.getLocalHost().getHostAddress());	
		// has member come in as a results of invite?
		
		Member member = new Member(); // declaring

        model.addAttribute("member", member);
		return "registration/start";
	}
	 
	@RequestMapping(method=RequestMethod.POST)
	public String create(@Valid Member member, BindingResult errors, HttpServletRequest request) {
		if (errors.hasErrors()) {
			LOG.debug("errors:"+errors);
			return "registration/start";
		}
		LOG.debug("****************************************************Registration controller create");
		// need this for login later 
		String originalPassword = member.getPassword();
		//HttpSession session = request.getSession(true);
	//	PasswordUtil.savePasswordToSession(request, member.getPassword());
		try {
			registrationControllerDelegate.createMember(member);
		} catch (EmailExistsException e) {
			LOG.info("Email exists", e);
			errors.addError(new ObjectError("Email", "Email exists"));
			return "registration/start";
		} catch (UserNameExistsException e) {
			LOG.info("Username exists", e);
			errors.addError(new ObjectError("userName", "User name exists"));
			return "registration/start";
		} catch (MultipleUsersExistForUserName e) {
			LOG.info("Username exists", e);
			errors.addError(new ObjectError("userName", "User name exists"));
			return "registration/start";
		}
		// now log the user in to mvc env
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(member.getUsername(), originalPassword);
        if (LOG.isDebugEnabled())
        {
        	LOG.debug("**********************************Authenticate user:"+member.getUsername());
        }
        Authentication authentication = authenticationManager.authenticate(token);
        if (LOG.isDebugEnabled())
        {
        	LOG.debug("**********************************************auth:"+authentication);
        }        
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        // Create a new session and add the security context.
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
        
     //   SecurityContextHolder.getContext().setAuthentication(auth);  
		return "redirect:/web/account/"+member.getMemberId();
	}


}
