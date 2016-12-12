package id.padiku.app.valueobject.inventory;

import id.padiku.app.SystemConstant;
import id.padiku.app.valueobject.BaseEntity;
import id.padiku.app.valueobject.master.Company;
import id.padiku.app.valueobject.master.CompanyLookup;
import id.padiku.app.valueobject.master.CompanyWarehouse;
import id.padiku.app.valueobject.party.Party;
import id.padiku.app.valueobject.procurement.PurchaseOrder;

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
@Table(name = "GOODS_RECEIPT_NOTE")
public class GoodsReceiptNote extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8450727138327986615L;
	
	public GoodsReceiptNote() {}
	
	public static GoodsReceiptNote getInstance(Long pkCompany) {
		GoodsReceiptNote obj = new GoodsReceiptNote();
			obj.setCompany(Company.getInstance(pkCompany));
			obj.setStatus(SystemConstant.ValidFlag.VALID);
		return obj;
	}

	@Id
	@SequenceGenerator(name="GOODS_RECEIPT_NOTE_PK_GOODS_RECEIPT_NOTE_SEQ", sequenceName="GOODS_RECEIPT_NOTE_PK_GOODS_RECEIPT_NOTE_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="GOODS_RECEIPT_NOTE_PK_GOODS_RECEIPT_NOTE_SEQ")
	@Column(name = "PK_GOODS_RECEIPT_NOTE", unique = true ,nullable = false)
	private Long pkGoodsReceiptNote;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_COMPANY")
	private Company company;
	
	@Column(name="GRN_NUMBER")
	private String grnNumber;
	
	@OneToOne
	@JoinColumn(name="FK_C_LOOKUP_GRN_FROM")
	private CompanyLookup grnFrom;
	
	@Column(name="GRN_PLAN_NO")
	private String grnPlanNumber;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_PARTY_SUPPLIER")
	private Party supplier;
	
	@Transient
	private String supplierName;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_COMPANY_WAREHOUSE")
	private CompanyWarehouse companyWarehouse;
	
	@OneToOne
	@JoinColumn(name = "FK_C_LOOKUP_CURRENCY")
	private CompanyLookup currency;
	
	@Column(name="GRN_QTY")
	private BigDecimal quantity;
	
	@Column(name="GRN_SUBVALUE")
	private BigDecimal subvalue;
	
	@Column(name="GRN_VAT")
	private BigDecimal vatPercent;
	
	@Column(name="GRN_VAT_VALUE")
	private BigDecimal vatValue;
	
	@Column(name="GRN_TOTAL_VALUE")
	private BigDecimal totalValue;
	
	@Column(name="GRN_DATE")
	private Date goodsReceiptNoteDate;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_PURCHASE_ORDER")
	private PurchaseOrder purchaseOrder;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_PARTY_REFERENCE")
	private Party partyReference;
	
	@Column(name="GRN_IS_POSTED")
	private Boolean isPosted;
	
	@Transient
	private String isPostedDesc;
	
	@Column(name="STATUS")
	private Integer status;
	
	@OneToMany(mappedBy="goodsReceiptNote", cascade=CascadeType.ALL, orphanRemoval = true)
	private List<GoodsReceiptNoteDetail> goodsReceiptNoteDetails = new ArrayList<>();

	public Long getPkGoodsReceiptNote() {
		return pkGoodsReceiptNote;
	}
	public void setPkGoodsReceiptNote(Long pkGoodsReceiptNote) {
		this.pkGoodsReceiptNote = pkGoodsReceiptNote;
	}

	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}

	public String getGrnNumber() {
		return grnNumber;
	}
	public void setGrnNumber(String grnNumber) {
		this.grnNumber = grnNumber;
	}

	public CompanyLookup getGrnFrom() {
		return grnFrom;
	}
	public void setGrnFrom(CompanyLookup grnFrom) {
		this.grnFrom = grnFrom;
	}

	public String getGrnPlanNumber() {
		return grnPlanNumber;
	}
	public void setGrnPlanNumber(String grnPlanNumber) {
		this.grnPlanNumber = grnPlanNumber;
	}

	public Party getSupplier() {
		return supplier;
	}
	public void setSupplier(Party supplier) {
		this.supplier = supplier;
	}
	
	@Transient
	public String getSupplierName() {
		if(this.supplier!=null){
			return supplier.getName();
		}else{			
			return supplierName;
		}
	}
	@Transient
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public CompanyWarehouse getCompanyWarehouse() {
		return companyWarehouse;
	}
	public void setCompanyWarehouse(CompanyWarehouse companyWarehouse) {
		this.companyWarehouse = companyWarehouse;
	}

	public CompanyLookup getCurrency() {
		return currency;
	}
	public void setCurrency(CompanyLookup currency) {
		this.currency = currency;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getSubvalue() {
		return subvalue;
	}
	public void setSubvalue(BigDecimal subvalue) {
		this.subvalue = subvalue;
	}

	public BigDecimal getVatPercent() {
		return vatPercent;
	}
	public void setVatPercent(BigDecimal vatPercent) {
		this.vatPercent = vatPercent;
	}

	public BigDecimal getVatValue() {
		return vatValue;
	}
	public void setVatValue(BigDecimal vatValue) {
		this.vatValue = vatValue;
	}

	public BigDecimal getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(BigDecimal totalValue) {
		this.totalValue = totalValue;
	}

	public Date getGoodsReceiptNoteDate() {
		return goodsReceiptNoteDate;
	}
	public void setGoodsReceiptNoteDate(Date goodsReceiptNoteDate) {
		this.goodsReceiptNoteDate = goodsReceiptNoteDate;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}
	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public Party getPartyReference() {
		return partyReference;
	}
	public void setPartyReference(Party partyReference) {
		this.partyReference = partyReference;
	}

	public Boolean getIsPosted() {
		return isPosted;
	}
	public void setIsPosted(Boolean isPosted) {
		this.isPosted = isPosted;
	}

	@Transient
	public String getIsPostedDesc() {
		if(isPosted != null){
			if(isPosted) {
				return "Posted";
			} else {
				return "Draft";
			}
		}
		return "";
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<GoodsReceiptNoteDetail> getGoodsReceiptNoteDetails() {
		return goodsReceiptNoteDetails;
	}
	public void setGoodsReceiptNoteDetails(List<GoodsReceiptNoteDetail> goodsReceiptNoteDetails) {
		if(this.goodsReceiptNoteDetails != null) {
			this.goodsReceiptNoteDetails.clear();
		}else{
			this.goodsReceiptNoteDetails = new ArrayList<>();
		}
		if(goodsReceiptNoteDetails != null){			
			this.goodsReceiptNoteDetails.addAll(goodsReceiptNoteDetails);
		}
	}
	
	
	
}
