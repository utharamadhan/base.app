package id.base.app.valueobject.course;

import id.base.app.valueobject.BaseEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "GROUP_COURSE")
public class GroupCourse extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2512890745160322636L;
	
	public static final String PK_GROUP_COURSE 	= "pkGroupCourse";
	public static final String CODE 			= "code";
	public static final String NAME 			= "name";
	public static final String BASIC_PICTURE_URL 	= "basicPictureURL";
	public static final String SHORT_DESCRIPTION	= "shortDesription";
	public static final String FULL_DESCRIPTION	= "fullDesription";
	public static final String STATUS			= "status";
	
	public static GroupCourse getInstance() {
		return new GroupCourse();
	}
	
	@Id
	@SequenceGenerator(name="GROUP_COURSE_PK_GROUP_COURSE_SEQ", sequenceName="GROUP_COURSE_PK_GROUP_COURSE_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="GROUP_COURSE_PK_GROUP_COURSE_SEQ")
	@Column(name = "PK_GROUP_COURSE", unique = true ,nullable = false)
	private Long pkGroupCourse;
	
	@Column(name="CODE")
	private String code;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="BASIC_PICTURE_URL")
	private String basicPictureURL;
	
	@Column(name="SHORT_DESCRIPTION")
	private String shortDescription;
	
	@Column(name="FULL_DESCRIPTION")
	private String fullDescription;

	@Column(name="STATUS")
	private Integer status;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, mappedBy="groupCourse")
	private List<GCBasicInformation> gcBasicInformationList = new ArrayList<>(); 
	
	public Long getPkGroupCourse() {
		return pkGroupCourse;
	}
	public void setPkGroupCourse(Long pkGroupCourse) {
		this.pkGroupCourse = pkGroupCourse;
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
	
	public String getBasicPictureURL() {
		return basicPictureURL;
	}
	
	public void setBasicPictureURL(String basicPictureURL) {
		this.basicPictureURL = basicPictureURL;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	
	public String getFullDescription() {
		return fullDescription;
	}
	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public List<GCBasicInformation> getGcBasicInformationList() {
		return gcBasicInformationList;
	}
	public void setGcBasicInformationList(List<GCBasicInformation> gcBasicInformationList) {
		if(this.gcBasicInformationList == null) {
			this.gcBasicInformationList = new ArrayList<>();
		} else {
			this.gcBasicInformationList.clear();
		}
		if(null != gcBasicInformationList) {			
			this.gcBasicInformationList.addAll(gcBasicInformationList);
		}
	}

}
