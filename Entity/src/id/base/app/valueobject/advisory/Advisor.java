package id.base.app.valueobject.advisory;

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
@Table(name = "ADVISOR")
public class Advisor extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2886297042567028121L;
	
	public static final String PK_ADVISOR = "pkAdvisor";
	public static final String NAME 	= "name";
	public static final String PROFILE_DESCRIPTION 	= "profileDescription";
	public static final String STATUS 	= "status";
	
	public static Advisor getInstance() {
		return new Advisor();
	}
	
	@Id
	@SequenceGenerator(name="ADVISOR_PK_ADVISOR_SEQ", sequenceName="ADVISOR_PK_ADVISOR_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ADVISOR_PK_ADVISOR_SEQ")
	@Column(name = "PK_ADVISOR", unique = true ,nullable = false)
	private Long pkAdvisor;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="PROFILE_DESCRIPTION")
	private String profileDescription;
	
	@Column(name="BASIC_PICTURE_URL")
	private String basicPictureURL;
	
	@Column(name="STATUS")
	private Integer status;

	public Long getPkAdvisor() {
		return pkAdvisor;
	}
	public void setPkAdvisor(Long pkAdvisor) {
		this.pkAdvisor = pkAdvisor;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getProfileDescription() {
		return profileDescription;
	}
	public void setProfileDescription(String profileDescription) {
		this.profileDescription = profileDescription;
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
	
}
