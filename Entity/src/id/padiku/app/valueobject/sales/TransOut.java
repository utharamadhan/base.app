package id.padiku.app.valueobject.sales;

import id.padiku.app.SystemConstant;
import id.padiku.app.valueobject.BaseEntity;
import id.padiku.app.valueobject.Lookup;
import id.padiku.app.valueobject.master.Company;
import id.padiku.app.valueobject.master.CompanyLookup;
import id.padiku.app.valueobject.master.CompanyProduct;
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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "TRANS_OUT")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="transOutJid", scope=TransOut.class)
public class TransOut extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 337482988493182164L;
	
	public TransOut() {}
	
	public static TransOut getInstance(Long pkCompany) {
		TransOut obj = new TransOut();
			obj.setCompany(Company.getInstance(pkCompany));
			obj.setStatus(SystemConstant.ValidFlag.VALID);
		return obj;
	}
	
	public static final String ID = "pkTransOut";
	public static final String COMPANY = "company";
	public static final String COMPANY_ID = COMPANY + ".pkCompany";
	public static final String OUT_NO = "outNo";
	public static final String STATUS = "status";
	
	@Id
	@SequenceGenerator(name="TRANS_OUT_PK_TRANS_OUT_SEQ", sequenceName="TRANS_OUT_PK_TRANS_OUT_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TRANS_OUT_PK_TRANS_OUT_SEQ")
	@Column(name = "PK_TRANS_OUT", unique = true ,nullable = false)
	private Long pkTransOut;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_COMPANY")
	private Company company;
	
	@Column(name="SOURCE_TYPE")
	private String sourceType;
	
	@OneToOne
	@JoinColumn(name="FK_LOOKUP_ITEM_TYPE")
	private Lookup itemType;
	
	@Column(name="OUT_NO")
	private String outNo;
	
	@OneToOne
	@JoinColumn(name="FK_PARTY_THIRD_PARTY")
	private Party thirdParty;
	
	@Transient
	private String thirdPartyName;
	
	@OneToOne
	@JoinColumn(name="FK_PARTY_TRANSPORTER")
	private Party transporter;
	
	@OneToOne
	@JoinColumn(name="FK_C_LOOKUP_TOP")
	private CompanyLookup termOfPayment;
	
	@Column(name="OUT_DATE")
	private Date outDate;
	
	@Column(name="MAIN_FEE")
	private BigDecimal mainFee;
	
	@Column(name="TOTAL_OUT_FEE")
	private BigDecimal totalOutFee;
	
	@Column(name="RECEIVED_DATE")
	private Date receivedDate;
	
	@Column(name="RECEIVED_MESSAGE")
	private String receivedMessage;
	
	@Column(name="ADVICE_MAIN_FEE")
	private BigDecimal adviceMainFee;
	
	@Column(name="MARGIN_PERCENTAGE")
	private Integer marginPercentage;
	
	@Column(name="STATUS")
	private Integer status;
	
	@OneToMany(mappedBy="transOut", cascade=CascadeType.ALL, orphanRemoval = true)
	@Column
	private List<TransOutFee> fees = new ArrayList<>();
	
	@OneToMany(mappedBy="transOut", cascade=CascadeType.ALL, orphanRemoval = true)
	@Column
	private List<TransOutItem> items = new ArrayList<>();
	
	@Transient
	private CompanyProduct stockCompanyProduct;

	public Long getPkTransOut() {
		return pkTransOut;
	}
	public void setPkTransOut(Long pkTransOut) {
		this.pkTransOut = pkTransOut;
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

	public Lookup getItemType() {
		return itemType;
	}
	public void setItemType(Lookup itemType) {
		this.itemType = itemType;
	}

	public String getOutNo() {
		return outNo;
	}
	public void setOutNo(String outNo) {
		this.outNo = outNo;
	}

	public Party getThirdParty() {
		return thirdParty;
	}
	public void setThirdParty(Party thirdParty) {
		this.thirdParty = thirdParty;
	}
	@Transient
	public String getThirdPartyName() {
		if(thirdParty != null) {
			return thirdParty.getName();
		}
		return "";
	}
	@Transient
	public void setThirdPartyName(String thirdPartyName) {
		this.thirdPartyName = thirdPartyName;
	}
	
	public Party getTransporter() {
		return transporter;
	}

	public void setTransporter(Party transporter) {
		this.transporter = transporter;
	}

	public CompanyLookup getTermOfPayment() {
		return termOfPayment;
	}
	public void setTermOfPayment(CompanyLookup termOfPayment) {
		this.termOfPayment = termOfPayment;
	}

	public Date getOutDate() {
		return outDate;
	}
	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public BigDecimal getMainFee() {
		return mainFee;
	}

	public void setMainFee(BigDecimal mainFee) {
		this.mainFee = mainFee;
	}

	public BigDecimal getTotalOutFee() {
		return totalOutFee;
	}

	public void setTotalOutFee(BigDecimal totalOutFee) {
		this.totalOutFee = totalOutFee;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public String getReceivedMessage() {
		return receivedMessage;
	}

	public void setReceivedMessage(String receivedMessage) {
		this.receivedMessage = receivedMessage;
	}
	
	public BigDecimal getAdviceMainFee() {
		return adviceMainFee;
	}

	public void setAdviceMainFee(BigDecimal adviceMainFee) {
		this.adviceMainFee = adviceMainFee;
	}

	public Integer getMarginPercentage() {
		return marginPercentage;
	}

	public void setMarginPercentage(Integer marginPercentage) {
		this.marginPercentage = marginPercentage;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<TransOutFee> getFees() {
		return fees;
	}
	public void setFees(List<TransOutFee> fees) {
		if(this.fees == null) {
			this.fees = new ArrayList<TransOutFee>();
		}
		if(fees != null) {			
			this.fees.clear();
			this.fees.addAll(fees);
		}
	}

	public List<TransOutItem> getItems() {
		return items;
	}
	public void setItems(List<TransOutItem> items) {
		if(this.items == null) {
			this.items = new ArrayList<TransOutItem>();
		}
		if(items != null) {			
			this.items.clear();
			this.items.addAll(items);
		}
	}

	@Transient
	public CompanyProduct getStockCompanyProduct() {
		if(this.items != null && this.items.size() > 0){
			for(TransOutItem item : this.items) {
				if(item.getStock() != null) {
					return item.getStock().getCompanyProduct();
				}
			}
		}
		return stockCompanyProduct;
	}
	@Transient
	public void setStockCompanyProduct(CompanyProduct stockCompanyProduct) {
		this.stockCompanyProduct = stockCompanyProduct;
	}
	
}