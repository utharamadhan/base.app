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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="RESEARCH_TIME_PLANNING")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="researchTimePlanningJid", scope=ResearchTimePlanning.class)
public class ResearchTimePlanning implements Serializable{

	private static final long serialVersionUID = 4773703045736684745L;

	@Id
	@SequenceGenerator(name="RESEARCH_TM_PLAN_PK_RESEARCH_TIME_PLANNING_SEQ", sequenceName="RESEARCH_TM_PLAN_PK_RESEARCH_TIME_PLANNING_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="RESEARCH_TM_PLAN_PK_RESEARCH_TIME_PLANNING_SEQ")
	@Column(name = "PK_RESEARCH_TIME_PLANNING", unique = true ,nullable = false, precision = 10, scale = 0)
	private Long pkResearchTimePlanning;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_RESEARCH", nullable=true)
	private Research research;
	
	@Column(name="DATE_FROM")
	private Date dateFrom;
	
	@Column(name="DATE_TO")
	private Date dateTo;
	
	@Column(name="TIME_FROM")
	private Date timeFrom;
	
	@Column(name="TIME_TO")
	private Date timeTo;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="CONTENT")
	private String content;
	  
	@Column(name="STATUS")
	private Integer status;

	public Long getPkResearchTimePlanning() {
		return pkResearchTimePlanning;
	}

	public void setPkResearchTimePlanning(Long pkResearchTimePlanning) {
		this.pkResearchTimePlanning = pkResearchTimePlanning;
	}

	public Research getResearch() {
		return research;
	}

	public void setResearch(Research research) {
		this.research = research;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public Date getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(Date timeFrom) {
		this.timeFrom = timeFrom;
	}

	public Date getTimeTo() {
		return timeTo;
	}

	public void setTimeTo(Date timeTo) {
		this.timeTo = timeTo;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
