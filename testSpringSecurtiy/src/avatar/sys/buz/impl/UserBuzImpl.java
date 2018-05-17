/*
 * @(#) ResetPasswordBuzImpl.java  2010-6-27 上午11:14:32
 *
 * Copyright 2010 by Sparta 
 */

package avatar.sys.buz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import avatar.base.security.dao.SysUsersDao;
import avatar.base.security.entity.SysUsers;
import avatar.sys.buz.UserBuz;


/**
 *
 */
public class UserBuzImpl implements UserBuz {
	
	private SysUsersDao sysUsersDao;

	/* 
	 * 重新设置密码，成功后返回true.
	 */
	@Override
	public boolean resetPassword(String username, String password, String confirmPassword) {
		
		
		
		return false;
	}

	/* 
	 * 保存用户及相对应的角色列表
	 */
	public boolean saveUser( SysUsers user, String rolesList ) {
		
		String[] roles = new String[]{""};
		
		if( null != rolesList && !"".equals(rolesList))
			//将以逗号分融的角色字串解析成数组。
			roles = rolesList.split(",");
		
		sysUsersDao.persist( user, roles  );
		return true;
	}
	
	
	/* 更新用户
	 * @see avatar.sys.buz.UserBuz#updateUser(avatar.base.security.entity.SysUsers, java.lang.String)
	 */
	@Override
	public boolean updateUser(SysUsers user, String rolesList, String tempPassword ) {
		
		
		String[] roles = new String[]{""};
		
		if( null != rolesList && !"".equals(rolesList))
			//将以逗号分融的角色字串解析成数组。
			roles = rolesList.split(",");
		
		sysUsersDao.attachDirty( user, roles, tempPassword  );
		return true;
		
	}
	
	/*
	 * 根据用户id(userId)删除用户
	 */
	public boolean deleteUser(String userId){
		sysUsersDao.delete( userId );
		return true;
	}
	
	/*
	 * 查询用户列表
	 */
	public List<SysUsers> findUserLst( String qryUnit, 
			String qryUserAccount, String qryUsername, 
			String qryModule, String qryRole ){
		
		return sysUsersDao.findUserLst( qryUnit, qryUserAccount, qryUsername,
				qryModule, qryRole );
	}
	
	/*
	 * 根据id查询用户
	 */
	public SysUsers findUserById(String userId){
		return sysUsersDao.findById(userId);
	}
	
	/*
	 * 根据单位id取单位名称
	 */
	public String getUnitNameByUnitId( String unitId ){
		return null;
	}
	
	/*
	 * 通过系统模块得到角色列表。
	 */
	public HashMap getRolesMap( String xtmk ){
		
		return sysUsersDao.getRolesMap( xtmk );
	}
	

//~ 注入*******************************************8
	
	/*
	 * 注入
	 */
	public SysUsersDao getSysUsersDao(){
		return sysUsersDao;
	}
	
	public void setSysUsersDao(SysUsersDao sysUsersDao){
		this.sysUsersDao = sysUsersDao;
	}




}
