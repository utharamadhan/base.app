package id.base.app.valueobject.procurement;

import id.base.app.valueobject.BaseEntity;
import id.base.app.valueobject.Lookup;
import id.base.app.valueobject.master.CompanyProduct;

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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "PURCHASE_ORDER_DETAIL")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="purchaseOrderDetailJid", scope=PurchaseOrderDetail.class)
public class PurchaseOrderDetail extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4090194500708619499L;
	
	@Id
	@SequenceGenerator(name="PURCHASE_ORDER_DETAIL_PK_PURCHASE_ORDER_DETAIL_SEQ", sequenceName="PURCHASE_ORDER_DETAIL_PK_PURCHASE_ORDER_DETAIL_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PURCHASE_ORDER_DETAIL_PK_PURCHASE_ORDER_DETAIL_SEQ")
	@Column(name = "PK_PURCHASE_ORDER_DETAIL", unique = true ,nullable = false)
	private Long pkPurchaseOrderDetail;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_PURCHASE_ORDER")
	private PurchaseOrder purchaseOrder;
	
	@Column(name="SEQ_NO")
	private Integer sequenceNumber;

	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_COMPANY_PRODUCT")
	private CompanyProduct product;
	
	@OneToOne
	@JoinColumn(name = "FK_LOOKUP_UOM")
	private Lookup uom;
	
	@Column(name = "ORDER_QTY")
	private BigDecimal quantity;
	
	@Column(name = "PURCHASE_PRICE")
	private BigDecimal purchasePrice;
	
	@Column(name = "ORDER_VALUE")
	private BigDecimal orderValue;

	public Long getPkPurchaseOrderDetail() {
		return pkPurchaseOrderDetail;
	}
	public void setPkPurchaseOrderDetail(Long pkPurchaseOrderDetail) {
		this.pkPurchaseOrderDetail = pkPurchaseOrderDetail;
	}
	
	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}
	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}
	
	public Integer getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
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
	
	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	
	public BigDecimal getOrderValue() {
		return orderValue;
	}
	public void setOrderValue(BigDecimal orderValue) {
		this.orderValue = orderValue;
	}
}