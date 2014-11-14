package com.martinsweft.web.controller.account;

import com.martinsweft.domain.search.SearchHolder;
import com.martinsweft.domain.user.Member;

/**
 * Interface for the account connectors. Allows swapping in and out of rest or business
 * service
 * @author fergusmacdermot
 *
 */
public interface AccountBusiness {

	SearchHolder getMemberFriendList(SearchHolder seachHolder);
	Member getMemberById(final Long id);

}
