package com.martinsweft.dao.location;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.martinsweft.dao.JpaDAOImpl;
import com.martinsweft.domain.Location;
@Repository("locationDAO")
@Component
public class LocationDAOImpl extends JpaDAOImpl<Long, Location> implements LocationDAO {
}
