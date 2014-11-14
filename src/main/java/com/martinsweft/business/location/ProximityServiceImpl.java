package com.martinsweft.business.location;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.martinsweft.dao.location.ProximityDAO;
import com.martinsweft.domain.search.SearchHolder;

@Service("proximityService")
public class ProximityServiceImpl implements ProximityService{
	private static final Logger log = Logger.getLogger(ProximityServiceImpl.class);

	@Autowired private ProximityDAO proximityDAO;

	@Transactional(readOnly = false)
	public SearchHolder findMyNearestEstablishments(SearchHolder searchHolder) {
		// TODO Auto-generated method stub
		return proximityDAO.findMyNearestEstablishments(searchHolder);
	}

}
