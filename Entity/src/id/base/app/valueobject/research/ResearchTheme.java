package id.base.app.valueobject.research;

import id.base.app.SystemConstant;
import id.base.app.valueobject.BaseEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "RESEARCH_THEME")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="researchThemeJid", scope=ResearchTheme.class)
public class ResearchTheme extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = -4393126578454824054L;
	
	public static final String PK_RESEARCH_THEME	= "pkResearchTheme";
	public static final String TITLE 				= "title";
	public static final String DESCRIPTION 			= "description";
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
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="STATUS")
	private Integer status;

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
	
	public String getStatusDescr() {
		return SystemConstant.ValidFlag.validFlagMap.get(status);
	}
}