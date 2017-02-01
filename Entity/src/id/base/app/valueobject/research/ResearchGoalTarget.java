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
@Table(name="RESEARCH_GOAL_TARGET")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="researchGoalTargetJid", scope=ResearchGoalTarget.class)
public class ResearchGoalTarget implements Serializable{

	private static final long serialVersionUID = 3405815570771567020L;
	
	public static final String PK_RESEARCH_GOAL_TARGET = "pkResearchGoalTarget";
	public static final String FK_RESEARCH 			= "research.pkResearch";
	public static final String TITLE				= "title";
	public static final String TIME_PLANNING_DATE_TO= "researchTimePlanning.dateTo";
	public static final String PROGRESS_TYPE		= "progressType";
	public static final String TARGET_DATE			= "targetDate";
	public static final String STATUS				= "status";
	
	public static final String[] MAINTENANCE_LIST_FIELDS = {
		PK_RESEARCH_GOAL_TARGET, TITLE, TIME_PLANNING_DATE_TO, PROGRESS_TYPE, TARGET_DATE, STATUS
	};
	
	public static ResearchGoalTarget getInstance() {
		return new ResearchGoalTarget();
	}
	
	@Id
	@SequenceGenerator(name="RESEARCH_GOAL_TARGET_PK_RESEARCH_GOAL_TARGET_SEQ", sequenceName="RESEARCH_GOAL_TARGET_PK_RESEARCH_GOAL_TARGET_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="RESEARCH_GOAL_TARGET_PK_RESEARCH_GOAL_TARGET_SEQ")
	@Column(name = "PK_RESEARCH_GOAL_TARGET", unique = true ,nullable = false, precision = 10, scale = 0)
	private Long pkResearchGoalTarget;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_RESEARCH", nullable=true)
	private Research research;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="CONTENT")
	private String content;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_RESEARCH_TIME_PLANNING", nullable=true)
	private ResearchTimePlanning researchTimePlanning;
	
	@Column(name="PROGRESS_TYPE")
	private Integer progressType;
	
	@Column(name="TARGET_DATE")
	private Date targetDate;
	
	@Column(name="STATUS")
	private Integer status;
	
	@Transient
	private Long fkResearch;

	public Long getPkResearchGoalTarget() {
		return pkResearchGoalTarget;
	}

	public void setPkResearchGoalTarget(Long pkResearchGoalTarget) {
		this.pkResearchGoalTarget = pkResearchGoalTarget;
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

	public ResearchTimePlanning getResearchTimePlanning() {
		return researchTimePlanning;
	}

	public void setResearchTimePlanning(ResearchTimePlanning researchTimePlanning) {
		this.researchTimePlanning = researchTimePlanning;
	}

	public Integer getProgressType() {
		return progressType;
	}

	public void setProgressType(Integer progressType) {
		this.progressType = progressType;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
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
