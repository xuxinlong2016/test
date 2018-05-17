/*
 * @(#) UserAct.java  2011-4-22 ����11:13:32
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
	
	//�û�ʵ��
	private SysUsers user;
	
	//���޸Ļ��ɾ�����û�id
	private String userId;
	
	//���޸Ļ��ɾ�����û���
	private String userName;
	
	//�û����ڵ�λ����
	private String unitName;
	
	//��ѯ���û��б�
	private List<SysUsers> usersList;
	
	//��ѯ�û��б�ĸ����ѯ����:��λ���˺š��û�����������ϵͳ����ɫ��
	private String qryUnit,qryUserAccount,qryUsername,qryModule,qryRole;
	
	//��ҳ��Ϊĳ�û�ѡ������Ӧ�����н�ɫid��һ���Զ��ŷָ��Ľ�ɫ�ַ�������
	private String rolesIdList;
	
	//�û�����ص�ϵͳģ��Ĳ�ѯ������һ��ģ��id��ģ�����Ƶ�map
	private Map<String,String> moduleMap;
	
	//�û�ѡ����ϵͳģ��֮�󣬸��ݸ�ϵͳģ����ȡ��ͬ�Ľ�ɫ�б��γ�map(��ɫidΪkey����ɫ����Ϊvalue)��
	private Map<String,String> roleMap ;
	
	//��ʱ����(�ж��Ƿ�������û�������)
	private String tempPassword;
	
	/*
	 * ����
	 * 
	 * ��ҳ���չʾ����managerUser;
	 * ѡ���ɫ�б��Action������ͨ�������µ�Action·���ͽ�ɫѡ�񴰿ڣ�
	 * ѡ��λ�б�����ʹ�õ�λAction����ص�ҳ�棬���½���
	 * �����û�addUser;
	 * �޸��û�updateUser;
	 * ɾ���û�deleteUser;
	 * ��ѯ�û�queryUser;
	 * ������޸�ͼƬ��ť��ø��Ĵ��޸��û���ʵ��findSingleUser;
	 * �����û�exportUser;
	 * ��ѯ�����У������ϵͳģ��������б�����selectModuleForUser;
	 */
	
//~***************************************************************
	
	/*
	 * ����˵��״ν���ҳ�档
	 */
	public String managerUser(){
		
		publicMethod();
	
		return SUCCESS;
	}

	/*
	 * ����û�
	 */
	public String addUser() {

		if(user == null) {
			message = "����Ҫ��ӵ��û������ڣ�";
			return ERROR;
		}
		
		if(userBuz.saveUser( user, rolesIdList ))
			message =  "�û� " + user.getUserName() + " ����ɹ���";
		else message = "�û� " + user.getUserName() + " ����ʧ�ܣ�";
		
		publicMethod();
		
		return SUCCESS;
	}

	/*
	 * �����û�
	 */
	public String updateUser(){
		if(user == null) {
			message = "����Ҫ���µ��û������ڣ�";
			return ERROR;
		}
		
		if(userBuz.updateUser( user, rolesIdList, tempPassword ))
			message =  "�û� " + user.getUserName() + " ���³ɹ���";
		else message = "�û� " + user.getUserName() + " ����ʧ�ܣ�";
		
		publicMethod();
		
		return SUCCESS;
	}
	
	/*
	 * ɾ���û�
	 */
	public String deleteUser(){
		
		if( "".equals( userId ) || null == userId ) {
			message = "����ɾ�����û������ڣ�";
			return ERROR;
		}
		
		if(	userBuz.deleteUser( userId ) )
			message =  "�û� " + userName + " ɾ���ɹ���";
		else message = "�û� " + userName + " ɾ��ʧ�ܣ�";
		
		publicMethod();
		
		return SUCCESS;
	}
	
	/*
	 * �����û��б�
	 */
	public String queryUser(){
		
//		sersList = userBuz.findUserLst( qryUnit, qryUserAccount, qryUsername, qryModule, qryRole );
		
		publicMethod();
		
		return SUCCESS;
	}
	
	
	/*
	 * �����û�id���ҵ����û�
	 */
	public String findSingleUser(){
		
		user = userBuz.findUserById(userId);
		
		tempPassword = user.getUserPassword();
		
		//�����û����ڵĵ�λidȡ�õ�λ����
		unitName = userBuz.getUnitNameByUnitId( user.getUserDept() );
		
		publicMethod();
		
		return SUCCESS;
	}
	
	/*
	 * ���û�����Excel
	 */
	public String exportUser(){
		
		return SUCCESS;
	}
	
	/*
	 * ���û�ѡ��ϵͳģ��ʱ����ø�ģ�������еĽ�ɫMap��
	 */
	public String selectRoleMapOnModuleForUser(){
		
		roleMap = userBuz.getRolesMap( qryModule );
		
		return SUCCESS;
	}
	
	//��������:����ҳ��������û���ɾ�Ĳ��Ĳ�����ʼ����
	public void publicMethod(){
		
		usersList = userBuz.findUserLst( qryUnit, qryUserAccount, qryUsername, qryModule, qryRole );
		
		roleMap = userBuz.getRolesMap( "01" );
		
		
		//��initParameters.xml�����ļ�����ȡ����ϵͳ�б�
		if( moduleMap == null || moduleMap.size() <= 0 ){

			new LoadInitParameters().initPara();
			
			moduleMap = AppinitWebBean.getOption("xtmk");

		}
	}
	
	
//~**************************************************************
	
	// ���سɹ���ʧ�ܵ���Ϣ
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

	//��ҳ����User����
	public void setUser(SysUsers user){
		this.user = user;
	}
	
	public SysUsers getUser(){
		return user;
	}
	
	//�û��б���ҳ��
	public void setUsersList(List<SysUsers> usersList){
		this.usersList = usersList;
	}
	
	public List<SysUsers> getUsersList(){
		return usersList;
	}
	
	//���û�ѡ�еĽ�ɫ�б���ҳ��
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
	
	//ע��
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
