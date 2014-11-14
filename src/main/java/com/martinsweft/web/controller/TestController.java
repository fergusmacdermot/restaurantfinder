//package com.martinsweft.web.controller;
//
//import java.net.UnknownHostException;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import com.martinsweft.common.util.PasswordUtil;
//import com.martinsweft.domain.user.Member;
//
//@Controller
//@RequestMapping("testcontroller")
//public class TestController {
//	
//    /*
//     * logger
//     */
//	private static final Logger log = Logger.getLogger(TestController.class);
//	@Resource
//	private RestConnection restConnection;
//	@Autowired
//	private AuthenticationManager authenticationManager;
//	
//	
//	
//	@RequestMapping(method = RequestMethod.GET)
//	public String prepareRegister() {
//		
//	  RestAuthenticationCredentials credentials = new RestAuthenticationCredentials();
//	  credentials.setUsername("fergus211");
//	  credentials.setPassword("password");
//	  RestConnection connection = new RestConnection();
//	  String url = "/resources/members/member/search/fergusmacdermot@gmail.com";
//	  try {
//		  Client c = Client.create();
//		  WebResource webResource = c.resource("http://ourspot246.appspot.com/resources/tester/123");
//		  ClientResponse res = webResource.get(ClientResponse.class);
//		 log.error(res);
//		
//	  } 
//	  catch (Exception e)
//	  {
//		  log.error(e);
//		  try
//		  {
//			  log.error("can't user ourspot246, try 127");
//			  Client c = Client.create();
//			  WebResource webResource = c.resource("http://127.0.0.1/resources/tester/123");
//			  ClientResponse res = webResource.get(ClientResponse.class); 
//			  log.error(res);
//		  }
//		  catch (Exception e1)
//		  {
//			  log.error(e1);
//			  try
//			  {
//				  log.error("can't user 127, try localhost");
//				  Client c = Client.create();
//				  WebResource webResource = c.resource("http://localhost/resources/tester/123");
//				  ClientResponse res = webResource.get(ClientResponse.class); 
//				  log.error(res);
//			  }
//			  catch (Exception e2)
//			  {
//				  log.error(e2);
//			  } 
//		  } 
//	  }
//		// has member come in as a results of invite?
//		
//		return "test";
//	}
//	 
//	@RequestMapping(method=RequestMethod.POST)
//	public String create(@Valid Member member, BindingResult result, HttpServletRequest request) throws UnknownHostException {
//		if (result.hasErrors()) {
//			return "registration/start";
//		}
//		log.error("****************************************************");
//		 // got through here
//		WebResource resource = restConnection.getWebResourceForLocal(RestURI.REGISTRATION_URI+RestURI.REGISTRATION_REST_CONTROLLER);
//		
//		if (log.isDebugEnabled())
//		{
//			log.debug("Attempt to call rest service:"+resource.getURI());
//		}
//		// need this for login later
//		String originalPassword = member.getPassword();
//		PasswordUtil.savePasswordToSession(request, member.getPassword());
//
//		ClientResponse s = resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, member);
//		
//		//assertEquals(201, s.getStatus());
//		
//		Member response = s.getEntity(Member.class);	
//		if (log.isDebugEnabled())
//		{
//			log.debug("Created:"+response);
//		}
//		// now log the user in to mvc env
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(response.getUsername(), originalPassword);
//        log.debug("going to authentication:"+response.getUsername());
//      //  log.debug("going to authentication:"+response.getPassword());
//        
//        Authentication auth = authenticationManager.authenticate(token);
//        SecurityContextHolder.getContext().setAuthentication(auth);      
//		return "redirect:/web/account/"+response.getId();
//	}
//
//
//}
