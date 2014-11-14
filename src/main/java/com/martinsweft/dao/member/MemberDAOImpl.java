package com.martinsweft.dao.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.martinsweft.dao.JpaDAOImpl;
import com.martinsweft.domain.search.SearchHolder;
import com.martinsweft.domain.search.Searchable;
import com.martinsweft.domain.user.Member;
import com.martinsweft.domain.user.MemberRelated;
import com.martinsweft.domain.user.MemberRelation;
import com.martinsweft.domain.user.Status;
import com.mweft.domain.exception.ErrorResponse;
import com.mweft.domain.exception.Errors;
import com.mweft.domain.exception.MultipleUsersExistForUserName;

//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Query;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.orm.jpa.support.JpaDaoSupport;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.stereotype.Repository;
//
//import com.mweft.domain.exception.ErrorResponse;
//import com.mweft.domain.exception.Errors;
//import com.mweft.domain.exception.MultipleUsersExistForUserName;
//import com.mweft.domain.search.SearchHolder;
//import com.mweft.domain.security.Member;
//import com.mweft.domain.security.MemberGrantedAuthority;
//import com.mweft.domain.security.MemberRelated;
//import com.mweft.domain.security.MemberRelation;
//import com.mweft.domain.security.Status;
@Repository("memberDAO")
@Component
public class MemberDAOImpl extends JpaDAOImpl<Long, Member> implements MemberDAO{
	
	@PersistenceContext private EntityManager em;
	
	//private static final Logger log = Logger.getLogger(MemberDAOImpl.class);
//	 @Autowired
//	 EntityManagerFactory em;
//
//	@Override
//	public void saveOrUpdateMember(Member member) {
//		if (null != member.getId())
//		{
//			getJpaTemplate().merge(member);
//		}
//		else
//		{
//			getJpaTemplate().persist(member);
//		}
//	}

	@SuppressWarnings("unchecked")
	public SearchHolder getMemberFriendList(final SearchHolder holder) {

		// get related users
		
		String status = (String)holder.getSearchTerms().get("status");
		Long fromId = (Long)holder.getSearchTerms().get("id");
		//TODO do this by status look up
		String query = "select mr from MemberRelation mr where mr.fromId = :fromId and mr.status = :status order by mr.createDate desc";
		Query q = em.createQuery(query);
		q.setParameter("fromId", fromId.longValue());
		q.setParameter("status", Status.getStatus(status));
		// need to expand
		float resultsPerPage = holder.getResultsPerPage();
		
		float currentPage = holder.getCurrentPage();
		
		float startPoint = currentPage * resultsPerPage;
		
		Float fl = Float.valueOf(startPoint);
		
		q.setMaxResults(holder.getResultsPerPage().intValue()); 
        q.setFirstResult(fl.intValue()); 
		List<MemberRelation> list = q.getResultList();
		List<Searchable> results = new ArrayList<Searchable>();
		MemberRelated related = null;
		for (MemberRelation relation: list)
		{
			// need to get the details for the to relation
			related = new MemberRelated(findById(relation.getToId()));
			related.setStatus(relation.getStatus());
			results.add(related);
//			if (relation.getStatus().getValue().equalsIgnoreCase(status))
//			{
//				results.add(related);
//			}		
		}
		Map<String, List<Searchable>> returnResults = new HashMap<String, List<Searchable>>();
		returnResults.put(status, results);
		holder.setTotalResults(Float.parseFloat(results.size()+""));
		holder.setResults(returnResults);
		return holder;
	}
	@SuppressWarnings("unchecked")
	public void getMembersByKeyword(SearchHolder holder) {

	//	em  = transactionManager.getEntityManagerFactory().createEntityManager();
		// get related users
		//Query q = entityManager.createQuery("SELECT m FROM Member m WHERE m.username LIKE :username").setParameter("username", userName);
		Query q = em.createQuery("SELECT m FROM Member m WHERE m.username LIKE :username ORDER BY m.username ASC");
		q.setParameter("username", "%"+holder.getSearchTerms().get("username")+"%");
		float totalResults = holder.getTotalResults();
		// don't need to run twice of already have total results
		if (totalResults < 1)
		{
			holder.setTotalResults(Float.parseFloat(q.getResultList().size()+""));
			q = em.createQuery("SELECT m FROM Member m WHERE m.username LIKE :username ORDER BY m.username ASC");	
			q.setParameter("username", "%"+holder.getSearchTerms().get("username")+"%");
		}
		//q.setFlushMode(FlushModeType.)
		// need to expand
		float resultsPerPage = holder.getResultsPerPage();
		
		float currentPage = holder.getCurrentPage();
		
		float startPoint = currentPage * resultsPerPage;
		
		Float fl = Float.valueOf(startPoint);
		
		q.setMaxResults(holder.getResultsPerPage().intValue()); 
        q.setFirstResult(fl.intValue()); 
		List<Member> list = q.getResultList();
		List<Searchable> results = new ArrayList<Searchable>();
		for (Member member: list)
		{
			Member mem = new Member(member);
			results.add(mem);
		}
		Map<String, List<Searchable>> myResults = new HashMap<String, List<Searchable>>();
		myResults.put("keyword", results); 
		
		holder.setResults(myResults);
		
	}
//	private void closeEM(EntityManager em)
//	{
//		if (null != em && em.isOpen())
//		{			
//			em.close();
//		}
//	}
//	@Override
//	public Member getMember(final Long id) {
//		return getEntityManager().find(Member.class, id);
//	}
//	@Override
//	public void deleteMember(final Long id) {
//		Member member = getMember(id);
//		EntityManager em = getEntityManager();
//		em.remove(member);
//		em.close();
//	}
//	private EntityManager getEntityManager()
//	{
//		return null;
//		//return transactionManager.getEntityManagerFactory().createEntityManager();
//	}

	public void createOrUpdateMemberRelation(final MemberRelation relation) {
		if (null != relation.getId())
		{
			em.merge(relation);
		}
		else
		{
			em.persist(relation);
		}
	}
//
//	@Override
//	public void deleteMemberRelation(Long fromId, Long toId) {
////		List<MemberRelation> relations = getMemberRelation(fromId, toId);
////		for (MemberRelation relation: relations)
////		{
////			EntityManager em = getEntityManager();
////			em.remove(relation);
////			em.close();
////		}
////		// now do the reverse way round
////		relations = getMemberRelation(toId, fromId);
////		for (MemberRelation relation: relations)
////		{
////			EntityManager em = getEntityManager();
////			em.remove(relation);
////			em.close();
////		}
//		
//	}
//
	public MemberRelation getMemberRelation(Long fromId, Long toId)
	{
		// get related users - could be multiple though shouldn't. DN has no integrity constraints
		Query q = em.createQuery("select mr from MemberRelation mr where mr.fromId = :fromId and " +
				" mr.toId = :toId");
		q.setParameter("fromId", fromId);
		q.setParameter("toId", toId);
		if (q.getResultList().isEmpty())
		{
			return new MemberRelation();
		}
		return (MemberRelation)q.getResultList().get(0);
	}


	public Member getMemberByUserName(String userName) throws MultipleUsersExistForUserName {
		// get related users
		//Query q = entityManager.createQuery("select from "+Member.class.getName()+" where username = '"+userName+"'");
		
		Query q = entityManager.createQuery("SELECT m FROM Member m WHERE m.username = :username").setParameter("username", userName);
		if (q.getResultList().isEmpty())
		{
			return null;
		}
		if (q.getResultList().size()>1)
		{
			ErrorResponse code = new ErrorResponse(Errors.MULTIPLE_USERNAMES_EXISTS, String.format("Multiple members exist for %s", userName));
			throw new MultipleUsersExistForUserName(code);
		}
		Member member = (Member) q.getResultList().get(0);
		//member.setAuthoritiesSet(loadUserAuthorities(member.getId()));
		return member;
	}



	public Member getMemberByEmail(String email) {
		// get related users
		Query q = entityManager
				.createQuery(
						"select m from Member m where m.email = :email and m.active = :active")
				.setParameter("email", email).setParameter("active", true);
		if (q.getResultList().isEmpty())
		{
			return null;
		}
		//TODO will have to do a check to make sure not > 1 
		return (Member) q.getResultList().get(0);
	}


	public List<MemberRelated> getAllAcceptedFriends(Long fromId) {
		String query = "select mr from MemberRelation mr where mr.fromId = :fromId order by mr.createDate desc";
		Query q = em.createQuery(query);
		q.setParameter("fromId", fromId.longValue());
		
//		// get related users
//		Query q = em
//				.createQuery(
//						"select mr from MemberRelation mr where mr.fromId = :fromId  order by mr.createDate desc")
//				.setParameter("fromId", fromId);
		@SuppressWarnings("unchecked")
		List<MemberRelation> list = q.getResultList();
		List<MemberRelated> results = new ArrayList<MemberRelated>();
		for (MemberRelation relation: list)
		{
			// need to get the details for the to relation
			
			MemberRelated related = new MemberRelated(findById(relation.getToId()));
			related.setStatus(relation.getStatus());

			if (relation.getStatus().getValue().equalsIgnoreCase(Status.ACCEPTED.getValue()))
			{
				results.add(related);
			}
		}
		return results;
	}


	public void disableMember(Long id) {
		Member member = findById(id);
		member.setActive(Boolean.FALSE);
		merge(member);
	}



}
