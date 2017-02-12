package id.base.app.valueobject.research;

import id.base.app.valueobject.BaseEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "RESEARCH")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="researchJid", scope=Research.class)
public class Research extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = -4581348296050006591L;
	
	public static final String PK_RESEARCH			= "pkResearch";
	public static final String IS_INTERNAL			= "isInternal";
	public static final String TITLE 				= "title";
	public static final String SUB_TITLE			= "subTitle";
	public static final String STATUS				= "status";
	
	public static Research getInstance() {
		return new Research();
	}
	
	@Id
	@SequenceGenerator(name="RESEARCH_PK_RESEARCH_SEQ", sequenceName="RESEARCH_PK_RESEARCH_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="RESEARCH_PK_RESEARCH_SEQ")
	@Column(name = "PK_RESEARCH", unique = true ,nullable = false)
	private Long pkResearch;
	
	@Column(name="IS_INTERNAL")
	private Boolean isInternal;
	
	@ManyToOne
	@JoinColumn(name="FK_RESEARCH_THEME")
	private ResearchTheme researchTheme;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="SUBTITLE")
	private String subtitle;
	
	@Column(name="IMAGE_URL")
	private String imageURL;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="PRINCIPLE_PERMIT_FILE")
	private String principlePermitFile;
	
	@Column(name="PRINCIPLE_PERMIT_DESCRIPTION")
	private String principlePermitDescription;
	
	@Column(name="PROCUREMENT_FILE")
	private String procurementFile;
	
	@Column(name="PROCUREMENT_DESCRIPTION")
	private String procurementDescription;
	
	@Column(name="WORK_ORDER_FILE")
	private String workOrderFile;
	
	@Column(name="WORK_ORDER_DESCRIPTION")
	private String workOrderDescription;
	
	@Column(name="VENDOR")
	private String vendor;
	
	@Column(name="PROJECT_VALUE")
	private BigDecimal projectValue;
		
	@Column(name="STATUS")
	private Integer status;
	
	@OneToMany(mappedBy="research", cascade=CascadeType.DETACH)
	private List<ResearchOfficer> researchers;
	
	@OneToMany(mappedBy="research", cascade=CascadeType.DETACH)
	private List<ResearchImplementation> implementations;
	
	@OneToMany(mappedBy="research", cascade=CascadeType.DETACH)
	private List<ResearchResult> results;

	public Long getPkResearch() {
		return pkResearch;
	}

	public void setPkResearch(Long pkResearch) {
		this.pkResearch = pkResearch;
	}

	public Boolean getIsInternal() {
		return isInternal;
	}

	public void setIsInternal(Boolean isInternal) {
		this.isInternal = isInternal;
	}

	public ResearchTheme getResearchTheme() {
		return researchTheme;
	}

	public void setResearchTheme(ResearchTheme researchTheme) {
		this.researchTheme = researchTheme;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrinciplePermitFile() {
		return principlePermitFile;
	}

	public void setPrinciplePermitFile(String principlePermitFile) {
		this.principlePermitFile = principlePermitFile;
	}

	public String getPrinciplePermitDescription() {
		return principlePermitDescription;
	}

	public void setPrinciplePermitDescription(String principlePermitDescription) {
		this.principlePermitDescription = principlePermitDescription;
	}

	public String getProcurementFile() {
		return procurementFile;
	}

	public void setProcurementFile(String procurementFile) {
		this.procurementFile = procurementFile;
	}

	public String getProcurementDescription() {
		return procurementDescription;
	}

	public void setProcurementDescription(String procurementDescription) {
		this.procurementDescription = procurementDescription;
	}

	public String getWorkOrderFile() {
		return workOrderFile;
	}

	public void setWorkOrderFile(String workOrderFile) {
		this.workOrderFile = workOrderFile;
	}

	public String getWorkOrderDescription() {
		return workOrderDescription;
	}

	public void setWorkOrderDescription(String workOrderDescription) {
		this.workOrderDescription = workOrderDescription;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public BigDecimal getProjectValue() {
		return projectValue;
	}

	public void setProjectValue(BigDecimal projectValue) {
		this.projectValue = projectValue;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<ResearchOfficer> getResearchers() {
		return researchers;
	}

	public void setResearchers(List<ResearchOfficer> researchers) {
		this.researchers = researchers;
	}

	public List<ResearchImplementation> getImplementations() {
		return implementations;
	}

	public void setImplementations(List<ResearchImplementation> implementations) {
		this.implementations = implementations;
	}

	public List<ResearchResult> getResults() {
		return results;
	}

	public void setResults(List<ResearchResult> results) {
		this.results = results;
	}
	
}