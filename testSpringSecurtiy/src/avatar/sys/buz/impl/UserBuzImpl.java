/*
 * @(#) ResetPasswordBuzImpl.java  2010-6-27 ����11:14:32
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
	 * �����������룬�ɹ��󷵻�true.
	 */
	@Override
	public boolean resetPassword(String username, String password, String confirmPassword) {
		
		
		
		return false;
	}

	/* 
	 * �����û������Ӧ�Ľ�ɫ�б�
	 */
	public boolean saveUser( SysUsers user, String rolesList ) {
		
		String[] roles = new String[]{""};
		
		if( null != rolesList && !"".equals(rolesList))
			//���Զ��ŷ��ڵĽ�ɫ�ִ����������顣
			roles = rolesList.split(",");
		
		sysUsersDao.persist( user, roles  );
		return true;
	}
	
	
	/* �����û�
	 * @see avatar.sys.buz.UserBuz#updateUser(avatar.base.security.entity.SysUsers, java.lang.String)
	 */
	@Override
	public boolean updateUser(SysUsers user, String rolesList, String tempPassword ) {
		
		
		String[] roles = new String[]{""};
		
		if( null != rolesList && !"".equals(rolesList))
			//���Զ��ŷ��ڵĽ�ɫ�ִ����������顣
			roles = rolesList.split(",");
		
		sysUsersDao.attachDirty( user, roles, tempPassword  );
		return true;
		
	}
	
	/*
	 * �����û�id(userId)ɾ���û�
	 */
	public boolean deleteUser(String userId){
		sysUsersDao.delete( userId );
		return true;
	}
	
	/*
	 * ��ѯ�û��б�
	 */
	public List<SysUsers> findUserLst( String qryUnit, 
			String qryUserAccount, String qryUsername, 
			String qryModule, String qryRole ){
		
		return sysUsersDao.findUserLst( qryUnit, qryUserAccount, qryUsername,
				qryModule, qryRole );
	}
	
	/*
	 * ����id��ѯ�û�
	 */
	public SysUsers findUserById(String userId){
		return sysUsersDao.findById(userId);
	}
	
	/*
	 * ���ݵ�λidȡ��λ����
	 */
	public String getUnitNameByUnitId( String unitId ){
		return null;
	}
	
	/*
	 * ͨ��ϵͳģ��õ���ɫ�б�
	 */
	public HashMap getRolesMap( String xtmk ){
		
		return sysUsersDao.getRolesMap( xtmk );
	}
	

//~ ע��*******************************************8
	
	/*
	 * ע��
	 */
	public SysUsersDao getSysUsersDao(){
		return sysUsersDao;
	}
	
	public void setSysUsersDao(SysUsersDao sysUsersDao){
		this.sysUsersDao = sysUsersDao;
	}




}
