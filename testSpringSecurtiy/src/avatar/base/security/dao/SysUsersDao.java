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

	// 对用户输入的密码进行MD5编码
	private PasswordEncoder passwordEncoder;

	// 通过SysrolesDao获得角色实例。
	private SysRolesDao sysRolesDao;

	@SuppressWarnings("null")
	public void persist(SysUsers transientInstance, String[] rolesArr ) {
		log.debug("持久化一个用户实例！");
		try {
			// 先将该用户对应的所有角色
			Set<SysUsersRoles> userRoles = new HashSet<SysUsersRoles>();

			// 角色实例
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
			
			// 密码通过盐值加密以备存储入数据库
			String newPassword = passwordEncoder.encodePassword(transientInstance.getUserPassword().trim(),transientInstance.getUserName().trim());

			transientInstance.setUserPassword(newPassword);

			if(null != userRoles )
				transientInstance.setSysUsersRoleses(userRoles);
			
			getHibernateTemplate().persist(transientInstance);
			
			log.debug("持久化实例成功！");
		} catch (RuntimeException re) {
			log.error("持久化用户实例失败！", re);
			throw re;
		}
	}

	/*
	 * 允许当前用户修改密码
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
	 * 保存或更新一个用户实例，并且保存用户和角色之间的关联关系。
	 */
	@SuppressWarnings("null")
	public void attachDirty(SysUsers instance, String[] rolesArr, 
			String tempPassword ) {
		log.debug("更新一个用户实例");
		try {

			// 先将该用户对应的所有角色
			Set<SysUsersRoles> userRoles = new HashSet<SysUsersRoles>();

			// 角色实例
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
			
			//若密码已经更新
			if (tempPassword.equals(instance.getUserPassword())) {

				// 密码通过盐值加密以备存储入数据库
				String newPassword = passwordEncoder.encodePassword(instance
						.getUserPassword().trim(), instance.getUserName()
						.trim());

				instance.setUserPassword(newPassword);
			}

			if(null != userRoles )
				instance.setSysUsersRoleses(userRoles);

			getHibernateTemplate().saveOrUpdate(instance);

			log.debug("更新用户实例成功！");

		} catch (RuntimeException re) {
			log.error("更新用户实例失败！", re);
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
	 * 根据用户id删除一个用户，非物理删除，而是一个逻辑删除，置enabled为false。
	 */
	public void delete(String userId) {
		log.debug("根据一个用户id删除一个用户，角色id为：" + userId);
		try {

			SysUsers user = findById(userId);
			user.setEnabled( false );
			getHibernateTemplate().saveOrUpdate(user);

			log.debug("删除成功！");
		} catch (RuntimeException re) {

			log.error("删除失败！", re);
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
	 * 根据用户账号返回SysUsers实例对象。
	 * 
	 * @author sparta 2011-4-8 下午01:53:05
	 *@param userAccount
	 *            用户账号，比如admin等。
	 *@return SysUsers实例对象。
	 */
	public SysUsers findByUserAccount(String userAccount) {
		log.debug("根据UserAccount查找SysUsers实例对象: " + userAccount);
		try {
			SysUsers instance = (SysUsers) getHibernateTemplate().find(
					"from SysUsers where user_account='" + userAccount + "'")
					.get(0);
			if (instance == null) {
				log.debug("没有相匹配的SysUsers实例对象！");
				instance = new SysUsers();
			} else {
				log.debug("相匹配的SysUsers实例对象被找到！");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("findByUserAccount() 错误！", re);
			throw re;
		}
	}

	/*
	 * 得到用户列表，参数列表（查询条件）分别为用户所在单位、用户账号、用户名称、 用户所在的子系统、跟用户相关联的角色。
	 */
	public List<SysUsers> findUserLst(String qryUnit, String qryUserAccount,
			String qryUsername, String qryModule, String qryRole) {

		List<SysUsers> list = null;
		
		String sql = "select users from SysUsers users where users.enabled = 1 ";
		
		//查询与用户相关联的角色列表。
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
			log.error("findUserLst() 错误！", re);
			throw re;
		}

	}



	/*
	 * 通过系统模块得到角色列表。
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
			log.error("提取角色Map失败！", re);
			throw re;
		}

		return rolesMap;
	}

	/**
	 *@author sparta 2011-3-30 下午03:51:48
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
	 *@author sparta 2011-3-30 下午03:51:48
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

	// 注入密码MD5编码
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	// 注入sysRolesDao
	public void setSysRolesDao(SysRolesDao sysRolesDao) {
		this.sysRolesDao = sysRolesDao;
	}

	public SysRolesDao getSysRolesDao() {
		return sysRolesDao;
	}

}
