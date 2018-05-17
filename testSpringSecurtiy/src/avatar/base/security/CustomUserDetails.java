/*
 * @(#) CustomUserDetails.java  2011-4-13 ����01:44:14
 *
 * Copyright 2011 by Sparta 
 */

package avatar.base.security;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *ʵ����UserDetails����չ������Ϣ������getSubSystem()������ sparta 11/4/13��
 */
public interface CustomUserDetails extends UserDetails {

	//�û�id
	public String getUserId();

	//�û��˻�
	public String getUserAccount();

	//�û���
	public String getUserName();

	//�û�����
	public String getUserPassword();

	//�û���������
	public String getUserDesc();

	//�û��Ƿ�����
	public boolean getEnabled();

	//�Ƿ񳬼��û�
	public Boolean getIssys();
	
	//�����ĵ�λ
	public String getUserDept();

	//�û�ְλ
	public String getUserDuty();

	//�û��ֹܵ���ϵͳ
	public String getSubSystem();
	
	//�û����Ӧ�Ľ�ɫ��
	public Set getSysUsersRoleses();

}
