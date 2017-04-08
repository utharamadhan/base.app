package id.base.app.valueobject.report;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="VW_RESEARCH_DEVELOPMENT_REPORT")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="VWResearchDevelopmentReportJid", scope=VWResearchDevelopmentReport.class)
public class VWResearchDevelopmentReport implements Serializable {
	
	private static final long serialVersionUID = 6618790635044454782L;
	
	public static final String PK_RESEARCH			= "pkResearch";
	public static final String TITLE 				= "title";
	public static final String SUB_TITLE			= "subTitle";
	
	public static VWResearchDevelopmentReport getInstance() {
		return new VWResearchDevelopmentReport();
	}
	
	@Id
	@Column(name="PK_RESEARCH")
	private Long pkResearch;

	@Column(name="TYPE")
	private String type;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="SUBTITLE")
	private String subtitle;
	
	@Column(name="DATE_FROM")
	private Date dateFrom;
	
	@Column(name="DATE_TO")
	private Date dateTo;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="THEME")
	private String theme;
	
	@Column(name="PROJECT_OFFICER")
	private String projectOfficer;
	
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
	private String projectValue;
	
	@Column(name="IMPLEMENTATION_STATUS")
	private String implementationStatus;
	
	@Column(name="RESULT")
	private String result;

	public Long getPkResearch() {
		return pkResearch;
	}

	public void setPkResearch(Long pkResearch) {
		this.pkResearch = pkResearch;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getProjectOfficer() {
		return projectOfficer;
	}

	public void setProjectOfficer(String projectOfficer) {
		this.projectOfficer = projectOfficer;
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

	public String getProjectValue() {
		return projectValue;
	}

	public void setProjectValue(String projectValue) {
		this.projectValue = projectValue;
	}

	public String getImplementationStatus() {
		return implementationStatus;
	}

	public void setImplementationStatus(String implementationStatus) {
		this.implementationStatus = implementationStatus;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
}