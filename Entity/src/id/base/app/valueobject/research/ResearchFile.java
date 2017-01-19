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
@Table(name="RESEARCH_FILE")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="researchFileJid", scope=ResearchFile.class)
public class ResearchFile implements Serializable{

	private static final long serialVersionUID = 1903665607520542837L;
	
	@Id
	@SequenceGenerator(name="RESEARCH_FILE_PK_RESEARCH_FILE_SEQ", sequenceName="RESEARCH_FILE_PK_RESEARCH_FILE_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="RESEARCH_FILE_PK_RESEARCH_FILE_SEQ")
	@Column(name = "PK_RESEARCH_FILE", unique = true ,nullable = false, precision = 10, scale = 0)
	private Long pkResearchFile;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_RESEARCH", nullable=true)
	private Research research;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="FILE")
	private String file;
	
	@Column(name="UPLOAD_DATE")
	private Date uploadDate;
	
	@Column(name="STATUS")
	private Integer status;

	public Long getPkResearchFile() {
		return pkResearchFile;
	}

	public void setPkResearchFile(Long pkResearchFile) {
		this.pkResearchFile = pkResearchFile;
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

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
