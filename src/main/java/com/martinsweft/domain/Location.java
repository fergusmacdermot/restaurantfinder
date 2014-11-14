package com.martinsweft.domain;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;

import com.martinsweft.domain.search.Searchable;


@Entity
@Table(name = "location")
@Inheritance(strategy=InheritanceType.JOINED)
public class Location implements Searchable {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name = "location_id", nullable = false)
	@XmlElement	
	private long locationId;
	@Column(name = "name", nullable = false,length = 72)
	private String name;
	@Column(name = "full_address", nullable = false)
	private String fullAddress;
	@Column(name = "address_one", nullable = true)
	private String addressOne;
	@Column(name = "address_two", nullable = true)
	private String addressTwo;
	@Column(name = "address_three", nullable = true)
	private String addressThree;
	@Column(name = "address_four", nullable = true)
	private String addressFour;
	@Column(name = "telephone", nullable = false)
	private String telephone;
	@Column(name = "post_code", nullable = false)
	private String postCode;
	@Column(name = "town", nullable = false)
	private String town;
	@Column(name = "country", nullable = false, length = 72)
	private String country;
	@Column(name = "longitude", nullable = false)
	private double longitude;
	@Column(name = "latitude", nullable = false)
	private double latitude;
	@Column(name = "lang_code", nullable = true)
	private String languageCode;	
	@Column(name = "parent_id", nullable = false)
	private long parentId;
	
	@Temporal(TemporalType.DATE) @NotNull @Column(updatable=false)
	private Date createDate;
	
    @Version
    @Column(name = "LAST_UPDATED_TIME")
    private Date updatedTime;
    
    @Transient
    private String distance;
	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}


    
    public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy="location",targetEntity=LocationAttribute.class,
    	       fetch=FetchType.EAGER)
    @org.hibernate.annotations.Cascade(value=org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private Collection<LocationAttribute> locationAttributes;      
	//private List<String> geoCellsData = new ArrayList<String>();
		
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}
//	private List<EstablishmentAttribute> establishmentAttributes;
//	public List<EstablishmentAttribute> getEstablishmentAttributes() {
//		return establishmentAttributes;
//	}
//
//	public void setEstablishmentAttributes(
//			List<EstablishmentAttribute> establishmentAttributes) {
//		this.establishmentAttributes = establishmentAttributes;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public String getAddressOne() {
		return addressOne;
	}

	public void setAddressOne(String addressOne) {
		this.addressOne = addressOne;
	}

	public String getAddressTwo() {
		return addressTwo;
	}

	public void setAddressTwo(String addressTwo) {
		this.addressTwo = addressTwo;
	}

	public String getAddressThree() {
		return addressThree;
	}

	public void setAddressThree(String addressThree) {
		this.addressThree = addressThree;
	}

	public String getAddressFour() {
		return addressFour;
	}

	public void setAddressFour(String addressFour) {
		this.addressFour = addressFour;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public long getLocationId() {
		return locationId;
	}

	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Collection<LocationAttribute> getLocationAttributes() {
		return locationAttributes;
	}

	public void setLocationAttributes(Collection<LocationAttribute> locationAttributes) {
		this.locationAttributes = locationAttributes;
	}

	public void addLocationAttribute( LocationAttribute attr)
	{
		attr.setLocation(this);
		if (null == locationAttributes)
		{
			locationAttributes = new HashSet<LocationAttribute>();
		}
		locationAttributes.add(attr);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime
				* result
				+ ((locationAttributes == null) ? 0 : locationAttributes
						.hashCode());
		result = prime * result + (int) (locationId ^ (locationId >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (Double.doubleToLongBits(latitude) != Double
				.doubleToLongBits(other.latitude))
			return false;
		if (locationAttributes == null) {
			if (other.locationAttributes != null)
				return false;
		} else if (!locationAttributes.equals(other.locationAttributes))
			return false;
		if (locationId != other.locationId)
			return false;
		if (Double.doubleToLongBits(longitude) != Double
				.doubleToLongBits(other.longitude))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Location [locationId=" + locationId + ", name=" + name
				+ ", fullAddress=" + fullAddress + ", telephone=" + telephone
				+ ", town=" + town + ", longitude=" + longitude + ", latitude="
				+ latitude + ", languageCode=" + languageCode + ", parentId="
				+ parentId + ", distance=" + distance + ", locationAttributes="
				+ locationAttributes + "]";
	}



}
