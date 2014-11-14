package com.martinsweft.dao.location;

import com.martinsweft.domain.search.SearchHolder;

public interface ProximityDAO {

	SearchHolder findMyNearestEstablishments(SearchHolder searchHolder);

	//SearchHolder searchByPostcode(SearchHolder holder);


}
