package id.base.app.valueobject.research;

import id.base.app.valueobject.BaseEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "RESEARCH")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="researchJid", scope=Research.class)
public class Research extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 366527046429586298L;
	
	public static final String PK_RESEARCH			= "pkResearch";
	public static final String TITLE 				= "title";
	public static final String SUB_TITLE			= "subTitle";
	public static final String ABSTRACT 			= "abstract";
	public static final String INTRODUCTION			= "introduction";
	public static final String LITERATURE_REVIEW	= "literatureReview";
	public static final String METHODS				= "methods";
	public static final String RESULTS				= "results";
	public static final String STATUS				= "status";
	public static final String RESEARCH_DATE		= "researchDate";
	
	public static Research getInstance() {
		return new Research();
	}
	
	@Id
	@SequenceGenerator(name="RESEARCH_PK_RESEARCH_SEQ", sequenceName="RESEARCH_PK_RESEARCH_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="RESEARCH_PK_RESEARCH_SEQ")
	@Column(name = "PK_RESEARCH", unique = true ,nullable = false)
	private Long pkResearch;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="SUBTITLE")
	private String subTitle;
	
	@Column(name="IMAGE_URL")
	private String imageURL;
	
	@Column(name="ABSTRACT_DESC")
	private String abstractDesc;
	
	@Column(name="INTRODUCTION")
	private String introduction;
	
	@Column(name="LITERATURE_REVIEW")
	private String literatureReview;
	
	@Column(name="METHODS")
	private String methods;
	
	@Column(name="RESULTS")
	private String results;
	
	@Column(name="RESEARCH_DATE_FROM")
	private Date researchDateFrom;
	
	@Column(name="RESEARCH_DATE_TO")
	private Date researchDateTo;
	
	@Column(name="STATUS")
	private Integer status;
	
	@OneToMany(mappedBy="research", cascade=CascadeType.DETACH)
	private List<ResearchResearchTopic> researchTopics;
	
	@OneToMany(mappedBy="research", cascade=CascadeType.DETACH)
	private List<ResearchResearcher> researchers;
	
	@OneToMany(mappedBy="research", cascade=CascadeType.DETACH)
	private List<ResearchTimePlanning> timePlannings;
	
	@OneToMany(mappedBy="research", cascade=CascadeType.DETACH)
	private List<ResearchBudgeting> budgetings;
	
	@OneToMany(mappedBy="research", cascade=CascadeType.DETACH)
	private List<ResearchGoalTarget> goalTarget;
	
	@OneToMany(mappedBy="research", cascade=CascadeType.DETACH)
	private List<ResearchMemo> memos;
	
	@OneToMany(mappedBy="research", cascade=CascadeType.DETACH)
	private List<ResearchFile> files;

	public Long getPkResearch() {
		return pkResearch;
	}

	public void setPkResearch(Long pkResearch) {
		this.pkResearch = pkResearch;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getAbstractDesc() {
		return abstractDesc;
	}

	public void setAbstractDesc(String abstractDesc) {
		this.abstractDesc = abstractDesc;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getLiteratureReview() {
		return literatureReview;
	}

	public void setLiteratureReview(String literatureReview) {
		this.literatureReview = literatureReview;
	}

	public String getMethods() {
		return methods;
	}

	public void setMethods(String methods) {
		this.methods = methods;
	}

	public String getResults() {
		return results;
	}

	public void setResults(String results) {
		this.results = results;
	}

	public Date getResearchDateFrom() {
		return researchDateFrom;
	}

	public void setResearchDateFrom(Date researchDateFrom) {
		this.researchDateFrom = researchDateFrom;
	}

	public Date getResearchDateTo() {
		return researchDateTo;
	}

	public void setResearchDateTo(Date researchDateTo) {
		this.researchDateTo = researchDateTo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<ResearchResearchTopic> getResearchTopics() {
		return researchTopics;
	}

	public void setResearchTopics(List<ResearchResearchTopic> researchTopics) {
		this.researchTopics = researchTopics;
	}

	public List<ResearchResearcher> getResearchers() {
		return researchers;
	}

	public void setResearchers(List<ResearchResearcher> researchers) {
		this.researchers = researchers;
	}

	public List<ResearchTimePlanning> getTimePlannings() {
		return timePlannings;
	}

	public void setTimePlannings(List<ResearchTimePlanning> timePlannings) {
		this.timePlannings = timePlannings;
	}

	public List<ResearchBudgeting> getBudgetings() {
		return budgetings;
	}

	public void setBudgetings(List<ResearchBudgeting> budgetings) {
		this.budgetings = budgetings;
	}

	public List<ResearchGoalTarget> getGoalTarget() {
		return goalTarget;
	}

	public void setGoalTarget(List<ResearchGoalTarget> goalTarget) {
		this.goalTarget = goalTarget;
	}

	public List<ResearchMemo> getMemos() {
		return memos;
	}

	public void setMemos(List<ResearchMemo> memos) {
		this.memos = memos;
	}

	public List<ResearchFile> getFiles() {
		return files;
	}

	public void setFiles(List<ResearchFile> files) {
		this.files = files;
	}
}
