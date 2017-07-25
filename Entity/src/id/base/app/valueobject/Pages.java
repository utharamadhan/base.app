package id.base.app.valueobject;

import id.base.app.ILookupConstant;
import id.base.app.SystemConstant;
import id.base.app.encryptor.EncodeDecode;

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

@Entity
@Table(name = "PAGES")
public class Pages extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8529693428251387150L;
	
	public static final String PK_PAGES = "pkPages";
	public static final String TITLE 	= "title";
	public static final String CONTENT 	= "content";
	public static final String ORDER_NO	= "orderNo";
	public static final String STATUS 	= "status";
	public static final String TYPE 	= "type";
	public static final String PERMALINK = "permalink";
	
	public static Pages getInstance() {
		return new Pages();
	}
	
	@Id
	@SequenceGenerator(name="PAGES_PK_PAGES_SEQ", sequenceName="PAGES_PK_PAGES_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PAGES_PK_PAGES_SEQ")
	@Column(name = "PK_PAGES", unique = true ,nullable = false)
	private Long pkPages;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="CONTENT")
	private String content;
	
	@Column(name="STATUS")
	private Integer status;
	
	@Transient
	private String statusStr;
	
	@Column(name="PUBLISH_DATE")
	private Date publishDate;
	
	@Column(name="IMAGE_URL")
	private String imageURL;
	
	@Column(name="IMAGE_THUMB_URL")
	private String imageThumbURL;
	
	@Column(name="PERMALINK")
	private String permalink;
	
	@Column(name="ORDER_NO")
	private Integer orderNo;
	
	@Column(name="TYPE")
	private String type;
	
	@Transient
	private String typeStr;
	
	@Transient
	public String encodedPicture;

	public Long getPkPages() {
		return pkPages;
	}

	public void setPkPages(Long pkPages) {
		this.pkPages = pkPages;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
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

	public String getPermalink() {
		return permalink;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeStr() {
		return SystemConstant.PagesType.PAGES_MAP.get(type);
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
}