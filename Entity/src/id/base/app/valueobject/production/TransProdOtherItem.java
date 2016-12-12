package id.base.app.valueobject.production;

import id.base.app.valueobject.Lookup;
import id.base.app.valueobject.master.CompanyProduct;
import id.base.app.valueobject.master.Stock;

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
@Table(name = "TRANS_PROD_OTHER_ITEM")
public class TransProdOtherItem implements Serializable {

	private static final long serialVersionUID = -6942748519101976919L;
	
	@Id
	@SequenceGenerator(name="TRANS_PROD_OTHER_ITEM_PK_TRANS_PROD_OTHER_ITEM_SEQ", sequenceName="TRANS_PROD_OTHER_ITEM_PK_TRANS_PROD_OTHER_ITEM_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TRANS_PROD_OTHER_ITEM_PK_TRANS_PROD_OTHER_ITEM_SEQ")
	@Column(name = "PK_TRANS_PROD_OTHER_ITEM", unique = true ,nullable = false)
	private Long pkTransProdOtherItem;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_TRANS_PROD")
	private TransProd transProd;

	@ManyToOne
	@JoinColumn(name="FK_COMPANY_PRODUCT")
	private CompanyProduct companyProduct;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_STOCK")
	private Stock stock;
	
	@Column(name="VOLUME")
	private BigDecimal volume;
	
	@Column(name="VOLUME_IN_KG")
	private BigDecimal volumeInKg;
	
	@OneToOne
	@JoinColumn(name="FK_LOOKUP_UOM")
	private Lookup uom;
	
	@Column(name="STATUS")
	private Integer status;

	public Long getPkTransProdOtherItem() {
		return pkTransProdOtherItem;
	}

	public void setPkTransProdOtherItem(Long pkTransProdOtherItem) {
		this.pkTransProdOtherItem = pkTransProdOtherItem;
	}

	public TransProd getTransProd() {
		return transProd;
	}

	public void setTransProd(TransProd transProd) {
		this.transProd = transProd;
	}

	public CompanyProduct getCompanyProduct() {
		return companyProduct;
	}

	public void setCompanyProduct(CompanyProduct companyProduct) {
		this.companyProduct = companyProduct;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
