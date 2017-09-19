package id.base.app.valueobject.program;

import id.base.app.ILookupConstant;
import id.base.app.SystemConstant;
import id.base.app.util.DateTimeFunction;
import id.base.app.valueobject.BaseEntity;
import id.base.app.valueobject.Category;
import id.base.app.valueobject.Lookup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "PROGRAM_ITEM")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="programItemJid", scope=ProgramItem.class)
public class ProgramItem extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = -8415773929940728045L;
	
	public static final String PK_PROGRAM_ITEM = "pkProgramItem";
	public static final String CODE 			= "code";
	public static final String TITLE 			= "title";
	public static final String SUBTITLE 		= "subtitle";
	public static final String PERMALINK		= "permalink";
	public static final String DATE_FROM		= "dateFrom";
	public static final String CLOSING_DATE_REG	= "closingDateReg";	
	public static final String METHOD_LOOKUP	= "methodLookup";
	public static final String METHOD_LOOKUP_PK	= METHOD_LOOKUP+"."+Lookup.ID;
	public static final String ORGANIZER_LOOKUP	= "organizerLookup";
	public static final String ORGANIZER_LOOKUP_PK = ORGANIZER_LOOKUP+"."+Lookup.ID;
	public static final String PAYMENT_LOOKUP	= "paymentLookup";
	public static final String PAYMENT_LOOKUP_PK = PAYMENT_LOOKUP+"."+Lookup.ID;
	public static final String DESCRIPTION		= "description";
	public static final String ADMISSION_REQUIREMENT = "admissionRequirement";
	public static final String PAYMENT_DETAIL	= "paymentDetail";
	public static final String TYPE				= "type";
	public static final String IS_TEACHER_IN_PROFILE_DISPLAY = "isTeacherInProfileDisplay";
	public static final String STATUS			= "status";
	
	public static ProgramItem getInstance() {
		return new ProgramItem();
	}
	
	public static ProgramItem getInstance(Long pkProgramItem) {
		ProgramItem obj = new ProgramItem();
		obj.setPkProgramItem(pkProgramItem);
		return obj;
	}
	
	@Id
	@SequenceGenerator(name="PROGRAM_ITEM_PK_PROGRAM_ITEM_SEQ", sequenceName="PROGRAM_ITEM_PK_PROGRAM_ITEM_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PROGRAM_ITEM_PK_PROGRAM_ITEM_SEQ")
	@Column(name = "PK_PROGRAM_ITEM", unique = true ,nullable = false)
	private Long pkProgramItem;
		
	@Column(name="CODE")
	private String code;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="PERMALINK")
	private String permalink;
	
	@Column(name="IMAGE_URL")
	private String imageURL;
	
	@Column(name="IMAGE_THUMB_URL")
	private String imageThumbURL;
	
	@Column(name="IMAGE_BACKGROUND_URL")
	private String imageBackgroundURL;
	
	@Column(name="DATE_FROM")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dateFrom;
	
	@Column(name="DATE_TO")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dateTo;
	
	@OneToOne
	@JoinColumn(name="FK_LOOKUP_METHOD")
	private Lookup methodLookup;
	
	@OneToOne
	@JoinColumn(name="FK_LOOKUP_ORGANIZER")
	private Lookup organizerLookup;
	
	@OneToOne
	@JoinColumn(name="FK_LOOKUP_PAYMENT")
	private Lookup paymentLookup;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="IS_CERTIFICATION")
	private Boolean isCertification;
	
	@Column(name="ADMISSION_REQUIREMENT")
	private String admissionRequirement;
	
	@Column(name="PAYMENT_DESC")
	private String paymentDesc;
	
	@Column(name="CLOSING_DATE_REG")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date closingDateReg;
	
	@Column(name="LOCATION")
	private String location;
	
	@Column(name="PAYMENT_DETAIL")
	private String paymentDetail;
	
	@Column(name="ORGANIZER_DETAIL")
	private String organizerDetail;
	
	@Column(name="SILABUS_DESC")
	private String silabusDesc;
	
	@Column(name="CONTACT_US_DESC")
	private String contactUsDesc;
	
	@Column(name="FACILITY_DESC")
	private String facilityDesc;
	
	@OneToOne
	@JoinColumn(name="FK_LOOKUP_PROGRAM_DISPLAY")
	private Lookup programDisplayLookup;
	
	@Column(name="BROCHURE_URL1")
	private String brochureURL1;
	
	@Column(name="BROCHURE_URL2")
	private String brochureURL2;
	
	@Column(name="TYPE")
	private String type;
	
	@Column(name="BACKGROUND_IMAGE_SIZE")
	private Integer backgroundImageSize;
	
	@Column(name="IS_BACKGROUND_SLIDESHOW")
	private Boolean isBackgroundSlideshow;
	
	@Column(name="IS_TEACHER_IN_PROFILE_DISPLAY")
	private Boolean isTeacherInProfileDisplay;
	
	@Column(name="STATUS")
	private Integer status;
	
	@Transient
	private String statusStr;

	@ManyToMany
	@JoinTable(name="PROGRAM_ITEM_CATEGORY",
			joinColumns=@JoinColumn(name="FK_PROGRAM_ITEM"),
			inverseJoinColumns=@JoinColumn(name="FK_CATEGORY"))
	private List<Category> categories;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="programItem", orphanRemoval = true)
	private List<ProgramItemImage> images;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="programItem", orphanRemoval = true)
	private List<ProgramItemTeacher> teachers;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="programItem", orphanRemoval = true)
	private List<ProgramItemMenu> menus;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="programItem", orphanRemoval = true)
	private List<ProgramItemTestimonial> testimonials;
	
	@Transient
	public String eventDate;
	
	@Transient
	public Boolean eligibleRegistration;

	public Long getPkProgramItem() {
		return pkProgramItem;
	}

	public void setPkProgramItem(Long pkProgramItem) {
		this.pkProgramItem = pkProgramItem;
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

	public String getPermalink() {
		return permalink;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
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

	public String getImageBackgroundURL() {
		return imageBackgroundURL;
	}

	public void setImageBackgroundURL(String imageBackgroundURL) {
		this.imageBackgroundURL = imageBackgroundURL;
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

	public Lookup getMethodLookup() {
		return methodLookup;
	}

	public void setMethodLookup(Lookup methodLookup) {
		this.methodLookup = methodLookup;
	}

	public Lookup getOrganizerLookup() {
		return organizerLookup;
	}

	public void setOrganizerLookup(Lookup organizerLookup) {
		this.organizerLookup = organizerLookup;
	}

	public Lookup getPaymentLookup() {
		return paymentLookup;
	}

	public void setPaymentLookup(Lookup paymentLookup) {
		this.paymentLookup = paymentLookup;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsCertification() {
		return isCertification;
	}

	public void setIsCertification(Boolean isCertification) {
		this.isCertification = isCertification;
	}

	public String getAdmissionRequirement() {
		return admissionRequirement;
	}

	public void setAdmissionRequirement(String admissionRequirement) {
		this.admissionRequirement = admissionRequirement;
	}

	public String getPaymentDetail() {
		return paymentDetail;
	}

	public void setPaymentDetail(String paymentDetail) {
		this.paymentDetail = paymentDetail;
	}
	
	public String getPaymentDesc() {
		return paymentDesc;
	}

	public void setPaymentDesc(String paymentDesc) {
		this.paymentDesc = paymentDesc;
	}

	public Date getClosingDateReg() {
		return closingDateReg;
	}

	public void setClosingDateReg(Date closingDateReg) {
		this.closingDateReg = closingDateReg;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getOrganizerDetail() {
		return organizerDetail;
	}

	public void setOrganizerDetail(String organizerDetail) {
		this.organizerDetail = organizerDetail;
	}

	public String getSilabusDesc() {
		return silabusDesc;
	}

	public void setSilabusDesc(String silabusDesc) {
		this.silabusDesc = silabusDesc;
	}
	
	public String getContactUsDesc() {
		return contactUsDesc;
	}

	public void setContactUsDesc(String contactUsDesc) {
		this.contactUsDesc = contactUsDesc;
	}
	
	public String getFacilityDesc() {
		return facilityDesc;
	}

	public void setFacilityDesc(String facilityDesc) {
		this.facilityDesc = facilityDesc;
	}

	public Lookup getProgramDisplayLookup() {
		return programDisplayLookup;
	}

	public void setProgramDisplayLookup(Lookup programDisplayLookup) {
		this.programDisplayLookup = programDisplayLookup;
	}
	
	public String getBrochureURL1() {
		return brochureURL1;
	}

	public void setBrochureURL1(String brochureURL1) {
		this.brochureURL1 = brochureURL1;
	}

	public String getBrochureURL2() {
		return brochureURL2;
	}

	public void setBrochureURL2(String brochureURL2) {
		this.brochureURL2 = brochureURL2;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Integer getBackgroundImageSize() {
		return backgroundImageSize;
	}

	public void setBackgroundImageSize(Integer backgroundImageSize) {
		this.backgroundImageSize = backgroundImageSize;
	}
	
	public Boolean getIsBackgroundSlideshow() {
		return isBackgroundSlideshow;
	}

	public void setIsBackgroundSlideshow(Boolean isBackgroundSlideshow) {
		this.isBackgroundSlideshow = isBackgroundSlideshow;
	}

	public Boolean getIsTeacherInProfileDisplay() {
		return isTeacherInProfileDisplay;
	}

	public void setIsTeacherInProfileDisplay(Boolean isTeacherInProfileDisplay) {
		this.isTeacherInProfileDisplay = isTeacherInProfileDisplay;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
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
	
	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	public List<ProgramItemImage> getImages() {
		return images;
	}

	public void setImages(List<ProgramItemImage> images) {
		if(this.images == null){
			this.images = new ArrayList<ProgramItemImage>();
		}else{
			this.images.clear();	
		}
		if(null != images){
			this.images.addAll(images);
		}
	}

	public List<ProgramItemTeacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<ProgramItemTeacher> teachers) {
		if(this.teachers == null){
			this.teachers= new ArrayList<ProgramItemTeacher>();
		}else{
			this.teachers.clear();	
		}
		if(null != teachers){
			this.teachers.addAll(teachers);
		}
	}
	
	public List<ProgramItemMenu> getMenus() {
		return menus;
	}

	public void setMenus(List<ProgramItemMenu> menus) {
		if(this.menus == null){
			this.menus= new ArrayList<ProgramItemMenu>();
		}else{
			this.menus.clear();	
		}
		if(null != menus){
			this.menus.addAll(menus);
		}
	}
	
	public List<ProgramItemTestimonial> getTestimonials() {
		return testimonials;
	}

	public void setTestimonials(List<ProgramItemTestimonial> testimonials) {
		if(this.testimonials == null){
			this.testimonials= new ArrayList<ProgramItemTestimonial>();
		}else{
			this.testimonials.clear();	
		}
		if(null != testimonials){
			this.testimonials.addAll(testimonials);
		}
	}

	public String getEventDate() {
		if(dateTo==null){
			return DateTimeFunction.date2String(dateFrom, SystemConstant.SYSTEM_DATE_MASK);
		}else{
			return DateTimeFunction.date2String(dateFrom, SystemConstant.SYSTEM_DATE_MASK) + " - " + 
					DateTimeFunction.date2String(dateTo, SystemConstant.SYSTEM_DATE_MASK);
		}
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public Boolean getEligibleRegistration() {
		Boolean eligible = false;
		if(closingDateReg!=null && !(DateTimeFunction.getCurrentDateWithoutTime().compareTo(closingDateReg)>0)){
			eligible = true;
		}
		return eligible;
	}

}