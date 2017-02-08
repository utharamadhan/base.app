package id.base.app.valueobject.research;

import id.base.app.valueobject.BaseEntity;

import java.io.Serializable;

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
@Table(name="RESEARCH_RESULT")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="researchResultJid", scope=ResearchResult.class)
public class ResearchResult extends BaseEntity implements Serializable{

	private static final long serialVersionUID = -4395233547070390376L;
	
	public static ResearchResult getInstance() {
		return new ResearchResult();
	}
	
	@Id
	@SequenceGenerator(name="RESEARCH_RESULT_PK_RESEARCH_RESULT_SEQ", sequenceName="RESEARCH_RESULT_PK_RESEARCH_RESULT_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="RESEARCH_RESULT_PK_RESEARCH_RESULT_SEQ")
	@Column(name = "PK_RESEARCH_RESULT", unique = true ,nullable = false, precision = 10, scale = 0)
	private Long pkResearchResult;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_RESEARCH", nullable=true)
	private Research research;
	
	@Column(name="RESULT_TITLE")
	private String resultTitle;
	
	@Column(name="RESULT_DESC")
	private String resultDesc;

	public Long getPkResearchResult() {
		return pkResearchResult;
	}

	public void setPkResearchResult(Long pkResearchResult) {
		this.pkResearchResult = pkResearchResult;
	}

	public Research getResearch() {
		return research;
	}

	public void setResearch(Research research) {
		this.research = research;
	}

	public String getResultTitle() {
		return resultTitle;
	}

	public void setResultTitle(String resultTitle) {
		this.resultTitle = resultTitle;
	}

	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}
}
