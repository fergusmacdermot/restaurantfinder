package com.martinsweft.web.controller.account;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.martinsweft.domain.search.SearchHolder;
import com.martinsweft.domain.user.Member;

@Service
public class AccountControllerDelegate {
	
	@Resource(name="accountBusinessConnector")  private AccountBusiness accountBusiness;
	

	public SearchHolder getUserFriendsByStatus(SearchHolder searchHolder)
	{
		return accountBusiness.getMemberFriendList(searchHolder);
	}
	
	public Member getMemberById(final Long id)
	{
		return accountBusiness.getMemberById(id);
	}

}
