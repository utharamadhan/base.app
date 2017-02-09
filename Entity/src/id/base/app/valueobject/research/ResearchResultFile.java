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
@Table(name="RESEARCH_RESULT_FILE")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="researchResultFileJid", scope=ResearchResultFile.class)
public class ResearchResultFile implements Serializable{
	
	private static final long serialVersionUID = -3366592272700365481L;

	@Id
	@SequenceGenerator(name="RESEARCH_RESULT_FILE_PK_RESEARCH_RESULT_FILE_SEQ", sequenceName="RESEARCH_RESULT_FILE_PK_RESEARCH_RESULT_FILE_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="RESEARCH_RESULT_FILE_PK_RESEARCH_RESULT_FILE_SEQ")
	@Column(name = "PK_RESEARCH_RESULT_FILE", unique = true ,nullable = false, precision = 10, scale = 0)
	private Long pkResearchResultFile;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_RESEARCH", nullable=true)
	private Research research;

	@Column(name="RESULT_FILE")
	private String resultFile;

	public Research getResearch() {
		return research;
	}

	public void setResearch(Research research) {
		this.research = research;
	}

	public String getResultFile() {
		return resultFile;
	}

	public void setResultFile(String resultFile) {
		this.resultFile = resultFile;
	}
}