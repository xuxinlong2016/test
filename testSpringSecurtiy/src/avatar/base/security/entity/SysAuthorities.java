package avatar.base.security.entity;

// Generated 2011-3-23 11:09:37 by Hibernate Tools 3.2.2.GA

import java.util.HashSet;
import java.util.Set;

/**
 * PubAuthorities generated by hbm2java
 */
public class SysAuthorities implements java.io.Serializable {

	private String authorityId;
	private String authorityName;
	private String authorityDesc;
	private Boolean enabled;
	private Boolean issys;
	private String module;
	private Set pubRolesAuthoritieses = new HashSet(0);
	private Set pubAuthoritiesResourceses = new HashSet(0);

	public SysAuthorities() {
	}

	public SysAuthorities(String authorityId) {
		this.authorityId = authorityId;
	}

	public SysAuthorities(String authorityId, String authorityName,
			String authorityDesc, Boolean enabled, Boolean issys, String module,
			Set pubRolesAuthoritieses, Set pubAuthoritiesResourceses) {
		this.authorityId = authorityId;
		this.authorityName = authorityName;
		this.authorityDesc = authorityDesc;
		this.enabled = enabled;
		this.issys = issys;
		this.module = module;
		this.pubRolesAuthoritieses = pubRolesAuthoritieses;
		this.pubAuthoritiesResourceses = pubAuthoritiesResourceses;
	}

	public String getAuthorityId() {
		return this.authorityId;
	}

	public void setAuthorityId(String authorityId) {
		this.authorityId = authorityId;
	}

	public String getAuthorityName() {
		return this.authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	public String getAuthorityDesc() {
		return this.authorityDesc;
	}

	public void setAuthorityDesc(String authorityDesc) {
		this.authorityDesc = authorityDesc;
	}

	public Boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getIssys() {
		return this.issys;
	}

	public void setIssys(Boolean issys) {
		this.issys = issys;
	}
	
	public String getModule() {
		return this.module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public Set getSysRolesAuthoritieses() {
		return this.pubRolesAuthoritieses;
	}

	public void setSysRolesAuthoritieses(Set pubRolesAuthoritieses) {
		this.pubRolesAuthoritieses = pubRolesAuthoritieses;
	}

	public Set getSysAuthoritiesResourceses() {
		return this.pubAuthoritiesResourceses;
	}

	public void setSysAuthoritiesResourceses(Set pubAuthoritiesResourceses) {
		this.pubAuthoritiesResourceses = pubAuthoritiesResourceses;
	}

}
