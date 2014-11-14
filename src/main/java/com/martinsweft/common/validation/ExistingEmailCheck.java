package com.martinsweft.common.validation;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Service;

import com.martinsweft.business.member.MemberService;
import com.martinsweft.domain.user.Member;
@Service
public class ExistingEmailCheck implements ConstraintValidator<ValidateExistingEmail, String> {

	@Resource
	private MemberService memberService;

	public void initialize(ValidateExistingEmail constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isValid(String value, ConstraintValidatorContext context) {
		System.out.println("checking::::::::::::::::: email"+value);
		Member member = memberService.getMemberByEmail(value);
		System.out.println("member:::::::::::::::::"+member);
		if (null != member && null != member.getUsername())
		{
			return false;
		}
		return true;
	}
	
//	@Override
//	public void initialize(ValidateExistingEmail arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
//		
//		System.out.println("checking:::::::::::::::::"+arg0);
//		Member member = memberService.getMemberByEmail(arg0);
//		System.out.println("member:::::::::::::::::"+member);
//		if (null != member && null != member.getUsername())
//		{
//			return false;
//		}
//		return true;
//	}


}
