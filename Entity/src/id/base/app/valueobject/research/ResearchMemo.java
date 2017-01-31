package id.base.app.valueobject.research;

import java.io.Serializable;
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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="RESEARCH_MEMO")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="researchMemoJid", scope=ResearchMemo.class)
public class ResearchMemo implements Serializable{

	private static final long serialVersionUID = 4385944858336723227L;
	
	public static final String PK_RESEARCH_MEMO = "pkResearchMemo";
	public static final String TITLE			= "title";
	public static final String STATUS			= "status";
	
	public static ResearchMemo getInstance() {
		return new ResearchMemo();
	}
	
	@Id
	@SequenceGenerator(name="RESEARCH_MEMO_PK_RESEARCH_MEMO_SEQ", sequenceName="RESEARCH_MEMO_PK_RESEARCH_MEMO_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="RESEARCH_MEMO_PK_RESEARCH_MEMO_SEQ")
	@Column(name = "PK_RESEARCH_MEMO", unique = true ,nullable = false, precision = 10, scale = 0)
	private Long pkResearchMemo;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_RESEARCH", nullable=true)
	private Research research;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="CONTENT")
	private String content;
	
	@Column(name="NOTIFY_ME_DATE")
	private Date notifyMeDate;
	
	@Column(name="STATUS")
	private Integer status;

	@Transient
	private Long fkResearch;
	
	public Long getPkResearchMemo() {
		return pkResearchMemo;
	}

	public void setPkResearchMemo(Long pkResearchMemo) {
		this.pkResearchMemo = pkResearchMemo;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getNotifyMeDate() {
		return notifyMeDate;
	}

	public void setNotifyMeDate(Date notifyMeDate) {
		this.notifyMeDate = notifyMeDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getFkResearch() {
		return fkResearch;
	}

	public void setFkResearch(Long fkResearch) {
		this.fkResearch = fkResearch;
	}
	
}
