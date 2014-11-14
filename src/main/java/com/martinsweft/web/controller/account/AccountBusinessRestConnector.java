//package com.martinsweft.web.controller.account;
//
//import org.springframework.stereotype.Service;
//
//import com.martinsweft.domain.search.SearchHolder;
//import com.martinsweft.domain.user.Member;
//
//@Service("accountBusinessRestConnector")
//public class AccountBusinessRestConnector implements AccountBusiness {
////    /*
////     * logger
////     */
////	private static final Logger log = Logger.getLogger("AccountBusinessRestConnector");
////	
////	@Resource
////	private RestConnection restConnection;
//
//	@Override
//	public SearchHolder getUserFriendsByStatus(SearchHolder seachHolder) {
////		WebResource resource = buildWebResource("/resources/members/member/relations/related/"+Status.PENDING.getValue()+"/"+id+"/0/"+SystemValues.RESULTS_PER_PAGE);
////		
//////		if (log.isDebugEnabled())
//////		{
//////			log.debug("Attempt to call rest service:"+resource.getURI()+", uname"+authentication.getName());
//////		}
////		
////		ClientResponse s = resource.header("username", authentication.getName()).header("password", obtainPasswordFromSession(session)).type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
////		SearchHolder holder = s.getEntity(SearchHolder.class);	
////		
////		model.addAttribute("holder", holder);
////		// now get the list of requests made to me
////		resource = buildWebResource("/resources/members/member/relations/related/"+Status.REQUESTED.getValue()+"/"+id+"/0/"+SystemValues.RESULTS_PER_PAGE);
//////		if (log.isDebugEnabled())
//////		{
//////			log.debug("Attempt to call rest service:"+resource.getURI()+", uname"+authentication.getName());
//////		}		
////		s = resource.header("username", authentication.getName()).header("password", obtainPasswordFromSession(session)).type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
////		SearchHolder holderRequested = s.getEntity(SearchHolder.class);	
////		
////		model.addAttribute("holderRequested", holderRequested);		
////		// get the member detail to display on home page
////		resource = buildWebResource("/resources/members/member/"+id);
////		s = resource.header("username", authentication.getName()).header("password", obtainPasswordFromSession(session)).type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
////		Member member = s.getEntity(Member.class);
//		return null;
//	}
//
//	@Override
//	public Member getMemberById(Long id) {
////		resource = buildWebResource("/resources/members/member/"+id);
////		s = resource.header("username", authentication.getName()).header("password", obtainPasswordFromSession(session)).type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
////		Member member = s.getEntity(Member.class);
//		return null;
//	}
//
//
//}
