package com.martinsweft.web.controller.registration;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.martinsweft.domain.user.Member;
import com.mweft.domain.exception.EmailExistsException;
import com.mweft.domain.exception.MultipleUsersExistForUserName;
import com.mweft.domain.exception.UserNameExistsException;

@Service
public class RegistrationControllerDelegate {
	
	@Resource(name="registrationBusinessConnector")  private RegistrationBusiness registrationBusiness;
	
	public Member createMember(Member member) throws EmailExistsException, UserNameExistsException, MultipleUsersExistForUserName
	{
		return registrationBusiness.createMember(member);
	}

}
