package id.base.app.valueobject.inventory;

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

@Entity
@Table(name = "GOODS_RECEIPT_NOTE_DETAIL")
public class GoodsReceiptNoteDetail extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5251092862373976273L;
	
	@Id
	@SequenceGenerator(name="GOODS_RECEIPT_NOTE_DETAIL_PK_GOODS_RECEIPT_NOTE_DETAIL_SEQ", sequenceName="GOODS_RECEIPT_NOTE_DETAIL_PK_GOODS_RECEIPT_NOTE_DETAIL_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="GOODS_RECEIPT_NOTE_DETAIL_PK_GOODS_RECEIPT_NOTE_DETAIL_SEQ")
	@Column(name = "PK_GOODS_RECEIPT_NOTE_DETAIL", unique = true ,nullable = false)
	private Long pkGoodsReceiptNoteDetail;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_GOODS_RECEIPT_NOTE")
	private GoodsReceiptNote goodsReceiptNote;
	
	@Column(name="SEQ_NO")
	private Integer sequenceNumber;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_COMPANY_PRODUCT")
	private CompanyProduct product;
	
	@OneToOne
	@JoinColumn(name="FK_LOOKUP_UOM")
	private Lookup uom;
	
	@Column(name="RECEIVED_QUANTITY")
	private BigDecimal quantity;
	
	@Column(name="RECEIVED_PRICE")
	private BigDecimal price;
	
	@Column(name="RECEIVED_VALUE")
	private BigDecimal value;

	public Long getPkGoodsReceiptNoteElement() {
		return pkGoodsReceiptNoteDetail;
	}
	public void setPkGoodsReceiptNoteElement(Long pkGoodsReceiptNoteElement) {
		this.pkGoodsReceiptNoteDetail = pkGoodsReceiptNoteElement;
	}

	public GoodsReceiptNote getGoodsReceiptNote() {
		return goodsReceiptNote;
	}
	public void setGoodsReceiptNote(GoodsReceiptNote goodsReceiptNote) {
		this.goodsReceiptNote = goodsReceiptNote;
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
