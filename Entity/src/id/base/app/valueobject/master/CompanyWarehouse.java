package id.base.app.valueobject.master;

import id.base.app.SystemConstant;
import id.base.app.valueobject.BaseEntity;
import id.base.app.valueobject.Lookup;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "COMPANY_WAREHOUSE")
public class CompanyWarehouse extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 8425592030053710549L;
	
	public CompanyWarehouse () {}
	
	public static CompanyWarehouse getInstance(Long pkCompany) {
		CompanyWarehouse obj = new CompanyWarehouse();
			obj.setCompany(Company.getInstance(pkCompany));
			obj.setStatus(SystemConstant.ValidFlag.VALID);
		return obj;
	}
	
	public static final String ID = "pkCompanyWarehouse";
	public static final String CODE = "code";
	public static final String NAME = "name";
	public static final String TYPE = "type";
	public static final String TYPE_ID = TYPE +"."+ Lookup.ID;
	public static final String TYPE_NAME_ID = TYPE +"."+ Lookup.NAME_ID;
	public static final String TYPE_NAME_EN = TYPE +"."+ Lookup.NAME_EN;
	
	public static final String[] MAINTENANCE_LIST_FIELDS = {
		ID, CODE, NAME, TYPE, TYPE_ID, TYPE_NAME_ID, TYPE_NAME_EN
	};
	
	@Id
	@SequenceGenerator(name="COMPANY_WAREHOUSE_PK_COMPANY_WAREHOUSE_SEQ", sequenceName="COMPANY_WAREHOUSE_PK_COMPANY_WAREHOUSE_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="COMPANY_WAREHOUSE_PK_COMPANY_WAREHOUSE_SEQ")
	@Column(name = "PK_COMPANY_WAREHOUSE", unique = true ,nullable = false)
	private Long pkCompanyWarehouse;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_COMPANY")
	private Company company;
	
	@Column(name = "WAREHOUSE_CODE")
	private String code;
	
	@Column(name = "WAREHOUSE_NAME")
	private String name;
	
	@OneToOne
	@JoinColumn(name="FK_LOOKUP_WAREHOUSE_TYPE")
	private Lookup type;
	
	@Column(name = "STATUS")
	private Integer status;
	
	public Long getPkCompanyWarehouse() {
		return pkCompanyWarehouse;
	}

	public void setPkCompanyWarehouse(Long pkCompanyWarehouse) {
		this.pkCompanyWarehouse = pkCompanyWarehouse;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
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

	public Lookup getType() {
		return type;
	}

	public void setType(Lookup type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}