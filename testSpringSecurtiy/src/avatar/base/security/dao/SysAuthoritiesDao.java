package avatar.base.security.dao;

// Generated 2011-3-23 11:09:38 by Hibernate Tools 3.2.2.GA

import java.util.HashMap;
import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import avatar.base.security.entity.*;
import avatar.utils.Util;

/**
 * Home object for domain model class SysAuthorities.
 * @see avatar.base.security.entity.SysAuthorities
 * @author Hibernate Tools
 */
public class SysAuthoritiesDao extends HibernateDaoSupport {

	private static final Log log = LogFactory.getLog(SysAuthoritiesDao.class);
	
	//��Դdao
	private SysResourcesDao sysResourcesDao;
	
	//��ɫ��Ȩ�޵Ķ�Ӧ��ϵ��dao
	private SysRolesAuthoritiesDao sysRolesAuthoritiesDao;
	
	//Ȩ�޺���Դ��Ӧ��ϵ��dao
	private SysAuthoritiesResourcesDao sysAuthoritiesResourcesDao;

	/*
	 * ����ҳ�淵�ص�Ȩ�޶�����б��档
	 */
	public void persist(SysAuthorities transientInstance) {

		log.debug("Ȩ�� " + transientInstance.getAuthorityDesc() + " �־û�!");

		try {

			// Ϊд���Ȩ������Ψһid
			transientInstance.setAuthorityId(Util.getPkId() + "");
			
			String authorityName = transientInstance.getAuthorityName();

			// ��Ȩ�ޱ�ʶǰ׺��Ϊ"AUTH_"ʱ��Ҫ��Ӹñ�ʶ��
			if (authorityName.startsWith("AUTH")) {

				if (!authorityName.startsWith("AUTH_")) {
					authorityName = authorityName.substring(0, 4) + "_"
							+ authorityName.substring(4);
					transientInstance.setAuthorityName(authorityName);
				}

			} else {
				transientInstance.setAuthorityName("AUTH_" + authorityName);
			}

			// �־û�
			getHibernateTemplate().persist(transientInstance);

			log.debug("Ȩ�� " + transientInstance.getAuthorityDesc() + " �־û��ɹ���");

		} catch (RuntimeException re) {

			log.error("Ȩ�� " + transientInstance.getAuthorityDesc() + " �־û�ʧ�ܣ�", re);
			throw re;

		}
	}

	/*
	 * ��Ȩ��ʵ�����и��¡�
	 */
	public void attachDirty(SysAuthorities instance) {

		log.debug("����Ȩ��" + instance.getAuthorityName() + "!");

		try {

			getHibernateTemplate().saveOrUpdate(instance);
			
			log.debug("����Ȩ�� " + instance.getAuthorityName() + "�ɹ�!");

		} catch (RuntimeException re) {

			log.error("����Ȩ�� " + instance.getAuthorityName() + "ʧ��!", re);
			throw re;

		}
	}

	public void attachClean(SysAuthorities instance) {
		log.debug("attaching clean SysAuthorities instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}


	
	/**
	 * ����Ȩ��idɾ����Ȩ�ޣ���ɫ��Ȩ�ޣ�Ȩ�޺���Դ�Ĺ�ϵ��
	 * 
	 * @author sparta 2011-4-10 ����03:31:22
	 *@param authorityId
	 */
	public void delete(String authorityId) {
		log.debug("����Ȩ��idɾ����Ȩ�ޣ�Ȩ��idΪ �� " + authorityId);
		try {
			
			SysAuthorities authority = findById( authorityId );
			
			getHibernateTemplate().delete( authority );
		
			log.debug("Ȩ��id" + authorityId + "ɾ���ɹ���");
		} catch (RuntimeException re) {
			log.error("Ȩ��id" + authorityId + "ɾ��ʧ�ܣ�", re);
			throw re;
		}
	}


	/**
	 * ͨ��id����Ȩ�޵�ʵ����
	 *@author sparta 2011-4-20 ����09:17:02
	 *@param id
	 *@return
	 */
	public SysAuthorities findById( String id ) {
		log.debug("ͨ��id�õ�Ȩ�����ʵ��: " + id);
		try {
			
			SysAuthorities instance = (SysAuthorities) getHibernateTemplate().find( "from SysAuthorities where authority_id='" + id + "'").get(0);
			
			if (instance == null) {
				log.debug("��Ȩ���಻���ڣ�");
			} else {
				log.debug("�ɹ��ҵ���Ȩ�����ʵ����");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("����Ȩ����ʵ��ʧ�ܣ�", re);
			throw re;
		}
	}
	
	/**
	 * ����ƽ̨�е���ϵͳ����Ȩ���б�
	 * 
	 * @author sparta 2011-4-23 ����18:32:38
	 *@return
	 */
	public List<SysAuthorities> findAuthoritiesLst(String xtmk) {
		try {
			List<SysAuthorities> results = getHibernateTemplate().find(
					"from SysAuthorities where module='" + xtmk + "'");

			return results;

		} catch (RuntimeException re) {

			throw re;
		}
	}
	
	/**
	 * ����Ȩ��id���õ�����ԴidΪ����"true"Ϊֵ��Map��
	 * 
	 * @author sparta 2011-4-23 ����18:15:21
	 *@param authorityId
	 *@return
	 */
	public HashMap getSelectedResourcesMap(String authorityId) {
		try {
		
			List<String> results = getSession().createSQLQuery("select a.resource_id " +
					"from Sys_Resources a, Sys_Authorities_Resources b " +
					"where a.resource_id = b.resource_id and b.Authority_id='"+
					authorityId +"' and a.enabled=1").list();

			HashMap<String,String> hashMap = new HashMap<String,String>(0);

			for (String resourcesId : results) {
				hashMap.put( resourcesId, "true" );
			}
			
			return hashMap;
		} catch (RuntimeException re) {

			throw re;
		}
	}

	/*
	 * ����Ȩ�޺���Դ֮��һ�Զ�Ĺ�ϵ��
	 */
	public boolean saveAuthorityAndReSource(String authorityId, String[] resourceskey) {

		SysAuthorities sysAuthorities = findById(authorityId);
		SysResources sysResources = null;
		SysAuthoritiesResources sysAuthoritiesResources;
		
		//����������֮ǰ��ɾ��֮ǰ��ӵ�е�ȫ����Ӧ��ϵ
		sysAuthoritiesResourcesDao.deleteOldAuthorityAndResourceRelative( authorityId );

		try {
			/*
			 * ���û���ǰ̨ͨ��checkboxѡ�е�����Ȩ��id��ȡ������
			 * �ڽ��б���֮ǰ������Ҫͨ��id��ȡ��Ȩ�޵�ʵ����
			 * Ϊ��ɫȨ�޹�����sysRolesAuthorities��setSysAuthorities()�ṩ���ݡ�
			 * ���ͨ��hibernate��save()���������½���sysRolesAuthoritiesʵ����
			 */
			for (String resId : resourceskey) {
				
				sysResources = sysResourcesDao.findById( resId );
				
				/*
				 * ����һ���µ�ʵ������Ҫ������ᱨ
				 * identifier of an instance of xxx  
				 * was altered from xxx to xxx"���쳣��
				 */
				sysAuthoritiesResources = new SysAuthoritiesResources();
				sysAuthoritiesResources.setId( Util.getPkId() );
				sysAuthoritiesResources.setSysResources( sysResources );
				sysAuthoritiesResources.setSysAuthorities( sysAuthorities );
				sysAuthoritiesResources.setEnabled(true);

				getHibernateTemplate().save( sysAuthoritiesResources );

			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

		return true;
	}
	
	
	/**
	 * �õ���Դ�б�
	 */
	public List<SysResources> getResourcesList() {
		try {

			List<SysResources> results = getHibernateTemplate().find(
					"from SysResources where enabled=1");

			return results;

		} catch (RuntimeException re) {

			throw re;
		}
	}
	
	//ע��
	public void setSysResourcesDao( SysResourcesDao sysResourcesDao){
		this.sysResourcesDao = sysResourcesDao;
	}
	
	public SysResourcesDao getSysResourcesDao(){
		return sysResourcesDao;
	}
	
	public void setSysRolesAuthoritiesDao( SysRolesAuthoritiesDao sysRolesAuthoritiesDao){
		this.sysRolesAuthoritiesDao = sysRolesAuthoritiesDao;
	}
	
	public SysRolesAuthoritiesDao getSysRolesAuthoritiesDao(){
		return sysRolesAuthoritiesDao;
	}
	
	public SysAuthoritiesResourcesDao getSysAuthoritiesResourcesDao(){
		return sysAuthoritiesResourcesDao;
	}
	
	public void setSysAuthoritiesResourcesDao( SysAuthoritiesResourcesDao sysAuthoritiesResourcesDao){
		this.sysAuthoritiesResourcesDao = sysAuthoritiesResourcesDao;
	}


}
