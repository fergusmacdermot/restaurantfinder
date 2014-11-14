package com.martinsweft.web.controller.member;

import java.util.List;

import org.springframework.stereotype.Service;

import com.martinsweft.domain.search.SearchHolder;
import com.martinsweft.domain.user.Member;
import com.martinsweft.domain.user.MemberRelated;
import com.martinsweft.domain.user.MemberRelation;

@Service("memberBusinessRestConnector")
public class MemberBusinessRestConnector implements MemberBusiness {
//    /*
//     * logger
//     */
//	private static final Logger log = Logger.getLogger("AccountBusinessRestConnector");
//	
//	@Resource
//	private RestConnection restConnection;



	public Member getMemberById(Long id) {
//		resource = buildWebResource("/resources/members/member/"+id);
//		s = resource.header("username", authentication.getName()).header("password", obtainPasswordFromSession(session)).type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
//		Member member = s.getEntity(Member.class);
		return null; 
	}


	public Member getMemberByEmail(String email) {
//	    String url = "/resources/members/member/search/"+emailMessage.getToEmail();
//	    Object response = restConnection.doGet(getRestAuthenticationCredentials(authentication, session), url, Member.class);	
//	    log.debug(response);
		return null;
	}

//	@Override
//	public void suggestService(EmailMessage emailMessage) {
//		// TODO Auto-generated method stub
////		StringBuilder sb = new StringBuilder();
////		sb.append("/resources/members/member/relations/suggest/");
////		sb.append(member.getId());
////
////		response = restConnection.doPost(getRestAuthenticationCredentials(authentication, session), sb.toString(), String.class, emailMessage);
////		if (log.isDebugEnabled())
////		{
////			log.debug("response:"+response);
////		}
////		if (response instanceof ErrorResponse)
////		{
////			log.debug("error response");
////			ErrorResponse errorResponse = (ErrorResponse)response;
////			int errorCode = errorResponse.getErrorCode();
////			model.addAttribute("error", errorCode);
////			return "account/inviteFriend";
////		}	
//	}

	public void updateMemberRelation(MemberRelation memberRelation) {
//		// TODO Auto-generated method stub
//		WebResource resource = restConnection.getWebResourceForLocal("/resources/members/member/relations/"+fromId+"/"+relationType+"/"+toId);
////		if (log.isDebugEnabled())
////		{
////			log.debug("Attempt to call rest service:"+resource.getURI());
////		}
//		Member member = (Member)authentication.getPrincipal();
//		ClientResponse s = resource.header("username", authentication.getName()).header("password", obtainPasswordFromSession(session)).type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
//		URI location = s.getLocation();
	}


	public void findMemberByKeyword(SearchHolder holder) {
//		if (null == holder.getKeyword() || holder.getKeyword().length() < 1)
//		{
//			session.setAttribute("keywordError", Errors.KEYWORD_NOT_PROVIDED);
//			return "redirect:/web/account/"+member.getId();
//		}
//		String uri = RestURI.MEMBER_SEARCH+holder.getKeyword()+"/"+holder.getCurrentPage().intValue()+"/"+SystemValues.RESULTS_PER_PAGE;
//		//WebResource resource = restConnection.getWebResourceForLocal(RestURI.MEMBER_SEARCH+holder.getKeyword()+"/"+holder.getCurrentPage().intValue()+"/"+SystemValues.RESULTS_PER_PAGE);
//		holder = (SearchHolder)restConnection.doGet(getRestAuthenticationCredentials(authentication, session), uri, SearchHolder.class);	
////		if (log.isDebugEnabled())

	}


	public List<MemberRelated> getAllAcceptedFriends(Long fromId) {
		// TODO Auto-generated method stub
		return null;
	}
}
