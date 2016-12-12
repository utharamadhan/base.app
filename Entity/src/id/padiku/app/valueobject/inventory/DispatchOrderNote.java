package id.padiku.app.valueobject.inventory;

import id.padiku.app.SystemConstant;
import id.padiku.app.valueobject.BaseEntity;
import id.padiku.app.valueobject.master.Company;
import id.padiku.app.valueobject.master.CompanyLookup;
import id.padiku.app.valueobject.master.CompanyWarehouse;
import id.padiku.app.valueobject.party.Party;

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
@Table(name = "DISPATCH_ORDER_NOTE")
public class DispatchOrderNote extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1652507333783768693L;

	public DispatchOrderNote() {}
	
	public static DispatchOrderNote getInstance(Long pkCompany) {
		DispatchOrderNote obj = new DispatchOrderNote();
			obj.setCompany(Company.getInstance(pkCompany));
			obj.setStatus(SystemConstant.ValidFlag.VALID);
		return obj;
	}

	@Id
	@SequenceGenerator(name="DISPATCH_ORDER_NOTE_PK_DISPATCH_ORDER_NOTE_SEQ", sequenceName="DISPATCH_ORDER_NOTE_PK_DISPATCH_ORDER_NOTE_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="DISPATCH_ORDER_NOTE_PK_DISPATCH_ORDER_NOTE_SEQ")
	@Column(name = "PK_DISPATCH_ORDER_NOTE", unique = true ,nullable = false)
	private Long pkDispatchOrderNote;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_COMPANY")
	private Company company;
	
	@Column(name="DISPATCH_NUMBER")
	private String dispatchNumber;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_COMPANY_WAREHOUSE")
	private CompanyWarehouse companyWarehouse;

	@Column(name="PICKING_NUMBER")
	private String pickingNumber;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_PARTY_CUSTOMER")
	private Party customer;
	
	@Transient
	private String customerName;
	
	@OneToOne
	@JoinColumn(name = "FK_C_LOOKUP_CURRENCY")
	private CompanyLookup currency;
	
	@Column(name="PICKING_QTY")
	private BigDecimal quantity;
	
	@Column(name="PICKING_SUBVALUE")
	private BigDecimal subvalue;
	
	@Column(name="PICKING_VAT")
	private BigDecimal vatPercent;
	
	@Column(name="PICKING_VAT_VALUE")
	private BigDecimal vatValue;
	
	@Column(name="PICKING_TOTAL_VALUE")
	private BigDecimal totalValue;
	
	@Column(name="PICKING_DATE")
	private Date pickingDate;
	
	@Column(name="STATUS")
	private Integer status;
	
	@OneToMany(mappedBy="dispatchOrderNote", cascade=CascadeType.ALL, orphanRemoval = true)
	private List<DispatchOrderNoteDetail> dispatchOrderNoteDetails = new ArrayList<>();

	public Long getPkDispatchOrderNote() {
		return pkDispatchOrderNote;
	}
	public void setPkDispatchOrderNote(Long pkDispatchOrderNote) {
		this.pkDispatchOrderNote = pkDispatchOrderNote;
	}

	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}

	public String getDispatchNumber() {
		return dispatchNumber;
	}
	public void setDispatchNumber(String dispatchNumber) {
		this.dispatchNumber = dispatchNumber;
	}
	
	public CompanyWarehouse getCompanyWarehouse() {
		return companyWarehouse;
	}
	public void setCompanyWarehouse(CompanyWarehouse companyWarehouse) {
		this.companyWarehouse = companyWarehouse;
	}
	
	public String getPickingNumber() {
		return pickingNumber;
	}
	public void setPickingNumber(String pickingNumber) {
		this.pickingNumber = pickingNumber;
	}

	public Party getCustomer() {
		return customer;
	}
	public void setCustomer(Party customer) {
		this.customer = customer;
	}
	
	@Transient
	public String getCustomerName() {
		if(this.customer!=null){
			return customer.getName();
		}else{			
			return customerName;
		}
	}
	@Transient
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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

	public Date getPickingDate() {
		return pickingDate;
	}
	public void setPickingDate(Date pickingDate) {
		this.pickingDate = pickingDate;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<DispatchOrderNoteDetail> getDispatchOrderNoteDetails() {
		return dispatchOrderNoteDetails;
	}
	public void setDispatchOrderNoteDetails(List<DispatchOrderNoteDetail> dispatchOrderNoteDetails) {
		if(this.dispatchOrderNoteDetails != null) {
			this.dispatchOrderNoteDetails.clear();
		}else{
			this.dispatchOrderNoteDetails = new ArrayList<>();
		}
		if(dispatchOrderNoteDetails != null){			
			this.dispatchOrderNoteDetails.addAll(dispatchOrderNoteDetails);
		}
	}
}
