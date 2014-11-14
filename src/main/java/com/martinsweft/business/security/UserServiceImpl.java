package com.martinsweft.business.security;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.martinsweft.business.member.MemberService;
import com.martinsweft.domain.user.Member;
import com.martinsweft.domain.user.MemberGrantedAuthority;
import com.mweft.domain.exception.MultipleUsersExistForUserName;

@Service("userDetailsManager")
public class UserServiceImpl implements UserService {
	



	private static final Logger log = Logger.getLogger(UserServiceImpl.class);
	//protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
	@Resource
	private MemberService memberService;	

	@Transactional
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {

		if (log.isDebugEnabled())
		{
			log.debug("Looking for:"+username);
		}
		Member user = null;
		try {
			user = memberService.getMemberByUserName(username);
		} catch (MultipleUsersExistForUserName e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		user.setAuthoritiesSet(memberService.loadUserAuthorities(user.getId()));
	
		
		// now load up the granted authorities
		
        return (UserDetails)user;
	}

	
	private Long[] loadUserOrganisations(Long userId)
	{
		List<Long> longs = 
         jdbcTemplate.query(organisationFinderSql, new Long[] {userId}, new RowMapper<Long>() {
            public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getLong(1);
            }
        });
		return longs.toArray(new Long[longs.size()]);
		
	}
	

    /**
     * Executes the SQL <tt>usersByUsernameQuery</tt> and returns a list of UserDetails objects.
     * There should normally only be one matching user.
     */
    protected List<Member> loadUsersByUsername(String username) {
        return jdbcTemplate.query(userByUsernameQuery, new String[] {username}, new RowMapper<Member>() {
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            	Member user = new Member();
            	user.setUsername(rs.getString(1));
            	user.setPassword(rs.getString(2));
            	user.setActive(rs.getBoolean(3));
//            	user.setId(rs.getLong(4));
//            	user.setInterfaceSecurityRequired(rs.getString(5));
//            	user.setInterfaceKey(rs.getString(6));
                return user;
            }

        });
    }
//    /**
//     * Loads authorities and their groups by executing the SQL from <tt>groupAuthoritiesByUsernameQuery</tt>.
//     *
//     * @return a list of GrantedAuthority objects for the user
//     */
//    public List<MemberGrantedAuthority> loadGroupAuthorities(Long userId, Long organisationId) {
//        return jdbcTemplate.query(groupAuthoritiesByUserIdQuery, new Long[] {userId, organisationId}, new RowMapper<UserAuthority>() {
//            public UserAuthority mapRow(ResultSet rs, int rowNum) throws SQLException {
//                 UserAuthority authority = new UserAuthority(rs.getString(3));
//                 authority.setGroupId(rs.getLong(1));
//                 authority.setGroupName(rs.getString(2));
//                 return authority;
//            }
//        });
//    }
    
	private void logMessage(String message)
	{
		if (log.isDebugEnabled())
		{
			log.debug(message);
		}
	}
//    /**
//     * Allows subclasses to add their own granted authorities to the list to be returned in the <tt>UserDetails</tt>.
//     *
//     * @param username the userId, for use by finder methods
//     * @param authorities the current granted authorities, as populated from the <code>authoritiesByUsername</code>
//     *        mapping
//     */
//    protected Set<GrantedAuthority> loadCustomAuthorities(Set<MemberGrantedAuthority> authorities) {
//    	
//    	List<GrantedAuthority> myAuth = new ArrayList<GrantedAuthority>(authorities);
//    	//i
//    	if (myAuth.size()< 1)
//    	{
//    		return new HashSet<GrantedAuthority>();
//    	}
//    	return new HashSet<GrantedAuthority>(roleHierarchy.getReachableGrantedAuthorities(myAuth));
//    }
//    
//    /**
//     * Recursive load of user groups. Will load any groups further down the hierarchy
//     * @param userId
//     * @return
//     */
//    public Set<Group> loadUserGroups(Long userId, Long organisationId)
//    {
//    	List<Group> groups = jdbcTemplate.query(userGroupsQuery, new Long[] {userId, organisationId}, new RowMapper<Group>() {
//            public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
//            	 Group group = new Group();
//            	 group.setId(rs.getLong(1));
//            	 group.setName(rs.getString(2));
//            	 group.setParentId(rs.getLong(3));
//                 return group;
//            }
//        });
//    	
//    	if (enableGroupHierachy)
//    	{
//    		// recursively go down the groups ladder and add them all.
//    		Set<Group> set = new HashSet<Group>();
//    		
//    		for (Group group:groups)
//    		{
//    			set.add(group);
//    			set.addAll(resolveGroupChildren(group.getId()));
//    		}
//    		return set;
//    	}
//    	if (log.isDebugEnabled())
//    	{
//    		log.debug("User has ["+groups.size()+"] groups");
//    	}
//    	return new HashSet<Group>(groups);
//    }
//    
//	@SuppressWarnings("unchecked")
//	@Transactional
//	public List<Group> resolveGroupChildren(final Long groupId) {
//		List<Group> childUsers = new ArrayList<Group>();
//		List<Group> groups = runGroupChildQuery( groupId);
//		if (groups.isEmpty()) {
//			return childUsers;
//		}
//		for (final Group user : groups) {
//			childUsers.add(user);
//			childUsers.addAll(resolveGroupChildren(user.getId()));
//		}
//		return childUsers;
//	}
//    private List<Group> runGroupChildQuery(final Long groupId)
//    {
//    	return jdbcTemplate.query(groupChildrenQuery, new Long[] {groupId}, new RowMapper<Group>() {
//            public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
//            	 Group group = new Group();
//            	 group.setId(rs.getLong(1));
//            	 group.setName(rs.getString(2));
//                 return group;
//            }
//        });
//    	
//    }
    
    
	private JdbcTemplate jdbcTemplate;
	private SessionFactory sessionFactory;
	private String userByUsernameQuery;
	private String groupChildrenQuery;
	private String authoritiesByUserIdQuery;
	private String groupAuthoritiesByUserIdQuery;
	private Boolean enableGroups;
	private Boolean enableUserAuthorities;
	private Boolean enableGroupHierachy;
	private String userGroupsQuery;
	private String organisationFinderSql;
	private RoleHierarchy roleHierarchy;
	
	public void setRoleHierarchy(RoleHierarchy roleHierarchy) {
		this.roleHierarchy = roleHierarchy;
	}

	public void setGroupChildrenQuery(String groupChildrenQuery) {
		this.groupChildrenQuery = groupChildrenQuery;
	}
	public void setOrganisationFinderSql(String organisationFinderSql) {
		this.organisationFinderSql = organisationFinderSql;
	}

	public void setUserGroupsQuery(String userGroupsQuery) {
		this.userGroupsQuery = userGroupsQuery;
	}

	public void setEnableGroupHierachy(Boolean enableGroupHierachy) {
		this.enableGroupHierachy = enableGroupHierachy;
	}



	public void setEnableUserAuthorities(Boolean enableUserAuthorities) {
		this.enableUserAuthorities = enableUserAuthorities;
	}

	public void setEnableGroups(Boolean enableGroups) {
		this.enableGroups = enableGroups;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	public void setUserByUsernameQuery(String userByUsernameQuery) {
		this.userByUsernameQuery = userByUsernameQuery;
	}


	public void setAuthoritiesByUserIdQuery(String authoritiesByUserIdQuery) {
		this.authoritiesByUserIdQuery = authoritiesByUserIdQuery;
	}


	public void setGroupAuthoritiesByUserIdQuery(
			String groupAuthoritiesByUserIdQuery) {
		this.groupAuthoritiesByUserIdQuery = groupAuthoritiesByUserIdQuery;
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

//	public void afterPropertiesSet() throws Exception {
//		Assert.notNull(jdbcTemplate, "jdbcTemplate is null");
//		Assert.notNull(roleHierarchy, "roleHierarchy is null");
//		Assert.notNull(sessionFactory, "sessionFactory is null");
//		
//	}

	private User loadUserByUserId(Long id)
	{
		return (User)sessionFactory.getCurrentSession().load(User.class, id);
	}
//	/*
//	 * Note that this method filters out super_admin users
//	 * @see net.activem.vouchernet.service.user.UserService#findUsersForOrganisation(java.lang.Long)
//	 */
//	public List<User> findUsersForOrganisationUsers(final Long organisationId) {
//		
//		List<Long> userIds =  jdbcTemplate.query(findUsersForOrganisationQuery, new Long[] {organisationId}, new RowMapper<Long>() {
//            public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
//                 return rs.getLong(1);
//            }
//        });
//		// no add the groups they are in
//		List<User> users = new ArrayList<User>();
//		User user = null;
//		for (Long id: userIds)
//		{
//			user = loadUserByUserId(id);
//		//	user.setGroups(loadUserGroups(id, organisationId));
//			users.add(user);
//			
//			
//		}
//		return users;
//	}
//	private final String findUsersForOrganisationQuery = "select vu.id " +
//			"from vouchernet_user vu left join vouchernet_user_authorities vua on vu.id = vua.user_id " +
//			"join group_members gm on gm.user_id =  vu.id " +
//			"join groups g on g.id =  gm.group_id and g.organisation_id = ?";
}
