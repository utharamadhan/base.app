package id.base.app.valueobject.testimonial;

import id.base.app.ILookupConstant;
import id.base.app.valueobject.BaseEntity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "TESTIMONIAL")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="testimonialJid", scope=Testimonial.class)
public class Testimonial extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -1244254539091598602L;
	
	public static final String PK_TESTIMONIAL = "pkTestimonial";
	public static final String NAME = "name";
	public static final String PERMALINK = "permalink";
	public static final String JOB_TITLE = "jobTitle";
	public static final String PHOTO_URL = "photoURL";
	public static final String PHOTO_THUMB_URL	= "photoThumbURL";
	public static final String CONTENT = "content";
	public static final String TESTIMONIAL_DATE = "testimonialDate";
	public static final String STATUS = "status";
	
	public static Testimonial getInstance() {
		return new Testimonial();
	}
	
	@Id
	@SequenceGenerator(name="TESTIMONIAL_PK_TESTIMONIAL_SEQ", sequenceName="TESTIMONIAL_PK_TESTIMONIAL_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TESTIMONIAL_PK_TESTIMONIAL_SEQ")
	@Column(name = "PK_TESTIMONIAL", unique = true ,nullable = false)
	private Long pkTestimonial;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="PERMALINK")
	private String permalink;
	
	@Column(name="JOB_TITLE")
	private String jobTitle;
	
	@Column(name="PHOTO_URL")
	private String photoURL;
	
	@Column(name="PHOTO_THUMB_URL")
	private String photoThumbURL;
	
	@Column(name="CONTENT")
	private String content;
	
	@Column(name="TESTIMONIAL_DATE")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date testimonialDate;
	
	@Column(name="STATUS")
	private Integer status;
	
	@Transient
	private String statusStr;
	
	@Transient
	public String encodedPicture;

	public Long getPkTestimonial() {
		return pkTestimonial;
	}

	public void setPkTestimonial(Long pkTestimonial) {
		this.pkTestimonial = pkTestimonial;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPermalink() {
		return permalink;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getPhotoURL() {
		return photoURL;
	}

	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}
	
	public String getPhotoThumbURL() {
		return photoThumbURL;
	}

	public void setPhotoThumbURL(String photoThumbURL) {
		this.photoThumbURL = photoThumbURL;
	}

	public Date getTestimonialDate() {
		return testimonialDate;
	}

	public void setTestimonialDate(Date testimonialDate) {
		this.testimonialDate = testimonialDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
	
	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}
	
	public String getEncodedPicture() throws Exception {
		if(getPhotoThumbURL()!=null && !"".equals(getPhotoThumbURL())){
			/*encodedPicture = EncodeDecode.getBase64FromLink(getPhotoThumbURL());*/
			encodedPicture = getPhotoThumbURL();
		}else if(getPhotoURL()!=null && !"".equals(getPhotoURL())){
			/*encodedPicture = EncodeDecode.getBase64FromLink(getPhotoURL());*/
			encodedPicture = getPhotoURL();
		}
		return encodedPicture;
	}
	public void setEncodedPicture(String encodedPicture) {
		this.encodedPicture = encodedPicture;
	}
	
}