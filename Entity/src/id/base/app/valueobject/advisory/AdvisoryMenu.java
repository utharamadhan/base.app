package id.base.app.valueobject.advisory;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import id.base.app.valueobject.BaseEntity;

@Entity
@Table(name = "ADVISORY_MENU")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="advisoryMenuJid", scope=AdvisoryMenu.class)
public class AdvisoryMenu extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8793258862185720735L;
	
	public static final String PK_ADVISORY_MENU 		= "pkAdvisoryMenu";
	public static final String CODE 					= "code";
	public static final String NAME 					= "name";
	public static final String VALID 					= "valid";
	
	public static AdvisoryMenu getInstance() {
		return new AdvisoryMenu();
	}
	
	public static AdvisoryMenu getInstance(Long pkCourse) {
		AdvisoryMenu c = getInstance();
		return c;
	}
	
	@Id
	@SequenceGenerator(name="ADVISORY_PK_ADVISORY_MENU_SEQ", sequenceName="ADVISORY_PK_ADVISORY_MENU_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ADVISORY_PK_ADVISORY_MENU_SEQ")
	@Column(name = "PK_ADVISORY_MENU", unique = true ,nullable = false)
	private Long pkAdvisoryMenu;
		
	@Column(name="CODE")
	private String code;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="VALID")
	private Integer valid;
	
	
	public Long getPkAdvisoryMenu() {
		return pkAdvisoryMenu;
	}

	public void setPkAdvisoryMenu(Long pkAdvisoryMenu) {
		this.pkAdvisoryMenu = pkAdvisoryMenu;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}
		
	
}
