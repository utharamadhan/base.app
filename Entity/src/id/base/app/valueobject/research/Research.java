package id.base.app.valueobject.research;

import id.base.app.valueobject.BaseEntity;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "RESEARCH")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="researchJid", scope=Research.class)
public class Research extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 366527046429586298L;
	
	public static final String PK_RESEARCH			= "pkResearch";
	public static final String CODE 				= "code";
	public static final String NAME 				= "name";
	public static final String DESCRIPTION 			= "description";
	public static final String STATUS				= "status";
	
	public static Research getInstance() {
		return new Research();
	}
	
	@Id
	@SequenceGenerator(name="RESEARCH_PK_RESEARCH_SEQ", sequenceName="RESEARCH_PK_RESEARCH_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="RESEARCH_PK_RESEARCH_SEQ")
	@Column(name = "PK_RESEARCH", unique = true ,nullable = false)
	private Long pkResearch;
	
	@ManyToOne
	@JoinColumn(name="FK_RESEARCH_TOPIC")
	private ResearchTopic researchTopic;
	
	@Column(name="CODE")
	private String code;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="SUBTITLE")
	private String subtitle;
	
	@Column(name="BASIC_PICTURE_URL")
	private String basicPictureURL;
	
	@Column(name="FULL_DESCRIPTION")
	private String fullDescription;
	
	@Column(name="RESEARCH_DATE")
	private Date researchDate;
	
	@Column(name="FILE_URL")
	private String fileURL;
	
	@Column(name="STATUS")
	private Integer status;

	public Long getPkResearch() {
		return pkResearch;
	}
	public void setPkResearch(Long pkResearch) {
		this.pkResearch = pkResearch;
	}

	public ResearchTopic getResearchTopic() {
		return researchTopic;
	}
	public void setResearchTopic(ResearchTopic researchTopic) {
		this.researchTopic = researchTopic;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getBasicPictureURL() {
		return basicPictureURL;
	}
	public void setBasicPictureURL(String basicPictureURL) {
		this.basicPictureURL = basicPictureURL;
	}

	public String getFullDescription() {
		return fullDescription;
	}
	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}

	public Date getResearchDate() {
		return researchDate;
	}
	public void setResearchDate(Date researchDate) {
		this.researchDate = researchDate;
	}
	
	public String getFileURL() {
		return fileURL;
	}
	public void setFileURL(String fileURL) {
		this.fileURL = fileURL;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

}
