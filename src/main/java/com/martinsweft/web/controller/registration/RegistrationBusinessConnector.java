package com.martinsweft.web.controller.registration;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.martinsweft.business.member.MemberService;
import com.martinsweft.domain.user.Member;
import com.mweft.domain.exception.EmailExistsException;
import com.mweft.domain.exception.MultipleUsersExistForUserName;
import com.mweft.domain.exception.UserNameExistsException;

@Service("registrationBusinessConnector")
public class RegistrationBusinessConnector implements RegistrationBusiness {
	
    /*
     * logger
     */
	private static final Logger log = Logger.getLogger("RegistrationBusinessConnector");
	
	@Resource
	private MemberService memberService;
	
	public Member createMember(Member member) throws EmailExistsException, UserNameExistsException, MultipleUsersExistForUserName {
		memberService.createMember(member);
		if (log.isLoggable(Level.FINE))
		{
			log.fine("Created member:"+member);
		}
		return member;
	}

}
