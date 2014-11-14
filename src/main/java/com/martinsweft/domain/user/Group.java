//package com.martinsweft.domain.user;
//
//import java.io.Serializable;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import javax.xml.bind.annotation.XmlRootElement;
//
//
//@Entity
//@Table(name = "groups")
//@XmlRootElement(name = "member")
//public class Group implements Serializable {
//	private static final long serialVersionUID = 1L;
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	private Long organisationId;
//	private Long parentId;
//	@Column(name="parent_id", nullable=true)
//	public Long getParentId() {
//		return parentId;
//	}
//	public void setParentId(Long parentId) {
//		this.parentId = parentId;
//	}
//	@Column(name="organisation_id", nullable=false)
//	public Long getOrganisationId() {
//		return organisationId;
//	}
//	public void setOrganisationId(Long organisationId) {
//		this.organisationId = organisationId;
//	}
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = super.hashCode();
//		result = prime * result
//				+ ((id == null) ? 0 : id.hashCode());
//		return result;
//	}
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (!super.equals(obj))
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Group other = (Group) obj;
//		if (id == null) {
//			if (other.id != null)
//				return false;
//		} else if (!id.equals(other.id))
//			return false;
//		return true;
//	}
//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//
//	
//	
//}
