package avatar.base.security.dao;

// Generated 2011-3-23 11:09:38 by Hibernate Tools 3.2.2.GA

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.StringUtils;
import org.springframework.util.SystemPropertyUtils;

import avatar.base.security.entity.SysAuthorities;
import avatar.base.security.entity.SysRoles;
import avatar.base.security.entity.SysRolesAuthorities;
import avatar.base.security.entity.SysUsersRoles;
import avatar.utils.Util;

/**
 * Home object for domain model class SysRoles.
 * 
 * @see avatar.base.security.entity.SysRoles
 * @author Hibernate Tools
 */
public class SysRolesDao extends HibernateDaoSupport {

	private static final Log log = LogFactory.getLog(SysRolesDao.class);

	
	//权限dao
	private SysAuthoritiesDao sysAuthoritiesDao;
	
	//角色和权限的对应关系的dao
	private SysRolesAuthoritiesDao sysRolesAuthoritiesDao;
	
	//用户和角色对应关系的dao
	private SysUsersRolesDao sysUsersRolesDao;

	/*
	 * 将从页面返回的角色对象进行保存。
	 */
	public void persist(SysRoles transientInstance) {

		log.debug("角色 " + transientInstance.getRoleName() + " 持久化!");

		try {

			// 为写入的角色设置唯一id
			transientInstance.setRoleId(Util.getPkId() + "");
			
			String roleName = transientInstance.getRoleName();

			// 当角色标识前缀不为"ROLE_"时，要添加该标识。
			if (roleName.startsWith("ROLE")) {

				if (!roleName.startsWith("ROLE_")) {
					roleName = roleName.substring(0, 4) + "_"
							+ roleName.substring(4);
					transientInstance.setRoleName(roleName);
				}

			} else {
				transientInstance.setRoleName("ROLE_" + roleName);
			}

			// 持久化
			getHibernateTemplate().persist(transientInstance);

			log.debug("角色 " + transientInstance.getRoleName() + " 持久化成功！");

		} catch (RuntimeException re) {

			log.error("角色 " + transientInstance.getRoleName() + " 持久化失败！", re);
			throw re;

		}
	}

	/*
	 * 对角色实例进行更新。
	 */
	public void attachDirty(SysRoles instance) {

		log.debug("更新角色 " + instance.getRoleName() + "!");

		try {

			getHibernateTemplate().saveOrUpdate(instance);
			
			log.debug("更新角色 " + instance.getRoleName() + "成功!");

		} catch (RuntimeException re) {

			log.error("更新角色 " + instance.getRoleName() + "失败!", re);
			throw re;

		}
	}

	/*
	 * 将传入的对象置为Transent状态。
	 */
	public void attachClean(SysRoles instance) {
		log.debug("attaching clean SysRoles instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(SysRoles persistentInstance) {
		log.debug("deleting SysRoles instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/**
	 * 根据角色id删除该角色。
	 * 
	 * @author sparta 2011-4-10 下午03:31:22
	 *@param roleId
	 */
	public void delete(String roleId) {
		log.debug("根据角色id删除该角色！角色id为 ： " + roleId);
		try {
			
			SysRoles role = findById( roleId );
			
			getHibernateTemplate().delete( role );
		
			log.debug("角色id" + roleId + "删除成功！");
		} catch (RuntimeException re) {
			log.error("角色id" + roleId + "删除失败！", re);
			throw re;
		}
	}

	/*
	 * 将传入的detached(托管)状态的对象的属性复制到持久化对象中，并返回该持久化对象。 如果该session中没有关联的持久化对象，加载一个。
	 * 如果传入对象未保存，保存一个副本并作为持久对象返回，传入对象依然保持detached状态。
	 */

	public SysRoles merge(SysRoles detachedInstance) {
		log.debug("merging SysRoles instance");
		try {
			SysRoles result = (SysRoles) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/*
	 * 通过id得到一个角色实例。
	 */
	public SysRoles findById( String id) {

		log.debug("得到一个角色实例通过下面的id: " + id);

		try {

			SysRoles instance = (SysRoles)getHibernateTemplate().find(
					"from SysRoles where roleId= '" + id + "'").get(0);

			if (instance == null) {
				log.debug("查找的角色实例不存在！");
			} else {
				log.debug("成功查找到该实例！");
			}

			return instance;

		} catch (RuntimeException re) {
			log.error("查找失败!", re);
			throw re;
		}
	}

	/*
	 * Hibernate工具生成的代码。
	 */
	public List findByExample(SysRoles instance) {
		log.debug("finding SysRoles instance by example");
		try {
			List results = ((Criteria) getHibernateTemplate().find(
					"avatar.base.security.entity.SysRoles")).list();
			
			
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	/*
	 * 试验采用本地main()方法运行返回的list结果是什么？
	 * 经过验证发现，实际上这个功能返回的列表中仅有一条数据，即SysRoles的实例。
	 * 通过list.get(0)将该实例取出，然后转型到SysRoles，进行getRoleName()等操作即可。
	 * 并非此前我想象的直接将各个字段的内容提出来形成一个列表。 11/4/16 15:09
	 */
	public List findByExampleByLocal(SysRoles instance, SessionFactory sessionFactory ) {
		log.debug("finding SysRoles instance by example");
		try {
			
			List results = sessionFactory.openSession().createCriteria(
			"avatar.base.security.entity.SysRoles").add(
			Example.create(instance)).list();
			
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	/*
	 * 试验使用本地main()方法将实例的字段提取成Map。
	 */
	public List findByExampleByLocalForMap( Session session ) {
		log.debug("finding SysRoles instance by example");
		try {
			
			List results = session
		     .createSQLQuery("select role_id,role_Name from Sys_Roles")
		     .setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();

			
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	

	/**
	 * 根据平台中的子系统返回角色列表。
	 * 
	 * @author sparta 2011-4-10 下午03:37:38
	 *@return
	 */
	public List<SysRoles> findRolesLst(String xtmk) {
		try {
			
			String hql = "";
			
			//若xtmk为空，则返回所有角色列表。
			if( null == xtmk ){
				hql = "from SysRoles";
			}else{
				hql = "from SysRoles where module='" + xtmk + "'";
			}
			
			List<SysRoles> results = getHibernateTemplate().find(hql);

			return results;

		} catch (RuntimeException re) {

			throw re;
		}
	}

	/**
	 * 得到权限列表。
	 */
	public List<SysAuthorities> getPermissionList() {
		try {

			List<SysAuthorities> results = getHibernateTemplate().find(
					"from SysAuthorities");

			return results;

		} catch (RuntimeException re) {

			throw re;
		}
	}

	/**
	 * 根据角色id，得到以权限id为键，"true"为值的Map。
	 * 
	 * @author sparta 2011-4-12 上午08:00:21
	 *@param roleId
	 *@return
	 */
	public HashMap getSelectedPermissionMap(String roleId) {
		try {
		
			List<String> results = getSession().createSQLQuery("select a.authority_id " +
					"from Sys_Authorities a, Sys_Roles_Authorities b " +
					"where a.authority_id = b.authority_id and b.role_id='"+ roleId +"'").list();

			HashMap<String,String> hashMap = new HashMap<String,String>(0);

			for (String authorityId : results) {
				hashMap.put( authorityId, "true" );
			}
			
			return hashMap;
		} catch (RuntimeException re) {

			throw re;
		}
	}

	/*
	 * 保存角色和权限之间一对多的关系。
	 */
	public boolean savePermissionAndRole(String roleId, String[] permisskey) {

		SysRoles sysRoles = findById(roleId);
		SysAuthorities sysAuthorities = null;
		SysRolesAuthorities sysRolesAuthorities;
		
		//在重新设置之前先删除之前所拥有的全部对应关系
		sysRolesAuthoritiesDao.deleteOldRoleAndPermissionRelative( roleId );

		try {
			/*
			 * 将用户在前台通过checkbox选中的所有权限id提取出来。
			 * 在进行保存之前，首先要通过id提取该权限的实例，
			 * 为角色权限关联表sysRolesAuthorities的setSysAuthorities()提供数据。
			 * 最后通过hibernate的save()方法保存新建的sysRolesAuthorities实例。
			 */
			for (String perId : permisskey) {
				
				sysAuthorities = sysAuthoritiesDao.findById(perId);
				
				/*
				 * 生成一个新的实例很重要，否则会报
				 * identifier of an instance of xxx  
				 * was altered from xxx to xxx"的异常。
				 */
				sysRolesAuthorities = new SysRolesAuthorities();
				sysRolesAuthorities.setId(Util.getPkId());
				sysRolesAuthorities.setSysRoles(sysRoles);
				sysRolesAuthorities.setSysAuthorities(sysAuthorities);
				sysRolesAuthorities.setEnabled(true);

				getHibernateTemplate().save(sysRolesAuthorities);

			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

		return true;
	}
	
	
	//注入
	public void setSysAuthoritiesDao( SysAuthoritiesDao sysAuthoritiesDao){
		this.sysAuthoritiesDao = sysAuthoritiesDao;
	}
	
	public SysAuthoritiesDao getSysAuthoritiesDao(){
		return sysAuthoritiesDao;
	}
	
	public void setSysRolesAuthoritiesDao( SysRolesAuthoritiesDao sysRolesAuthoritiesDao){
		this.sysRolesAuthoritiesDao = sysRolesAuthoritiesDao;
	}
	
	public SysRolesAuthoritiesDao getSysRolesAuthoritiesDao(){
		return sysRolesAuthoritiesDao;
	}
	
	public SysUsersRolesDao getSysUsersRolesDao(){
		return sysUsersRolesDao;
	}
	
	public void setSysUsersRolesDao( SysUsersRolesDao sysUsersRolesDao){
		this.sysUsersRolesDao = sysUsersRolesDao;
	}
	

	//本地测试入口。
	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		SessionFactory sessionFactory = (SessionFactory) context
				.getBean("sessionFactory");

		Session session = sessionFactory.openSession();
		System.out.println(session);
		
		
	}

}
