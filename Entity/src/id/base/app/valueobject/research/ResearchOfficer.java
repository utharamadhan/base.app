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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="RESEARCH_OFFICER")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="researchOfficerJid", scope=ResearchOfficer.class)
public class ResearchOfficer implements Serializable{

	private static final long serialVersionUID = 7920291590570883616L;

	public static final String PK_RESEARCH_OFFICER	= "pkResearchOfficer";
	public static final String FK_RESEARCH	= "research.pkResearch";
	public static final String OFFICER_NAME	= "officerName";
	public static final String OFFICER_POSITION	= "officerPosition";
	
	public static final String[] MAINTENANCE_LIST_FIELDS = {
		PK_RESEARCH_OFFICER, FK_RESEARCH, OFFICER_NAME, OFFICER_POSITION
	};
	
	public static ResearchOfficer getInstance() {
		return new ResearchOfficer();
	}
	@Id
	@SequenceGenerator(name="RESEARCH_OFFICER_PK_RESEARCH_OFFICER_SEQ", sequenceName="RESEARCH_OFFICER_PK_RESEARCH_OFFICER_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="RESEARCH_OFFICER_PK_RESEARCH_OFFICER_SEQ")
	@Column(name = "PK_RESEARCH_OFFICER", unique = true ,nullable = false, precision = 10, scale = 0)
	private Long pkResearchOfficer;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_RESEARCH", nullable=true)
	private Research research;

	@Column(name="OFFICER_NAME")
	private String officerName;
	
	@Column(name="OFFICER_POSITION")
	private String officerPosition;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_PARTY_OFFICER", nullable=true)
	private Party partyOfficer;
	
	@Transient
	private Long fkResearch;
	
	public Long getPkResearchOfficer() {
		return pkResearchOfficer;
	}

	public void setPkResearchOfficer(Long pkResearchOfficer) {
		this.pkResearchOfficer = pkResearchOfficer;
	}

	public Research getResearch() {
		return research;
	}

	public void setResearch(Research research) {
		this.research = research;
	}

	public String getOfficerName() {
		return officerName;
	}

	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}

	public String getOfficerPosition() {
		return officerPosition;
	}

	public void setOfficerPosition(String officerPosition) {
		this.officerPosition = officerPosition;
	}

	public Party getPartyOfficer() {
		return partyOfficer;
	}

	public void setPartyOfficer(Party partyOfficer) {
		this.partyOfficer = partyOfficer;
	}

	public Long getFkResearch() {
		return fkResearch;
	}

	public void setFkResearch(Long fkResearch) {
		this.fkResearch = fkResearch;
	}
}
