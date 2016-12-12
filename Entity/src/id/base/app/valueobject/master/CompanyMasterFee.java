package id.base.app.valueobject.master;

import id.base.app.SystemConstant;
import id.base.app.valueobject.BaseEntity;
import id.base.app.valueobject.Lookup;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name = "COMPANY_MASTER_FEE")
public class CompanyMasterFee extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -3558636979643433035L;
	
	public CompanyMasterFee() {}
	
	public static CompanyMasterFee getInstance(Long pkCompany) {
		CompanyMasterFee obj = new CompanyMasterFee();
			obj.setCompany(Company.getInstance(pkCompany));
			obj.setStatus(SystemConstant.ValidFlag.VALID);
		return obj;
	}
	
	public static final String COMPANY = "company";
	public static final String COMPANY_ID = COMPANY +"."+ Company.ID;
	public static final String ID = "pkCompanyMasterFee";
	public static final String FEE_TYPE		= "feeType";
	public static final String FEE_TYPE_ID	= FEE_TYPE +"."+ Lookup.ID;
	public static final String FEE_TYPE_NAME_ID	= FEE_TYPE +"."+ Lookup.NAME_ID;
	public static final String FEE_TYPE_CODE = FEE_TYPE +"."+ Lookup.CODE;
	public static final String DESCR = "descr";
	public static final String UNIT_FEE = "unitFee";
	public static final String UOM = "uom";
	public static final String UOM_ID = UOM +"."+ Lookup.ID;
	public static final String UOM_NAME_ID = UOM +"."+ Lookup.NAME_ID;
	public static final String UOM_NAME_EN = UOM +"."+ Lookup.NAME_EN;
	public static final String PREDEFINED = "predefined";
	public static final String STATUS = "status";
	
	public static final String[] MAINTENANCE_LIST_FIELDS = {
		ID, DESCR, UNIT_FEE, UOM, UOM_ID, UOM_NAME_ID, UOM_NAME_EN, FEE_TYPE, FEE_TYPE_NAME_ID
	};
	
	@Id
	@SequenceGenerator(name="COMPANY_MASTER_FEE_PK_COMPANY_MASTER_FEE_SEQ", sequenceName="COMPANY_MASTER_FEE_PK_COMPANY_MASTER_FEE_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="COMPANY_MASTER_FEE_PK_COMPANY_MASTER_FEE_SEQ")
	@Column(name = "PK_COMPANY_MASTER_FEE", unique = true ,nullable = false)
	private Long pkCompanyMasterFee;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_COMPANY")
	private Company company;
	
	@OneToOne
	@JoinColumn(name="FK_LOOKUP_FEE_TYPE")
	private Lookup feeType;
	
	@Column(name="DESCR")
	private String descr;
	
	@Column(name="UNIT_FEE")
	private BigDecimal unitFee;
	
	@OneToOne
	@JoinColumn(name="FK_LOOKUP_UOM")
	private Lookup uom;
	
	@Column(name="IS_PREDEFINED")
	private Boolean predefined;
	
	@Column(name="STATUS")
	private Integer status;
	
	public Long getPkCompanyMasterFee() {
		return pkCompanyMasterFee;
	}

	public void setPkCompanyMasterFee(Long pkCompanyMasterFee) {
		this.pkCompanyMasterFee = pkCompanyMasterFee;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Lookup getFeeType() {
		return feeType;
	}

	public void setFeeType(Lookup feeType) {
		this.feeType = feeType;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public BigDecimal getUnitFee() {
		return unitFee;
	}

	public void setUnitFee(BigDecimal unitFee) {
		this.unitFee = unitFee;
	}

	public Lookup getUom() {
		return uom;
	}

	public void setUom(Lookup uom) {
		this.uom = uom;
	}
	
	public Boolean getPredefined() {
		return predefined;
	}

	public void setPredefined(Boolean predefined) {
		this.predefined = predefined;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}	
}
