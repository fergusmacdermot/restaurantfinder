package com.martinsweft.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.martinsweft.domain.search.Searchable;

@Entity
@Table(name = "location_attr")
@XmlRootElement(name = "location_attr")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class LocationAttribute  implements Searchable{


	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name = "location_attribute_id", nullable = false)
	@XmlElement
	private long locationAttributeId;
	
	@ManyToOne(optional=false)
    @JoinColumn(name="location_id",referencedColumnName="location_id")
    private Location location;
	
	@Column(name = "name", nullable = false,length = 64)
	private String name;
	@Column(name = "value", nullable = false,length = 128)
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public long getLocationAttributeId() {
		return locationAttributeId;
	}

	public void setLocationAttributeId(long locationAttributeId) {
		this.locationAttributeId = locationAttributeId;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (locationAttributeId ^ (locationAttributeId >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		LocationAttribute other = (LocationAttribute) obj;
		if (locationAttributeId != other.locationAttributeId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LocationAttribute [locationAttributeId=" + locationAttributeId
				+ ", name=" + name + ", value=" + value + "]";
	}


}
