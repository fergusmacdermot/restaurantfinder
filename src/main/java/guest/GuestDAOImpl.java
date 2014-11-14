//package guest;
// 
//import java.util.List;
//
//import javax.annotation.PostConstruct;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import org.springframework.stereotype.Repository;
//
//import com.martinsweft.dao.JpaDAOImpl;
// //public class GuestDAOImpl extends JpaDAOImpl<Long, Guest> implements GuestDAO{
//@Repository("guestDAO")
////public class GuestDAOImpl{
//public class GuestDAOImpl extends JpaDAOImpl<Long, Guest> implements GuestDAO{
////    // Injected database connection:
//    @PersistenceContext private EntityManager em;
//	 @PostConstruct
//	 public void init() {
//	  super.setEntityManager(em);
//	 }
////    // Stores a new guest:
////    @Transactional
////    public void persist(Guest guest) {
////        em.persist(guest);
////    }
//// 
//    // Retrieves all the guests:
//    public List<Guest> getAllGuests() {
////    	TypedQuery<Guest> query = em.createQuery(
////            "SELECT g FROM Guest g ORDER BY g.id", Guest.class);
////    	return query.getResultList();
//    	return null;
//    }
//}