/**
 * 
 */
package com.martinsweft.business.location;

import com.martinsweft.domain.search.SearchHolder;



/**
 * @author fergusmacdermot
 *
 */
public interface ProximityService {
	
	SearchHolder findMyNearestEstablishments(SearchHolder searchHolder);


//	SELECT name,
//	6371 * 2 * ASIN(SQRT(POWER(SIN(RADIANS(51.48571 - ABS(location.latitude))), 2) + COS(RADIANS(51.48571)) * COS(RADIANS(ABS(location.latitude))) * POWER(SIN(RADIANS(-0.1738 - location.longitude)), 2))) AS distance
//	FROM location
//	HAVING distance < 20
//	ORDER BY distance;

}
