package com.martinsweft.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.BeforeTransaction;

@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/application-context.xml" })
public class BaseTest {
	@Autowired
    protected ApplicationContext applicationContext;
	
	protected ProviderManager authenticationManager;
	@BeforeTransaction
	public void onSetUp() throws Exception{
		authenticationManager = (ProviderManager)applicationContext.getBean("authenticationManager");	
	}

	
	protected void loginUser(String user, String password)
	{
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, password);   
	    Authentication auth = authenticationManager.authenticate(token);
	    SecurityContextHolder.getContext().setAuthentication(auth);       
	}
}
