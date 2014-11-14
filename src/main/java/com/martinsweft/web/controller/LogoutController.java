package com.martinsweft.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/loggedout")
public class LogoutController extends BaseController {
    /*
     * logger
     */
//	private static final Logger log = Logger.getLogger(LogoutController.class);

	
	@RequestMapping(method=RequestMethod.GET)
	public String logout() {
		
		return "redirect:/web/login";
	}
//	
//	@RequestMapping(value="/list/{fromId}")
//	public String getFriendsList(SearchHolder holder, Model model, @PathVariable(value="fromId") final String fromId, HttpSession session) throws UnknownHostException {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		WebResource resource = buildWebResource("/resources/members/member/relations/related/"+Status.ACCEPTED.getValue()+"/"+fromId+"/"+holder.getCurrentPage().intValue()+"/"+SystemValues.RESULTS_PER_PAGE);
//		//WebResource resource = restConnection.getWebResourceForLocal(RestURI.MEMBER_SEARCH+holder.getKeyword()+"/"+holder.getCurrentPage().intValue()+"/"+SystemValues.RESULTS_PER_PAGE);
//		if (log.isDebugEnabled())
//		{
//			log.debug("Attempt to call rest service:"+resource.getURI());
//		}
//		Member member = (Member)authentication.getPrincipal();
//		ClientResponse s = resource.header("username", authentication.getName()).header("password", obtainPasswordFromSession(session)).type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
//		holder = s.getEntity(SearchHolder.class);	
//		model.addAttribute("holder", holder);
//		if (log.isDebugEnabled())
//		{
//			log.debug("member:"+member);
//		}		
//		model.addAttribute("member", member);
//		return "account/userFriendList";
//	}
//	
//	@RequestMapping(value="/{relationType}/{fromId}/{toId}", method=RequestMethod.GET)
//	public String createOrUpdateMemberRelation(@PathVariable(value="relationType") final String relationType, 
//			@PathVariable(value="fromId") final String fromId,
//			@PathVariable(value="toId") final String toId, Model model, HttpSession session) throws UnknownHostException {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		WebResource resource = restConnection.getWebResourceForLocal("/resources/members/member/relations/"+fromId+"/"+relationType+"/"+toId);
//		if (log.isDebugEnabled())
//		{
//			log.debug("Attempt to call rest service:"+resource.getURI());
//		}
//		Member member = (Member)authentication.getPrincipal();
//		ClientResponse s = resource.header("username", authentication.getName()).header("password", obtainPasswordFromSession(session)).type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
//		URI location = s.getLocation();
//		return "redirect:/web/account/"+member.getId();
//	}	
//	/**
//	 * Prepares the form for the invite.
//	 * @param model
//	 * @param session
//	 * @return
//	 * @throws UnknownHostException
//	 */
//	@RequestMapping(value="/invite", method=RequestMethod.GET)
//	public String inviteFriend(Model model) {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		Member member = (Member)authentication.getPrincipal();
//		model.addAttribute("member", member);
//		EmailMessage emailMessage = new EmailMessage(); 
//        model.addAttribute("emailMessage", emailMessage);
//        emailMessage.setSubject("See my restaurants");
//        emailMessage.setMessage("Follow the link below to see my restaurants");
//		return "account/inviteFriend";
//	}	
//	
//	/**
//	 * Validates the email inpout, checks there are not existing members, then sends email across.
//	 * @param model
//	 * @param session
//	 * @return
//	 * @throws UnknownHostException
//	 */
//	@RequestMapping(value="/invite", method=RequestMethod.POST)
//	public String inviteFriend(@Valid EmailMessage emailMessage, BindingResult result, Model model, HttpSession session) throws UnknownHostException {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		Member member = (Member)authentication.getPrincipal();
//		model.addAttribute("member", member);
//        model.addAttribute("emailMessage", emailMessage);	
//        
//		if (result.hasErrors()) {
//			return "account/inviteFriend";
//		}		
//		// check to see if the user exists
//
//		WebResource resource = restConnection.getWebResourceForLocal("/resources/members/member/search/"+emailMessage.getToEmail());
//		if (log.isDebugEnabled())
//		{
//			log.debug("Attempt to call rest service:"+resource.getURI());
//		}
//		//Member member = (Member)authentication.getPrincipal();
//		ClientResponse s = resource.header("username", authentication.getName()).header("password", obtainPasswordFromSession(session)).type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
//		Member foundMember = s.getEntity(Member.class);
//		// if member populated return to page as they have to go that way.
//		if (log.isDebugEnabled())
//		{
//			log.debug("found:"+foundMember);
//		}	
//		if (null != foundMember.getId())
//		{
//			model.addAttribute("foundMember", foundMember);
//			return "account/inviteFriend";
//		}
//		// we've got past here so send the email
//		resource = restConnection.getWebResourceForLocal("/resources/members/member/relations/suggest/"+member.getId());
//		if (log.isDebugEnabled())
//		{
//			log.debug("Attempt to call rest service:"+resource.getURI());
//		}
//		s = resource.header("username", authentication.getName()).header("password", obtainPasswordFromSession(session)).type(MediaType.APPLICATION_JSON).post(ClientResponse.class, emailMessage);
//		
//		return "redirect:/web/account/"+member.getId();
//	}		
//	
//	private void addLoggedInMemberToModel(Model model)
//	{
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		Member member = (Member)authentication.getPrincipal();
//		model.addAttribute("member", member);
//	}
	
}
