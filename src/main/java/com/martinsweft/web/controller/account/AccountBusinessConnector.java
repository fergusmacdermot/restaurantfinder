package com.martinsweft.web.controller.account;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.martinsweft.business.member.MemberService;
import com.martinsweft.domain.search.SearchHolder;
import com.martinsweft.domain.user.Member;

@Service("accountBusinessConnector")
public class AccountBusinessConnector implements AccountBusiness {
	
    /*
     * logger
     */
	//private static final Logger log = Logger.getLogger("AccountBusinessConnector");
	
	@Resource
	private MemberService memberService;


	public SearchHolder getMemberFriendList(SearchHolder seachHolder) {
		return memberService.getMemberFriendList(seachHolder);
	}


	public Member getMemberById(Long id) {
		return memberService.getMemberById(id);
	}
	


}
