package id.base.app.valueobject.advisory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

import id.base.app.valueobject.AppUser;
import id.base.app.valueobject.BaseEntity;

@Entity
@Table(name = "ARTICLE")
public class Article extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2886297042567028121L;
	
	public static final String PK_ARTICLE = "pkArticle";
	public static final String NAME 		= "name";
	public static final String CONTENT 		= "content";
	public static final String ADVISOR 		= "advisor";
	public static final String ADVISOR_PK	= "advisor.pkAppUser";
	public static final String CATEGORY		= "category";
	public static final String CATEGORY_PK	= "category.pkCategory";
	public static final String STATUS 		= "status";
	
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
	
	@ManyToMany()
	@JoinTable(name="ARTICLE_ADVISOR",
	joinColumns=@JoinColumn(name="FK_ARTICLE"),
	inverseJoinColumns=@JoinColumn(name="FK_APP_USER"))
	private List<AppUser> advisor = new ArrayList<AppUser>();
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss")
	@Column(name="ARTICLE_TIME", nullable=true, insertable=true, updatable=false)
	protected Date articleTime;
	
	@Transient
	private String createdAdvisor;
	
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
	public List<AppUser> getAdvisor() {
		return advisor;
	}
	public void setAdvisor(List<AppUser> advisor) {
		this.advisor = advisor;
	}
	public Date getArticleTime() {
		return articleTime;
	}
	public void setArticleTime(Date articleTime) {
		this.articleTime = articleTime;
	}
	public String getCreatedAdvisor() {
		if(createdAdvisor==null){
			createdAdvisor = "";
		}
		if(getAdvisor()!=null){
			int idx = 1; 
			for(AppUser dataAdvisor : getAdvisor()){
				if(dataAdvisor.getParty()!=null && dataAdvisor.getParty().getName()!=null){
					if(idx<getAdvisor().size()){
						createdAdvisor += dataAdvisor.getParty().getName() + ",";
					}else{
						createdAdvisor += dataAdvisor.getParty().getName();
					}
				}
				idx++;
			}
		}
		return createdAdvisor;
	}
	public void setCreatedAdvisor(String createdAdvisor) {
		this.createdAdvisor = createdAdvisor;
	}
	
}
