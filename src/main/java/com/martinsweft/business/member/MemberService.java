package com.martinsweft.business.member;

import java.util.List;

import com.martinsweft.domain.search.SearchHolder;
import com.martinsweft.domain.user.Member;
import com.martinsweft.domain.user.MemberRelated;
import com.martinsweft.domain.user.MemberRelation;
import com.mweft.domain.exception.EmailExistsException;
import com.mweft.domain.exception.MemberRelationAlreadyExists;
import com.mweft.domain.exception.MessageSendException;
import com.mweft.domain.exception.MultipleUsersExistForUserName;
import com.mweft.domain.exception.TooManyRemindersException;
import com.mweft.domain.exception.TooSoonToReminderException;
import com.mweft.domain.exception.UserNameExistsException;

public interface MemberService {
	
// this is meaningless	void updateMember(final Member member) throws MultipleUsersExistForUserName, UserNameExistsException;
//	
	void createMember(final Member member) throws EmailExistsException, UserNameExistsException, MultipleUsersExistForUserName;
//	
	Member getMemberById(final Long id);
	Member getMemberByEmail(final String email);
	void disableMember(final Long id);
	Member getMemberByUserName(final String userName) throws MultipleUsersExistForUserName;
	void getMembersByKeyword(SearchHolder holder);
//	
//	Set<MemberGrantedAuthority> loadUserAuthorities(Long userId);
//	@PreAuthorize("#memberRelation.name == principal.id")
	void requestMemberRelation(final MemberRelation memberRelation) throws MemberRelationAlreadyExists, MessageSendException, TooManyRemindersException, TooSoonToReminderException;
//	@PreAuthorize("#fromId == principal.id")
	void acceptMemberRelation(final Long fromId, final Long toId);
//	@PreAuthorize("#initiatorId == principal.id")
//	void suggestMemberRelation(final Long fromId, final Long toId, final Long initiatorId);
//	@PreAuthorize("#fromId == principal.id")
//	void deleteMemberRelation(final Long fromId, final Long toId);
	SearchHolder getMemberFriendList(final SearchHolder holder);
//	@PreAuthorize("#fromId == principal.id")
	List<MemberRelated> getAllAcceptedFriends(final Long fromId);
}
