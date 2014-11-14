package com.martinsweft.domain.user;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.martinsweft.domain.IJSONObject;
import com.martinsweft.domain.search.Searchable;

@Entity
@Table(name = "member")
@XmlRootElement(name = "member")
@XmlAccessorType(XmlAccessType.PROPERTY)
@Inheritance(strategy=InheritanceType.JOINED)
public class Member implements UserDetails, Searchable, IJSONObject {
	
	public Member(Member member)
	{
		this.firstname = null != member.getFirstname()?member.getFirstname():"";
		this.lastname = null != member.getLastname()?member.getLastname():"";
		this.username = member.getUsername();
		this.email = member.getEmail();
		this.mobile = member.getMobile();
		this.password = member.getPassword();
		this.active = member.getActive();
		this.authoritiesSet = member.getAuthoritiesSet();
//		this.groups = member.getGroups();
		this.memberId = member.getMemberId();
	}
	
	public Member(String firstname, String lastname, String username,
			String email, String mobile, String password, Boolean active,
			Collection<MemberGrantedAuthority> authoritiesSet, long memberId) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.active = active;
		this.authoritiesSet = authoritiesSet;
//		this.groups = groups;
		this.memberId = memberId;
	}
	private static final long serialVersionUID = 1L;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="member",targetEntity=MemberGrantedAuthority.class,
 	       fetch=FetchType.EAGER)
	@org.hibernate.annotations.Cascade(value=org.hibernate.annotations.CascadeType.DELETE_ORPHAN)	
	@XmlElement
	private Collection<MemberGrantedAuthority> authoritiesSet;

//	@OneToMany(cascade = CascadeType.ALL, mappedBy="member",targetEntity=Group.class,
// 	       fetch=FetchType.EAGER)
//	@org.hibernate.annotations.Cascade(value=org.hibernate.annotations.CascadeType.DELETE_ORPHAN)	
//	@XmlElement
//	private Set<Group> groups;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name = "member_id", nullable = false)
	@XmlElement	
	private long memberId;
	@Column(name="first_name")
	@XmlElement
//	@ValidateTextField("First Name")
	private String firstname;
	@Column(name="last_name")
	@XmlElement
//	@ValidateTextField("Family Name")
	private String lastname;
	@XmlElement
//	@ValidateUserName("Username")
//	@ValidateExistingUserName("Username")
	@Column(name="username")
	private String username;
	@Column(name="email")
	@XmlElement
//	@ValidateExistingEmail("Email")
//	@ValidateEmail("Email")
	private String email;
	@Column
	@XmlElement
	private String mobile;
	
//	@ValidatePassword("Password")
	@Column(name = "password", nullable = false,length = 256)
	@XmlElement
	private String password;
	@XmlElement
	private String salt;


	
	@Temporal(TemporalType.DATE) 
	@Column(updatable=false)
	private Date createDate;

	@Version
    @Column(name = "LAST_UPDATED_TIME")
    private Date updatedTime;
    
	@Column
	@XmlElement
	private Boolean active;
	@Transient
	private List<Long> acceptedFriends;
	public List<Long> getAcceptedFriends() {
		return acceptedFriends;
	}

	public void setAcceptedFriends(List<Long> acceptedFriends) {
		this.acceptedFriends = acceptedFriends;
	}

	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Member() {
	}

	public Member(String username) {
		this.username = username;
	}


	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	
//	@Column
//	@Enumerated(EnumType.STRING)
//	public Title getTitle() {
//		return title;
//	}
//
//	public void setTitle(Title title) {
//		this.title = title;
//	}


	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String forename) {
		this.firstname = forename;
	}


	public String getLastname() {
		return lastname;
	}

	public void setLastname(String surname) {
		this.lastname = surname;
	}
	

	public String getUsername() { 
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean hasRole(Role role)
	{
		if (getAuthorities().isEmpty())
		{
			return false;
		}
		for (GrantedAuthority auth:getAuthorities())
		{
			if (role.getValue().equalsIgnoreCase(auth.getAuthority()))
			{
				return true;
			}
		}
		return false;
	}
	public Boolean hasRole(Role[] role)
	{
		for (GrantedAuthority auth:getAuthorities())
		{
			for(int i =0;i<role.length;i++)
			{
				Role r = role[i];
				
				if (r.getValue().equalsIgnoreCase(auth.getAuthority()))
				{
					return true;
				}
			}
		}
		return false;
	}
	public Boolean hasRole(String role)
	{
		for (GrantedAuthority auth:getAuthorities())
		{
			if (role.equalsIgnoreCase(auth.getAuthority()))
			{
				return true;
			}
		}
		return false;
	}
    public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	

	public Collection<MemberGrantedAuthority> getAuthoritiesSet() {
		return authoritiesSet;
	}

	public void addAuthority(MemberGrantedAuthority authority)
	{
		if (null == authoritiesSet)
		{
			authoritiesSet = new HashSet<MemberGrantedAuthority>();
		}
		authority.setMember(this);
		authoritiesSet.add(authority);
	}
	public void setAuthoritiesSet(Collection<MemberGrantedAuthority> authoritiesSet) {
		this.authoritiesSet = authoritiesSet;
	}
	@Transient
	@XmlTransient
	public Collection<GrantedAuthority> getAuthorities() {
		return null != authoritiesSet? new HashSet<GrantedAuthority>(authoritiesSet): new HashSet<GrantedAuthority>();
	}
	public void setAuthorities(Collection<GrantedAuthority> authorities)
	{
		
	}
	
	@Transient
	public boolean isAccountNonExpired() {
		return active;
	}
	@Transient
	public boolean isAccountNonLocked() {
		return active;
	}
	@Transient
	public boolean isCredentialsNonExpired() {

		return active;
	}
	@Transient
	public boolean isEnabled() {
	
		return active;
	}
//	@Transient
//	public Set<Group> getGroups() {
//		return groups;
//	}
//	@Transient
//	public void setGroups(Set<Group> groups) {
//		this.groups = groups;
//	}

	public long getMemberId() {
		return memberId;
	}

	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((active == null) ? 0 : active.hashCode());
		result = prime * result
				+ ((authoritiesSet == null) ? 0 : authoritiesSet.hashCode());
		result = prime * result
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result
				+ ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + (int) (memberId ^ (memberId >>> 32));
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((salt == null) ? 0 : salt.hashCode());
		result = prime * result
				+ ((updatedTime == null) ? 0 : updatedTime.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
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
		Member other = (Member) obj;
		if (active == null) {
			if (other.active != null)
				return false;
		} else if (!active.equals(other.active))
			return false;
		if (authoritiesSet == null) {
			if (other.authoritiesSet != null)
				return false;
		} else if (!authoritiesSet.equals(other.authoritiesSet))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (memberId != other.memberId)
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (salt == null) {
			if (other.salt != null)
				return false;
		} else if (!salt.equals(other.salt))
			return false;
		if (updatedTime == null) {
			if (other.updatedTime != null)
				return false;
		} else if (!updatedTime.equals(other.updatedTime))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", firstname=" + firstname
				+ ", username=" + username + "]";
	}
	
	

}
