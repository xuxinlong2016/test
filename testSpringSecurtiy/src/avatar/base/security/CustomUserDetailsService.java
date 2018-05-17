/*
 * @(#) MyUserDetailsService.java  2011-3-23 ����09:04:31
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
 *�������Ҫ������ΪSpring Security�ṩһ�������û���֤���UserDetails��
 *��UserDetails�����û��������롢�Ƿ���á��Ƿ���ڵ���Ϣ��
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
		
		//�õ��û���Ȩ��
		auths = sysUsersDao.loadUserAuthoritiesByName( username );
		
		//�����û���ȡ��һ��SysUsers�����Ի�ȡ���û���������Ϣ��
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
	
	//�����û����湦�ܡ�
    public void setUserCache(UserCache userCache) {
        this.userCache = userCache;
    }
    
    public UserCache getUserCache(){
    	return this.userCache;
    }
	
}
