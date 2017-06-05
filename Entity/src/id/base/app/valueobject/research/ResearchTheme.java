package id.base.app.valueobject.research;

import id.base.app.ILookupConstant;
import id.base.app.valueobject.BaseEntity;

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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "RESEARCH_THEME")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="researchThemeJid", scope=ResearchTheme.class)
public class ResearchTheme extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = -2252270457685333271L;
	
	public static final String PK_RESEARCH_THEME	= "pkResearchTheme";
	public static final String TITLE 				= "title";
	public static final String STATUS				= "status";
	
	public static ResearchTheme getInstance() {
		return new ResearchTheme();
	}
	
	@Id
	@SequenceGenerator(name="RESEARCH_THEME_PK_RESEARCH_THEME_SEQ", sequenceName="RESEARCH_THEME_PK_RESEARCH_THEME_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="RESEARCH_THEME_PK_RESEARCH_THEME_SEQ")
	@Column(name = "PK_RESEARCH_THEME", unique = true ,nullable = false)
	private Long pkResearchTheme;
	
	@Column(name="TITLE")
	private String title;
	
	@ManyToOne
	@JoinColumn(name="FK_RESEARCH_THEME_PARENT")
	private ResearchTheme researchThemeParent;
	
	@Column(name="STATUS")
	private Integer status;
	
	@Transient
	private String statusStr;

	public Long getPkResearchTheme() {
		return pkResearchTheme;
	}

	public void setPkResearchTheme(Long pkResearchTheme) {
		this.pkResearchTheme = pkResearchTheme;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ResearchTheme getResearchThemeParent() {
		return researchThemeParent;
	}

	public void setResearchThemeParent(ResearchTheme researchThemeParent) {
		this.researchThemeParent = researchThemeParent;
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