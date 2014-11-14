package com.martinsweft.web.controller.registration;

import com.martinsweft.domain.user.Member;
import com.mweft.domain.exception.EmailExistsException;
import com.mweft.domain.exception.MultipleUsersExistForUserName;
import com.mweft.domain.exception.UserNameExistsException;

/**
 * Interface for the registration connectors. Allows swapping in and out of rest or business
 * service
 * @author fergusmacdermot
 *
 */
public interface RegistrationBusiness {
	
	Member createMember(Member member) throws EmailExistsException, UserNameExistsException, MultipleUsersExistForUserName;

}
