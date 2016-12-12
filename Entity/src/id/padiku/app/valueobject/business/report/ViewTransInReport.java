package id.padiku.app.valueobject.business.report;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="VW_TRANS_IN")
public class ViewTransInReport implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5656533495088436436L;

	public static final String FILE_NAME = "LaporanPenerimaan_";
	
	public static final String IN_DATE = "inDate";
	public static final String FILTER_IN_DATE_START = "inDateStart";
	public static final String FILTER_IN_DATE_END = "inDateEnd";
	public static final String SOURCE_TYPE = "sourceType";
	public static final String FK_LOOKUP_ITEM_TYPE = "fkLookupItemType";

	@Id
	@Column(name="PK_TRANS_IN")
	private Long pkTransIn; 
	
	@Column(name="FK_COMPANY")
	private Long fkCompany;
	
	@Column(name="IN_DATE")
	private Date inDate;

	@Column(name="SUPPLIER_CODE")
	private String supplierCode;
	
	@Column(name="SUPPLIER_NAME")
	private String supplierName;
	
	@Column(name="PRODUCT_CODE")
	private String productCode;
	
	@Column(name="PRODUCT_NAME")
	private String productName;
	
	@Column(name="SOURCE_TYPE")
	private String sourceType;
	
	@Column(name="FK_LOOKUP_ITEM_TYPE")
	private Long fkLookupItemType;
	
	@Column(name="ITEM_TYPE_CODE")
	private String itemTypeCode;
	
	@Column(name="FK_LOOKUP_UOM")
	private Long fkLookupUOM;
	
	@Column(name = "UOM_CODE")
	private String uomCode;
	
	@Column(name = "VOLUME")
	private BigDecimal volume;
	
	@Column(name = "FEE")
	private BigDecimal fee;
	
	@Column(name="TOTAL_FEE")
	private BigDecimal totalFee;
	
	@Column(name="CREATION_TIME")
	private Date creationTime;

	public Long getPkTransIn() {
		return pkTransIn;
	}
	public void setPkTransIn(Long pkTransIn) {
		this.pkTransIn = pkTransIn;
	}

	public Long getFkCompany() {
		return fkCompany;
	}
	public void setFkCompany(Long fkCompany) {
		this.fkCompany = fkCompany;
	}

	public Date getInDate() {
		return inDate;
	}
	public void setInDate(Date inDate) {
		this.inDate = inDate;
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

	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public Long getFkLookupItemType() {
		return fkLookupItemType;
	}
	public void setFkLookupItemType(Long fkLookupItemType) {
		this.fkLookupItemType = fkLookupItemType;
	}

	public String getItemTypeCode() {
		return itemTypeCode;
	}
	public void setItemTypeCode(String itemTypeCode) {
		this.itemTypeCode = itemTypeCode;
	}

	public Long getFkLookupUOM() {
		return fkLookupUOM;
	}
	public void setFkLookupUOM(Long fkLookupUOM) {
		this.fkLookupUOM = fkLookupUOM;
	}

	public String getUomCode() {
		return uomCode;
	}
	public void setUomCode(String uomCode) {
		this.uomCode = uomCode;
	}

	public BigDecimal getVolume() {
		return volume;
	}
	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}
	
	public BigDecimal getFee() {
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	
	public BigDecimal getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}
	
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	
}
