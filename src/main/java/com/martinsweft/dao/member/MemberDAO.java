package com.martinsweft.dao.member;

import java.util.List;

import com.martinsweft.dao.JpaDAO;
import com.martinsweft.domain.search.SearchHolder;
import com.martinsweft.domain.user.Member;
import com.martinsweft.domain.user.MemberRelated;
import com.martinsweft.domain.user.MemberRelation;
import com.mweft.domain.exception.MultipleUsersExistForUserName;

public interface MemberDAO extends JpaDAO<Long, Member> {
//	void saveOrUpdateMember(Member member);
	Member getMemberByUserName(final String userName) throws MultipleUsersExistForUserName;
//	Member getMember(final Long id);
//	void deleteMember(final Long id);
//	
	void createOrUpdateMemberRelation(final MemberRelation relation);
//
//	void addMemberGrantedAuthority(MemberGrantedAuthority authority);
//	Set<MemberGrantedAuthority> loadUserAuthorities(Long userId);
//	
	List<MemberRelated> getAllAcceptedFriends(final Long fromId);
//	void deleteMemberRelation(final Long fromId, final Long toId);
	SearchHolder getMemberFriendList(final SearchHolder holder);
	MemberRelation getMemberRelation(Long fromId, Long toId);
	void getMembersByKeyword(final SearchHolder holder);
	/**
	 * Returns member by email address or an empty member. Never returns null
	 * @param email
	 * @return
	 */
	Member getMemberByEmail(final String email);
	void disableMember(Long id);
	
}
