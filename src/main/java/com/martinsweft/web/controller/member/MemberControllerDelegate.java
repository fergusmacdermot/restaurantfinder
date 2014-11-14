package com.martinsweft.web.controller.member;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.martinsweft.domain.search.SearchHolder;
import com.martinsweft.domain.user.Member;
import com.martinsweft.domain.user.MemberRelated;
import com.martinsweft.domain.user.MemberRelation;
import com.mweft.domain.exception.MemberRelationAlreadyExists;
import com.mweft.domain.exception.MessageSendException;
import com.mweft.domain.exception.TooManyRemindersException;
import com.mweft.domain.exception.TooSoonToReminderException;

@Service
public class MemberControllerDelegate {
	
	@Resource(name="memberBusinessConnector")  private MemberBusiness memberBusiness;
	
	public Member getMemberById(final Long id)
	{
		return memberBusiness.getMemberById(id);
	}
	public Member getMemberByEmail(final String email)
	{
		return memberBusiness.getMemberByEmail(email);
	}
	
//	public void suggestService(final EmailMessage emailMessage)
//	{
//		memberBusiness.suggestService(emailMessage); 
//	}
	
	public void updateMemberRelation(MemberRelation memberRelation) throws MemberRelationAlreadyExists, MessageSendException, TooManyRemindersException, TooSoonToReminderException
	{
		memberBusiness.updateMemberRelation(memberRelation);
	}
	
	public void findMemberByKeyword(SearchHolder holder)
	{
		memberBusiness.findMemberByKeyword(holder);
	}
	
	public List<Long> getAllAcceptedFriends(final Long fromId)
	{
		List<Long> results = new ArrayList<Long>();
		for (MemberRelated rel: memberBusiness.getAllAcceptedFriends(fromId))
		{
			results.add(rel.getMemberId());
		}
		return results;
	}
}
