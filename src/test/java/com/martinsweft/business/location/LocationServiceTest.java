package com.martinsweft.business.location;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.*;
import com.martinsweft.domain.Location;
import com.martinsweft.domain.LocationAttribute;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/application-context.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class LocationServiceTest {

	@Autowired
    private LocationService locationService;
	
	@Test
    public void testSave() 
	{
		Location location = new Location();
		location.setName("my place");
		location.setCountry("UK");
		location.setLatitude(0.0);
		location.setLongitude(0.1);
		location.setTown("London");
		location.setPostCode("IP9 9EW");
		location.setFullAddress("the full address");
		location.setTelephone("dsfsdf");
		LocationAttribute attr = new LocationAttribute();
		attr.setName("name");
		attr.setValue("value");
		location.addLocationAttribute(attr);
		location = locationService.saveLocation(location); 
		assertNotNull(location);
		
	}
	
	@Test
    public void testUpdate() 
	{
		Location location = new Location();
		location.setName("a restaruan");
		location.setCountry("UK");
		location.setLatitude(0.0);
		location.setLongitude(0.1);
		location.setTown("London");
		location.setPostCode("IP9 9EW");
		location.setFullAddress("the full address");
		location.setTelephone("dsfsdf");
		location = locationService.saveLocation(location); 
		assertNotNull(location);
		assertNotNull(location.getLocationId());
		long locationId = location.getLocationId();
		location.setLatitude(111);
		location = locationService.updateLocation(location); 
		assertNotNull(location);
		location = locationService.findById(locationId);
		assertEquals(locationId, location.getLocationId());
		
		
	}	
}
