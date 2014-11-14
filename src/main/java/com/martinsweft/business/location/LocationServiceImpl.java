package com.martinsweft.business.location;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.martinsweft.dao.location.LocationDAO;
import com.martinsweft.domain.Location;

@Service("locationService")
public class LocationServiceImpl implements LocationService{
	private static final Logger log = Logger.getLogger(LocationServiceImpl.class);

	@Resource private LocationDAO locationDAO;
//	@Resource private EstablishmentAttributeDAO establishmentAttributeDAO;
	@Transactional(readOnly = false)
	public Location saveLocation(Location location) {
		location.setCreateDate(new Date()); 
		locationDAO.persist(location);
		log.debug("saved location:"+location);
		return location;
	}

	public Location updateLocation(Location location) {
		locationDAO.merge(location);
		log.debug("updated location:"+location);
		return location;
	}

	public Location findById(long id) {
		// TODO Auto-generated method stub
		return locationDAO.findById(id);
	}
	
//	@Override
//	@Transactional(readOnly=true)
//	public SearchHolder searchByPostcode(SearchHolder holder) {
//		// TODO Auto-generated method stub
//		return restaurantDAO.searchByPostcode(holder);
//	}
//
//	@Override
//	@Transactional
//	public void saveEstablishment(Establishment establishment) {
//		
//		//List<EstablishmentAttribute> attrs = establishment.getEstablishmentAttributes();
//		//System.out.println("attributes:"+attrs.size());
//		//establishment.setEstablishmentAttributes(null);
//		restaurantDAO.createObject(establishment);
//		// iterate through the attr and save to get round crappy google api
////		for (EstablishmentAttribute attr:attrs)
////		{
////			attr.setParentId(establishment.getId());
////			establishmentAttributeDAO.addEstablishmentAttribute(attr);
////			System.out.println("saved:"+attr);
////			EstablishmentAttribute eAttr = new EstablishmentAttribute();
////			eAttr.setName(attr.getName());
////			eAttr.setParentId(establishment.getId());
////			eAttr.setValue(attr.getValue());
////			establishmentAttributeDAO.addEstablishmentAttribute(attr);
////			System.out.println("saved new:"+attr);
////			EstablishmentAttr at = new EstablishmentAttr();
////			at.setUserId(establishment.getId());
////			at.setAuthority(attr.getName());
////			establishmentAttributeDAO.addEstablishmentAttr(at);
////			System.out.println("saved key :"+attr.getKey());
////		}
//	}

//	@Override
//	@Transactional
//	public void saveEstablishmentAttribute(
//			EstablishmentAttribute establishmentAttribute) {
//		System.out.println("saving:"+establishmentAttribute);
//		establishmentAttributeDAO.createObject(establishmentAttribute);
//		System.out.println("saved:"+establishmentAttribute);
//		
//	}

}
