package id.base.app.valueobject.publication;

import id.base.app.ILookupConstant;
import id.base.app.encryptor.EncodeDecode;
import id.base.app.util.StringFunction;
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
@Table(name = "NEWS")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="newsJid", scope=News.class)
public class News extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = -5088650121565273985L;
	
	public static final String PK_NEWS = "pkNews";
	public static final String TITLE 	= "title";
	public static final String PERMALINK = "permalink";
	public static final String CONTENT 	= "content";
	public static final String EXCERPT 	= "excerpt";
	public static final String PUBLISH_DATE	= "publishDate";
	public static final String IMAGE_URL	= "imageURL";
	public static final String STATUS 	= "status";
	
	public static News getInstance() {
		return new News();
	}
	
	@Id
	@SequenceGenerator(name="NEWS_PK_NEWS_SEQ", sequenceName="NEWS_PK_NEWS_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="NEWS_PK_NEWS_SEQ")
	@Column(name = "PK_NEWS", unique = true ,nullable = false)
	private Long pkNews;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="PERMALINK")
	private String permalink;
	
	@Column(name="PUBLISH_DATE")
	private Date publishDate;

	@Column(name="CONTENT")
	private String content;
	
	@Column(name="EXCERPT")
	private String excerpt;
	
	@Column(name="IMAGE_URL")
	private String imageURL;
	
	@Column(name="STATUS")
	private Integer status;
	
	@Transient
	private String statusStr;
	
	@Transient
	private String shortContent;
	
	@Transient
	public String encodedPicture;

	public Long getPkNews() {
		return pkNews;
	}
	public void setPkNews(Long pkNews) {
		this.pkNews = pkNews;
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
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getExcerpt() {
		return excerpt;
	}
	public void setExcerpt(String excerpt) {
		this.excerpt = excerpt;
	}
	
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
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
	
	@Transient
	public String getShortContent() {
		if(content!=null){
			try {
				String clearContent = StringFunction.removeHTMLTags(content);
				this.setShortContent(new String(clearContent.substring(0, 280) + "..."));	
			} catch (Exception e) {}
		}
		return shortContent;
	}
	@Transient
	public void setShortContent(String shortContent) {
		this.shortContent = shortContent;
	}
	
	public String getEncodedPicture() throws Exception {
		if(getImageURL()!=null && !"".equals(getImageURL())){
			encodedPicture = EncodeDecode.getBase64FromLink(getImageURL());
		}
		return encodedPicture;
	}
	public void setEncodedPicture(String encodedPicture) {
		this.encodedPicture = encodedPicture;
	}
	
}