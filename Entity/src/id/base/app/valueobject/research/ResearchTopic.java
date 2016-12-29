package id.base.app.valueobject.research;

import id.base.app.valueobject.BaseEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "RESEARCH_TOPIC")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="researchTopicJid", scope=ResearchTopic.class)
public class ResearchTopic extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2437553903886281337L;
	
	public static final String PK_RESEARCH_TOPIC	= "pkResearchTopic";
	public static final String CODE 				= "code";
	public static final String NAME 				= "name";
	public static final String DESCRIPTION 			= "description";
	public static final String STATUS				= "status";
	
	public static ResearchTopic getInstance() {
		return new ResearchTopic();
	}
	
	@Id
	@SequenceGenerator(name="RESEARCH_TOPIC_PK_RESEARCH_TOPIC_SEQ", sequenceName="RESEARCH_TOPIC_PK_RESEARCH_TOPIC_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="RESEARCH_TOPIC_PK_RESEARCH_TOPIC_SEQ")
	@Column(name = "PK_RESEARCH_TOPIC", unique = true ,nullable = false)
	private Long pkResearchTopic;
	
	@Column(name="CODE")
	private String code;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="STATUS")
	private Integer status;

	public Long getPkResearchTopic() {
		return pkResearchTopic;
	}
	public void setPkResearchTopic(Long pkResearchTopic) {
		this.pkResearchTopic = pkResearchTopic;
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

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

}
