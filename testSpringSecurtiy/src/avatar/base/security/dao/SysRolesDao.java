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

	
	//Ȩ��dao
	private SysAuthoritiesDao sysAuthoritiesDao;
	
	//��ɫ��Ȩ�޵Ķ�Ӧ��ϵ��dao
	private SysRolesAuthoritiesDao sysRolesAuthoritiesDao;
	
	//�û��ͽ�ɫ��Ӧ��ϵ��dao
	private SysUsersRolesDao sysUsersRolesDao;

	/*
	 * ����ҳ�淵�صĽ�ɫ������б��档
	 */
	public void persist(SysRoles transientInstance) {

		log.debug("��ɫ " + transientInstance.getRoleName() + " �־û�!");

		try {

			// Ϊд��Ľ�ɫ����Ψһid
			transientInstance.setRoleId(Util.getPkId() + "");
			
			String roleName = transientInstance.getRoleName();

			// ����ɫ��ʶǰ׺��Ϊ"ROLE_"ʱ��Ҫ��Ӹñ�ʶ��
			if (roleName.startsWith("ROLE")) {

				if (!roleName.startsWith("ROLE_")) {
					roleName = roleName.substring(0, 4) + "_"
							+ roleName.substring(4);
					transientInstance.setRoleName(roleName);
				}

			} else {
				transientInstance.setRoleName("ROLE_" + roleName);
			}

			// �־û�
			getHibernateTemplate().persist(transientInstance);

			log.debug("��ɫ " + transientInstance.getRoleName() + " �־û��ɹ���");

		} catch (RuntimeException re) {

			log.error("��ɫ " + transientInstance.getRoleName() + " �־û�ʧ�ܣ�", re);
			throw re;

		}
	}

	/*
	 * �Խ�ɫʵ�����и��¡�
	 */
	public void attachDirty(SysRoles instance) {

		log.debug("���½�ɫ " + instance.getRoleName() + "!");

		try {

			getHibernateTemplate().saveOrUpdate(instance);
			
			log.debug("���½�ɫ " + instance.getRoleName() + "�ɹ�!");

		} catch (RuntimeException re) {

			log.error("���½�ɫ " + instance.getRoleName() + "ʧ��!", re);
			throw re;

		}
	}

	/*
	 * ������Ķ�����ΪTransent״̬��
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
	 * ���ݽ�ɫidɾ���ý�ɫ��
	 * 
	 * @author sparta 2011-4-10 ����03:31:22
	 *@param roleId
	 */
	public void delete(String roleId) {
		log.debug("���ݽ�ɫidɾ���ý�ɫ����ɫidΪ �� " + roleId);
		try {
			
			SysRoles role = findById( roleId );
			
			getHibernateTemplate().delete( role );
		
			log.debug("��ɫid" + roleId + "ɾ���ɹ���");
		} catch (RuntimeException re) {
			log.error("��ɫid" + roleId + "ɾ��ʧ�ܣ�", re);
			throw re;
		}
	}

	/*
	 * �������detached(�й�)״̬�Ķ�������Ը��Ƶ��־û������У������ظó־û����� �����session��û�й����ĳ־û����󣬼���һ����
	 * ����������δ���棬����һ����������Ϊ�־ö��󷵻أ����������Ȼ����detached״̬��
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
	 * ͨ��id�õ�һ����ɫʵ����
	 */
	public SysRoles findById( String id) {

		log.debug("�õ�һ����ɫʵ��ͨ�������id: " + id);

		try {

			SysRoles instance = (SysRoles)getHibernateTemplate().find(
					"from SysRoles where roleId= '" + id + "'").get(0);

			if (instance == null) {
				log.debug("���ҵĽ�ɫʵ�������ڣ�");
			} else {
				log.debug("�ɹ����ҵ���ʵ����");
			}

			return instance;

		} catch (RuntimeException re) {
			log.error("����ʧ��!", re);
			throw re;
		}
	}

	/*
	 * Hibernate�������ɵĴ��롣
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
	 * ������ñ���main()�������з��ص�list�����ʲô��
	 * ������֤���֣�ʵ����������ܷ��ص��б��н���һ�����ݣ���SysRoles��ʵ����
	 * ͨ��list.get(0)����ʵ��ȡ����Ȼ��ת�͵�SysRoles������getRoleName()�Ȳ������ɡ�
	 * ���Ǵ�ǰ�������ֱ�ӽ������ֶε�����������γ�һ���б� 11/4/16 15:09
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
	 * ����ʹ�ñ���main()������ʵ�����ֶ���ȡ��Map��
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
	 * ����ƽ̨�е���ϵͳ���ؽ�ɫ�б�
	 * 
	 * @author sparta 2011-4-10 ����03:37:38
	 *@return
	 */
	public List<SysRoles> findRolesLst(String xtmk) {
		try {
			
			String hql = "";
			
			//��xtmkΪ�գ��򷵻����н�ɫ�б�
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
	 * �õ�Ȩ���б�
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
	 * ���ݽ�ɫid���õ���Ȩ��idΪ����"true"Ϊֵ��Map��
	 * 
	 * @author sparta 2011-4-12 ����08:00:21
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
	 * �����ɫ��Ȩ��֮��һ�Զ�Ĺ�ϵ��
	 */
	public boolean savePermissionAndRole(String roleId, String[] permisskey) {

		SysRoles sysRoles = findById(roleId);
		SysAuthorities sysAuthorities = null;
		SysRolesAuthorities sysRolesAuthorities;
		
		//����������֮ǰ��ɾ��֮ǰ��ӵ�е�ȫ����Ӧ��ϵ
		sysRolesAuthoritiesDao.deleteOldRoleAndPermissionRelative( roleId );

		try {
			/*
			 * ���û���ǰ̨ͨ��checkboxѡ�е�����Ȩ��id��ȡ������
			 * �ڽ��б���֮ǰ������Ҫͨ��id��ȡ��Ȩ�޵�ʵ����
			 * Ϊ��ɫȨ�޹�����sysRolesAuthorities��setSysAuthorities()�ṩ���ݡ�
			 * ���ͨ��hibernate��save()���������½���sysRolesAuthoritiesʵ����
			 */
			for (String perId : permisskey) {
				
				sysAuthorities = sysAuthoritiesDao.findById(perId);
				
				/*
				 * ����һ���µ�ʵ������Ҫ������ᱨ
				 * identifier of an instance of xxx  
				 * was altered from xxx to xxx"���쳣��
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
	
	
	//ע��
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
	

	//���ز�����ڡ�
	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		SessionFactory sessionFactory = (SessionFactory) context
				.getBean("sessionFactory");

		Session session = sessionFactory.openSession();
		System.out.println(session);
		
		
	}

}
