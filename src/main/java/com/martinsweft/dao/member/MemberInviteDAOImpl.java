package com.martinsweft.dao.member;
//package com.mweft.dao.member;
//
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.Query;
//
//import org.springframework.stereotype.Service;
//
//import com.martinsweft.dao.JpaDAOImpl;
//import com.mweft.domain.security.MemberInvite;
//@Service
//public class MemberInviteDAOImpl  extends JpaDAOImpl<Long, MemberInvite> implements MemberInviteDAO{
//
//	@Override
//	public MemberInvite findMemberInviteByEmailandSuggestedId(final String email,
//			final Long suggestedId) {
//		EntityManager em  = null;
//		try
//		{
//			em  = null;
//			// get related users - could be multiple though shouldn't. DN has no integrity constraints
//			Query q = em.createQuery("select from "+MemberInvite.class.getName()+" where suggestedId = "+suggestedId+" and " +
//					" email = '"+email+"'");
//			List<MemberInvite> results = q.getResultList();
//			if (results.isEmpty())
//			{
//				return null;
//			}
//			return (MemberInvite)q.getResultList().get(0);
//		}
//		finally
//		{
//			closeEM(em);
//		}
//	}
//	
//	private void closeEM(EntityManager em)
//	{
//		if (null != em)
//		{
//			em.close();
//		}
//	}
//
//}
