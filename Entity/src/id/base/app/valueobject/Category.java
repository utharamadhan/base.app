package id.base.app.valueobject;

import id.base.app.ILookupConstant;
import id.base.app.encryptor.EncodeDecode;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "CATEGORY")
public class Category extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 8342553462195240581L;
	
	public static final String PK_CATEGORY 	= "pkCategory";
	public static final String TYPE 		= "type";
	public static final String TITLE 		= "title";
	public static final String PERMALINK	= "permalink";
	public static final String STATUS		= "status";
	
	public static Category getInstance() {
		return new Category();
	}
	
	public static Category getInstance(Long pkCourse) {
		Category c = getInstance();
		return c;
	}
	
	@Id
	@SequenceGenerator(name="CATEGORY_PK_CATEGORY_SEQ", sequenceName="CATEGORY_PK_CATEGORY_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CATEGORY_PK_CATEGORY_SEQ")
	@Column(name = "PK_CATEGORY", unique = true ,nullable = false)
	private Long pkCategory;
		
	@Column(name="TYPE")
	private String type;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="PERMALINK")
	private String permalink;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="ORDER_NO")
	private Integer orderNo;
	
	@Column(name="BASIC_PICTURE_URL")
	private String basicPictureURL;
	
	@Transient
	public String encodedPicture;
	
	@Column(name="STATUS")
	private Integer status;
	
	@Transient
	private String statusStr;
	
	public Long getPkCategory() {
		return pkCategory;
	}

	public void setPkCategory(Long pkCategory) {
		this.pkCategory = pkCategory;
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
	
	public String getPermalink() {
		return permalink;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getBasicPictureURL() {
		return basicPictureURL;
	}

	public void setBasicPictureURL(String basicPictureURL) {
		this.basicPictureURL = basicPictureURL;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getStatusStr() {
		return ILookupConstant.ArticleStatus.ARTICLE_STATUS_MAP.get(status);
	}

	public String getEncodedPicture() throws Exception {
		if(getBasicPictureURL()!=null && !"".equals(getBasicPictureURL())){
			encodedPicture = EncodeDecode.getBase64FromLink(getBasicPictureURL());
		}
		return encodedPicture;
	}

	public void setEncodedPicture(String encodedPicture) {
		this.encodedPicture = encodedPicture;
	}
		
	
}
