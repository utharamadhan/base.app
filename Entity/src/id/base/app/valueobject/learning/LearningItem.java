package id.base.app.valueobject.learning;

import id.base.app.ILookupConstant;
import id.base.app.SystemConstant;
import id.base.app.encryptor.EncodeDecode;
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
@Table(name = "LEARNING_ITEM")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="courseJid", scope=LearningItem.class)
public class LearningItem extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = -8415773929940728045L;
	
	public static final String PK_LEARNING_ITEM = "pkLearningItem";
	public static final String CODE 			= "code";
	public static final String TITLE 			= "title";
	public static final String SUBTITLE 		= "subtitle";
	public static final String PERMALINK		= "permalink";
	public static final String DATE_FROM		= "dateFrom";
	public static final String METHOD_LOOKUP	= "methodLookup";
	public static final String METHOD_LOOKUP_PK	= METHOD_LOOKUP+"."+Lookup.ID;
	public static final String ORGANIZER_LOOKUP	= "organizerLookup";
	public static final String ORGANIZER_LOOKUP_PK = ORGANIZER_LOOKUP+"."+Lookup.ID;
	public static final String PAYMENT_LOOKUP	= "paymentLookup";
	public static final String PAYMENT_LOOKUP_PK = PAYMENT_LOOKUP+"."+Lookup.ID;
	public static final String DESCRIPTION		= "description";
	public static final String ADMISSION_REQUIREMENT = "admissionRequirement";
	public static final String PAYMENT_DETAIL	= "paymentDetail";
	public static final String STATUS			= "status";
	
	public static LearningItem getInstance() {
		return new LearningItem();
	}
	
	public static LearningItem getInstance(Long pkLearningItem) {
		LearningItem obj = new LearningItem();
		obj.setPkLearningItem(pkLearningItem);
		return obj;
	}
	
	@Id
	@SequenceGenerator(name="LEARNING_ITEM_PK_LEARNING_ITEM_SEQ", sequenceName="LEARNING_ITEM_PK_LEARNING_ITEM_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="LEARNING_ITEM_PK_LEARNING_ITEM_SEQ")
	@Column(name = "PK_LEARNING_ITEM", unique = true ,nullable = false)
	private Long pkLearningItem;
		
	@Column(name="CODE")
	private String code;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="PERMALINK")
	private String permalink;
	
	@Column(name="SUBTITLE")
	private String subtitle;
	
	@Column(name="IMAGE_URL")
	private String imageURL;
	
	@Column(name="IMAGE_THUMB_URL")
	private String imageThumbURL;
	
	@Column(name="IMAGE_BACKGROUND_URL")
	private String imageBackgroundURL;
	
	@Column(name="DATE_FROM")
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date dateFrom;
	
	@Column(name="DATE_TO")
	@DateTimeFormat(pattern = "MM/dd/yyyy")
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
	
	@Column(name="PAYMENT_DETAIL")
	private String paymentDetail;
	
	@Column(name="STATUS")
	private Integer status;
	
	@Transient
	private String statusStr;

	@ManyToMany
	@JoinTable(name="LEARNING_ITEM_CATEGORY",
			joinColumns=@JoinColumn(name="FK_LEARNING_ITEM"),
			inverseJoinColumns=@JoinColumn(name="FK_CATEGORY"))
	private List<Category> categories;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="learningItem", orphanRemoval = true)
	private List<LearningItemImage> images;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="learningItem", orphanRemoval = true)
	private List<LearningItemTeacher> teachers;
	
	@Transient
	public String encodedImage;
	
	@Transient
	public String encodedBackgroundImage;
	
	@Transient
	public String eventDate;

	public Long getPkLearningItem() {
		return pkLearningItem;
	}

	public void setPkLearningItem(Long pkLearningItem) {
		this.pkLearningItem = pkLearningItem;
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
	
	public List<LearningItemImage> getImages() {
		return images;
	}

	public void setImages(List<LearningItemImage> images) {
		if(this.images == null){
			this.images = new ArrayList<LearningItemImage>();
		}else{
			this.images.clear();	
		}
		if(null != images){
			this.images.addAll(images);
		}
	}

	public List<LearningItemTeacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<LearningItemTeacher> teachers) {
		if(this.teachers == null){
			this.teachers= new ArrayList<LearningItemTeacher>();
		}else{
			this.teachers.clear();	
		}
		if(null != teachers){
			this.teachers.addAll(teachers);
		}
	}

	public String getEncodedImage() throws Exception {
		if(getImageThumbURL()!=null && !"".equals(getImageThumbURL())){
			encodedImage = EncodeDecode.getBase64FromLink(getImageThumbURL());
		}else if(getImageURL()!=null && !"".equals(getImageURL())){
			encodedImage = EncodeDecode.getBase64FromLink(getImageURL());
		}
		return encodedImage;
	}

	public String getEncodedBackgroundImage() throws Exception {
		if(getImageBackgroundURL()!=null && !"".equals(getImageBackgroundURL())){
			encodedBackgroundImage = EncodeDecode.getBase64FromLink(getImageBackgroundURL());
		}
		return encodedBackgroundImage;
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
	

}