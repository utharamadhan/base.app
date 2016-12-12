package id.base.app.valueobject.procurement;

import id.base.app.SystemConstant;
import id.base.app.util.DateTimeFunction;
import id.base.app.valueobject.BaseEntity;
import id.base.app.valueobject.Lookup;
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
@Table(name = "TRANS_IN")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="transInJid", scope=TransIn.class)
public class TransIn extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 2955025181777627612L;

	public TransIn() {}
	
	public static TransIn getInstance(Long pkCompany) {
		TransIn obj = new TransIn();
			obj.setCompany(Company.getInstance(pkCompany));
			obj.setStatus(SystemConstant.ValidFlag.VALID);
		return obj;
	}
	
	public static final String ID = "pkTransIn";
	public static final String SOURCE_TYPE = "sourceType";
	public static final String ITEM_TYPE = "itemType";
	public static final String IN_NO = "inNo";
	public static final String THIRD_PARTY = "thirdParty";
	public static final String THIRD_PARTY_NAME = THIRD_PARTY +"."+ Party.NAME;
	public static final String TOF = "termOfPayment";
	public static final String TOF_NAME_ID = TOF +"."+ Lookup.NAME_ID;
	public static final String TOF_NAME_EN = TOF +"."+ Lookup.NAME_EN;
	public static final String IN_DATE = "inDate";
	public static final String MAIN_FEE = "mainFee";
	public static final String TOTAL_IN_FEE = "totalInFee";
	public static final String STATUS = "status";
	public static final String COMPANY = "company";
	public static final String COMPANY_ID = COMPANY + ".pkCompany";
	
	public static final String TRANS_IN_ITEMS = "items";
	public static final String TRANS_IN_ITEMS_STATUS = TRANS_IN_ITEMS +"."+ TransInItem.STATUS;
	public static final String TRANS_IN_ITEMS_COMPANY_PRODUCT_VALFIELD = TRANS_IN_ITEMS + "[%d].companyProduct.pkCompanyProduct";
	public static final String TRANS_IN_ITEMS_VOLUME_VALFIELD = TRANS_IN_ITEMS + "[%d].volume";
	public static final String TRANS_IN_ITEMS_UOM_VALFIELD = TRANS_IN_ITEMS + "[%d].uom.pkLookup";
	public static final String TRANS_IN_ITEMS_FEE_VALFIELD = TRANS_IN_ITEMS + "[%d].fee";
	public static final String TRANS_IN_ITEMS_TOTAL_FEE_VALFIELD = TRANS_IN_ITEMS + "[%d].totalFee";
	
	public static final String TRANS_IN_FEES = "fees";
	public static final String TRANS_IN_FEES_DESCR_VALFIELD = TRANS_IN_FEES + "[%d].descr";
	public static final String TRANS_IN_FEES_TOTAL_VALUE_VALFIELD = TRANS_IN_FEES + "[%d].fee";
	
	@Id
	@SequenceGenerator(name="TRANS_IN_PK_TRANS_IN_SEQ", sequenceName="TRANS_IN_PK_TRANS_IN_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TRANS_IN_PK_TRANS_IN_SEQ")
	@Column(name = "PK_TRANS_IN", unique = true ,nullable = false)
	private Long pkTransIn;

	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_COMPANY")
	private Company company;
	
	@Column(name="SOURCE_TYPE")
	private String sourceType;
	
	@Transient
	private String sourceTypeDescr;
	
	@Column(name="IN_NO")
	private String inNo;
	
	@OneToOne
	@JoinColumn(name="FK_PARTY_THIRD_PARTY")
	private Party thirdParty;
	
	@Transient
	private String thirdPartyName;
	
	@OneToOne
	@JoinColumn(name="FK_C_LOOKUP_TOP")
	private CompanyLookup termOfPayment;
	
	@Column(name="IN_DATE")
	private Date inDate;
	
	@Column(name="MAIN_FEE")
	private BigDecimal mainFee;
	
	@Column(name="TOTAL_IN_FEE")
	private BigDecimal totalInFee;
	
	@Column(name="ITEM_TYPE_CATEGORY")
	private String itemTypeCategory;
	
	@Column(name="STATUS")
	private Integer status;
	
	@OneToMany(mappedBy="transIn", cascade=CascadeType.ALL, orphanRemoval = true)
	@Column
	private List<TransInFee> fees = new ArrayList<>();
	
	@OneToMany(mappedBy="transIn", cascade=CascadeType.ALL, orphanRemoval = true)
	@Column
	private List<TransInItem> items = new ArrayList<>();

	@Transient
	private String inDateString;
	
	public Long getPkTransIn() {
		return pkTransIn;
	}

	public void setPkTransIn(Long pkTransIn) {
		this.pkTransIn = pkTransIn;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getSourceType() {
		return sourceType;
	}
	
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	
	public String getSourceTypeDescr() {
		return SystemConstant.TransInSourceType.TRANS_IN_SOURCE_TYPE_DESCR.get(sourceType);
	}

	public String getInNo() {
		return inNo;
	}

	public void setInNo(String inNo) {
		this.inNo = inNo;
	}

	public Party getThirdParty() {
		return thirdParty;
	}
	public void setThirdParty(Party thirdParty) {
		this.thirdParty = thirdParty;
	}
	@Transient
	public String getThirdPartyName() {
		if(this.thirdParty != null) {
			return this.thirdParty.getName();
		}
		return "";
	}
	@Transient
	public void setThirdPartyName(String thirdPartyName) {
		this.thirdPartyName = thirdPartyName;
	}

	public CompanyLookup getTermOfPayment() {
		return termOfPayment;
	}

	public void setTermOfPayment(CompanyLookup termOfPayment) {
		this.termOfPayment = termOfPayment;
	}

	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public BigDecimal getMainFee() {
		return mainFee;
	}

	public void setMainFee(BigDecimal mainFee) {
		this.mainFee = mainFee;
	}

	public BigDecimal getTotalInFee() {
		return totalInFee;
	}

	public void setTotalInFee(BigDecimal totalInFee) {
		this.totalInFee = totalInFee;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getItemTypeCategory() {
		return itemTypeCategory;
	}

	public void setItemTypeCategory(String itemTypeCategory) {
		this.itemTypeCategory = itemTypeCategory;
	}

	public List<TransInFee> getFees() {
		return fees;
	}
	public void setFees(List<TransInFee> fees) {
		if(this.fees == null) {
			this.fees = new ArrayList<TransInFee>();
		}
		if(fees != null) {			
			this.fees.clear();
			this.fees.addAll(fees);
		}
	}

	public List<TransInItem> getItems() {
		return items;
	}

	public void setItems(List<TransInItem> items) {
		if(this.items == null) {
			this.items = new ArrayList<TransInItem>();
		}
		if(items != null) {			
			this.items.clear();
			this.items.addAll(items);
		}
	}

	public String getInDateString() {
		if(inDate!=null){
			return DateTimeFunction.date2String(inDate, SystemConstant.SYSTEM_DATE_MONTH_POINT);
		}else{
			return null;	
		}
	}

}