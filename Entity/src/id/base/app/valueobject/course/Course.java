package id.base.app.valueobject.course;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import id.base.app.valueobject.BaseEntity;
import id.base.app.valueobject.Lookup;

@Entity
@Table(name = "COURSE")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="courseJid", scope=Course.class)
public class Course extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8793258862185720735L;
	
	public static final String PK_COURSE 		= "pkCourse";
	public static final String CODE 			= "code";
	public static final String NAME 			= "name";
	public static final String STATUS			= "status";
	public static final String GROUP_COURSE		= "groupCourse";
	public static final String PK_GROUP_COURSE	= "groupCourse.pkGroupCourse";
	
	public static Course getInstance() {
		return new Course();
	}
	
	public static Course getInstance(Long pkCourse) {
		Course c = getInstance();
			c.setPkCourse(pkCourse);
		return c;
	}
	
	@Id
	@SequenceGenerator(name="COURSE_PK_COURSE_SEQ", sequenceName="COURSE_PK_COURSE_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="COURSE_PK_COURSE_SEQ")
	@Column(name = "PK_COURSE", unique = true ,nullable = false)
	private Long pkCourse;
		
	@ManyToOne
	@JoinColumn(name="FK_GROUP_COURSE")
	private GroupCourse groupCourse;

	@Column(name="CODE")
	private String code;
	
	@Column(name="NAME")
	private String name;
	
	@ManyToOne
	@JoinColumn(name="FK_LOOKUP_COURSE_CATEGORY")
	private Lookup categoryLookup;
	
	@Column(name="SUBTITLE")
	private String subName;
	
	@Column(name="BASIC_PICTURE_URL")
	private String basicPictureURL;
	
	@Column(name="FULL_DESCRIPTION")
	private String fullDescription;

	@Column(name="ADMISSION_REQUIREMENT")
	private String admissionRequirement;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="COURSE_TAG",
	joinColumns=@JoinColumn(name="FK_COURSE"),
	inverseJoinColumns=@JoinColumn(name="FK_TAG"))
	private List<Tag> courseTags = new ArrayList<Tag>();
	
	@Transient
	private String tags;
	
	@Transient
	private List<String> listTags;
	
	@OneToMany(mappedBy="course", orphanRemoval=true, cascade=CascadeType.DETACH)
	private List<StudentCourse> studentCourses = new ArrayList<>();

	@Column(name="STATUS")
	private Integer status;

	public Long getPkCourse() {
		return pkCourse;
	}
	public void setPkCourse(Long pkCourse) {
		this.pkCourse = pkCourse;
	}

	public GroupCourse getGroupCourse() {
		return groupCourse;
	}
	public void setGroupCourse(GroupCourse groupCourse) {
		this.groupCourse = groupCourse;
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

	public Lookup getCategoryLookup() {
		return categoryLookup;
	}
	public void setCategoryLookup(Lookup categoryLookup) {
		this.categoryLookup = categoryLookup;
	}

	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}

	public String getBasicPictureURL() {
		return basicPictureURL;
	}
	public void setBasicPictureURL(String basicPictureURL) {
		this.basicPictureURL = basicPictureURL;
	}

	public String getFullDescription() {
		return fullDescription;
	}
	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}

	public String getAdmissionRequirement() {
		return admissionRequirement;
	}
	public void setAdmissionRequirement(String admissionRequirement) {
		this.admissionRequirement = admissionRequirement;
	}

	public List<Tag> getCourseTags() {
		return courseTags;
	}
	public void setCourseTags(List<Tag> courseTags) {
		this.courseTags = courseTags;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public List<StudentCourse> getStudentCourses() {
		return studentCourses;
	}
	public void setStudentCourses(List<StudentCourse> studentCourses) {
		if(this.studentCourses != null) {
			this.studentCourses.clear();
		}
		if(studentCourses != null) {
			this.studentCourses.addAll(studentCourses);	
		}
	}
	public void addStudentCourse(StudentCourse studentCourse) {
		if(this.studentCourses == null) {
			this.studentCourses = new ArrayList<StudentCourse>();
		}
		if(studentCourse != null) {
			this.studentCourses.add(studentCourse);	
		}
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public List<String> getListTags() {
		if(getTags()!=null){
			listTags = new ArrayList<String>();
			String[] dataTags = getTags().split(",");
			for(String dataTag : dataTags){
				listTags.add(dataTag);
			}
		}
		return listTags;
	}

	public void setListTags(List<String> listTags) {
		this.listTags = listTags;
	}
	
}
