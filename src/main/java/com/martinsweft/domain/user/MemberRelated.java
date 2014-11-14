package com.martinsweft.domain.user;

import com.martinsweft.domain.search.Searchable;


public class MemberRelated extends Member implements Searchable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8786086777115072772L;
	private Status status;

	public MemberRelated() {
	}

	public MemberRelated(Member member) {
		super(member);
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberRelated other = (MemberRelated) obj;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MemberRelated [status=" + status + ", getId()=" + getMemberId()
				+ ", getFirstname()=" + getFirstname() + ", getLastname()="
				+ getLastname() + ", getUsername()=" + getUsername() + "]";
	}

	   
}
      	