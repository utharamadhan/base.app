package id.base.app.valueobject.procurement;

import id.base.app.SystemConstant;
import id.base.app.valueobject.BaseEntity;
import id.base.app.valueobject.master.Company;
import id.base.app.valueobject.master.CompanyLookup;
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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "PURCHASE_ORDER")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="purchaseOrderJid", scope=PurchaseOrder.class)
public class PurchaseOrder extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5177987669458648329L;
	
	public PurchaseOrder() {}
	
	public static PurchaseOrder getInstance(Long pkRmu) {
		PurchaseOrder obj = new PurchaseOrder();
			obj.setCompany(Company.getInstance(pkRmu));
			obj.setStatus(SystemConstant.ValidFlag.VALID);
		return obj;
	}

	@Id
	@SequenceGenerator(name="PURCHASE_ORDER_PK_PURCHASE_ORDER_SEQ", sequenceName="PURCHASE_ORDER_PK_PURCHASE_ORDER_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PURCHASE_ORDER_PK_PURCHASE_ORDER_SEQ")
	@Column(name = "PK_PURCHASE_ORDER", unique = true ,nullable = false)
	private Long pkPurchaseOrder;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_COMPANY")
	private Company company;
	
	@Column(name="PO_NO")
	private String poNumber;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_PARTY_SUPPLIER")
	private Party supplier;
	
	@Transient
	private String supplierName;
	
	@OneToOne
	@JoinColumn(name = "FK_C_LOOKUP_TOF")
	private CompanyLookup termOfPayment;
	
	@OneToOne
	@JoinColumn(name = "FK_C_CURRENCY")
	private CompanyLookup currency;
	
	@Column(name="PO_QTY")
	private BigDecimal quantity;
	
	@Column(name="PO_SUBVALUE")
	private BigDecimal subvalue;
	
	@Column(name="PO_VAT")
	private BigDecimal vatPercent;
	
	@Column(name="PO_VAT_VALUE")
	private BigDecimal vatValue;
	
	@Column(name="PO_TOTAL_VALUE")
	private BigDecimal totalValue;
	
	@Column(name="PO_DATE")
	private Date purchaseOrderDate;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_PARTY_REFERENCE")
	private Party partyReference;
	
	@Column(name="STATUS")
	private Integer status;
	
	@OneToMany(mappedBy="purchaseOrder", cascade=CascadeType.ALL, orphanRemoval = true)
	private List<PurchaseOrderDetail> purchaseOrderElements = new ArrayList<>();
	
	public static final String ID = "pkPurchaseOrder";
	public static final String COMPANY = "company";
	public static final String PO_NUMBER = "poNumber";
	public static final String SUPPLIER = "supplier";
	public static final String TERM_OF_PAYMENT = "termOfPayment";
	public static final String CURRENCY = "currency";
	public static final String QUANTITY = "quantity";
	public static final String SUBVALUE = "subvalue";
	public static final String VAT_PERCENT = "vatPercent";
	public static final String VAT_VALUE = "vatValue";
	public static final String TOTAL_VALUE = "totalValue";
	public static final String PURCHASE_ORDER_DATE = "purchaseOrderDate";
	public static final String PARTY_REFERENCE = "partyReference";
	public static final String STATUS = "status";
	public static final String PURCHASE_ORDER_ELEMENTS = "purchaseOrderElements";
	
	public Long getPkPurchaseOrder() {
		return pkPurchaseOrder;
	}
	public void setPkPurchaseOrder(Long pkPurchaseOrder) {
		this.pkPurchaseOrder = pkPurchaseOrder;
	}

	public Company getCompany() {
		return company;
	}
	public void setCompany(Company rmu) {
		this.company = rmu;
	}

	public String getPoNumber() {
		return poNumber;
	}
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public Party getSupplier() {
		return supplier;
	}
	public void setSupplier(Party supplier) {
		this.supplier = supplier;
	}
	
	@Transient
	public String getSupplierName() {
		if(supplier != null){
			return supplier.getName();
		}else{
			return "";
		}
	}
	@Transient
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public CompanyLookup getTermOfPayment() {
		return termOfPayment;
	}
	public void setTermOfPayment(CompanyLookup termOfPayment) {
		this.termOfPayment = termOfPayment;
	}

	public CompanyLookup getCurrency() {
		return currency;
	}
	public void setCurrency(CompanyLookup currency) {
		this.currency = currency;
	}
	
	public Date getPurchaseOrderDate() {
		return purchaseOrderDate;
	}
	public void setPurchaseOrderDate(Date purchaseOrderDate) {
		this.purchaseOrderDate = purchaseOrderDate;
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
	
	public Party getPartyReference() {
		return partyReference;
	}
	public void setPartyReference(Party partyReference) {
		this.partyReference = partyReference;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public List<PurchaseOrderDetail> getPurchaseOrderElements() {
		return purchaseOrderElements;
	}
	public void setPurchaseOrderElements(List<PurchaseOrderDetail> purchaseOrderElements) {
		if(this.purchaseOrderElements != null) {
			this.purchaseOrderElements.clear();
		}else{
			this.purchaseOrderElements = new ArrayList<>();
		}
		if(purchaseOrderElements != null){
			this.purchaseOrderElements.addAll(purchaseOrderElements);
		}
	}
}