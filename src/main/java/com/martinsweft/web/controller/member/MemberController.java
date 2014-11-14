package com.martinsweft.web.controller.member;

//import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.martinsweft.common.SystemValues;
import com.martinsweft.common.util.SessionUtil;
import com.martinsweft.domain.search.SearchHolder;
import com.martinsweft.domain.user.Member;
import com.martinsweft.domain.user.MemberRelation;
import com.martinsweft.domain.user.Status;
import com.martinsweft.web.controller.BaseController;
import com.martinsweft.web.controller.account.AccountControllerDelegate;
import com.mweft.domain.exception.Errors;
import com.mweft.domain.exception.MemberRelationAlreadyExists;
import com.mweft.domain.exception.MessageSendException;
import com.mweft.domain.exception.TooManyEmailRemindersException;
import com.mweft.domain.exception.TooManyRemindersException;
import com.mweft.domain.exception.TooSoonToRemindRestException;
import com.mweft.domain.exception.TooSoonToReminderException;

@Controller
@RequestMapping("/member")
public class MemberController extends BaseController {
	/*
	 * logger
	 */
	private static final Logger log = Logger.getLogger("MemberController");

	@Resource
	private AccountControllerDelegate accountControllerDelegate;
	@Resource
	private MemberControllerDelegate memberControllerDelegate;

	@RequestMapping(value = "/search")
	public String searchByKeyword(SearchHolder holder, Model model,
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

	@RequestMapping(value = "/list/{fromId}")
	public String getFriendsList(SearchHolder holder, Model model,
			@PathVariable(value = "fromId") final Long fromId,
			HttpSession session) {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		Member authenticatedMember = (Member) authentication.getPrincipal();
		model.addAttribute("authenticatedMember", authenticatedMember);
		// get auth list of friends so can change link on page
		if (null == authenticatedMember.getAcceptedFriends()) {
			authenticatedMember.setAcceptedFriends(memberControllerDelegate
					.getAllAcceptedFriends(authenticatedMember.getMemberId()));
		}
		Member member = accountControllerDelegate.getMemberById(fromId);
		// authenticatedMember.setAcceptedFriends(memberControllerDelegate.getAllAcceptedFriends(authenticatedMember.getId()));
		model.addAttribute("member", member);
		holder.setResultsPerPage(Float.valueOf(SystemValues.RESULTS_PER_PAGE));
		holder.setCurrentPage(Float.valueOf(0));
		// holder.setKeyword(status);
		Map<String, Object> searchTerms = new HashMap<String, Object>();
		searchTerms.put("id", fromId.longValue());
		searchTerms.put("status", Status.ACCEPTED.getValue());
		holder.setSearchTerms(searchTerms);

		holder = accountControllerDelegate.getUserFriendsByStatus(holder);
		model.addAttribute("holder", holder);
		// if (log.isDebugEnabled())
		// {
		// log.debug("member:"+member);
		// }

		return "account/userFriendList";
	}

	@RequestMapping(value = "/{relationType}/{fromId}/{toId}", method = RequestMethod.GET)
	public String createOrUpdateMemberRelation(
			@PathVariable(value = "relationType") final String relationType,
			@PathVariable(value = "fromId") final Long fromId,
			@PathVariable(value = "toId") final Long toId, Model model,
			HttpSession session) {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		Member authenticatedMember = (Member) authentication.getPrincipal();
		MemberRelation relation = new MemberRelation();
		relation.setStatus(Status.getStatus(relationType));
		relation.setFromId(fromId);
		relation.setIntitiatorId(fromId);
		relation.setToId(toId);
		try {
			memberControllerDelegate.updateMemberRelation(relation);
		} catch (MemberRelationAlreadyExists e) {
			SessionUtil.saveErrorMessageToSession(session, e.getMessage());
			return "redirect:/web/account/" + authenticatedMember.getMemberId();
		} catch (MessageSendException e) {
			SessionUtil.saveErrorMessageToSession(session, e.getMessage());
			return "redirect:/web/account/" + authenticatedMember.getMemberId();
		} catch (TooManyRemindersException e) {
			SessionUtil.saveErrorMessageToSession(session, e.getErrorResponse()
					.getMessage());
			return "redirect:/web/account/" + authenticatedMember.getMemberId();
		} catch (TooSoonToReminderException e) {
			SessionUtil.saveErrorMessageToSession(session, e.getErrorResponse()
					.getMessage());
			return "redirect:/web/account/" + authenticatedMember.getMemberId();
		}
		return "redirect:/web/account/" + authenticatedMember.getMemberId();
	}

	// /**
	// * Prepares the form for the invite.
	// * @param model
	// * @param session
	// * @return
	// * @throws UnknownHostException
	// */
	// @RequestMapping(value="/invite", method=RequestMethod.GET)
	// public String inviteFriend(Model model) {
	// Authentication authentication =
	// SecurityContextHolder.getContext().getAuthentication();
	// Member member = (Member)authentication.getPrincipal();
	// model.addAttribute("member", member);
	// EmailMessage emailMessage = new EmailMessage();
	// model.addAttribute("emailMessage", emailMessage);
	// emailMessage.setSubject("See my restaurants");
	// emailMessage.setMessage("Follow the link below to see my restaurants");
	// return "account/inviteFriend";
	// }

	// /**
	// * Validates the email inpout, checks there are not existing members, then
	// sends email across.
	// * @param model
	// * @param session
	// * @return
	// * @throws UnknownHostException
	// */
	// @RequestMapping(value="/invite", method=RequestMethod.POST)
	// public String inviteFriend(@Valid EmailMessage emailMessage,
	// BindingResult result, Model model, HttpSession session,
	// HttpServletRequest request) throws UnknownHostException {
	// Authentication authentication =
	// SecurityContextHolder.getContext().getAuthentication();
	// Member member = (Member)authentication.getPrincipal();
	// model.addAttribute("member", member);
	// model.addAttribute("emailMessage", emailMessage);
	// if (null == emailMessage.getToEmail())
	// validateSingleProperty(emailMessage, "toEmail");
	// if (result.hasErrors()) {
	// return "account/inviteFriend";
	// }
	// // check to see if the user exists
	// Member foundMember =
	// memberControllerDelegate.getMemberByEmail(emailMessage.getToEmail());
	// if (null != foundMember && null != foundMember.getId())
	// {
	// model.addAttribute("foundMember", foundMember);
	// return "account/inviteFriend";
	// }
	// try
	// {
	// memberControllerDelegate.suggestService(emailMessage);
	// }
	// catch (TooManyEmailRemindersException e)
	// {
	// log.log(Level.INFO, e.getErrorResponse().getMessage());
	// String message = e.getErrorResponse().getMessage();
	// model.addAttribute("error", message);
	// return "account/inviteFriend";
	// }
	// catch (TooSoonToRemindRestException e)
	// {
	// log.log(Level.INFO, e.getErrorResponse().getMessage());
	// String message = e.getErrorResponse().getMessage();
	// model.addAttribute("error", message);
	// return "account/inviteFriend";
	// }
	// return "redirect:/web/account/"+member.getId();
	// }

	private void addLoggedInMemberToModel(Model model) {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		Member member = (Member) authentication.getPrincipal();
		model.addAttribute("member", member);
	}

}
