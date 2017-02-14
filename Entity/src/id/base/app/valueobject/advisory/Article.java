package id.base.app.valueobject.advisory;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import id.base.app.valueobject.BaseEntity;

@Entity
@Table(name = "ARTICLE")
public class Article extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2886297042567028121L;
	
	public static final String PK_ARTICLE = "pkArticle";
	public static final String NAME 	= "name";
	public static final String CONTENT 	= "content";
	public static final String STATUS 	= "status";
	
	public static Article getInstance() {
		return new Article();
	}
	
	@Id
	@SequenceGenerator(name="ARTICLE_PK_ARTICLE_SEQ", sequenceName="ARTICLE_PK_ARTICLE_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ARTICLE_PK_ARTICLE_SEQ")
	@Column(name = "PK_ARTICLE", unique = true ,nullable = false)
	private Long pkArticle;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="CONTENT")
	private String content;
	
	@Column(name="BASIC_PICTURE_URL")
	private String basicPictureURL;
	
	@ManyToOne
	@JoinColumn(name="FK_CATEGORY")
	private Category category;
	
	@Column(name="STATUS")
	private Integer status;
	
	public Long getPkArticle() {
		return pkArticle;
	}
	public void setPkArticle(Long pkArticle) {
		this.pkArticle = pkArticle;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getBasicPictureURL() {
		return basicPictureURL;
	}
	public void setBasicPictureURL(String basicPictureURL) {
		this.basicPictureURL = basicPictureURL;
	}
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
