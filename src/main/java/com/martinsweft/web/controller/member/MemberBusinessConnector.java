package com.martinsweft.web.controller.member;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.martinsweft.business.member.MemberService;
import com.martinsweft.domain.search.SearchHolder;
import com.martinsweft.domain.user.Member;
import com.martinsweft.domain.user.MemberRelated;
import com.martinsweft.domain.user.MemberRelation;
import com.martinsweft.domain.user.Status;
import com.mweft.domain.exception.MemberRelationAlreadyExists;
import com.mweft.domain.exception.MessageSendException;
import com.mweft.domain.exception.TooManyRemindersException;
import com.mweft.domain.exception.TooSoonToReminderException;

@Service("memberBusinessConnector")
public class MemberBusinessConnector implements MemberBusiness {
	
    /*
     * logger
     */
	//private static final Logger log = Logger.getLogger("AccountBusinessConnector");
	
	@Resource
	private MemberService memberService;
//	@Resource
//	private MemberInviteService memberInviteService;

	public Member getMemberById(Long id) {
		return memberService.getMemberById(id);
	}

	public Member getMemberByEmail(String email) {
		return memberService.getMemberByEmail(email);
	}
//	@Override
//	public void suggestService(EmailMessage emailMessage) {
//		memberInviteService.suggestService(emailMessage); 
//	}

	public void updateMemberRelation(MemberRelation memberRelation) throws MemberRelationAlreadyExists, MessageSendException, TooManyRemindersException, TooSoonToReminderException {
		if (memberRelation.getStatus() == Status.ACCEPTED)
		{
			memberService.acceptMemberRelation(memberRelation.getFromId(), memberRelation.getToId());
		}
		memberService.requestMemberRelation(memberRelation);		
	}

	public void findMemberByKeyword(SearchHolder holder) {
		memberService.getMembersByKeyword(holder);
	}
	public List<MemberRelated> getAllAcceptedFriends(Long fromId) {
		return memberService.getAllAcceptedFriends(fromId);
	}


}
