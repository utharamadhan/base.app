package id.base.app.valueobject.research;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="RESEARCH_BUDGETING")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="researchBudgetingJid", scope=ResearchBudgeting.class)
public class ResearchBudgeting implements Serializable{

	private static final long serialVersionUID = -95175200547586944L;
	
	@Id
	@SequenceGenerator(name="RESEARCH_BUDGETING_PK_RESEARCH_BUDGETING_SEQ", sequenceName="RESEARCH_BUDGETING_PK_RESEARCH_BUDGETING_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="RESEARCH_BUDGETING_PK_RESEARCH_BUDGETING_SEQ")
	@Column(name = "PK_RESEARCH_BUDGETING", unique = true ,nullable = false, precision = 10, scale = 0)
	private Long pkResearchBudgeting;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_RESEARCH", nullable=true)
	private Research research;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="BUDGET_DATE")
	private Date budgetDate;
	
	@Column(name="CONTENT")
	private String content;
	
	@Column(name="SIGN")
	private Integer sign;
	
	@Column(name="AMOUNT")
	private BigInteger amount;
	
	@Column(name="STATUS")
	private Integer status;

	public Long getPkResearchBudgeting() {
		return pkResearchBudgeting;
	}

	public void setPkResearchBudgeting(Long pkResearchBudgeting) {
		this.pkResearchBudgeting = pkResearchBudgeting;
	}

	public Research getResearch() {
		return research;
	}

	public void setResearch(Research research) {
		this.research = research;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getBudgetDate() {
		return budgetDate;
	}

	public void setBudgetDate(Date budgetDate) {
		this.budgetDate = budgetDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getSign() {
		return sign;
	}

	public void setSign(Integer sign) {
		this.sign = sign;
	}

	public BigInteger getAmount() {
		return amount;
	}

	public void setAmount(BigInteger amount) {
		this.amount = amount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
