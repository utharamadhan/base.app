package id.base.app.valueobject.research;

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
@Table(name = "RESEARCH_RESEARCH_TOPIC")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="researchResearchTopicJid", scope=ResearchResearchTopic.class)
public class ResearchResearchTopic implements Serializable{

	private static final long serialVersionUID = -8288212075899723042L;
	
	@Id
	@SequenceGenerator(name="RESEARCH_RS_TOPIC_PK_RESEARCH_RESEARCH_TOPIC_SEQ", sequenceName="RESEARCH_RS_TOPIC_PK_RESEARCH_RESEARCH_TOPIC_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="RESEARCH_RS_TOPIC_PK_RESEARCH_RESEARCH_TOPIC_SEQ")
	@Column(name = "PK_RESEARCH_RESEARCH_TOPIC", unique = true ,nullable = false, precision = 10, scale = 0)
	private Long pkResearchResearchTopic;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_RESEARCH", nullable=true)
	private Research research;

	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_RESEARCH_TOPIC", nullable=true)
	private ResearchTopic researchTopic;

	public Long getPkResearchResearchTopic() {
		return pkResearchResearchTopic;
	}

	public void setPkResearchResearchTopic(Long pkResearchResearchTopic) {
		this.pkResearchResearchTopic = pkResearchResearchTopic;
	}

	public Research getResearch() {
		return research;
	}

	public void setResearch(Research research) {
		this.research = research;
	}

	public ResearchTopic getResearchTopic() {
		return researchTopic;
	}

	public void setResearchTopic(ResearchTopic researchTopic) {
		this.researchTopic = researchTopic;
	}
}
