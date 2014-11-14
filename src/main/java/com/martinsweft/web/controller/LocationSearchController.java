package com.martinsweft.web.controller;

//import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.martinsweft.common.SystemValues;
import com.martinsweft.domain.Location;
import com.martinsweft.domain.search.SearchHolder;
import com.martinsweft.domain.user.Member;
import com.martinsweft.web.controller.account.AccountControllerDelegate;
import com.martinsweft.web.controller.member.MemberControllerDelegate;
import com.mweft.domain.exception.Errors;

@Controller
@RequestMapping("/location")
public class LocationSearchController extends BaseController {
	/*
	 * logger
	 */
	private static final Logger log = Logger.getLogger("MemberController");

	@Resource
	private AccountControllerDelegate accountControllerDelegate;
	@Resource
	private MemberControllerDelegate memberControllerDelegate;

	@RequestMapping(value = "/search")
	public List<Location> searchByKeyword(SearchHolder holder, Model model,
			HttpSession session) {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		Member authenticatedMember = (Member) authentication.getPrincipal();
		if (null == authenticatedMember.getAcceptedFriends())
		{
			authenticatedMember.setAcceptedFriends(memberControllerDelegate.getAllAcceptedFriends(authenticatedMember.getMemberId()));
		}
		if (null == holder.getKeyword() || holder.getKeyword().length() < 1)
		{
			session.setAttribute("keywordError", Errors.KEYWORD_NOT_PROVIDED);
			return "redirect:/web/account/"+authenticatedMember.getMemberId();
		}

		holder.setResultsPerPage(Float.valueOf(SystemValues.RESULTS_PER_PAGE));
		// holder.setCurrentPage(Float.valueOf(0));
		// holder.setKeyword(keyword);
		Map<String, Object> searchTerms = new HashMap<String, Object>();
		searchTerms.put("username", holder.getKeyword());
		holder.setSearchTerms(searchTerms);
		memberControllerDelegate.findMemberByKeyword(holder);
		model.addAttribute("holder", holder);
		model.addAttribute("authenticatedMember", authenticatedMember);
		return "account/userSearchResults";
	}
}
