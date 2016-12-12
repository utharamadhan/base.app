package id.base.app.valueobject.inventory;

import id.base.app.valueobject.BaseEntity;
import id.base.app.valueobject.Lookup;
import id.base.app.valueobject.master.CompanyProduct;
import id.base.app.valueobject.party.Party;

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
@Table(name = "DISPATCH_ORDER_NOTE_DETAIL")
public class DispatchOrderNoteDetail extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2777399580958525881L;

	@Id
	@SequenceGenerator(name="DISPATCH_ORDER_NOTE_DETAIL_PK_DISPATCH_ORDER_NOTE_DETAIL_SEQ", sequenceName="DISPATCH_ORDER_NOTE_DETAIL_PK_DISPATCH_ORDER_NOTE_DETAIL_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="DISPATCH_ORDER_NOTE_DETAIL_PK_DISPATCH_ORDER_NOTE_DETAIL_SEQ")
	@Column(name = "PK_DISPATCH_ORDER_NOTE_DETAIL", unique = true ,nullable = false)
	private Long pkDispatchOrderNoteDetail;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_DISPATCH_ORDER_NOTE")
	private DispatchOrderNote dispatchOrderNote;
	
	@Column(name="SEQ_NO")
	private Integer sequenceNumber;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_PARTY_SALES_ORDER")
	private Party salesOrder;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_COMPANY_PRODUCT")
	private CompanyProduct product;
	
	@OneToOne
	@JoinColumn(name="FK_LOOKUP_UOM")
	private Lookup uom;
	
	@Column(name="DISPATCH_QUANTITY")
	private BigDecimal quantity;
	
	@Column(name="DISPATCH_PRICE")
	private BigDecimal price;
	
	@Column(name="DISPATCH_VALUE")
	private BigDecimal value;

	public Long getPkDispatchOrderNoteDetail() {
		return pkDispatchOrderNoteDetail;
	}
	public void setPkDispatchOrderNoteDetail(Long pkDispatchOrderNoteDetail) {
		this.pkDispatchOrderNoteDetail = pkDispatchOrderNoteDetail;
	}

	public DispatchOrderNote getDispatchOrderNote() {
		return dispatchOrderNote;
	}
	public void setDispatchOrderNote(DispatchOrderNote dispatchOrderNote) {
		this.dispatchOrderNote = dispatchOrderNote;
	}

	public Integer getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	
	public Party getSalesOrder() {
		return salesOrder;
	}
	public void setSalesOrder(Party salesOrder) {
		this.salesOrder = salesOrder;
	}
	
	public CompanyProduct getProduct() {
		return product;
	}
	public void setProduct(CompanyProduct product) {
		this.product = product;
	}

	public Lookup getUom() {
		return uom;
	}
	public void setUom(Lookup uom) {
		this.uom = uom;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
}
