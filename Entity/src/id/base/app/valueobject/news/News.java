package id.base.app.valueobject.news;

import id.base.app.valueobject.BaseEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "NEWS")
public class News extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5088650121565273985L;
	
	public static final String PK_NEWS = "pkNews";
	public static final String CODE 	= "code";
	public static final String NAME 	= "name";
	public static final String CONTENT 	= "content";
	public static final String STATUS 	= "status";
	
	public static News getInstance() {
		return new News();
	}
	
	@Id
	@SequenceGenerator(name="NEWS_PK_NEWS_SEQ", sequenceName="NEWS_PK_NEWS_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="NEWS_PK_NEWS_SEQ")
	@Column(name = "PK_NEWS", unique = true ,nullable = false)
	private Long pkNews;
	
	@Column(name="CODE")
	private String code;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="CONTENT")
	private String content;
	
	@Column(name="STATUS")
	private Integer status;

	public Long getPkNews() {
		return pkNews;
	}
	public void setPkNews(Long pkNews) {
		this.pkNews = pkNews;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
