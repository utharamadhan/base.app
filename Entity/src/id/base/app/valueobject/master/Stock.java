package id.base.app.valueobject.master;

import id.base.app.valueobject.BaseEntity;
import id.base.app.valueobject.Lookup;
import id.base.app.valueobject.procurement.TransIn;
import id.base.app.valueobject.production.TransProd;

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
import javax.persistence.Transient;

@Entity
@Table(name = "STOCK")
public class Stock extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1925214707296972659L;
	
	@Id
	@SequenceGenerator(name="STOCK_PK_STOCK_SEQ", sequenceName="STOCK_PK_STOCK_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="STOCK_PK_STOCK_SEQ")
	@Column(name = "PK_STOCK", unique = true ,nullable = false)
	private Long pkStock;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_COMPANY_WAREHOUSE")
	private CompanyWarehouse companyWarehouse;
	
	@OneToOne
	@JoinColumn(name="FK_LOOKUP_ITEM_TYPE")
	private Lookup itemType;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_COMPANY_PRODUCT")
	private CompanyProduct companyProduct;
	
	@Column(name="VOLUME")
	private BigDecimal volume;
	
	@OneToOne
	@JoinColumn(name="FK_LOOKUP_UOM")
	private Lookup uom;
	
	@Column(name="VOLUME_IN_KG")
	private BigDecimal volumeInKg;
	
	@Column(name="SUSPENSE_VOLUME_IN_KG")
	private BigDecimal suspenseVolumeInKg;
	
	@Column(name="REMAINING_VOLUME_IN_KG")
	private BigDecimal remainingVolumeInKg;
	
	@Column(name="HPP")
	private BigDecimal hpp;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_TRANS_IN_FROM")
	private TransIn transIn;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_TRANS_PROD_FROM")
	private TransProd transProd;
	
	@Column(name="STATUS")
	private Integer status;
	
	@OneToMany(mappedBy="stock", cascade=CascadeType.ALL, orphanRemoval = true)
	@Column
	private List<StockHistory> stockHistories = new ArrayList<>();
	
	@Transient
	private String companyProductName;
	
	public Long getPkStock() {
		return pkStock;
	}

	public void setPkStock(Long pkStock) {
		this.pkStock = pkStock;
	}

	public CompanyWarehouse getCompanyWarehouse() {
		return companyWarehouse;
	}

	public void setCompanyWarehouse(CompanyWarehouse companyWarehouse) {
		this.companyWarehouse = companyWarehouse;
	}

	public Lookup getItemType() {
		return itemType;
	}
	public void setItemType(Lookup itemType) {
		this.itemType = itemType;
	}
	
	public CompanyProduct getCompanyProduct() {
		return companyProduct;
	}
	public void setCompanyProduct(CompanyProduct companyProduct) {
		this.companyProduct = companyProduct;
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

	public BigDecimal getSuspenseVolumeInKg() {
		return suspenseVolumeInKg;
	}

	public void setSuspenseVolumeInKg(BigDecimal suspenseVolumeInKg) {
		this.suspenseVolumeInKg = suspenseVolumeInKg;
	}

	public BigDecimal getRemainingVolumeInKg() {
		return remainingVolumeInKg;
	}

	public void setRemainingVolumeInKg(BigDecimal remainingVolumeInKg) {
		this.remainingVolumeInKg = remainingVolumeInKg;
	}

	public BigDecimal getHpp() {
		return hpp;
	}

	public void setHpp(BigDecimal hpp) {
		this.hpp = hpp;
	}

	public TransIn getTransIn() {
		return transIn;
	}

	public void setTransIn(TransIn transIn) {
		this.transIn = transIn;
	}

	public TransProd getTransProd() {
		return transProd;
	}

	public void setTransProd(TransProd transProd) {
		this.transProd = transProd;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public List<StockHistory> getStockHistories() {
		return stockHistories;
	}
	public void setStockHistories(List<StockHistory> stockHistories) {
		this.stockHistories = stockHistories;
	}

	public String getCompanyProductName() {
		return companyProductName;
	}

	public void setCompanyProductName(String companyProductName) {
		this.companyProductName = companyProductName;
	}

}
