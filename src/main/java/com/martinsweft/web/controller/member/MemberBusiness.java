package com.martinsweft.web.controller.member;

import java.util.List;

import com.martinsweft.domain.search.SearchHolder;
import com.martinsweft.domain.user.Member;
import com.martinsweft.domain.user.MemberRelated;
import com.martinsweft.domain.user.MemberRelation;
import com.mweft.domain.exception.MemberRelationAlreadyExists;
import com.mweft.domain.exception.MessageSendException;
import com.mweft.domain.exception.TooManyRemindersException;
import com.mweft.domain.exception.TooSoonToReminderException;

/**
 * Interface for the member connectors. Allows swapping in and out of rest or business
 * service
 * @author fergusmacdermot
 *
 */
public interface MemberBusiness {
	Member getMemberById(final Long id);
	Member getMemberByEmail(final String email);
//	void suggestService(final EmailMessage emailMessage);
	
	void updateMemberRelation(MemberRelation memberRelation) throws MemberRelationAlreadyExists, MessageSendException, TooManyRemindersException, TooSoonToReminderException;
	
	/**
	 * Find members by keyword supplied
	 * @param holder
	 * @return
	 */
	void findMemberByKeyword(SearchHolder holder);
	
	List<MemberRelated> getAllAcceptedFriends(final Long fromId);
}
