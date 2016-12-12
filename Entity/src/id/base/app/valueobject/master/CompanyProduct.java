package id.base.app.valueobject.master;

import id.base.app.SystemConstant;
import id.base.app.valueobject.BaseEntity;
import id.base.app.valueobject.Lookup;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
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

@Entity
@Table(name = "COMPANY_PRODUCT")
public class CompanyProduct extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -2250544624538305660L;
	
	public CompanyProduct() {}
	
	public static CompanyProduct getInstance(Long pkCompany) {
		CompanyProduct obj = new CompanyProduct();
			obj.setCompany(Company.getInstance(pkCompany));
			obj.setStatus(SystemConstant.ValidFlag.VALID);
		return obj;
	}
	
	public static final String ID = "pkCompanyProduct";
	public static final String CODE = "code";
	public static final String NAME = "name";
	public static final String BUYING_PRICE = "buyingPrice";
	public static final String SELLING_PRICE = "sellingPrice";
	public static final String ITEM_TYPE = "type";
	public static final String ITEM_TYPE_ID = ITEM_TYPE+"."+Lookup.ID;
	public static final String STATUS = "status";
	public static final String COMPANY = "company";
	public static final String COMPANY_ID = COMPANY +"."+Company.ID;
	public static final String STOCKS = "stocks";
	public static final String STOCKS_REMAINING_VOLUME_IN_KG = STOCKS +".remainingVolumeInKg";
	public static final String STOCKS_STATUS = STOCKS +".status";
	public static final String PREDEFINED = "predefined";
	
	@Id
	@SequenceGenerator(name="COMPANY_PRODUCT_PK_COMPANY_PRODUCT_SEQ", sequenceName="COMPANY_PRODUCT_PK_COMPANY_PRODUCT_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="COMPANY_PRODUCT_PK_COMPANY_PRODUCT_SEQ")
	@Column(name = "PK_COMPANY_PRODUCT", unique = true ,nullable = false)
	private Long pkCompanyProduct;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_COMPANY")
	private Company company;
	
	@Column(name = "PRODUCT_CODE")
	private String code;
	
	@Column(name = "PRODUCT_NAME")
	private String name;
	
	@OneToOne
	@JoinColumn(name="FK_LOOKUP_UOM")
	private Lookup uom;
	
	@Column(name="BUYING_PRICE")
	private BigDecimal buyingPrice;
	
	@Column(name="SELLING_PRICE")
	private BigDecimal sellingPrice;
	
	@OneToOne
	@JoinColumn(name="FK_LOOKUP_ITEM_TYPE")
	private Lookup type;
	
	@Column(name="IS_PREDEFINED")
	private Boolean predefined;
	
	@Column(name="STATUS")
	private Integer status;
	
	@OneToMany(mappedBy="companyProduct", cascade=CascadeType.DETACH)
	@Column
	private List<Stock> stocks = new ArrayList<Stock>();
	
	public Long getPkCompanyProduct() {
		return pkCompanyProduct;
	}

	public void setPkCompanyProduct(Long pkCompanyProduct) {
		this.pkCompanyProduct = pkCompanyProduct;
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

	public Lookup getUom() {
		return uom;
	}
	public void setUom(Lookup uom) {
		this.uom = uom;
	}

	public BigDecimal getBuyingPrice() {
		return buyingPrice;
	}
	public void setBuyingPrice(BigDecimal buyingPrice) {
		this.buyingPrice = buyingPrice;
	}

	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public Lookup getType() {
		return type;
	}

	public void setType(Lookup type) {
		this.type = type;
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

	public List<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}

}