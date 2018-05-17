/*
 * @(#) ResetPasswordBuz.java  2010-6-27 ����11:13:17
 *
 * Copyright 2010 by Sparta 
 */

package avatar.sys.buz;

import java.util.HashMap;
import java.util.List;

import avatar.base.security.entity.SysUsers;


/**
 *
 */
public interface UserBuz {
	
	/*
	 * �����������룬�ɹ��󷵻�true.
	 */
	public boolean resetPassword(String username, String password, String confirmPassword);
	
	/*
	 * �����û������������û�
	 * rolesList Ϊ�û���ҳ��ѡ��Ľ�ɫ�б�
	 */
	public boolean saveUser(SysUsers user, String rolesList );
	
	/*
	 * �����û������������û�
	 * rolesList Ϊ�û���ҳ��ѡ��Ľ�ɫ�б�
	 */
	public boolean updateUser(SysUsers user, String rolesList, String tempPassword );
	
	/*
	 * �����û�id(userId)ɾ���û�
	 */
	public boolean deleteUser(String userId);
	
	/*
	 * ��ѯ�û��б�
	 */
	public List<SysUsers> findUserLst( String qryUnit, 
			String qryUserAccount, String qryUsername, 
			String qryModule, String qryRole );
	
	/*
	 * ����id��ѯ�û�
	 */
	public SysUsers findUserById(String username);
	

	/*
	 * ���ݵ�λidȡ��λ����
	 */
	public String getUnitNameByUnitId( String unitId );
	
	/*
	 * ͨ��ϵͳģ��õ���ɫ�б�
	 */
	public HashMap getRolesMap( String xtmk );

}
