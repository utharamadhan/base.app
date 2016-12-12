package id.base.app.valueobject.production;

import id.base.app.SystemConstant;
import id.base.app.SystemConstant.TransInSourceType;
import id.base.app.valueobject.BaseEntity;
import id.base.app.valueobject.Lookup;
import id.base.app.valueobject.master.Company;
import id.base.app.valueobject.master.CompanyProduct;
import id.base.app.valueobject.master.CompanyWarehouse;
import id.base.app.valueobject.master.Stock;
import id.base.app.valueobject.party.Party;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "TRANS_PROD")
public class TransProd extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -6204724932491333683L;
	
	public TransProd() {}
	
	public static TransProd getInstance(Long pkCompany) {
		TransProd obj = new TransProd();
			obj.setCompany(Company.getInstance(pkCompany));
			obj.setStatus(SystemConstant.ValidFlag.VALID);
		return obj;
	}
	
	public static final String ID = "pkTransProd";
	public static final String PROD_NO = "prodNo";
	public static final String STOCK_FROM = "stockFrom";
	public static final String STOCK_TO = "stockFrom";
	public static final String VOLUME = "volume";
	public static final String VOLUME_IN_KG = "volumeInKg";
	public static final String UOM = "uom";
	public static final String UOM_ID = UOM + Lookup.ID;
	public static final String UOM_NAME_ID = UOM + Lookup.NAME_ID;
	public static final String HPP = "hpp";
	public static final String PROD_DATE_FROM = "prodDateFrom";
	public static final String PROD_DATE_TO = "prodDateTo";
	public static final String TOTAL_FEE = "totalFee";
	public static final String STATUS = "status";
	public static final String COMPANY = "company";
	public static final String COMPANY_ID = COMPANY + ".pkCompany";
	public static final String PLAN_NAME = "planName";
	public static final String TRANS_IN_SOURCE_TYPE = "transInSourceType";
	public static final String COMPANY_PRODUCT_FROM = "companyProductFrom";
	public static final String COMPANY_PRODUCT_FROM_NAME = COMPANY_PRODUCT_FROM +"."+CompanyProduct.NAME;
	public static final String COMPANY_PRODUCT_TO = "companyProductTo";
	public static final String COMPANY_PRODUCT_TO_NAME = COMPANY_PRODUCT_TO +"."+CompanyProduct.NAME;
	
	
	@Id
	@SequenceGenerator(name="TRANS_PROD_PK_TRANS_PROD_SEQ", sequenceName="TRANS_PROD_PK_TRANS_PROD_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TRANS_PROD_PK_TRANS_PROD_SEQ")
	@Column(name = "PK_TRANS_PROD", unique = true ,nullable = false)
	private Long pkTransProd;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_COMPANY")
	private Company company;
	
	@Column(name="PROD_NO")
	@JoinColumn(name="PROD_NO")
	private String prodNo;
	
	@OneToOne
	@JoinColumn(name="FK_LOOKUP_ACTOR_PROD")
	private Lookup actorProd;
	
	@ManyToOne
	@JoinColumn(name="FK_COMPANY_PRODUCT_FROM")
	private CompanyProduct companyProductFrom;
	
	@ManyToOne
	@JoinColumn(name="FK_COMPANY_PRODUCT_TO")
	private CompanyProduct companyProductTo;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_STOCK_FROM")
	private Stock stockFrom;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_STOCK_TO")
	private Stock stockTo;
	
	@Column(name="PLAN_NAME")
	private String planName;
	
	@Column(name="VOLUME")
	private BigDecimal volume;
	
	@OneToOne
	@JoinColumn(name="FK_LOOKUP_UOM")
	private Lookup uom;
	
	@Column(name="VOLUME_IN_KG")
	private BigDecimal volumeInKg;
	
	@Column(name="VOLUME_TO")
	private BigDecimal volumeTo;
	
	@OneToOne
	@JoinColumn(name="FK_LOOKUP_UOM_TO")
	private Lookup uomTo;
	
	@Column(name="VOLUME_TO_IN_KG")
	private BigDecimal volumeToInKg;
	
	@Column(name="HPP")
	private BigDecimal hpp;
	
	@Column(name="PROD_DATE_FROM")
	private Date prodDateFrom;
	
	@Column(name="PROD_DATE_TO")
	private Date prodDateTo;
	
	@Column(name="TOTAL_FEE")
	private BigDecimal totalFee;
	
	@Column(name="TRANS_IN_SOURCE_TYPE")
	private String transInSourceType;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_COMPANY_WAREHOUSE_MAIN")
	private CompanyWarehouse companyWarehouseMain;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_COMPANY_WAREHOUSE_OTHER")
	private CompanyWarehouse companyWarehouseOther;
	
	@OneToOne
	@JoinColumn(name="FK_PARTY_THIRD_PARTY")
	private Party thirdParty;
	
	@Transient
	private String thirdPartyName;
	
	@Column(name="EST_DAYS")
	private Integer estDays;
	
	@Column(name="STATUS")
	private Integer status;
	
	@OneToMany(mappedBy="transProd", cascade=CascadeType.ALL, orphanRemoval = true)
	@Column
	private List<TransProdOtherItem> otherItems = new ArrayList<>();
	
	@OneToMany(mappedBy="transProd", cascade=CascadeType.ALL, orphanRemoval = true)
	@Column
	private List<TransProdFee> fees = new ArrayList<>();
	
	@OneToMany(mappedBy="transProd", cascade=CascadeType.ALL, orphanRemoval = true)
	@Column
	private List<TransProdMachinery> machineries = new ArrayList<>();

	@Transient
	private String title;
	
	@Transient
	private String start;
	
	@Transient
	private String end;
	
	@Transient
	private String url;
	
	@Transient
	private String className;
	
	public Long getPkTransProd() {
		return pkTransProd;
	}

	public void setPkTransProd(Long pkTransProd) {
		this.pkTransProd = pkTransProd;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	public String getProdNo() {
		return prodNo;
	}

	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}

	public Stock getStockFrom() {
		return stockFrom;
	}

	public void setStockFrom(Stock stockFrom) {
		this.stockFrom = stockFrom;
	}

	public Stock getStockTo() {
		return stockTo;
	}

	public void setStockTo(Stock stockTo) {
		this.stockTo = stockTo;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

	public BigDecimal getVolumeInKg() {
		return volumeInKg;
	}

	public void setVolumeInKg(BigDecimal volumeInKg) {
		this.volumeInKg = volumeInKg;
	}

	public Lookup getUom() {
		return uom;
	}

	public void setUom(Lookup uom) {
		this.uom = uom;
	}
	
	public BigDecimal getVolumeTo() {
		return volumeTo;
	}

	public void setVolumeTo(BigDecimal volumeTo) {
		this.volumeTo = volumeTo;
	}

	public Lookup getUomTo() {
		return uomTo;
	}

	public void setUomTo(Lookup uomTo) {
		this.uomTo = uomTo;
	}

	public BigDecimal getVolumeToInKg() {
		return volumeToInKg;
	}

	public void setVolumeToInKg(BigDecimal volumeToInKg) {
		this.volumeToInKg = volumeToInKg;
	}

	public BigDecimal getHpp() {
		return hpp;
	}

	public void setHpp(BigDecimal hpp) {
		this.hpp = hpp;
	}

	public Lookup getActorProd() {
		return actorProd;
	}

	public void setActorProd(Lookup actorProd) {
		this.actorProd = actorProd;
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

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public Date getProdDateFrom() {
		return prodDateFrom;
	}

	public void setProdDateFrom(Date prodDateFrom) {
		this.prodDateFrom = prodDateFrom;
	}

	public Date getProdDateTo() {
		return prodDateTo;
	}
	
	public void setProdDateTo(Date prodDateTo) {
		this.prodDateTo = prodDateTo;
	}

	public BigDecimal getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}
	
	public String getTransInSourceType() {
		return transInSourceType;
	}

	public void setTransInSourceType(String transInSourceType) {
		this.transInSourceType = transInSourceType;
	}

	public CompanyWarehouse getCompanyWarehouseMain() {
		return companyWarehouseMain;
	}

	public void setCompanyWarehouseMain(CompanyWarehouse companyWarehouseMain) {
		this.companyWarehouseMain = companyWarehouseMain;
	}

	public CompanyWarehouse getCompanyWarehouseOther() {
		return companyWarehouseOther;
	}

	public void setCompanyWarehouseOther(CompanyWarehouse companyWarehouseOther) {
		this.companyWarehouseOther = companyWarehouseOther;
	}
	
	public Party getThirdParty() {
		return thirdParty;
	}
	
	public void setThirdParty(Party thirdParty) {
		this.thirdParty = thirdParty;
	}
	
	@Transient
	public String getThirdPartyName() {
		if(this.thirdParty != null) {
			return this.thirdParty.getName();
		}
		return "";
	}
	
	@Transient
	public void setThirdPartyName(String thirdPartyName) {
		this.thirdPartyName = thirdPartyName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getEstDays() {
		return estDays;
	}

	public void setEstDays(Integer estDays) {
		this.estDays = estDays;
	}
	
	public String getTitle() {
		if(getTransInSourceType()!=null && getCompanyProductFrom()!=null && getCompanyProductTo()!=null){
			return TransInSourceType.TRANS_IN_SOURCE_TYPE_DESCR.get(getTransInSourceType()) +" "+ getProdNo() + " (" + getCompanyProductFrom().getName() +"->" + getCompanyProductTo().getName() + ")";
		}else{
			return null;
		}
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<TransProdOtherItem> getOtherItems() {
		return otherItems;
	}

	public void setOtherItems(List<TransProdOtherItem> otherItems) {
		if(this.otherItems == null) {
			this.otherItems = new ArrayList<TransProdOtherItem>();
		}
		if(otherItems != null) {			
			this.otherItems.clear();
			this.otherItems.addAll(otherItems);
		}
	}
	
	public List<TransProdFee> getFees() {
		return fees;
	}

	public void setFees(List<TransProdFee> fees) {
		if(this.fees == null) {
			this.fees = new ArrayList<TransProdFee>();
		}
		if(fees != null) {			
			this.fees.clear();
			this.fees.addAll(fees);
		}
	}
	
	public List<TransProdMachinery> getMachineries() {
		return machineries;
	}

	public void setMachineries(List<TransProdMachinery> machineries) {
		if(this.machineries == null) {
			this.machineries = new ArrayList<TransProdMachinery>();
		}
		if(machineries != null) {			
			this.machineries.clear();
			this.machineries.addAll(machineries);
		}
	}

}
