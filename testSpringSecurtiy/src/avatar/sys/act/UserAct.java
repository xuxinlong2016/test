/*
 * @(#) UserAct.java  2011-4-22 下午11:13:32
 *
 * Copyright 2011 by Sparta 
 */

package avatar.sys.act;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import avatar.base.configuration.AppinitWebBean;
import avatar.base.configuration.LoadInitParameters;
import avatar.base.security.entity.SysUsers;
import avatar.sys.buz.UserBuz;

import com.opensymphony.xwork2.ActionSupport;


/**
 *
 */
public class UserAct extends ActionSupport {


	private static final long serialVersionUID = 1L;

	private String message;
	
	private UserBuz userBuz;
	
	//用户实例
	private SysUsers user;
	
	//待修改或待删除的用户id
	private String userId;
	
	//待修改或待删除的用户名
	private String userName;
	
	//用户所在单位名称
	private String unitName;
	
	//查询的用户列表
	private List<SysUsers> usersList;
	
	//查询用户列表的各项查询条件:单位、账号、用户名、所属子系统、角色。
	private String qryUnit,qryUserAccount,qryUsername,qryModule,qryRole;
	
	//在页面为某用户选择的相对应的所有角色id（一个以逗号分隔的角色字符串）。
	private String rolesIdList;
	
	//用户的相关的系统模块的查询条件，一个模块id和模块名称的map
	private Map<String,String> moduleMap;
	
	//用户选择了系统模块之后，根据该系统模块提取不同的角色列表形成map(角色id为key，角色描述为value)。
	private Map<String,String> roleMap ;
	
	//临时密码(判断是否更新了用户的密码)
	private String tempPassword;
	
	/*
	 * 总览
	 * 
	 * 进页面的展示采用managerUser;
	 * 选择角色列表的Action，可以通过建立新的Action路径和角色选择窗口；
	 * 选择单位列表，可以使用单位Action及相关的页面，或新建；
	 * 增加用户addUser;
	 * 修改用户updateUser;
	 * 删除用户deleteUser;
	 * 查询用户queryUser;
	 * 当点击修改图片按钮获得个的待修改用户的实例findSingleUser;
	 * 导出用户exportUser;
	 * 查询条件中，若点击系统模块的下拉列表，触发selectModuleForUser;
	 */
	
//~***************************************************************
	
	/*
	 * 点击菜单首次进入页面。
	 */
	public String managerUser(){
		
		publicMethod();
	
		return SUCCESS;
	}

	/*
	 * 添加用户
	 */
	public String addUser() {

		if(user == null) {
			message = "您所要添加的用户不存在！";
			return ERROR;
		}
		
		if(userBuz.saveUser( user, rolesIdList ))
			message =  "用户 " + user.getUserName() + " 保存成功！";
		else message = "用户 " + user.getUserName() + " 保存失败！";
		
		publicMethod();
		
		return SUCCESS;
	}

	/*
	 * 更新用户
	 */
	public String updateUser(){
		if(user == null) {
			message = "您所要更新的用户不存在！";
			return ERROR;
		}
		
		if(userBuz.updateUser( user, rolesIdList, tempPassword ))
			message =  "用户 " + user.getUserName() + " 更新成功！";
		else message = "用户 " + user.getUserName() + " 更新失败！";
		
		publicMethod();
		
		return SUCCESS;
	}
	
	/*
	 * 删除用户
	 */
	public String deleteUser(){
		
		if( "".equals( userId ) || null == userId ) {
			message = "您所删除的用户不存在！";
			return ERROR;
		}
		
		if(	userBuz.deleteUser( userId ) )
			message =  "用户 " + userName + " 删除成功！";
		else message = "用户 " + userName + " 删除失败！";
		
		publicMethod();
		
		return SUCCESS;
	}
	
	/*
	 * 查找用户列表
	 */
	public String queryUser(){
		
//		sersList = userBuz.findUserLst( qryUnit, qryUserAccount, qryUsername, qryModule, qryRole );
		
		publicMethod();
		
		return SUCCESS;
	}
	
	
	/*
	 * 根据用户id查找单个用户
	 */
	public String findSingleUser(){
		
		user = userBuz.findUserById(userId);
		
		tempPassword = user.getUserPassword();
		
		//根据用户所在的单位id取得单位名称
		unitName = userBuz.getUnitNameByUnitId( user.getUserDept() );
		
		publicMethod();
		
		return SUCCESS;
	}
	
	/*
	 * 将用户导出Excel
	 */
	public String exportUser(){
		
		return SUCCESS;
	}
	
	/*
	 * 当用户选择系统模块时，获得该模块下所有的角色Map。
	 */
	public String selectRoleMapOnModuleForUser(){
		
		roleMap = userBuz.getRolesMap( qryModule );
		
		return SUCCESS;
	}
	
	//公共方法:进入页面后或进行用户增删改查后的参数初始化。
	public void publicMethod(){
		
		usersList = userBuz.findUserLst( qryUnit, qryUserAccount, qryUsername, qryModule, qryRole );
		
		roleMap = userBuz.getRolesMap( "01" );
		
		
		//从initParameters.xml配置文件中提取的子系统列表。
		if( moduleMap == null || moduleMap.size() <= 0 ){

			new LoadInitParameters().initPara();
			
			moduleMap = AppinitWebBean.getOption("xtmk");

		}
	}
	
	
//~**************************************************************
	
	// 返回成功或失败的消息
	public String getMessage() {
		return message;
	}
	
	public void setUserId( String userId ){
		this.userId = userId;
	}
	
	public String getUserId(){
		return userId;
	}

	public void setUserName( String userName ){
		this.userName = userName;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public void setQryUnit( String qryUnit ){
		this.qryUnit = qryUnit;
	}
	
	public String getQryUnit(){
		return qryUnit;
	}
	
	public void setQryUserAccount( String qryUserAccount ){
		this.qryUserAccount = qryUserAccount;
	}
	
	public String getQryUserAccount(){
		return qryUserAccount;
	}
	
	public void setQryUsername( String qryUsername ){
		this.qryUsername = qryUsername;
	}
	
	public String getQryUsername(){
		return qryUsername;
	}
	
	public void setQryModule( String qryModule ){
		this.qryModule = qryModule;
	}
	
	public String getQryModule(){
		return qryModule;
	}
	
	public void setQryRole( String qryRole ){
		this.qryRole = qryRole;
	}
	
	public String getQryRole(){
		return qryRole;
	}
	
	public void setUnitName( String unitName ){
		this.unitName = unitName;
	}
	
	public String getUnitName(){
		return unitName;
	}

	//从页面获得User对象
	public void setUser(SysUsers user){
		this.user = user;
	}
	
	public SysUsers getUser(){
		return user;
	}
	
	//用户列表传到页面
	public void setUsersList(List<SysUsers> usersList){
		this.usersList = usersList;
	}
	
	public List<SysUsers> getUsersList(){
		return usersList;
	}
	
	//将用户选中的角色列表传到页面
	public void setRolesIdList(String rolesIdList){
		this.rolesIdList = rolesIdList;
	}
	
	public String getRolesIdList(){
		return rolesIdList;
	}
	
	public void setRoleMap(Map roleMap){
		this.roleMap = roleMap;
		
	}
	
	public Map getRoleMap(){
		return roleMap;
	}
	
	public void setModuleMap(Map moduleMap){
		this.moduleMap = moduleMap;
		
	}
	
	public Map getModuleMap(){
		return moduleMap;
	}
	
	public void setTempPassword( String tempPassword){
		this.tempPassword = tempPassword;
		
	}
	
	public String getTempPassword(){
		return tempPassword;
	}
	
	//注入
	public UserBuz getUserBuz(){
		return userBuz;
	}
	
	public void setUserBuz(UserBuz userBuz){
		this.userBuz = userBuz;
	}
	
	public static void main(String[] args) {
		
		String[] roles =  new String[] {"role1","role2","role3",};
		System.out.println("@@@@@@@@@@@@@@@@ roles.toString() =" + roles.toString());
	}

}
