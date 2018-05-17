/*
 * @(#) MyUserDetailsService.java  2011-3-23 上午09:04:31
 *
 * Copyright 2011 by Sparta 
 */

package avatar.base.security;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;


import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import avatar.base.security.dao.SysAuthoritiesResourcesDao;
import avatar.base.security.dao.SysUsersDao;
import avatar.base.security.entity.SysUsers;


/**
 *该类的主要作用是为Spring Security提供一个经过用户认证后的UserDetails。
 *该UserDetails包括用户名、密码、是否可用、是否过期等信息。
 *sparta 11/3/29
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private SysUsersDao sysUsersDao;
	
	@Autowired
	private SysAuthoritiesResourcesDao pubAuthoritiesResourcesDao;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserCache userCache;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		
		if( null == sysUsersDao ){
			sysUsersDao  = new SysUsersDao();
		}
		
		//得到用户的权限
		auths = sysUsersDao.loadUserAuthoritiesByName( username );
		
		//根据用户名取得一个SysUsers对象，以获取该用户的其他信息。
		SysUsers user = sysUsersDao.findByUserAccount( username );
			
		return new SysUsers( user.getUserId(), user.getUserAccount(), user.getUserName(),
				 user.getUserPassword(),user.getUserDesc(), true, false,
				 user.getUserDuty(), user.getUserDept(), user.getSubSystem(), 
				 new HashSet(0), true, true, true, auths);
	}
		
	//set PubUsersDao
	public void setSysUsersDao( SysUsersDao sysUsersDao ){
		this.sysUsersDao = sysUsersDao;
		
	}
	
	public SysUsersDao getSysUsersDao(){
		return sysUsersDao;
	}
	
	
	//set PubAuthoritiesResourcesHome
	public void setSysAuthoritiesResourcesDao( SysAuthoritiesResourcesDao pubAuthoritiesResourcesDao ){
		this.pubAuthoritiesResourcesDao = pubAuthoritiesResourcesDao;
		
	}
	
	public SysAuthoritiesResourcesDao getSysAuthoritiesResourcesDao(){
		return pubAuthoritiesResourcesDao;
		
	}
	
	//set DataSource
	public void setDataSource( DataSource dataSource ){
		this.dataSource = dataSource;
	}
	
	public DataSource getDataSource(){
		return dataSource;
	}
	
	//设置用户缓存功能。
    public void setUserCache(UserCache userCache) {
        this.userCache = userCache;
    }
    
    public UserCache getUserCache(){
    	return this.userCache;
    }
	
}
