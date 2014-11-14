package com.martinsweft.dao.location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.martinsweft.domain.Location;
import com.martinsweft.domain.search.SearchHolder;
import com.martinsweft.domain.search.Searchable;

@Repository("proximityDAO")
@Component
public class ProximityDAOImpl implements ProximityDAO{
	
	private static final Logger log = Logger.getLogger(ProximityDAOImpl.class);
	
	@PersistenceContext private EntityManager em;
	 
//    @PersistenceContext
//    public void setEntityManager(EntityManager entityManager) {
//        this. entityManager = entityManager;
//    }

	
//	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	public SearchHolder findMyNearestEstablishments(SearchHolder searchHolder) {
		// TODO Auto-generated method stub
		Map<String, Object> searchTerms = searchHolder.getSearchTerms();
		
		Query query = em.createNativeQuery(haversineMYSQL);
		query.setParameter( 1, searchTerms.get("latitude") );
		query.setParameter( 2, searchTerms.get("latitude") );
		query.setParameter( 3, searchTerms.get("longitude") );
		query.setParameter( 4, searchTerms.get("distance"));
		List tempResults = query.getResultList(); 
		System.out.println(tempResults.size());
		Map<String, List<Searchable>> results = new HashMap<String, List<Searchable>>();
		results.put("proximity", mapResults(tempResults));
		searchHolder.setResults(results);
		return searchHolder;
	}
	private List<Searchable> mapResults(List<Object[]> results)
	{
		//[422, 114.155356, 22.281836, Izakaya Daikichi, 0.0535122395998615]
		List<Searchable> myResults = new ArrayList<Searchable>();
		for (Object[] result: results)
		{
			Location location = new Location();
			location.setLocationId(new Long(result[0].toString()));
			location.setLongitude(new Double(result[1].toString()));
			location.setLatitude(new Double(result[2].toString()));
			location.setName(new String(result[3].toString()));
			location.setDistance(new String(result[4].toString()));
			location.setLanguageCode(new String(result[5].toString()));
			location.setFullAddress(new String(result[6].toString()));
			location.setParentId(new Long(result[7].toString()));
			location.setTelephone(new String(result[8].toString()));
			location.setTown(new String(result[9].toString()));
			myResults.add(location);
		}
		return myResults;
	}
	
	String haversineMYSQL = "SELECT location.location_id, location.longitude, location.latitude, location.name, 6371 * 2 * ASIN(SQRT(POWER(SIN(RADIANS(? - ABS(location.latitude))), 2) " +
		"+ COS(RADIANS(?)) * COS(RADIANS(ABS(location.latitude))) " +
		"* POWER(SIN(RADIANS(? - location.longitude)), 2))) AS distance, location.lang_code, location.full_address, location.parent_id, location.telephone, location.town  " +
		"FROM Location location HAVING distance < ? ORDER BY distance";




}
