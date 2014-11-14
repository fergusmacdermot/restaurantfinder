package com.martinsweft.web.controller.account;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.martinsweft.common.SystemValues;
import com.martinsweft.common.util.SessionUtil;
import com.martinsweft.dao.location.ProximityDAO;
import com.martinsweft.domain.search.SearchHolder;
import com.martinsweft.domain.user.Member;
import com.martinsweft.domain.user.Status;
import com.martinsweft.web.controller.BaseController;
import com.mweft.domain.exception.Errors;

@Controller
@RequestMapping("/account")
public class AccountController extends BaseController {
	

	
    /*
     * logger
     */
	private static final Logger LOG = Logger.getLogger(AccountController.class);

//	@Resource RestaurantFinderForWebApp restaurantFinder;
	
	@Resource
	private AccountControllerDelegate accountControllerDelegate;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
//	 @PreAuthorize("#id == authentication.name)")
	public String prepareHome(@PathVariable Long id, Model model, HttpSession session) {
		LOG.debug("****************************************************AccountController prepareHome, id:"+id+", method:"+RequestMethod.GET);
		Member authenticatedMember = (Member)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		LOG.debug("****************************************************AccountController authenticatedMember, :"+authenticatedMember);
		model.addAttribute("authenticatedMember", authenticatedMember);
		// dont do these searches if authenticated user is different
		 // get the users friends and relations in pending status
		if (id.longValue() == authenticatedMember.getMemberId())
		{
			SearchHolder searchHolder = new SearchHolder();
			searchHolder.setResultsPerPage(Float.valueOf(SystemValues.RESULTS_PER_PAGE));
			searchHolder.setCurrentPage(Float.valueOf(0));
			//holder.setKeyword(status);
			Map<String, Object> searchTerms = new HashMap<String, Object>();
			searchTerms.put("id", id); 
			searchTerms.put("status", Status.PENDING_RESPONSE.getValue());
			searchHolder.setSearchTerms(searchTerms);
			 
			searchHolder = accountControllerDelegate.getUserFriendsByStatus(searchHolder);

			model.addAttribute("holder", searchHolder);
			
			// now get the list of requests made to me
			//SearchHolder holderRequested = new SearchHolder();
			//holderRequested.setResultsPerPage(Float.valueOf(SystemValues.RESULTS_PER_PAGE));
			//holderRequested.setCurrentPage(Float.valueOf(0));
			searchTerms = new HashMap<String, Object>();
			searchTerms.put("id", id);
			searchTerms.put("status", Status.REQUESTED.getValue());
			//holderRequested.setSearchTerms(searchTerms);		
			//holderRequested = accountControllerDelegate.getUserFriendsByStatus(holderRequested);
			//model.addAttribute("holderRequested", holderRequested);
			// will have two sets of results in by status
			model.addAttribute("holder", searchHolder);
		}
	
		// get the member detail to display on home page
		Member member = accountControllerDelegate.getMemberById(id);
		model.addAttribute("member", member);
		// see if we have an error
		if (null != session.getAttribute("keywordError"))
		{
			Errors message = (Errors)session.getAttribute("keywordError");
			String errorMess = loadMessage(message.getCode(), null);
			model.addAttribute("keywordError", errorMess);
			session.removeAttribute("keywordError");
		}
		if (null != SessionUtil.obtainErrorMessageFromSession(session))
		{
			model.addAttribute(SessionUtil.ERROR_MESSAGE, SessionUtil.obtainErrorMessageFromSession(session));
			SessionUtil.removeErrorMessageFromSession(session);
		}
		return "account/home";
	}
	 
	@RequestMapping(value="/collect", method = RequestMethod.GET)
	public String create() throws MalformedURLException, IOException, InterruptedException {
//		restaurantFinder.runScaper();
		return "redirect:/done";
	}
	
	@Resource ProximityDAO proximityDAO;
	
	@RequestMapping(value="/search", method = RequestMethod.GET)
	public String search() throws MalformedURLException, IOException, InterruptedException {
		
//		double distance = 0;
//		List<Establishment> results = proximityDAO.proximitySearch(51.48571, -0.1738, distance);
//		System.out.println("results 1:"+results.size()+", distance:"+distance);
//		distance = 500;
//		results = proximitySearchDAO.proximitySearch(51.48571, -0.1738, distance);
//		System.out.println("results 2:"+results.size()+", distance:"+distance);	
//		distance = 1000;
//		results = proximitySearchDAO.proximitySearch(51.48571, -0.1738, distance);
//		System.out.println("results 3:"+results.size()+", distance:"+distance);	
//		distance = 2000;
//		results = proximitySearchDAO.proximitySearch(51.48571, -0.1738, distance);
//		System.out.println("results 4:"+results.size()+", distance:"+distance);		
//		distance = 5000;
//		results = proximitySearchDAO.proximitySearch(51.48571, -0.1738, distance);
//		System.out.println("results 5:"+results.size()+", distance:"+distance);		
//		distance = 10000;
//		results = proximitySearchDAO.proximitySearch(51.48571, -0.1738, distance);
//		System.out.println("results 6:"+results.size()+", distance:"+distance);	

		return "redirect:/done";
	}


}
