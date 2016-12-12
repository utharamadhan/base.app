package id.base.app.valueobject.business.report;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="VW_COST_EXPENSES_REPORT")
public class ViewCostExpensesReport implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5656533495088436436L;

	public static final String TRANS_DATE = "transDate";
	public static final String FILTER_TRANS_DATE_START = "transDateStart";
	public static final String FILTER_TRANS_DATE_END = "transDateEnd";
	public static final String FK_COMPANY = "fkCompany";
	public static final String STATUS = "status";
	
	public static final String FILE_NAME = "LaporanPenerimaan_";

	@Id
	@Column(name="ROWNUM_COST_EXPENSES")
	private Long pkCostExpenses; 
	
	@Column(name="PK_TRANS")
	private Long pkTrans;
	
	@Column(name="FK_COMPANY")
	private Long fkCompany;

	@Column(name="TRANS_DATE")
	private Date transDate;
	
	@Column(name="DESCR")
	private String descr;
	
	@Column(name="FEE")
	private BigDecimal fee;
	
	@Column(name="STATUS")
	private Integer status;

	public Long getPkCostExpenses() {
		return pkCostExpenses;
	}
	public void setPkCostExpenses(Long pkCostExpenses) {
		this.pkCostExpenses = pkCostExpenses;
	}

	public Long getPkTrans() {
		return pkTrans;
	}
	public void setPkTrans(Long pkTrans) {
		this.pkTrans = pkTrans;
	}

	public Long getFkCompany() {
		return fkCompany;
	}
	public void setFkCompany(Long fkCompany) {
		this.fkCompany = fkCompany;
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

	public BigDecimal getFee() {
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
