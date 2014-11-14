package com.martinsweft.business.member;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.martinsweft.common.SystemValues;
import com.martinsweft.dao.member.MemberDAO;
import com.martinsweft.domain.search.SearchHolder;
import com.martinsweft.domain.user.Member;
import com.martinsweft.domain.user.MemberGrantedAuthority;
import com.martinsweft.domain.user.MemberRelated;
import com.martinsweft.domain.user.MemberRelation;
import com.martinsweft.domain.user.Status;
import com.mweft.domain.exception.EmailExistsException;
import com.mweft.domain.exception.ErrorResponse;
import com.mweft.domain.exception.Errors;
import com.mweft.domain.exception.MemberRelationAlreadyExists;
import com.mweft.domain.exception.MessageSendException;
import com.mweft.domain.exception.MultipleUsersExistForUserName;
import com.mweft.domain.exception.TooManyRemindersException;
import com.mweft.domain.exception.TooSoonToReminderException;
import com.mweft.domain.exception.UserNameExistsException;
//import com.mweft.domain.security.MemberRelated;
//import com.mweft.domain.security.MemberRelation;
//import com.mweft.domain.security.Status;
@Service
public class MemberServiceImpl implements MemberService {

	//private static final Logger LOG = Logger.getLogger(MemberServiceImpl.class);
//	
	@Resource private MemberDAO memberDAO;
//
	@Resource private ShaPasswordEncoder passwordEncoder;
//	@Resource private EmailService emailService;
//	
////	@Override
////	public void saveOrUpdateMember(Member member) {
////		EntityManager em  = transactionManager.getEntityManagerFactory().createEntityManager();
////		em.persist(member);
////		em.close();
////	}
	@Transactional(readOnly=true)
	public SearchHolder getMemberFriendList(final SearchHolder holder)
	{
		return memberDAO.getMemberFriendList(holder); 
	}
	public Member getMemberById(final Long id)
	{
		return memberDAO.findById(id);
	}
//
//	@Override
//	@Transactional
//	public void deleteMember(final Long id) {
//		//TODO delete the authority
//		memberDAO.deleteMember(id); 
//		
//	}
//	
	
	@Transactional(readOnly = false)
	public void createMember(Member member) throws UserNameExistsException, EmailExistsException, MultipleUsersExistForUserName {
		// see if user exists
		Member existingMember = getMemberByUserName(member.getUsername());
		if (null != existingMember)
		{
			ErrorResponse code = new ErrorResponse(Errors.USERNAME_EXISTS, String.format("Username %s exists", member.getUsername()));
			throw new UserNameExistsException(code);
		}
		// can't have duplicate emails either
		existingMember = getMemberByEmail(member.getEmail());
		if (null != existingMember)
		{
			ErrorResponse code = new ErrorResponse(Errors.EMAIL_EXISTS, String.format("Email %s exists", member.getEmail()));
			throw new EmailExistsException(code);
		}
		String salt =  UUID.randomUUID().toString();
		String encPassword = passwordEncoder.encodePassword(member.getPassword(),salt);
		member.setSalt(salt);
		member.setPassword(encPassword);
		member.setActive(Boolean.TRUE);
		member.setCreateDate(new Date());
		// give them the role_user
		MemberGrantedAuthority authority = new MemberGrantedAuthority();
		authority.setAuthority("ROLE_USER");
		member.addAuthority(authority);		
		// 
		memberDAO.persist(member);
	}

//	@Transactional
//	public void updateMember(Member member) throws MultipleUsersExistForUserName, UserNameExistsException {
//		// see if user exists
//		Member existingMember = getMemberByUserName(member.getUsername());
//		if (null != existingMember)
//		{
//			// see if ids are same
//
//			if (member.getId().longValue() != existingMember.getId().longValue())
//			{
//				ErrorResponse code = new ErrorResponse(Errors.USERNAME_EXISTS, String.format("Username %s exists", member.getUsername()));
//				throw new UserNameExistsException(code);
//			}
//			// same member, just updating so copy over create date
//			// and password
//			existingMember = member;
//		}
//		String salt =  UUID.randomUUID().toString();
//
//		member.setPassword(encPassword);
//		member.setActive(Boolean.TRUE);
//		memberDAO.saveOrUpdateMember(member);
//	}

	@Transactional
	public void requestMemberRelation(final MemberRelation newMemberRelation) throws MemberRelationAlreadyExists, MessageSendException, TooManyRemindersException, TooSoonToReminderException {
		// need to do this for the relationship both way,
		// this'll make queries more efficient later on
		MemberRelation existingRelation = getRelationship(newMemberRelation.getFromId(), newMemberRelation.getToId());
		int requestCount = 1;
		if (null == existingRelation  || null == existingRelation.getId() || existingRelation.getId() < 1)
		{
			//no relationship so create it
			newMemberRelation.setCreateDate(new Date());
			newMemberRelation.setRequestCount(0);
			newMemberRelation.setStatus(Status.PENDING_RESPONSE);
			memberDAO.createOrUpdateMemberRelation(newMemberRelation);
			// and create the reverse relationship
			MemberRelation requestedRelation = new MemberRelation();
			requestedRelation.setRequestCount(requestCount);
			requestedRelation.setCreateDate(new Date());
			requestedRelation.setFromId(newMemberRelation.getToId());
			requestedRelation.setStatus(Status.REQUESTED);
			requestedRelation.setToId(newMemberRelation.getFromId());
			requestedRelation.setIntitiatorId(newMemberRelation.getIntitiatorId());
			memberDAO.createOrUpdateMemberRelation(requestedRelation);			
		}
		else
		{
			// now date check, say one week
			GregorianCalendar cal = new GregorianCalendar();
			cal.add(Calendar.DATE, -7);
			if (existingRelation.getCreateDate().after(cal.getTime()))
			{
				ErrorResponse code = new ErrorResponse(Errors.TOO_SOON_TO_SEND_REMINDER, "Too soon to send a reminder");
				throw new TooSoonToReminderException(code);
			}				
			// check to see it's not too soon
			requestCount = existingRelation.getRequestCount();
			if (requestCount > SystemValues.EMAIL_REMINDER_COUNT_MAX)
			{
				ErrorResponse code = new ErrorResponse(Errors.TOO_MANY_REMINDERS,String.format("You've already sent %s reminders", SystemValues.EMAIL_REMINDER_COUNT_MAX));
				throw new TooManyRemindersException(code);
			}

			requestCount++;
		}
		// check that not been accepted yet - dur!
		if (existingRelation.getStatus() == Status.ACCEPTED)
		{
			ErrorResponse response = new ErrorResponse(Errors.RELATIONSHIP_EXISTS_AND_ACCEPTED, "This relationship has been accepted");
			
			throw new MemberRelationAlreadyExists(response);
		}
		//TODO send an email - need to get member details
//		Member member = memberDAO.findById(memberRelation.getFromId());
//		EmailMessage message = new EmailMessage();
//		message.setFromEmail(member.getEmail());
//		message.setFromFirstname(member.getFirstname());
//		message.setFromLastname(member.getLastname());
//		member = memberDAO.getMember(memberRelation.getToId());
//		message.setToEmail(member.getEmail());
//		message.setToFirstname(member.getFirstname());
//		message.setToLastname(member.getLastname());
//		emailService.sendJoinMemberCircleMail(message);
	}
//	@Override
//	@Transactional
//	public void suggestMemberRelation(final Long fromId, final Long toId, final Long initiatorId) {
//		// need to do this for the relationship both way,
//		// this'll make queries more effecient later on
//		MemberRelation relation = new MemberRelation();
//		relation.setCreateDate(new Date());
//		relation.setFromId(fromId);
//		relation.setStatus(Status.SUGGESTED);
//		relation.setToId(toId);
//		relation.setIntitiatorId(initiatorId);
//		memberDAO.createOrUpdateMemberRelation(relation);
//		// now need to do the reverse relation ship
//		relation = new MemberRelation();
//		relation.setCreateDate(new Date());
//		relation.setFromId(toId);
//		relation.setStatus(Status.SUGGESTED);
//		relation.setToId(fromId);
//		relation.setIntitiatorId(initiatorId);
//		memberDAO.createOrUpdateMemberRelation(relation);
//		//TODO send an email
//	}
//	

	@Transactional
	public void acceptMemberRelation(final Long fromId, final Long toId) {
		MemberRelation relation = memberDAO.getMemberRelation(fromId, toId);
		relation.setAcceptDate(new Date());
		//relation.setFromId(fromId);
		relation.setStatus(Status.ACCEPTED);
		//relation.setToId(toId);
		memberDAO.createOrUpdateMemberRelation(relation);
		// now need to do the reverse relation ship
		relation = memberDAO.getMemberRelation(toId, fromId);
		relation.setAcceptDate(new Date());
		//relation.setFromId(toId);
		relation.setStatus(Status.ACCEPTED);
		//relation.setToId(fromId);
		memberDAO.createOrUpdateMemberRelation(relation);
		
	}
//	@Override
//	@Transactional
//	public void deleteMemberRelation(final Long fromId, final Long toId) {
//		memberDAO.deleteMemberRelation(fromId, toId);
//		
//	}

	@Transactional(readOnly=true)
	public Member getMemberByUserName(final String userName) throws MultipleUsersExistForUserName {
		//LOG.debug("looking for:"+userName);
		return memberDAO.getMemberByUserName(userName);
		//LOG.debug("looking for id:"+member.getMemberId());
	//	return memberDAO.findById(member.getMemberId());
	}
	
	@Transactional(readOnly=true)
	private MemberRelation getRelationship(final Long fromId, final Long toId)
	{
	//	MemberRelation relation = 
		return  memberDAO.getMemberRelation(fromId, toId);
	}


	@Transactional(readOnly=true)
	public void getMembersByKeyword(final SearchHolder holder) {
		memberDAO.getMembersByKeyword(holder);
	}


	@Transactional(readOnly=true)
	public Member getMemberByEmail(final String email) {
		// TODO Auto-generated method stub
		return memberDAO.getMemberByEmail(email);
	}

	@Transactional(readOnly=true)
	public List<MemberRelated> getAllAcceptedFriends(Long fromId) {
		// this to fix next
		return memberDAO.getAllAcceptedFriends(fromId);
	}
//	
//	private void checkMessageNotSentRecently()
//	{
//		
//	}

	public void disableMember(Long id) {
		memberDAO.disableMember(id);
		
	}

	
	

}
