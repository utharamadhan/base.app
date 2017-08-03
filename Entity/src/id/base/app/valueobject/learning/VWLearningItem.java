package id.base.app.valueobject.learning;

import id.base.app.SystemConstant;
import id.base.app.encryptor.EncodeDecode;
import id.base.app.util.DateTimeFunction;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="VW_LEARNING_ITEM")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="VWLearningItemJid", scope=VWLearningItem.class)
public class VWLearningItem implements Serializable {
	
	private static final long serialVersionUID = 6537519455353080348L;

	public static final String PK = "pk";
	public static final String CATEGORY_PERMALINK = "categoyPermalink";
	public static final String DATE_FROM = "dateFrom";
	public static final String FK_LOOKUP_METHOD = "fkLookupMethod";
	public static final String FK_LOOKUP_ORGANIZER = "fkLookupOrganizer";
	public static final String FK_LOOKUP_PAYMENT = "fkLookupPayment";
	public static final String PERIOD = "period";
	public static final String STATUS = "status";
	
	@Id
	@Column(name="PK")
	private Long pk;

	@Column(name="PK_CATEGORY")
	private Long pkCategory;
	
	@Column(name="CATEGORY_PERMALINK")
	private String categoyPermalink;
	
	@Column(name="PK_LEARNING_ITEM")
	private Long pkLearningItem;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="LEARNING_PERMALINK")
	private String learningPermalink;
	
	@Column(name="IMAGE_URL")
	private String imageURL;
	
	@Column(name="IMAGE_THUMB_URL")
	private String imageThumbURL;
	
	@Column(name="DATE_FROM")
	private Date dateFrom;
	
	@Column(name="DATE_TO")
	private Date dateTo;
	
	@Column(name="FK_LOOKUP_METHOD")
	private Long fkLookupMethod;
	
	@Column(name="FK_LOOKUP_ORGANIZER")
	private Long fkLookupOrganizer;
	
	@Column(name="FK_LOOKUP_PAYMENT")
	private Long fkLookupPayment;

	@Column(name="PERIOD")
	private String period;
	
	@Column(name="STATUS")
	private Integer status;
	
	@Transient
	private String encodedPicture;
	
	@Transient
	private String eventDate;

	public Long getPk() {
		return pk;
	}

	public void setPk(Long pk) {
		this.pk = pk;
	}

	public Long getPkCategory() {
		return pkCategory;
	}

	public void setPkCategory(Long pkCategory) {
		this.pkCategory = pkCategory;
	}

	public String getCategoyPermalink() {
		return categoyPermalink;
	}

	public void setCategoyPermalink(String categoyPermalink) {
		this.categoyPermalink = categoyPermalink;
	}

	public Long getPkLearningItem() {
		return pkLearningItem;
	}

	public void setPkLearningItem(Long pkLearningItem) {
		this.pkLearningItem = pkLearningItem;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLearningPermalink() {
		return learningPermalink;
	}

	public void setLearningPermalink(String learningPermalink) {
		this.learningPermalink = learningPermalink;
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

	public Long getFkLookupMethod() {
		return fkLookupMethod;
	}

	public void setFkLookupMethod(Long fkLookupMethod) {
		this.fkLookupMethod = fkLookupMethod;
	}

	public Long getFkLookupOrganizer() {
		return fkLookupOrganizer;
	}

	public void setFkLookupOrganizer(Long fkLookupOrganizer) {
		this.fkLookupOrganizer = fkLookupOrganizer;
	}

	public Long getFkLookupPayment() {
		return fkLookupPayment;
	}

	public void setFkLookupPayment(Long fkLookupPayment) {
		this.fkLookupPayment = fkLookupPayment;
	}
	
	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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