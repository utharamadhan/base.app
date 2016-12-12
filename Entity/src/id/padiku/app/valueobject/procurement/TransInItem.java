package id.padiku.app.valueobject.procurement;

import id.padiku.app.valueobject.CreateEntity;
import id.padiku.app.valueobject.Lookup;
import id.padiku.app.valueobject.UpdateEntity;
import id.padiku.app.valueobject.master.Company;
import id.padiku.app.valueobject.master.CompanyProduct;
import id.padiku.app.valueobject.party.Party;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "TRANS_IN_ITEM")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="transInItemJid", scope=TransInItem.class)
public class TransInItem implements Serializable {

	private static final long serialVersionUID = -2136171469249524269L;
	
	public static final String ID = "pkTransInItem";
	public static final String TRANS_IN = "transIn";
	public static final String TRANS_IN_THIRD_PARTY = TRANS_IN +"."+ TransIn.THIRD_PARTY;
	public static final String TRANS_IN_THIRD_PARTY_ALIAS = "thirdParty";
	public static final String TRANS_IN_THIRD_PARTY_ALIAS_ID = TRANS_IN_THIRD_PARTY_ALIAS+"."+Party.ID;
	public static final String TRANS_IN_THIRD_PARTY_ALIAS_NAME = TRANS_IN_THIRD_PARTY_ALIAS+"."+Party.NAME;
	public static final String TRANS_IN_ID = TRANS_IN +"."+ TransIn.ID;
	public static final String TRANS_IN_IN_NO = TRANS_IN +"."+ TransIn.IN_NO;
	public static final String TRANS_IN_IN_DATE = TRANS_IN +"."+ TransIn.IN_DATE;
	public static final String TRANS_IN_SOURCE_TYPE = TRANS_IN +"."+ TransIn.SOURCE_TYPE;
	public static final String TRANS_IN_MAIN_FEE = TRANS_IN +"."+ TransIn.MAIN_FEE;
	public static final String TRANS_IN_TOTAL_IN_FEE = TRANS_IN +"."+ TransIn.TOTAL_IN_FEE;
	public static final String TRANS_IN_STATUS = TRANS_IN +"."+ TransIn.STATUS;
	public static final String COMPANY_PRODUCT = "companyProduct";
	public static final String COMPANY_PRODUCT_ID = COMPANY_PRODUCT +"."+ CompanyProduct.ID;
	public static final String COMPANY_PRODUCT_NAME = COMPANY_PRODUCT +"."+ CompanyProduct.NAME;
	public static final String COMPANY_PRODUCT_TO = "companyProductTo";
	public static final String COMPANY_PRODUCT_TO_ID = COMPANY_PRODUCT_TO +"."+ CompanyProduct.ID;
	public static final String COMPANY_PRODUCT_TO_NAME = COMPANY_PRODUCT_TO +"."+ CompanyProduct.NAME;
	public static final String COMPANY_PRODUCT_COMPANY = COMPANY_PRODUCT+"."+"company";
	public static final String COMPANY_PRODUCT_COMPANY_ALIAS = "company";
	public static final String COMPANY_PRODUCT_COMPANY_ALIAS_ID = COMPANY_PRODUCT_COMPANY_ALIAS+"."+Company.ID;
	public static final String VOLUME = "volume";
	public static final String VOLUME_IN_KG = "volumeInKg";
	public static final String REMAINING_VOLUME_IN_KG = "remainingVolumeInKg";
	public static final String UOM = "uom";
	public static final String UOM_ID = UOM +"."+ Lookup.ID;
	public static final String UOM_NAME_ID = UOM +"."+ Lookup.NAME_ID;
	public static final String UOM_NAME_EN = UOM +"."+ Lookup.NAME_EN;
	public static final String ITEM_TYPE = "itemType";
	public static final String ITEM_TYPE_ID = ITEM_TYPE +"."+ Lookup.ID;
	public static final String ITEM_TYPE_NAME_ID = ITEM_TYPE +"."+ Lookup.NAME_ID;
	public static final String FEE = "fee";
	public static final String TOTAL_FEE = "totalFee";
	public static final String STATUS = "status";
	
	public static final String[] MAINTENANCE_LIST_FOR_ADD_TO_STOCK = {
		ID, TRANS_IN_ID, COMPANY_PRODUCT_ID, TRANS_IN_MAIN_FEE, TRANS_IN_TOTAL_IN_FEE, VOLUME_IN_KG, REMAINING_VOLUME_IN_KG, ITEM_TYPE_ID
	};
	
	@Id
	@Column(name = "PK_TRANS_IN_ITEM", unique = true ,nullable = false, precision = 10, scale = 0)
	@SequenceGenerator(name="TRANS_IN_ITEM_PK_TRANS_IN_ITEM_SEQ", sequenceName="TRANS_IN_ITEM_PK_TRANS_IN_ITEM_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TRANS_IN_ITEM_PK_TRANS_IN_ITEM_SEQ")
	@NotNull(groups=UpdateEntity.class, message="{error.message.update.not.allowed}")
	@Null(groups=CreateEntity.class, message="{error.message.create.not.allowed}")
	private Long pkTransInItem;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_TRANS_IN", nullable=true)
	private TransIn transIn = new TransIn();
	
	@OneToOne
	@JoinColumn(name="FK_COMPANY_PRODUCT")
	private CompanyProduct companyProduct;
	
	@Column(name="VOLUME")
	private BigDecimal volume;
	
	@OneToOne
	@JoinColumn(name="FK_LOOKUP_UOM")
	private Lookup uom;
	
	@Column(name="FEE")
	private BigDecimal fee;
	
	@Column(name="TOTAL_FEE")
	private BigDecimal totalFee;
	
	@OneToOne
	@JoinColumn(name="FK_COMPANY_PRODUCT_TO")
	private CompanyProduct companyProductTo;
	
	@Column(name="VOLUME_IN_KG")
	private BigDecimal volumeInKg;
	
	@Column(name="REMAINING_VOLUME_IN_KG")
	private BigDecimal remainingVolumeInKg;
	
	@OneToOne
	@JoinColumn(name="FK_LOOKUP_ITEM_TYPE")
	private Lookup itemType;
	
	@Column(name="STATUS")
	private Integer status;
	
	public Long getPkTransInItem() {
		return pkTransInItem;
	}

	public void setPkTransInItem(Long pkTransInItem) {
		this.pkTransInItem = pkTransInItem;
	}

	public TransIn getTransIn() {
		return transIn;
	}

	public void setTransIn(TransIn transIn) {
		this.transIn = transIn;
	}

	public CompanyProduct getCompanyProduct() {
		return companyProduct;
	}

	public void setCompanyProduct(CompanyProduct companyProduct) {
		this.companyProduct = companyProduct;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public CompanyProduct getCompanyProductTo() {
		return companyProductTo;
	}

	public void setCompanyProductTo(CompanyProduct companyProductTo) {
		this.companyProductTo = companyProductTo;
	}

	public BigDecimal getRemainingVolumeInKg() {
		return remainingVolumeInKg;
	}

	public void setRemainingVolumeInKg(BigDecimal remainingVolumeInKg) {
		this.remainingVolumeInKg = remainingVolumeInKg;
	}
	
	public Lookup getItemType() {
		return itemType;
	}

	public void setItemType(Lookup itemType) {
		this.itemType = itemType;
	}

	public BigDecimal getVolumeInKg() {
		return volumeInKg;
	}

	public void setVolumeInKg(BigDecimal volumeInKg) {
		this.volumeInKg = volumeInKg;
	}
	
}
