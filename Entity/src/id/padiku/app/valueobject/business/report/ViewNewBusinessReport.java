package id.padiku.app.valueobject.business.report;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="VW_NEW_BUSINESS_REPORT")
public class ViewNewBusinessReport implements Serializable {

	public static final String NEW_BUSINESS_REPORT_FILE = "NewBusinessReport_";
	/**
	 * 
	 */
	private static final long serialVersionUID = 4902717634083294292L;
//	public static final String ISSUED_DATE = "issuedDate";
//	public static final String ISSUED_DATE_START = "issuedDateStart";
//	public static final String ISSUED_DATE_END   = "issuedDateEnd";
	public static final String TRANSACTION_DATE = "transactionDate";
	public static final String TRANSACTION_DATE_START = "transactionDateStart";
	public static final String TRANSACTION_DATE_END	= "transactionDateEnd";
	public static final String PARTNER = "pkPartner";
	public static final String PRODUCT = "pkProduct";
	public static final String PARTY_USER_MAKER = "fkPartyUserMaker";
	public static final String FK_PARTY_SERVICING_AGENT = "fkServicingAgent";
	public static final String CONTRACT_STATUS_CODE = "contractStatusCode";
	public static final String PREMIUM_STATUS_CODE = "premiumStatusCode";
	public static final String DISPATCH_ADDRESS = "pkDispatchAddress";
	
	@Id
	@Column(name="PK_CONTRACT")
	private Integer pkContract;
	
	@Column(name="DISTRIBUTION_CHANNEL")
	private String distributionChannel;
	
	@Column(name="PK_DISPATCH_ADDRESS")
	private Long pkDispatchAddress;
	
	@Column(name="BRANCH_CODE")
	private String branchCode;
	
	@Column(name="BRANCH_NAME")
	private String branchName;
	
	@Column(name="AGENT_NUMBER")
	private Long agentNumber;
	
	@Column(name="AGENT_NAME")
	private String agentName;
	
	@Column(name="POLICY_NUMBER")
	private String policyNumber;
	
	@Column(name="POLICY_BUYER")
	private String policyBuyer;
	
	@Column(name="POLICY_BUYER_CLIENT_NUMBER")
	private String policyBuyerClientNumber;
	
	@Column(name="LIFE_ASSURED_NAME")
	private String lifeAssuredName;
	
	@Column(name="LIFE_ASSURED_CLIENT_NUMBER")
	private String lifeAssuredClientNumber;
	
	@Column(name="LIFE_ASSURED_ID_NUMBER")
	private String lifeAssuredIdNumber;
	
	@Column(name="ISSUED_DATE")
	private Date issuedDate;
	
	@Column(name="TRANSACTION_DATE")
	private Date transactionDate;
	
	@Column(name="EXPIRED_DATE")
	private Date expiredDate;
	
	@Column(name="PRODUCT_CODE")
	private String productCode;
	
	@Column(name="PRODUCT_NAME")
	private String productName;
	
	@Column(name="CURRENCY")
	private String currencyCode;
	
	@Column(name="BASIC_PREMIUM")
	private Long basicPremium;
	
	@Column(name="GENDER")
	private String lifeAssuredGender;
	
	@Column(name="PK_PARTNER")
	private Integer pkPartner;
	
	@Column(name="PK_PRODUCT")
	private Integer pkProduct;
	
	@Column(name="FK_PARTY_USER_MAKER")
	private Integer fkPartyUserMaker;
	
	@Column(name="FK_PARTY_SERVICING_AGENT")
	private Long fkServicingAgent;
	
	@Column(name="CONTRACT_STATUS")
	private String contractStatus;
	
	@Column(name="PREMIUM_STATUS")
	private String premiumStatus;
	
	@Column(name="CONTRACT_STATUS_CODE")
	private String contractStatusCode;
	
	@Column(name="PREMIUM_STATUS_CODE")
	private String premiumStatusCode;
	
	@Column(name="COMMISSION_AMOUNT")
	private Long commissionAmount;
	
	@Column(name="VAT")
	private Long vat;
	
	@Column(name="WHT")
	private Long wht;
	
	@Transient
	private int sequenceNumber;
	
	public int getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	
	public String getDistributionChannel() {
		return distributionChannel;
	}
	public void setDistributionChannel(String distributionChannel) {
		this.distributionChannel = distributionChannel;
	}
	
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	public Long getAgentNumber() {
		return agentNumber;
	}
	public void setAgentNumber(Long agentNumber) {
		this.agentNumber = agentNumber;
	}
	
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	
	public String getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}
	
	public String getPolicyBuyer() {
		return policyBuyer;
	}
	public void setPolicyBuyer(String policyBuyer) {
		this.policyBuyer = policyBuyer;
	}
	
	public String getPolicyBuyerClientNumber() {
		return policyBuyerClientNumber;
	}
	public void setPolicyBuyerClientNumber(String policyBuyerClientNumber) {
		this.policyBuyerClientNumber = policyBuyerClientNumber;
	}
	
	public String getLifeAssuredName() {
		return lifeAssuredName;
	}
	public void setLifeAssuredName(String lifeAssuredName) {
		this.lifeAssuredName = lifeAssuredName;
	}
	
	public String getLifeAssuredClientNumber() {
		return lifeAssuredClientNumber;
	}
	public void setLifeAssuredClientNumber(String lifeAssuredClientNumber) {
		this.lifeAssuredClientNumber = lifeAssuredClientNumber;
	}
	
	public String getLifeAssuredIdNumber() {
		return lifeAssuredIdNumber;
	}
	public void setLifeAssuredIdNumber(String lifeAssuredIdNumber) {
		this.lifeAssuredIdNumber = lifeAssuredIdNumber;
	}
	
	public Date getIssuedDate() {
		return issuedDate;
	}
	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}
	
	public Date getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}
	
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
	public Long getBasicPremium() {
		return basicPremium;
	}
	public void setBasicPremium(Long basicPremium) {
		this.basicPremium = basicPremium;
	}
	
	public Integer getPkPartner() {
		return pkPartner;
	}
	public void setPkPartner(Integer pkPartner) {
		this.pkPartner = pkPartner;
	}
	
	public Integer getPkProduct() {
		return pkProduct;
	}
	public void setPkProduct(Integer pkProduct) {
		this.pkProduct = pkProduct;
	}
	
	public Integer getFkPartyUserMaker() {
		return fkPartyUserMaker;
	}
	public void setFkPartyUserMaker(Integer fkPartyUserMaker) {
		this.fkPartyUserMaker = fkPartyUserMaker;
	}
	
	public String getLifeAssuredGender() {
		return lifeAssuredGender;
	}
	public void setLifeAssuredGender(String lifeAssuredGender) {
		this.lifeAssuredGender = lifeAssuredGender;
	}
	
	public Integer getPkContract() {
		return pkContract;
	}
	public void setPkContract(Integer pkContract) {
		this.pkContract = pkContract;
	}
	
	public String getContractStatus() {
		return contractStatus;
	}
	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}
	
	public String getPremiumStatus() {
		return premiumStatus;
	}
	public void setPremiumStatus(String premiumStatus) {
		this.premiumStatus = premiumStatus;
	}
	
	public String getContractStatusCode() {
		return contractStatusCode;
	}
	public void setContractStatusCode(String contractStatusCode) {
		this.contractStatusCode = contractStatusCode;
	}
	
	public String getPremiumStatusCode() {
		return premiumStatusCode;
	}
	public void setPremiumStatusCode(String premiumStatusCode) {
		this.premiumStatusCode = premiumStatusCode;
	}
	
	public Long getCommissionAmount() {
		return commissionAmount;
	}
	public void setCommissionAmount(Long commissionAmount) {
		this.commissionAmount = commissionAmount;
	}
	
	public Long getVat() {
		return vat;
	}
	public void setVat(Long vat) {
		this.vat = vat;
	}
	
	public Long getWht() {
		return wht;
	}
	public void setWht(Long wht) {
		this.wht = wht;
	}
	
	public Long getPkDispatchAddress() {
		return pkDispatchAddress;
	}
	public void setPkDispatchAddress(Long pkDispatchAddress) {
		this.pkDispatchAddress = pkDispatchAddress;
	}
	
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	public Long getFkServicingAgent() {
		return fkServicingAgent;
	}
	public void setFkServicingAgent(Long fkServicingAgent) {
		this.fkServicingAgent = fkServicingAgent;
	}
	
}
