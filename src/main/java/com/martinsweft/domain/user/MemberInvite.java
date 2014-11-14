package com.martinsweft.domain.user;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Object representing invited members to the service
 * @author fergusmacdermot
 *
 */
@Entity
@Table(name = "member_invites")
public class MemberInvite {

	public MemberInvite() {
	}

	private Long id;

	private int sendCount;
	public int getSendCount() {
		return sendCount;
	}

	public void setSendCount(int sendCount) {
		this.sendCount = sendCount;
	}

	private Long suggestedId;
	private String email;
	private String inviteCode;


	private Date createDate;
	private Date acceptDate;
	@Enumerated
	private Status status;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	public Long getId() {
		return id;
	}
  
	public void setId(Long id) {
		this.id = id;
	}

	public Long getSuggestedId() {
		return suggestedId;
	}

	public void setSuggestedId(Long suggestedId) {
		this.suggestedId = suggestedId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
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

	   
}
      	