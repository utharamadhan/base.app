package id.base.app.valueobject.event;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import id.base.app.ILookupConstant;
import id.base.app.encryptor.EncodeDecode;
import id.base.app.valueobject.BaseEntity;
import id.base.app.valueobject.Lookup;

@Entity
@Table(name = "EVENT")
public class Event extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2512890745160322636L;
	
	public static final String PK_EVENT = "pkEvent";
	public static final String TITLE	 	= "title";
	public static final String DESCRIPTION 	= "description";
	public static final String STATUS 	= "status";
	public static final String EVENT_DATE = "eventDate";
	
	public static Event getInstance() {
		return new Event();
	}
	
	@Id
	@SequenceGenerator(name="EVENT_PK_EVENT_SEQ", sequenceName="EVENT_PK_EVENT_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="EVENT_PK_EVENT_SEQ")
	@Column(name = "PK_EVENT", unique = true ,nullable = false)
	private Long pkEvent;
	
	@JsonProperty(value="title")
	@Column(name="TITLE")
	private String title;
	
	@OneToOne
	@JoinColumn(name="FK_LOOKUP_EVENT_STATUS")
	private Lookup eventStatusLookup;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@JsonProperty(value="start")
	@Column(name="EVENT_DATE")
	private Date eventDate;
	
	@Column(name="COVER_IMAGE_URL")
	private String coverImageURL;
	
	@Column(name="EXCERPT")
	private String excerpt;
	
	@Column(name="STATUS")
	private Integer status;
	
	@Transient
	private String statusStr;
	
	@Transient
	public String encodedPicture;

	public Long getPkEvent() {
		return pkEvent;
	}
	public void setPkEvent(Long pkEvent) {
		this.pkEvent = pkEvent;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public Lookup getEventStatusLookup() {
		return eventStatusLookup;
	}
	public void setEventStatusLookup(Lookup eventStatusLookup) {
		this.eventStatusLookup = eventStatusLookup;
	}
	
	public String getCoverImageURL() {
		return coverImageURL;
	}
	public void setCoverImageURL(String coverImageURL) {
		this.coverImageURL = coverImageURL;
	}
	
	public String getExcerpt() {
		return excerpt;
	}
	public void setExcerpt(String excerpt) {
		this.excerpt = excerpt;
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
	
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public String getStatusStr() {
		return ILookupConstant.ArticleStatus.ARTICLE_STATUS_MAP.get(status);
	}
	public String getEncodedPicture() throws Exception {
		if(getCoverImageURL()!=null && !"".equals(getCoverImageURL())){
			encodedPicture = EncodeDecode.getBase64FromLink(getCoverImageURL());
		}
		return encodedPicture;
	}
	public void setEncodedPicture(String encodedPicture) {
		this.encodedPicture = encodedPicture;
	}
}
