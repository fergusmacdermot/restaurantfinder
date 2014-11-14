package com.martinsweft.domain.user;

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

import org.springframework.security.core.GrantedAuthority;


@Entity
@Table(name = "member_authority")
@XmlRootElement(name = "member-authority")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class MemberGrantedAuthority implements GrantedAuthority {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4453603034402690173L;
	@ManyToOne(optional=false)
    @JoinColumn(name="member_id",referencedColumnName="member_id")
    private Member member;
	
	@Column
	@XmlElement	
	private String authority;
	
	public MemberGrantedAuthority(){};
	
	
	public MemberGrantedAuthority(String authority){
		this.authority = authority;
	};

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@XmlElement	
	@Column(name="authority_id")
	private Long id;


	public String getAuthority() {
		return authority;
	}
	

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public int compareTo(GrantedAuthority o) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public Member getMember() {
		return member;
	}


	public void setMember(Member member) {
		this.member = member;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return getAuthority();
	}
	



}
