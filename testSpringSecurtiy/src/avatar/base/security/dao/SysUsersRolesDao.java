package avatar.base.security.dao;

// Generated 2011-3-23 11:09:38 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import avatar.base.security.entity.*;

/**
 * Home object for domain model class SysUsersRoles.
 * @see avatar.base.security.entity.SysUsersRoles
 * @author Hibernate Tools
 */
public class SysUsersRolesDao extends HibernateDaoSupport {

	private static final Log log = LogFactory.getLog(SysUsersRolesDao.class);

	public void persist(SysUsersRoles transientInstance) {
		log.debug("persisting SysUsersRoles instance");
		try {
			getHibernateTemplate().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(SysUsersRoles instance) {
		log.debug("attaching dirty SysUsersRoles instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SysUsersRoles instance) {
		log.debug("attaching clean SysUsersRoles instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(SysUsersRoles persistentInstance) {
		log.debug("deleting SysUsersRoles instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SysUsersRoles merge(SysUsersRoles detachedInstance) {
		log.debug("merging SysUsersRoles instance");
		try {
			SysUsersRoles result = (SysUsersRoles) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public SysUsersRoles findById(long id) {
		log.debug("getting SysUsersRoles instance with id: " + id);
		try {
			SysUsersRoles instance = (SysUsersRoles) getHibernateTemplate().get("avatar.base.security.entity.SysUsersRoles", id);
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

	public List findByExample(SysUsersRoles instance) {
		log.debug("finding SysUsersRoles instance by example");
		try {
			List results = getSession().createCriteria(
					"avatar.base.security.entity.SysUsersRoles").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	/**
	 * 根据角色id返回用户和角色的关联关系的实例列表。
	 *@author sparta 2011-4-20 下午09:41:40
	 *@param roleId
	 *@return
	 */
	public List<SysUsersRoles> getUsersRolesLst( String roleId ){
		
		try{
			
			List<SysUsersRoles> list = 
				getHibernateTemplate().find("from SysUsersRoles where role_id='" + roleId +"'");
			
			return list;
			
		}catch( RuntimeException ex){
			
			log.error("提取用户跟角色的关联关系的实例列表失败！");
			throw ex;
		}
		
	}
	
	
}
