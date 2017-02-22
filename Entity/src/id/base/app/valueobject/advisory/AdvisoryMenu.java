package id.base.app.valueobject.advisory;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	public static final String ORDER_NO 				= "orderNo";
	public static final String VALID 					= "valid";
	
	public static AdvisoryMenu getInstance() {
		return new AdvisoryMenu();
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
	
	@Column(name="ORDER_NO")
	private Integer orderNo;
	
	@Column(name="MENU_TYPE")
	private Integer type;
	
	@Column(name="LINK")
	private String link;
	
	@ManyToOne
	@JoinColumn(name="FK_POST")
	private AdvisoryPost post;
	
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
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public AdvisoryPost getPost() {
		return post;
	}

	public void setPost(AdvisoryPost post) {
		this.post = post;
	}

	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}
		
	
}
