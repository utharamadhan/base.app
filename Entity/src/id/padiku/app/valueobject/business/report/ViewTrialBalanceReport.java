package id.padiku.app.valueobject.business.report;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="VW_TRIAL_BALANCE_REPORT")
public class ViewTrialBalanceReport implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2488555611904144593L;
	public static final String PERIOD = "period";
	public static final String YEAR = "year";
	public static final String TRIAL_BALANCE_REPORT_FILE = "TrialBalanceReport_";

	@Id
	@Column(name="ACCOUNT")
	private String accountName;
	
	@Column(name="SUN_GL_CODE")
	private String sunGlCode;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="MTD_DEBIT")
	private Long debitMtd;
	
	@Column(name="MTD_CREDIT")
	private Long creditMtd;
	
	@Column(name="YTD_DEBIT")
	private Long debitYtd;
	
	@Column(name="YTD_CREDIT")
	private Long creditYtd;
	
	@Column(name="PERIOD")
	private Integer period;
	
	@Column(name="YEAR")
	private Integer year;
	
	@Transient
	private Integer level;

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getDebitMtd() {
		return debitMtd;
	}

	public void setDebitMtd(Long debitMtd) {
		this.debitMtd = debitMtd;
	}

	public Long getCreditMtd() {
		return creditMtd;
	}

	public void setCreditMtd(Long creditMtd) {
		this.creditMtd = creditMtd;
	}

	public Long getDebitYtd() {
		return debitYtd;
	}

	public void setDebitYtd(Long debitYtd) {
		this.debitYtd = debitYtd;
	}

	public Long getCreditYtd() {
		return creditYtd;
	}

	public void setCreditYtd(Long creditYtd) {
		this.creditYtd = creditYtd;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getSunGlCode() {
		return sunGlCode;
	}

	public void setSunGlCode(String sunGlCode) {
		this.sunGlCode = sunGlCode;
	}	
}
