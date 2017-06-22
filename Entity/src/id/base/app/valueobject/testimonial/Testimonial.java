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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "TESTIMONIAL")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="testimonialJid", scope=Testimonial.class)
public class Testimonial extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -1244254539091598602L;
	
	public static final String PK_TESTIMONIAL = "pkTestimonial";
	public static final String NAME = "name";
	public static final String TITLE = "title";
	public static final String PHOTO_URL = "photoURL";
	public static final String DESCRIPTION = "description";
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
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="PHOTO_URL")
	private String photoURL;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="TESTIMONIAL_DATE")
	private Date testimonialDate;
	
	@Column(name="STATUS")
	private Integer status;
	
	@Transient
	private String statusStr;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPhotoURL() {
		return photoURL;
	}

	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}
	
	public Date getTestimonialDate() {
		return testimonialDate;
	}

	public void setTestimonialDate(Date testimonialDate) {
		this.testimonialDate = testimonialDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
	
}