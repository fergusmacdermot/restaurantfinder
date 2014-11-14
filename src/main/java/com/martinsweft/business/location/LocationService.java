/**
 * 
 */
package com.martinsweft.business.location;

import com.martinsweft.domain.Location;



/**
 * @author fergusmacdermot
 *
 */
public interface LocationService {

	Location saveLocation(Location location);
	Location updateLocation(Location location);
	Location findById(long id);
	
//	SearchHolder searchByPostcode(SearchHolder holder);
//	
//	void saveEstablishment(Establishment establishment);

}
