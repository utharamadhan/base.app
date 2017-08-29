package id.base.app.valueobject.research;

import id.base.app.ILookupConstant;
import id.base.app.encryptor.EncodeDecode;
import id.base.app.valueobject.BaseEntity;
import id.base.app.valueobject.Category;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	public static final String SUBTITLE				= "subtitle";
	public static final String YEAR_FROM			= "yearFrom";
	public static final String STATUS				= "status";
	public static final String IS_ITEM				= "isItem";
	public static final String PERMALINK			= "permalink";
	
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
	
	@ManyToMany
	@JoinTable(name="RESEARCH_CATEGORY",
			joinColumns=@JoinColumn(name="FK_RESEARCH"),
			inverseJoinColumns=@JoinColumn(name="FK_CATEGORY"))
	private List<Category> categories;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="SUBTITLE")
	private String subtitle;
	
	@Column(name="YEAR_FROM")
	private Integer yearFrom;
	
	@Column(name="YEAR_TO")
	private Integer yearTo;
	
	@Column(name="IMAGE_URL")
	private String imageURL;
	
	@Column(name="IMAGE_THUMB_URL")
	private String imageThumbURL;
	
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
	
	@Column(name = "IS_ITEM")
	private Boolean isItem;
	
	@Transient
	private String statusStr;
	
	@Column(name="PERMALINK")
	private String permalink;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="research")
	private List<ResearchOfficer> officers;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="research")
	private List<ResearchImplementation> implementations;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="research")
	private List<ResearchResult> results;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="research")
	private List<ResearchKeyword> keywords;
	
	@Transient
	private Integer fromTab;
	
	@Transient
	public String encodedPicture;
	
	@Transient
	private String year;

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
	
	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
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
	
	public Integer getYearFrom() {
		return yearFrom;
	}

	public void setYearFrom(Integer yearFrom) {
		this.yearFrom = yearFrom;
	}

	public Integer getYearTo() {
		return yearTo;
	}

	public void setYearTo(Integer yearTo) {
		this.yearTo = yearTo;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
	public String getImageThumbURL() {
		return imageThumbURL;
	}

	public void setImageThumbURL(String imageThumbURL) {
		this.imageThumbURL = imageThumbURL;
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
	
	public String getStatusStr() {
		return ILookupConstant.Status.STATUS_MAP.get(status);
	}

	public String getPermalink() {
		return permalink;
	}
	
	public Boolean getIsItem() {
		return isItem;
	}

	public void setIsItem(Boolean isItem) {
		this.isItem = isItem;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	public List<ResearchOfficer> getOfficers() {
		return officers;
	}

	public void setOfficers(List<ResearchOfficer> officers) {
		this.officers = officers;
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

	public List<ResearchKeyword> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<ResearchKeyword> keywords) {
		this.keywords = keywords;
	}

	public Integer getFromTab() {
		return fromTab;
	}

	public void setFromTab(Integer fromTab) {
		this.fromTab = fromTab;
	}
	
	public String getEncodedPicture() throws Exception {
		if(getImageThumbURL()!=null && !"".equals(getImageThumbURL())){
			encodedPicture = EncodeDecode.getBase64FromLink(getImageThumbURL());
		}else if(getImageURL()!=null && !"".equals(getImageURL())){
			encodedPicture = EncodeDecode.getBase64FromLink(getImageURL());
		}
		return encodedPicture;
	}
	public void setEncodedPicture(String encodedPicture) {
		this.encodedPicture = encodedPicture;
	}

	public String getYear() {
		if(getYearFrom()==null && getYearTo()==null){
			return "";
		}else{
			if(getYearFrom()==null){
				return getYearTo()+"";
			}else{
				String year = getYearFrom()+"";
				if(getYearTo()!=null){
					year += " - "+getYearTo();	
				}
				return year;
			}
		}
	}
	
}