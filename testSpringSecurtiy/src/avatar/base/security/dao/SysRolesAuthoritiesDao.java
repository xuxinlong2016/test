package avatar.base.security.dao;

// Generated 2011-3-23 11:09:38 by Hibernate Tools 3.2.2.GA

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.criterion.Example;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import avatar.base.security.entity.*;

/**
 * Home object for domain model class SysRolesAuthorities.
 * @see avatar.base.security.entity.SysRolesAuthorities
 * @author Hibernate Tools
 */
public class SysRolesAuthoritiesDao extends HibernateDaoSupport {

	private static final Log log = LogFactory
			.getLog(SysRolesAuthoritiesDao.class);

	
	/**
	 * 根据角色id删除角色与权限之间旧有的关联关系。
	 *@author sparta 2011-4-20 上午10:14:29
	 *@param roleId
	 */
	public void deleteOldRoleAndPermissionRelative( String roleId ){
		
		try{
			
			getSession().createSQLQuery("delete Sys_Roles_Authorities where role_id='" + roleId + "'");
			
			log.info("删除角色与权限之间的关联关系成功！");
			
		}catch(RuntimeException re){
			log.error("删除角色与权限之间的关联关系失败！");
			throw re;
		}
		
	}
	
	public List<SysRolesAuthorities> getRolesAuthoritiesLst( String roleId ){
		
		try{
			
			List<SysRolesAuthorities> list = 
				getHibernateTemplate().find("from SysRolesAuthorities where role_id='" + roleId +"'");
				
			return list;
			
		}catch( RuntimeException ex ){
			log.error("提取角色跟权限之间的关联关系的实例列表失败！");
			throw ex;
		}
		
	}

	public void persist(SysRolesAuthorities transientInstance) {
		log.debug("persisting SysRolesAuthorities instance");
		try {
			getHibernateTemplate().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(SysRolesAuthorities instance) {
		log.debug("attaching dirty SysRolesAuthorities instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SysRolesAuthorities instance) {
		log.debug("attaching clean SysRolesAuthorities instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(SysRolesAuthorities persistentInstance) {
		log.debug("deleting SysRolesAuthorities instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SysRolesAuthorities merge(SysRolesAuthorities detachedInstance) {
		log.debug("merging SysRolesAuthorities instance");
		try {
			SysRolesAuthorities result = (SysRolesAuthorities) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public SysRolesAuthorities findById(long id) {
		log.debug("getting SysRolesAuthorities instance with id: " + id);
		try {
			SysRolesAuthorities instance = (SysRolesAuthorities) getHibernateTemplate().get("avatar.base.security.entity.SysRolesAuthorities", id);
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

	public List findByExample(SysRolesAuthorities instance) {
		log.debug("finding SysRolesAuthorities instance by example");
		try {
			List results = getSession().createCriteria(
					"avatar.base.security.entity.SysRolesAuthorities").add(Example.create(instance))
					.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
