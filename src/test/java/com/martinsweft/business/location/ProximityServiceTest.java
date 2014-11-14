package com.martinsweft.business.location;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.martinsweft.domain.search.SearchHolder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/application-context.xml" })
public class ProximityServiceTest {

	@Autowired
    private ProximityService proximityService;
	
	@Test
    public void testSearch() 
	{
		SearchHolder searchHolder = new SearchHolder();
		Map<String, Object> searchTerms = new HashMap<String, Object>();
		searchTerms.put("latitude", 22.281625);
		searchTerms.put("longitude", 114.155231);
		searchTerms.put("distance", 1);
		searchHolder.setSearchTerms(searchTerms);
		searchHolder = proximityService.findMyNearestEstablishments(searchHolder);
		System.out.println(searchHolder.getResults().get("proximity").size());
		System.out.println(searchHolder.getResults().get("proximity").get(0).getClass());
		for (Object ob: searchHolder.getResults().get("proximity"))
		{
			System.out.println("ob: "+ob);
		}
		//[422, 114.155356, 22.281836, Izakaya Daikichi, 0.0535122395998615]
	}
	
//	@Test
//    public void testUpdate() 
//	{
//		Location location = new Location();
//		location.setCountry("UK");
//		location.setLatitude(0.0);
//		location.setLongitude(0.1);
//		location.setTown("London");
//		location.setPostCode("IP9 9EW");
//		location.setFullAddress("the full address");
//		location.setTelephone("dsfsdf");
//		location = locationService.saveLocation(location); 
//		//assertNotNull(location);
//		location.setLatitude(111);
//		location = locationService.updateLocation(location); 
//		System.out.println("finished:"+location);
//	}	
}
