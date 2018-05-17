package avatar.base.security.dao;

// Generated 2011-3-23 11:09:38 by Hibernate Tools 3.2.2.GA



import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import avatar.base.security.entity.*;
import avatar.utils.Util;


/**
 * Home object for domain model class SysUsers.
 * @see avatar.base.security.entity.SysUsers
 * @author Hibernate Tools
 */
public class SysResourcesDao extends HibernateDaoSupport {

	private static final Log log = LogFactory.getLog(SysUsersDao.class);


	/*
	 * �־û���Դ��
	 */
	public void persist( SysResources transientInstance ) {
		
		log.debug("�־û�һ����Դʵ����");
		try {
			
			transientInstance.setResourceId( Util.getPkId()+"" );
			
			getHibernateTemplate().persist( transientInstance );
			
			log.debug("�־û���Դʵ���ɹ���");
			
		} catch (RuntimeException re) {
			log.error("�־û���Դʵ��ʧ�ܣ�", re);
			throw re;
		}
	}
	

	/*
	 * ��������һ����Դʵ����
	 */
	public void attachDirty( SysResources instance ) {
		log.debug("����һ����Դʵ��");
		try {
			
			getHibernateTemplate().saveOrUpdate( instance );
			
			log.debug("������Դʵ���ɹ���");
			
		} catch (RuntimeException re) {
			log.error("������Դʵ��ʧ�ܣ�", re);
			throw re;
		}
	}
	
	/*
	 * ������Դidɾ��һ����Դ��������ɾ��������һ���߼�ɾ������enabledΪfalse��
	 */
	public void delete( String resourceId ) {
		log.debug("����һ����Դidɾ��һ���û�����ԴidΪ��" + resourceId );
		try {
			
			SysResources resource = findById( resourceId );
			
			resource.setEnabled( 0 );
//			getHibernateTemplate().saveOrUpdate( resource );
			
			log.debug("ɾ���ɹ���");
		} catch (RuntimeException re) {
			
			log.error("ɾ��ʧ�ܣ�", re);
			throw re;
		}
	}

	
	/*
	 * ������Դ����ɾ��һ�����󣬷�����ɾ��������һ���߼�ɾ������enabledΪfalse��
	 */
	public void delete( SysResources resource ) {
		log.debug("����һ����Դidɾ��һ���û�����ԴidΪ��" + resource.getResourceId() );
		try {
			
//			SysResources resource = findById( resourceId );
			
			resource.setEnabled( 0 );
//			getHibernateTemplate().saveOrUpdate( resource );
			
			log.debug("ɾ���ɹ���");
		} catch (RuntimeException re) {
			
			log.error("ɾ��ʧ�ܣ�", re);
			throw re;
		}
	}

	public SysResources findById(String id) {
		log.debug("getting SysUsers instance with id: " + id);
		try {
			SysResources instance = (SysResources) getHibernateTemplate()
					.find("from SysResources where resource_id='" + id +"'").get(0);
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
	

	/*
	 * �õ���Դ�б������б���ʵ���а����ţ�������Դ�š���Դ���ơ���Դ���͡���Դ��ַ��
	 */
	public List<SysResources> findResourcesLst( SysResources resource ){
			
		List<SysResources> list = null ;
		String sql = "from SysResources where enabled=1 ";
		try{
			if(!"".equals( resource.getResourceName() )){
				sql += "and resource_name like '" + resource.getResourceName() +"' ";
			}
			
			if(!"".equals( resource.getResourceDesc() )){
				sql += "and resource_desc like '" + resource.getResourceDesc() +"' ";
			}
			
			if(!"".equals( resource.getResourceType() )){
				sql += "and resource_type = '" + resource.getResourceType() +"' ";
			}
			
			if(!"".equals( resource.getResourceString() )){
				sql += "and resource_string like '%" + resource.getResourceString() + "%' ";
			}

			
			list = getHibernateTemplate().find( sql );
			
			
		}catch (RuntimeException re) {
			log.error("findByUserAccount() ����", re);
			throw re;
		}
		
		return list;
		
	}


}
