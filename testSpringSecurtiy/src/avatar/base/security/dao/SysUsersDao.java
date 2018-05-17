package avatar.base.security.dao;

// Generated 2011-3-23 11:09:38 by Hibernate Tools 3.2.2.GA

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;


import avatar.base.security.entity.SysAuthorities;
import avatar.base.security.entity.SysRoles;
import avatar.base.security.entity.SysUsers;
import avatar.base.security.entity.SysUsersRoles;
import avatar.utils.Util;

/**
 * Home object for domain model class SysUsers.
 * 
 * @see avatar.base.security.entity.SysUsers
 * @author Hibernate Tools
 */
public class SysUsersDao extends HibernateDaoSupport {

	private static final Log log = LogFactory.getLog(SysUsersDao.class);

	// ���û�������������MD5����
	private PasswordEncoder passwordEncoder;

	// ͨ��SysrolesDao��ý�ɫʵ����
	private SysRolesDao sysRolesDao;

	@SuppressWarnings("null")
	public void persist(SysUsers transientInstance, String[] rolesArr ) {
		log.debug("�־û�һ���û�ʵ����");
		try {
			// �Ƚ����û���Ӧ�����н�ɫ
			Set<SysUsersRoles> userRoles = new HashSet<SysUsersRoles>();

			// ��ɫʵ��
			SysRoles role = null;

			for (String roleId : rolesArr) {

				if (null != roleId && !"".equals(roleId)) {			
					
					role = sysRolesDao.findById(roleId);
					
					SysUsersRoles userRole = new SysUsersRoles(Util
							.getPkId(), transientInstance, role, true);

					userRoles.add( userRole );
				}

			}

			transientInstance.setUserId( Util.getPkId()+"");
			
			// ����ͨ����ֵ�����Ա��洢�����ݿ�
			String newPassword = passwordEncoder.encodePassword(transientInstance.getUserPassword().trim(),transientInstance.getUserName().trim());

			transientInstance.setUserPassword(newPassword);

			if(null != userRoles )
				transientInstance.setSysUsersRoleses(userRoles);
			
			getHibernateTemplate().persist(transientInstance);
			
			log.debug("�־û�ʵ���ɹ���");
		} catch (RuntimeException re) {
			log.error("�־û��û�ʵ��ʧ�ܣ�", re);
			throw re;
		}
	}

	/*
	 * ����ǰ�û��޸�����
	 */
	// public void changePassword(String userId,String oldPassword, String
	// newPassword) {
	// UserDetails userDetails = (UserDetails) SecurityContextHolder
	// .getContext().getAuthentication().getPrincipal();
	// String username = userDetails.getUsername();
	//
	// String encodedOldPassword = passwordEncoder.encodePassword(oldPassword,
	// username);
	// String encodedNewPassword = passwordEncoder.encodePassword(newPassword,
	// username);
	// userDetailsManager.changePassword(encodedOldPassword,
	// encodedNewPassword);
	// }
	/*
	 * ��������һ���û�ʵ�������ұ����û��ͽ�ɫ֮��Ĺ�����ϵ��
	 */
	@SuppressWarnings("null")
	public void attachDirty(SysUsers instance, String[] rolesArr, 
			String tempPassword ) {
		log.debug("����һ���û�ʵ��");
		try {

			// �Ƚ����û���Ӧ�����н�ɫ
			Set<SysUsersRoles> userRoles = new HashSet<SysUsersRoles>();

			// ��ɫʵ��
			SysRoles role = new SysRoles();

			for (String roleId : rolesArr) {
					
					if (null != roleId && !"".equals(roleId)) {

						role = sysRolesDao.findById(roleId);

						userRoles.add((SysUsersRoles) new SysUsersRoles(Util
								.getPkId(), instance, role, true));
					}

			}
			

//			if( null == instance.getUserId() || "".equals( instance.getUserId()))
//				instance.setUserId( Util.getPkId()+"");
			
			//�������Ѿ�����
			if (tempPassword.equals(instance.getUserPassword())) {

				// ����ͨ����ֵ�����Ա��洢�����ݿ�
				String newPassword = passwordEncoder.encodePassword(instance
						.getUserPassword().trim(), instance.getUserName()
						.trim());

				instance.setUserPassword(newPassword);
			}

			if(null != userRoles )
				instance.setSysUsersRoleses(userRoles);

			getHibernateTemplate().saveOrUpdate(instance);

			log.debug("�����û�ʵ���ɹ���");

		} catch (RuntimeException re) {
			log.error("�����û�ʵ��ʧ�ܣ�", re);
			throw re;
		}
	}

	public void attachClean(SysUsers instance) {
		log.debug("attaching clean SysUsers instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(SysUsers persistentInstance) {
		log.debug("deleting SysUsers instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/*
	 * �����û�idɾ��һ���û���������ɾ��������һ���߼�ɾ������enabledΪfalse��
	 */
	public void delete(String userId) {
		log.debug("����һ���û�idɾ��һ���û�����ɫidΪ��" + userId);
		try {

			SysUsers user = findById(userId);
			user.setEnabled( false );
			getHibernateTemplate().saveOrUpdate(user);

			log.debug("ɾ���ɹ���");
		} catch (RuntimeException re) {

			log.error("ɾ��ʧ�ܣ�", re);
			throw re;
		}
	}

	public SysUsers merge(SysUsers detachedInstance) {
		log.debug("merging SysUsers instance");
		try {
			SysUsers result = (SysUsers) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public SysUsers findById(java.lang.String id) {
		log.debug("getting SysUsers instance with id: " + id);
		try {
			SysUsers instance = (SysUsers) getHibernateTemplate().get(
					"avatar.base.security.entity.SysUsers", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}


	public SysUsers getUser() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection  conn = DriverManager.getConnection("jdbc:Oracle:thin:@localhost:1521:orcl", "xinlong", "sa");
			Statement  stmt = conn.createStatement();  
			ResultSet  res = stmt.executeQuery("select * from sys_users");  
			 while(res.next())  
			 {  
				 String name = res.getString("user_id");
				 System.out.println(name);
			   /*  String USER_ID = res.getString("USER_ID");  
			     String USER_ACCOUNT = res.getString("USER_ACCOUNT");  
			     String USER_NAME = res.getString("USER_NAME");  
			     String USER_PASSWORD = res.getString("USER_PASSWORD");  
			     String USER_DESC = res.getString("USER_DESC");  
			     String USER_DUTY = res.getString("USER_DUTY"); 
			     String USER_DEPT = res.getString("USER_DEPT"); 
			     String SUB_SYSTEM = res.getString("SUB_SYSTEM"); 
			     int ENABLED = res.getInt("ENABLED"); 
			     int ISSYS = res.getInt("ISSYS");
			     System.out.println(USER_ID);*/
			 }
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return new SysUsers();
	}
	/**
	 * �����û��˺ŷ���SysUsersʵ������
	 * 
	 * @author sparta 2011-4-8 ����01:53:05
	 *@param userAccount
	 *            �û��˺ţ�����admin�ȡ�
	 *@return SysUsersʵ������
	 */
	public SysUsers findByUserAccount(String userAccount) {
		log.debug("����UserAccount����SysUsersʵ������: " + userAccount);
		try {
			SysUsers instance = (SysUsers) getHibernateTemplate().find(
					"from SysUsers where user_account='" + userAccount + "'")
					.get(0);
			if (instance == null) {
				log.debug("û����ƥ���SysUsersʵ������");
				instance = new SysUsers();
			} else {
				log.debug("��ƥ���SysUsersʵ�������ҵ���");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("findByUserAccount() ����", re);
			throw re;
		}
	}

	/*
	 * �õ��û��б������б���ѯ�������ֱ�Ϊ�û����ڵ�λ���û��˺š��û����ơ� �û����ڵ���ϵͳ�����û�������Ľ�ɫ��
	 */
	public List<SysUsers> findUserLst(String qryUnit, String qryUserAccount,
			String qryUsername, String qryModule, String qryRole) {

		List<SysUsers> list = null;
		
		String sql = "select users from SysUsers users where users.enabled = 1 ";
		
		//��ѯ���û�������Ľ�ɫ�б�
		if( null != qryRole && !"".equals( qryRole )){
		
			sql = "select users from SysUsers users, SysUsersRoles usersRoles "
				+ "where users.userId = usersRoles.sysUsers.userId and users.enabled = 1 ";
		}
		
		try {

			if (null != qryUnit && !"".equals(qryUnit)) {
				sql += "and users.userDept='" + qryUnit + "' ";
			}

			if (null != qryUserAccount && !"".equals(qryUserAccount)) {
				sql += "and users.userAccount='" + qryUserAccount + "' ";
			}

			if (null != qryUsername && !"".equals(qryUsername)) {
				sql += "and users.userName = '" + qryUsername + "' ";
			}

			if (null != qryModule && !"".equals(qryModule) && !"00".equals( qryModule )) {
				sql += "and users.subSystem='" + qryModule + "' ";
			}

			if (null != qryRole && !"".equals(qryRole) && !"00".equals( qryRole )) {
				sql += "and usersRoles.sysRoles.roleId ='" + qryRole + "'";
			}

			list = getHibernateTemplate().find(sql);

			return list;

		} catch (RuntimeException re) {
			log.error("findUserLst() ����", re);
			throw re;
		}

	}



	/*
	 * ͨ��ϵͳģ��õ���ɫ�б�
	 */
	public HashMap getRolesMap(String xtmk) {

		HashMap rolesMap = new HashMap();

		try {
			String hql = "from SysRoles where module='" + xtmk + "'";

			List<SysRoles> list = getHibernateTemplate().find(hql);

			for (SysRoles role : list) {
				rolesMap.put(role.getRoleId(), role.getRoleDesc());
			}
		} catch (RuntimeException re) {
			log.error("��ȡ��ɫMapʧ�ܣ�", re);
			throw re;
		}

		return rolesMap;
	}

	/**
	 *@author sparta 2011-3-30 ����03:51:48
	 *@param username
	 *@param session
	 *@return
	 */
	public List<GrantedAuthority> loadUserAuthoritiesByName(String username) {

		try {

			List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();

			List<String> query1 = loadUserAuthorities(username);

			for (String roleName : query1) {
				GrantedAuthorityImpl authority = new GrantedAuthorityImpl(
						roleName);
				auths.add(authority);
			}

			return auths;

		} catch (RuntimeException re) {
			log.error("find by authorities by username failed.", re);
			throw re;
		}
	}

	/**
	 *@author sparta 2011-3-30 ����03:51:48
	 *@param username
	 *@param session
	 *@return
	 */
	public List<String> loadUserAuthorities(final String username) {

		try {

			return this.getHibernateTemplate().executeFind(
					new HibernateCallback() {
						public List<SysAuthorities> doInHibernate(
								Session session) throws HibernateException,
								SQLException {
							Query query = session
									.createSQLQuery("select a.authority_name "
											+ "from Sys_Authorities a, Sys_Roles_Authorities b, Sys_Users_Roles c, Sys_Users d "
											+ "where   a.authority_id = b.authority_id and b.role_id = c.role_id  "
											+ "and c.user_id = d.user_id and  d.user_account = '"
											+ username + "'");

							return query.list();
						}
					});

		} catch (RuntimeException re) {
			log.error("find by authorities by username failed.", re);
			throw re;
		}
	}

	// ע������MD5����
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	// ע��sysRolesDao
	public void setSysRolesDao(SysRolesDao sysRolesDao) {
		this.sysRolesDao = sysRolesDao;
	}

	public SysRolesDao getSysRolesDao() {
		return sysRolesDao;
	}

}
