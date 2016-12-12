package id.padiku.app.valueobject.master;

import id.padiku.app.SystemConstant;
import id.padiku.app.valueobject.BaseEntity;
import id.padiku.app.valueobject.Lookup;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
@Table(name = "COMPANY_MACHINERY")
public class CompanyMachinery extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = -1166302623002119932L;

	public CompanyMachinery() {}
	
	public static CompanyMachinery getInstance(Long pkCompany) {
		CompanyMachinery obj = new CompanyMachinery();
			obj.setCompany(Company.getInstance(pkCompany));
			obj.setStatus(SystemConstant.ValidFlag.VALID);
		return obj;
	}
	
	public static final String ID = "pkCompanyMachinery";
	public static final String CODE = "code";
	public static final String NAME = "name";
	public static final String MODEL = "model";
	public static final String MODEL_ID = MODEL +"."+ Lookup.ID;
	public static final String MODEL_NAME_ID = MODEL +"."+ Lookup.NAME_ID;
	public static final String MODEL_NAME_EN = MODEL +"."+ Lookup.NAME_EN;
	public static final String TYPE = "type";
	public static final String TYPE_ID = TYPE +"."+ Lookup.ID;
	public static final String TYPE_NAME_ID = TYPE +"."+ Lookup.NAME_ID;
	public static final String CAPACITY = "capacity";
	public static final String WEIGHTING = "weighting";
	public static final String WEIGHTING_ID = WEIGHTING +"."+ Lookup.ID;
	public static final String WEIGHTING_CODE = WEIGHTING +"."+ Lookup.CODE;
	public static final String WEIGHTING_NAME_ID = WEIGHTING +"."+ Lookup.NAME_ID;
	public static final String CAPACITY_UOM = "capacityUOM";
	public static final String CAPACITY_UOM_ID = CAPACITY_UOM +"."+ Lookup.ID;
	public static final String CAPACITY_UOM_CODE = CAPACITY_UOM +"."+ Lookup.CODE;
	public static final String CAPACITY_UOM_NAME_ID = CAPACITY_UOM +"."+ Lookup.NAME_ID;
	public static final String STATUS = "status";
	public static final String COMPANY = "company";
	public static final String COMPANY_ID = COMPANY +"."+ Company.ID;
	public static final String COMPANY_PRODUCT_FROM = "companyProductFrom";
	public static final String COMPANY_PRODUCT_FROM_ID = COMPANY_PRODUCT_FROM+"."+CompanyProduct.ID;
	public static final String COMPANY_PRODUCT_FROM_NAME = COMPANY_PRODUCT_FROM+"."+CompanyProduct.NAME;
	public static final String COMPANY_PRODUCT_TO = "companyProductTo";
	public static final String COMPANY_PRODUCT_TO_ID = COMPANY_PRODUCT_TO+"."+CompanyProduct.ID;
	public static final String COMPANY_PRODUCT_TO_NAME = COMPANY_PRODUCT_TO+"."+CompanyProduct.NAME;
	public static final String PREDEFINED = "predefined";
	
	public static final String[] MAINTENANCE_LIST_FOR_TRANS_PROD = {
		ID, NAME, CAPACITY, WEIGHTING_ID, WEIGHTING_CODE, WEIGHTING_NAME_ID, CAPACITY_UOM_CODE, CAPACITY_UOM_NAME_ID,
		COMPANY_PRODUCT_FROM_ID, COMPANY_PRODUCT_FROM_NAME, COMPANY_PRODUCT_TO_ID, COMPANY_PRODUCT_TO_NAME
	};

	@Id
	@SequenceGenerator(name="COMPANY_MACHINERY_PK_COMPANY_MACHINERY_SEQ", sequenceName="COMPANY_MACHINERY_PK_COMPANY_MACHINERY_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="COMPANY_MACHINERY_PK_COMPANY_MACHINERY_SEQ")
	@Column(name = "PK_COMPANY_MACHINERY", unique = true ,nullable = false)
	private Long pkCompanyMachinery;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_COMPANY")
	private Company company;
	
	@Column(name="MACHINE_CODE")
	private String code;
	
	@Column(name="MACHINE_NAME")
	private String name;
	
	@OneToOne
	@JoinColumn(name="FK_LOOKUP_MACHINE_MODEL")
	private Lookup model;
	
	@OneToOne
	@JoinColumn(name="FK_LOOKUP_MACHINE_TYPE")
	private Lookup type;
	
	@Column(name="MACHINE_CAPACITY")
	private BigDecimal capacity;
	
	@OneToOne
	@JoinColumn(name="FK_LOOKUP_MACHINE_WEIGHTING")
	private Lookup weighting;
	
	@OneToOne
	@JoinColumn(name="FK_LOOKUP_MACHINE_CAPACITY_UOM")
	private Lookup capacityUOM;
	
	@OneToOne
	@JoinColumn(name="FK_LOOKUP_MACHINE_POWER_SOURCE")
	private Lookup powerSource;
	
	@OneToOne
	@JoinColumn(name="FK_LOOKUP_MACHINE_OWNERSHIPS")
	private Lookup ownerships;
	
	@Column(name="MACHINE_PURCHASE_DATE")
	private Date purchaseDate;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_COMPANY_PRODUCT_FROM")
	private CompanyProduct companyProductFrom;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_COMPANY_PRODUCT_TO")
	private CompanyProduct companyProductTo;
	
	@Column(name="IS_PREDEFINED")
	private Boolean predefined;
	
	@Column(name="STATUS")
	private Integer status;

	public Long getPkCompanyMachinery() {
		return pkCompanyMachinery;
	}
	public void setPkCompanyMachinery(Long pkCompanyMachinery) {
		this.pkCompanyMachinery = pkCompanyMachinery;
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

	public Lookup getModel() {
		return model;
	}
	public void setModel(Lookup model) {
		this.model = model;
	}

	public Lookup getType() {
		return type;
	}
	public void setType(Lookup type) {
		this.type = type;
	}

	public BigDecimal getCapacity() {
		return capacity;
	}
	public void setCapacity(BigDecimal capacity) {
		this.capacity = capacity;
	}

	public Lookup getWeighting() {
		return weighting;
	}
	public void setWeighting(Lookup weighting) {
		this.weighting = weighting;
	}

	public Lookup getCapacityUOM() {
		return capacityUOM;
	}
	public void setCapacityUOM(Lookup capacityUOM) {
		this.capacityUOM = capacityUOM;
	}

	public Lookup getPowerSource() {
		return powerSource;
	}
	public void setPowerSource(Lookup powerSource) {
		this.powerSource = powerSource;
	}

	public Lookup getOwnerships() {
		return ownerships;
	}
	public void setOwnerships(Lookup ownerships) {
		this.ownerships = ownerships;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	
	public CompanyProduct getCompanyProductFrom() {
		return companyProductFrom;
	}
	public void setCompanyProductFrom(CompanyProduct companyProductFrom) {
		this.companyProductFrom = companyProductFrom;
	}

	public CompanyProduct getCompanyProductTo() {
		return companyProductTo;
	}
	public void setCompanyProductTo(CompanyProduct companyProductTo) {
		this.companyProductTo = companyProductTo;
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
