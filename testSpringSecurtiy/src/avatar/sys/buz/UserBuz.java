/*
 * @(#) ResetPasswordBuz.java  2010-6-27 上午11:13:17
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
	 * 重新设置密码，成功后返回true.
	 */
	public boolean resetPassword(String username, String password, String confirmPassword);
	
	/*
	 * 根据用户对象来保存用户
	 * rolesList 为用户在页面选择的角色列表。
	 */
	public boolean saveUser(SysUsers user, String rolesList );
	
	/*
	 * 根据用户对象来更新用户
	 * rolesList 为用户在页面选择的角色列表。
	 */
	public boolean updateUser(SysUsers user, String rolesList, String tempPassword );
	
	/*
	 * 根据用户id(userId)删除用户
	 */
	public boolean deleteUser(String userId);
	
	/*
	 * 查询用户列表
	 */
	public List<SysUsers> findUserLst( String qryUnit, 
			String qryUserAccount, String qryUsername, 
			String qryModule, String qryRole );
	
	/*
	 * 根据id查询用户
	 */
	public SysUsers findUserById(String username);
	

	/*
	 * 根据单位id取单位名称
	 */
	public String getUnitNameByUnitId( String unitId );
	
	/*
	 * 通过系统模块得到角色列表。
	 */
	public HashMap getRolesMap( String xtmk );

}
