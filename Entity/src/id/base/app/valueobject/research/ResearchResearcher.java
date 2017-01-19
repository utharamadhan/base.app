package id.base.app.valueobject.research;

import id.base.app.valueobject.party.Party;

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
@Table(name="RESEARCH_RESEARCHER")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="researchResearcherJid", scope=ResearchResearcher.class)
public class ResearchResearcher implements Serializable{

	private static final long serialVersionUID = -5050327372862215987L;
	
	@Id
	@SequenceGenerator(name="RESEARCH_RESEARCHER_PK_RESEARCH_RESEARCHER_SEQ", sequenceName="RESEARCH_RESEARCHER_PK_RESEARCH_RESEARCHER_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="RESEARCH_RESEARCHER_PK_RESEARCH_RESEARCHER_SEQ")
	@Column(name = "PK_RESEARCH_RESEARCHER", unique = true ,nullable = false, precision = 10, scale = 0)
	private Long pkResearchResearcher;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_RESEARCH", nullable=true)
	private Research research;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_PARTY", nullable=true)
	private Party party;

	public Long getPkResearchResearcher() {
		return pkResearchResearcher;
	}

	public void setPkResearchResearcher(Long pkResearchResearcher) {
		this.pkResearchResearcher = pkResearchResearcher;
	}

	public Research getResearch() {
		return research;
	}

	public void setResearch(Research research) {
		this.research = research;
	}

	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}
}
