package id.base.app.valueobject.aboutUs;

import id.base.app.ILookupConstant;
import id.base.app.valueobject.BaseEntity;

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
@Table(name = "ENGAGEMENT")
public class Engagement extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7058920704118955735L;
	
	public static final String PK_ENGAGEMENT = "pkEngagement";
	public static final String TITLE 	= "title";
	public static final String CONTENT 	= "content";
	public static final String STATUS 	= "status";
	
	public static Engagement getInstance() {
		return new Engagement();
	}
	
	@Id
	@SequenceGenerator(name="ENGAGEMENT_PK_ENGAGEMENT_SEQ", sequenceName="ENGAGEMENT_PK_ENGAGEMENT_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ENGAGEMENT_PK_ENGAGEMENT_SEQ")
	@Column(name = "PK_ENGAGEMENT", unique = true ,nullable = false)
	private Long pkEngagement;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="CONTENT")
	private String content;
	
	@Column(name="STATUS")
	private Integer status;
	
	@Transient
	private String statusStr;

	public Long getPkEngagement() {
		return pkEngagement;
	}
	public void setPkEngagement(Long pkEngagement) {
		this.pkEngagement = pkEngagement;
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
		return ILookupConstant.ArticleStatus.ARTICLE_STATUS_MAP.get(status);
	}
}
