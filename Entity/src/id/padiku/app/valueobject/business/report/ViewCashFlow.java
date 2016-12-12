package id.padiku.app.valueobject.business.report;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="VW_CASH_FLOW")
public class ViewCashFlow implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1597967193019034505L;
	
	public static final String FK_COMPANY = "fkCompany";
	public static final String SOURCE = "source";
	public static final String TRANS_DATE = "transDate";
	public static final String DEBIT = "debit";
	public static final String KREDIT = "kredit";
	public static final String FILTER_TRANS_DATE_START = "transDateStart";
	public static final String FILTER_TRANS_DATE_END = "transDateEnd";	

	public static final String FILE_NAME = "LaporanCashFlow_";
	
	@Id
	@Column(name="ROWNUM_CASH_FLOW")
	private Long pkCashFlow;
	
	@Column(name="FK_COMPANY")
	private Long fkCompany;
	
	@Column(name="SOURCE")
	private String source;
	
	@Column(name="ID")
	private String id;

	@Column(name="TRANS_DATE")
	private Date transDate;
	
	@Column(name="DESC")
	private String descr;
	
	@Column(name="MAIN_FEE")
	private BigDecimal mainFee;
	
	@Column(name="OTHER_FEE")
	private BigDecimal otherFee;

	@Column(name="DEBIT")
	private BigDecimal debit;
	
	@Column(name="KREDIT")
	private BigDecimal kredit;

	public Long getPkCashFlow() {
		return pkCashFlow;
	}
	public void setPkCashFlow(Long pkCashFlow) {
		this.pkCashFlow = pkCashFlow;
	}
	
	public Long getFkCompany() {
		return fkCompany;
	}
	public void setFkCompany(Long fkCompany) {
		this.fkCompany = fkCompany;
	}
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public Date getTransDate() {
		return transDate;
	}
	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}

	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}

	public BigDecimal getMainFee() {
		return mainFee;
	}
	public void setMainFee(BigDecimal mainFee) {
		this.mainFee = mainFee;
	}

	public BigDecimal getOtherFee() {
		return otherFee;
	}
	public void setOtherFee(BigDecimal otherFee) {
		this.otherFee = otherFee;
	}

	public BigDecimal getDebit() {
		return debit;
	}
	public void setDebit(BigDecimal debit) {
		this.debit = debit;
	}
	public BigDecimal getKredit() {
		return kredit;
	}
	public void setKredit(BigDecimal kredit) {
		this.kredit = kredit;
	}

}
