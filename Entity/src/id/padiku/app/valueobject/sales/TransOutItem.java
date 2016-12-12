package id.padiku.app.valueobject.sales;

import id.padiku.app.valueobject.CreateEntity;
import id.padiku.app.valueobject.Lookup;
import id.padiku.app.valueobject.UpdateEntity;
import id.padiku.app.valueobject.master.Stock;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "TRANS_OUT_ITEM")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="transOutItemJid", scope=TransOutItem.class)
public class TransOutItem implements Serializable {

	private static final long serialVersionUID = -9024404974149394334L;
	
	public static final String ID = "pkTransOutItem";
	public static final String VOLUME = "volume";
	public static final String UOM = "uom";
	public static final String UOM_NAME_ID = UOM +"."+ Lookup.NAME_ID;
	public static final String UOM_NAME_EN = UOM +"."+ Lookup.NAME_EN;
	public static final String FEE = "fee";
	public static final String TOTAL_FEE = "totalFee";
	
	@Id
	@Column(name = "PK_TRANS_OUT_ITEM", unique = true ,nullable = false, precision = 10, scale = 0)
	@SequenceGenerator(name="TRANS_OUT_ITEM_PK_TRANS_OUT_ITEM_SEQ", sequenceName="TRANS_OUT_ITEM_PK_TRANS_OUT_ITEM_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TRANS_OUT_ITEM_PK_TRANS_OUT_ITEM_SEQ")
	@NotNull(groups=UpdateEntity.class, message="{error.message.update.not.allowed}")
	@Null(groups=CreateEntity.class, message="{error.message.create.not.allowed}")
	private Long pkTransOutItem;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_TRANS_OUT", nullable=true)
	private TransOut transOut = new TransOut();
	
	@OneToOne
	@JoinColumn(name="FK_STOCK")
	private Stock stock;

	@Column(name="VOLUME")
	private BigDecimal volume;
	
	@Column(name="VOLUME_IN_KG")
	private BigDecimal volumeInKg;
	
	@OneToOne
	@JoinColumn(name="FK_LOOKUP_UOM")
	private Lookup uom;
	
	@Column(name="FEE")
	private BigDecimal fee;
	
	@Column(name="TOTAL_FEE")
	private BigDecimal totalFee;
	
	@Transient
	private Date lastDateTransaction;
	
	@Transient
	private String thirdPartyName;

	public Long getPkTransOutItem() {
		return pkTransOutItem;
	}

	public void setPkTransOutItem(Long pkTransOutItem) {
		this.pkTransOutItem = pkTransOutItem;
	}

	public TransOut getTransOut() {
		return transOut;
	}

	public void setTransOut(TransOut transOut) {
		this.transOut = transOut;
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

	@Transient
	public void setLastDateTransaction(Date lastDateTransaction) {
		this.lastDateTransaction = lastDateTransaction;
	}

	@Transient
	public void setThirdPartyName(String thirdPartyName) {
		this.thirdPartyName = thirdPartyName;
	}
}
