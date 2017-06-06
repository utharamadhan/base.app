package id.base.app.valueobject.research;

import java.io.Serializable;

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
@Table(name = "RESEARCH_KEYWORD")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="researchKeywordJid", scope=ResearchKeyword.class)
public class ResearchKeyword implements Serializable{

	private static final long serialVersionUID = 2030735913500154051L;
	
	public static final String PK_RESEARCH_KEYWORD	= "pkResearchKeyword";
	public static final String KEYWORD 				= "keyword";
	
	public static ResearchKeyword getInstance() {
		return new ResearchKeyword();
	}
	
	@Id
	@SequenceGenerator(name="RESEARCH_KEYWORD_PK_RESEARCH_KEYWORD_SEQ", sequenceName="RESEARCH_KEYWORD_PK_RESEARCH_KEYWORD_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="RESEARCH_KEYWORD_PK_RESEARCH_KEYWORD_SEQ")
	@Column(name = "PK_RESEARCH_KEYWORD", unique = true ,nullable = false)
	private Long pkResearchKeyword;
	
	@Column(name="KEYWORD")
	private String keyword;
	
	@ManyToOne
	@JoinColumn(name="FK_RESEARCH")
	private Research research;

	public Long getPkResearchKeyword() {
		return pkResearchKeyword;
	}

	public void setPkResearchKeyword(Long pkResearchKeyword) {
		this.pkResearchKeyword = pkResearchKeyword;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Research getResearch() {
		return research;
	}

	public void setResearch(Research research) {
		this.research = research;
	}	
}
