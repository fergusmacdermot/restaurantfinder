//package com.martinsweft.web.controller.registration;
//
//import java.net.UnknownHostException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Service;
//
//import com.mweft.common.connection.rest.RestConnection;
//import com.mweft.domain.security.Member;
//import com.mweft.web.rest.RestURI;
//import com.sun.jersey.api.client.WebResource;
//
//@Service("registrationBusinessRestConnector")
//public class RegistrationBusinessRestConnector implements RegistrationBusiness {
//    /*
//     * logger
//     */
//	private static final Logger log = Logger.getLogger("RegistrationBusinessRestConnector");
//	
//	@Resource
//	private RestConnection restConnection;
//	@Override
//	public Member createMember(Member member) {
//		 // got through here
//		WebResource resource = null;
//		try {
//			resource = restConnection.getWebResourceForLocal(RestURI.REGISTRATION_URI+RestURI.REGISTRATION_REST_CONTROLLER);
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		log.log(Level.FINE, "****************************************************"+resource.getURI());
//
//
//	//	ClientResponse s = resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, member);
//		return member;
//	}
//
//}
