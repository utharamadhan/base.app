package id.base.app.valueobject.publication;

import id.base.app.valueobject.BaseEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "RESEARCH_REPORT")
public class ResearchReport extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7698703933226904281L;
	
	private ResearchReport(){}
	
	public static final String PK_RESEARCH_REPORT = "pkResearchReport";
	public static final String CODE = "code";
	public static final String TITLE = "title";
	public static final String DESCRIPTION = "description";
	public static final String LINK = "link";
	public static final String STATUS = "status";
	
	public static ResearchReport getInstance() {
		return new ResearchReport();
	}
	
	@Id
	@SequenceGenerator(name="RESEARCH_REPORT_PK_RESEARCH_REPORT_SEQ", sequenceName="RESEARCH_REPORT_PK_RESEARCH_REPORT_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="RESEARCH_REPORT_PK_RESEARCH_REPORT_SEQ")
	@Column(name = "PK_RESEARCH_REPORT", unique = true ,nullable = false)
	private Long pkResearchReport;
	
	@Column(name="CODE")
	private String code;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="LINK")
	private String link;
	
	@Column(name="STATUS")
	private Integer status;

	public Long getPkResearchReport() {
		return pkResearchReport;
	}
	public void setPkResearchReport(Long pkResearchReport) {
		this.pkResearchReport = pkResearchReport;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
