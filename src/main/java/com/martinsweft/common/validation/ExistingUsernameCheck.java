package com.martinsweft.common.validation;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.martinsweft.business.member.MemberService;
import com.martinsweft.domain.user.Member;
import com.mweft.domain.exception.MultipleUsersExistForUserName;

public class ExistingUsernameCheck implements ConstraintValidator<ValidateExistingUserName, String> {

	@Resource
	private MemberService memberService;
	


	public void initialize(ValidateExistingUserName arg0) {
		// TODO Auto-generated method stub
		
	}


	public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
		
		System.out.println("checking:::::::::::::::::user name"+arg0);
		Member member;
		try {
			member = memberService.getMemberByUserName(arg0);
		} catch (MultipleUsersExistForUserName e) {
			return true;
		}
		System.out.println("member:::::::::::::::::"+member);
		if (null != member && null != member.getUsername())
		{
			return false;
		}
		return true;
	}

}
