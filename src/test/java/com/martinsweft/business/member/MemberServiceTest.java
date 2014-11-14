package com.martinsweft.business.member;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.martinsweft.common.SystemValues;
import com.martinsweft.domain.search.SearchHolder;
import com.martinsweft.domain.search.Searchable;
import com.martinsweft.domain.user.Member;
import com.martinsweft.domain.user.MemberGrantedAuthority;
import com.martinsweft.domain.user.MemberRelated;
import com.martinsweft.domain.user.MemberRelation;
import com.martinsweft.domain.user.Status;
import com.martinsweft.test.BaseTest;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class MemberServiceTest extends BaseTest{

	@Autowired
    private MemberService memberService;
	@Test
    public void testGetMemberByUsername() throws Exception
	{
		Member member = new Member();
		member.setActive(true);
		Random random = new Random();
		int i = random.nextInt();
		member.setEmail("makima"+i+"@gmail.com");
		member.setUsername("fergus"+i);
		member.setFirstname("fergus");
		member.setLastname("macd");
		member.setPassword(PASSWORD);
		MemberGrantedAuthority authority = new MemberGrantedAuthority();
		authority.setAuthority("ROLE_USER");
		member.addAuthority(authority);
		memberService.createMember(member);
		assertNotNull(member);
		assertNotNull(member.getMemberId());
		Member myMember = memberService.getMemberByUserName("fergus"+i);
		assertNotNull(myMember);
		assertNotNull(myMember.getAuthorities());
	}
	
    public void testGetMemberByKeyword() throws Exception
	{
		Member member = new Member();
		member.setActive(true);
		Random random = new Random();
		int i = random.nextInt();
		member.setEmail("makima"+i+"@gmail.com");
		member.setUsername("fergus"+i);
		member.setFirstname("fergus");
		member.setLastname("macd");
		member.setPassword(PASSWORD);
		MemberGrantedAuthority authority = new MemberGrantedAuthority();
		authority.setAuthority("ROLE_USER");
		member.addAuthority(authority);
		memberService.createMember(member);
		
		member = new Member();
		member.setActive(true);
		random = new Random();
		i = random.nextInt();
		member.setEmail("makima"+i+"@gmail.com");
		member.setUsername("fergus"+i);
		member.setFirstname("fergus");
		member.setLastname("macd");
		member.setPassword(PASSWORD);
		authority = new MemberGrantedAuthority();
		authority.setAuthority("ROLE_USER");
		member.addAuthority(authority);
		memberService.createMember(member);		
		
		SearchHolder holder = new SearchHolder();
		holder.setResultsPerPage(Float.valueOf(SystemValues.RESULTS_PER_PAGE));
		holder.setCurrentPage(Float.valueOf(0));
		Map<String, Object> searchTerms = new HashMap<String, Object>();
		String keyword = "fergus";
		searchTerms.put("username", keyword);
		holder.setSearchTerms(searchTerms);
		memberService.getMembersByKeyword(holder);
		Map<String, List<Searchable>> myResults = holder.getResults();
		assertNotNull(myResults);
		assertEquals(1, myResults.size());
		List<Searchable> resultList = myResults.get("keyword");
		assertNotNull(resultList);
		assertEquals(2, resultList.size());		
		assertNotNull(holder.getResultByType("keyword"));
		assertEquals(2, holder.getResultByType("keyword").size());	
	}	

	@Test
    public void testSave() throws Exception
	{
		Member member = new Member();
		member.setActive(true);
		Random random = new Random();
		int i = random.nextInt();
		member.setEmail("makima"+i+"@gmail.com");
		member.setUsername("fergus"+i);
		member.setFirstname("fergus");
		member.setLastname("macd");
		member.setPassword(PASSWORD);
		MemberGrantedAuthority authority = new MemberGrantedAuthority();
		authority.setAuthority("ROLE_USER");
		member.addAuthority(authority);
		memberService.createMember(member);
		assertNotNull(member);
		assertNotNull(member.getMemberId());
	}

	@Test
    public void testFindByID() throws Exception
	{
		Member member = new Member();
		member.setActive(true);
		Random random = new Random();
		int i = random.nextInt();
		member.setEmail("makima"+i+"@gmail.com");
		member.setUsername("fergus"+i);
		member.setFirstname("fergus");
		member.setLastname("macd");
		member.setPassword(PASSWORD);
		MemberGrantedAuthority authority = new MemberGrantedAuthority();
		authority.setAuthority("ROLE_USER");
		member.addAuthority(authority);
		memberService.createMember(member);
		assertNotNull(member);
		assertNotNull(member.getMemberId());
		
		Member savedMember = memberService.getMemberById(member.getMemberId());
		assertNotNull(savedMember);
		assertEquals(savedMember, member);
	}	
//	
//	@Test
//    public void testDisableMember() throws Exception
//	{
//		Member member = new Member();
//		member.setActive(true);
//		Random random = new Random();
//		int i = random.nextInt();
//		member.setEmail("makima"+i+"@gmail.com");
//		member.setUsername("fergus"+i);
//		member.setFirstname("fergus");
//		member.setLastname("macd");
//		member.setPassword("alrefd");
//		MemberGrantedAuthority authority = new MemberGrantedAuthority();
//		authority.setAuthority("ROLE_USER");
//		member.addAuthority(authority);
//		memberService.createMember(member);
//		assertNotNull(member);
//		assertNotNull(member.getMemberId());
//		
//		memberService.disableMember(member.getMemberId());
//		
//		Member disabledMember = memberService.getMemberById(member.getMemberId());
//		assertNotNull(disabledMember);
//		assertFalse(disabledMember.getActive());
//	}		
	@Test
    public void testRequestMemberRelation() throws Exception
	{
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(response.getUsername(), originalPassword);
//        log.debug("going to authentication:"+response.getUsername());
//      //  log.debug("going to authentication:"+response.getPassword());
//        
//        Authentication auth = authenticationManager.authenticate(token);
//        SecurityContextHolder.getContext().setAuthentication(auth);       	
		//create 3 member
		Member fromMember = getMember();
		memberService.createMember(fromMember);
		assertNotNull(fromMember);
		assertNotNull(fromMember.getMemberId());
		
		Member toMember = getMember();
		memberService.createMember(toMember);
		assertNotNull(toMember);
		assertNotNull(toMember.getMemberId());	
		
		Member member3= getMember();
		memberService.createMember(member3);
		assertNotNull(member3);
		assertNotNull(member3.getMemberId());	
		
		MemberRelation memberRelation = new MemberRelation();
		memberRelation.setFromId(fromMember.getMemberId());
		memberRelation.setIntitiatorId(fromMember.getMemberId());
		memberRelation.setToId(toMember.getMemberId());
		// need to log user in 
		loginUser(fromMember.getUsername(), PASSWORD);		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(fromMember.getUsername(), PASSWORD);   
	    Authentication auth = authenticationManager.authenticate(token);
	    SecurityContextHolder.getContext().setAuthentication(auth);     		
		
		memberRelation.setName(fromMember.getUsername());
		memberService.requestMemberRelation(memberRelation) ;
		// check been set up
		assertNotNull(memberRelation);
		assertNotNull(memberRelation.getId());
		assertEquals(Status.PENDING_RESPONSE, memberRelation.getStatus());
		assertEquals(0, memberRelation.getRequestCount());
		// check actually saved to the two members with correct status
		SearchHolder holder = new SearchHolder();
		holder.setResultsPerPage(Float.valueOf(SystemValues.RESULTS_PER_PAGE));
		holder.setCurrentPage(Float.valueOf(0));
		//holder.setKeyword(status);
		Map<String, Object> searchTerms = new HashMap<String, Object>();
		Long fromIdLong = Long.parseLong(fromMember.getMemberId()+"");
		searchTerms.put("id", fromIdLong); 
		searchTerms.put("status", Status.PENDING_RESPONSE.getValue());
		holder.setSearchTerms(searchTerms);
		
		holder = memberService.getMemberFriendList(holder);
		assertNotNull(holder);
		Map<String, List<Searchable>> returnResults = holder.getResults();
		assertNotNull(returnResults);
		List<Searchable> finalResults = returnResults.get(Status.PENDING_RESPONSE.getValue());
		assertNotNull(finalResults);
		assertEquals(1, finalResults.size());
		MemberRelated saveRelation = (MemberRelated)finalResults.get(0);
		assertEquals(Status.PENDING_RESPONSE, saveRelation.getStatus());
		
		holder = new SearchHolder();
		holder.setResultsPerPage(Float.valueOf(SystemValues.RESULTS_PER_PAGE));
		holder.setCurrentPage(Float.valueOf(0));
		//holder.setKeyword(status);
		searchTerms = new HashMap<String, Object>();
		Long toIdLong = Long.parseLong(toMember.getMemberId()+"");
		searchTerms.put("id", toIdLong); 
		searchTerms.put("status", Status.REQUESTED.getValue());
		holder.setSearchTerms(searchTerms);
		
		holder = memberService.getMemberFriendList(holder);
		assertNotNull(holder);
		returnResults = holder.getResults();
		assertNotNull(returnResults);
		finalResults = returnResults.get(Status.REQUESTED.getValue());
		assertNotNull(finalResults);
		assertEquals(1, finalResults.size());		
		saveRelation = (MemberRelated)finalResults.get(0);
		
		assertEquals(Status.REQUESTED, saveRelation.getStatus());
		
		
    
	}		
	@Test
    public void testGetMemberFriendList() throws Exception
	{
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(response.getUsername(), originalPassword);
//        log.debug("going to authentication:"+response.getUsername());
//      //  log.debug("going to authentication:"+response.getPassword());
//        
//        Authentication auth = authenticationManager.authenticate(token);
//        SecurityContextHolder.getContext().setAuthentication(auth);       	
		//create 3 member
		Member fromMember = getMember();
		memberService.createMember(fromMember);
		assertNotNull(fromMember);
		assertNotNull(fromMember.getMemberId());
		
		Member toMember = getMember();
		memberService.createMember(toMember);
		assertNotNull(toMember);
		assertNotNull(toMember.getMemberId());	
		
		Member member3= getMember();
		memberService.createMember(member3);
		assertNotNull(member3);
		assertNotNull(member3.getMemberId());	
		
		MemberRelation memberRelation = new MemberRelation();
		memberRelation.setFromId(fromMember.getMemberId());
		memberRelation.setIntitiatorId(fromMember.getMemberId());
		memberRelation.setToId(toMember.getMemberId());
		// need to log user in 
		loginUser(fromMember.getUsername(), PASSWORD);		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(fromMember.getUsername(), PASSWORD);   
	    Authentication auth = authenticationManager.authenticate(token);
	    SecurityContextHolder.getContext().setAuthentication(auth);     		
		
		memberRelation.setName(fromMember.getUsername());
		memberService.requestMemberRelation(memberRelation) ;
		// check been set up
		assertNotNull(memberRelation);
		assertNotNull(memberRelation.getId());
		assertEquals(Status.PENDING_RESPONSE, memberRelation.getStatus());
		
		memberService.acceptMemberRelation(fromMember.getMemberId(), toMember.getMemberId());
		
		List<MemberRelated> friends =  memberService.getAllAcceptedFriends(fromMember.getMemberId()); 
		assertNotNull(friends);
		assertEquals(1, friends.size());	
		List<MemberRelated> otherFriends =  memberService.getAllAcceptedFriends(toMember.getMemberId()); 
		assertNotNull(otherFriends);
		assertEquals(1, otherFriends.size());			
	}
	private Member getMember()
	{
		Member member = new Member();
		member.setActive(true);
		Random random = new Random();
		int i = random.nextInt();
		member.setEmail("makima"+i+"@gmail.com");
		member.setUsername("fergus"+i);
		member.setFirstname("fergus");
		member.setLastname("macd");
		member.setPassword(PASSWORD);
		MemberGrantedAuthority authority = new MemberGrantedAuthority();
		authority.setAuthority("ROLE_USER");
		member.addAuthority(authority);
		return member;
	}
	private static final String PASSWORD = "alrefd";
}
