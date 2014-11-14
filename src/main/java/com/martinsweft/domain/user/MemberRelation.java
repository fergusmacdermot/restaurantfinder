package com.martinsweft.domain.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
@Entity
@Table(name = "member_relation")
public class MemberRelation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)		
	private Long id;
	@Column(name="from_id", nullable = false)
	private Long fromId;
	@Column(name="to_id", nullable = false)
	private Long toId;
	@Column(name="initiator_id", nullable = false)
	private Long intitiatorId;
	@Column(name="request_count", nullable = false)
	private int requestCount = 0;
	@Temporal(TemporalType.DATE) 
	@Column(name="create_date", updatable=false, nullable = false)
	private Date createDate;

	@Version
    @Column(name = "last_updated_date", nullable = false)
    private Date updatedTime;
	
	@Column(name="accept_date")
	private Date acceptDate;
	
	@Column(name="status", nullable = false)
	@Enumerated
	private Status status;
	@Column(name="invite_id")
	private String inviteId;	
	@Column(name="name")
	private String name;

	public int getRequestCount() {
		return requestCount;
	}

	public void setRequestCount(int requestCount) {
		this.requestCount = requestCount;
	}

	public Long getIntitiatorId() {
		return intitiatorId;
	}

	public void setIntitiatorId(Long intitiatorId) {
		this.intitiatorId = intitiatorId;
	}
	public Long getId() {
		return id;
	}
  
	public void setId(Long id) {
		this.id = id;
	}

	


	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}


	
	public String getInviteId() {
		return inviteId;
	}

	public void setInviteId(String inviteId) {
		this.inviteId = inviteId;
	}

	public Long getFromId() {
		return fromId;
	}

	public void setFromId(Long fromId) {
		this.fromId = fromId;
	}

	public Long getToId() {
		return toId;
	}

	public void setToId(Long toId) {
		this.toId = toId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getAcceptDate() {
		return acceptDate;
	}

	public void setAcceptDate(Date acceptDate) {
		this.acceptDate = acceptDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((acceptDate == null) ? 0 : acceptDate.hashCode());
		result = prime * result
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((fromId == null) ? 0 : fromId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((toId == null) ? 0 : toId.hashCode());
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
		MemberRelation other = (MemberRelation) obj;
		if (acceptDate == null) {
			if (other.acceptDate != null)
				return false;
		} else if (!acceptDate.equals(other.acceptDate))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (fromId == null) {
			if (other.fromId != null)
				return false;
		} else if (!fromId.equals(other.fromId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (status != other.status)
			return false;
		if (toId == null) {
			if (other.toId != null)
				return false;
		} else if (!toId.equals(other.toId))
			return false;
		return true;
	}

	
	
}
