package id.padiku.app.valueobject.business.report;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="VW_STOCK")
public class ViewStock implements Serializable {
	
	public static final String REPORT_STOCK_FILE = "ReportStock_";
	/**
	 * 
	 */
	private static final long serialVersionUID = 8377678857488211450L;

	@Id
	@Column(name="PK_STOCK")
	private Long pkStock; 
	
	@Column(name="FK_COMPANY")
	private Long fkCompany;
	
	@Column(name="SUPPLIER_CODE")
	private String supplierCode;
	
	@Column(name="SUPPLIER_NAME")
	private String supplierName;
	
	@Column(name="PRODUCT_CODE")
	private String productCode;
	
	@Column(name="PRODUCT_NAME")
	private String productName;
	
	@Column(name="FK_LOOKUP_UOM")
	private Long fkLookupUom;
	
	@Column(name="UOM_CODE")
	private String uomCode;
	
	@Column(name="UOM_NAME")
	private String uomName;

	@Column(name="VOLUME")
	private BigDecimal volume;
	
	@Column(name="BUYING_PRICE")
	private BigDecimal buyingPrice;
	
	@Column(name="HPP")
	private BigDecimal hpp;
	
	public Long getPkStock() {
		return pkStock;
	}
	public void setPkStock(Long pkStock) {
		this.pkStock = pkStock;
	}
	
	public Long getFkCompany() {
		return fkCompany;
	}
	public void setFkCompany(Long fkCompany) {
		this.fkCompany = fkCompany;
	}
	
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public Long getFkLookupUom() {
		return fkLookupUom;
	}
	public void setFkLookupUom(Long fkLookupUom) {
		this.fkLookupUom = fkLookupUom;
	}
	
	public String getUomCode() {
		return uomCode;
	}
	public void setUomCode(String uomCode) {
		this.uomCode = uomCode;
	}
	
	public String getUomName() {
		return uomName;
	}
	public void setUomName(String uomName) {
		this.uomName = uomName;
	}
	
	public BigDecimal getVolume() {
		return volume;
	}
	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}
	
	public BigDecimal getBuyingPrice() {
		return buyingPrice;
	}
	public void setBuyingPrice(BigDecimal buyingPrice) {
		this.buyingPrice = buyingPrice;
	}
	
	public BigDecimal getHpp() {
		return hpp;
	}
	public void setHpp(BigDecimal hpp) {
		this.hpp = hpp;
	}
	
}
