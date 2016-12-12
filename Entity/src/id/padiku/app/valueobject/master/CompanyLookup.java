package id.padiku.app.valueobject.master;

import id.padiku.app.SystemConstant;
import id.padiku.app.util.StringFunction;
import id.padiku.app.valueobject.BaseEntity;
import id.padiku.app.valueobject.CreateEntity;
import id.padiku.app.valueobject.LookupGroup;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "COMPANY_LOOKUP")
public class CompanyLookup extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4061391116371248992L;
	
	public CompanyLookup() {}
	
	public static CompanyLookup getInstance(Long pkCompanyLookup) {
		CompanyLookup obj = new CompanyLookup();
			obj.setCompany(Company.getInstance(pkCompanyLookup));
			obj.setStatus(SystemConstant.ValidFlag.VALID);
		return obj;
	}
	
	@Id
	@SequenceGenerator(name="COMPANY_LOOKUP_PK_COMPANY_LOOKUP_SEQ", sequenceName="COMPANY_LOOKUP_PK_COMPANY_LOOKUP_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="COMPANY_LOOKUP_PK_COMPANY_LOOKUP_SEQ")
	@Column(name = "PK_COMPANY_LOOKUP", unique = true ,nullable = false)
	private Long pkCompanyLookup;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_COMPANY")
	private Company company;
	
	@Column(name="LOOKUP_GROUP")
	@NotEmpty(groups=CreateEntity.class, message="{error.lookup.group.mandatory}")
	private String lookupGroupString;
	
	@Transient
	private LookupGroup lookupGroup;
	
	@Column(name="CODE")
	private String code;
	
	@Column(name="NAME_ID")
	private String nameId;
	
	@Column(name="NAME_EN")
	private String nameEn;
	
	@Transient
	private String name;
	
	@Column(name="ORDER_NO")
	private Long orderNo;
	
	@Column(name="STATUS")
	private Integer status;
	
	public static final String ID					= "pkCompanyLookup";
	public static final String COMPANY				= "company";
	public static final String COMPANY_ID			= COMPANY + "."+ Company.ID;
	public static final String LOOKUP_GROUP_STRING 	= "lookupGroupString";
	public static final String CODE					= "code";
	public static final String NAME_ID				= "nameId";
	public static final String NAME_EN				= "nameEn";
	public static final String ORDER_NO				= "orderNo";
	public static final String STATUS				= "status";

	public Long getPkCompanyLookup() {
		return pkCompanyLookup;
	}
	public void setPkCompanyLookup(Long pkCompanyLookup) {
		this.pkCompanyLookup = pkCompanyLookup;
	}

	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}

	public String getLookupGroupString() {
		return lookupGroupString;
	}
	public void setLookupGroupString(String lookupGroupString) {
		this.lookupGroupString = lookupGroupString;
	}

	public LookupGroup getLookupGroup() {
		return lookupGroup;
	}
	public void setLookupGroup(LookupGroup lookupGroup) {
		this.lookupGroup = lookupGroup;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getNameId() {
		return nameId;
	}
	public void setNameId(String nameId) {
		this.nameId = nameId;
	}

	public String getNameEn() {
		return nameEn;
	}
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	
	@Transient
	public String getName() {
		if(StringFunction.isEmpty(nameEn)){
			return nameId;
		}else if(StringFunction.isNotEmpty(nameId)){
			return nameId + " (" + nameEn + ")"; 
		}else{
			return null;
		}
	}
	@Transient
	public void setName(String name) {
		this.name = name;
	}

	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
