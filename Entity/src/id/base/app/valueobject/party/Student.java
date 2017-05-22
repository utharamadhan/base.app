package id.base.app.valueobject.party;

import id.base.app.valueobject.BaseEntity;
import id.base.app.valueobject.Lookup;
import id.base.app.valueobject.course.StudentCourse;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="STUDENT")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="studentJid", scope=Student.class)
public class Student extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 727318892302903192L;
	
	public static final String PK_STUDENT 				= "pkStudent";
	public static final String NAME						= "name";
	public static final String BIRTH_DATE				= "birthDate";
	public static final String BIRTH_PLACE				= "birthPlace";
	public static final String PHONE_NUMBER				= "phoneNumber";
	public static final String EMAIL 					= "email";
	public static final String ADDRESS					= "address";
	public static final String STUDENT_STATUS_LOOKUP	= "studentStatusLookup";
	public static final String STUDENT_STATUS_LOOKUP_PK	= "studentStatusLookup.pkLookup";
	public static final String CURRENT_LEARNING			= "currentLearning";
	public static final String COMPANY					= "company";
	public static final String COMPANY_POSITION			= "companyPosition";
	public static final String COMPANY_CITY				= "companyCity";
	public static final String COMPANY_DESCRIPTION		= "companyDescription";
	public static final String SCHOOL					= "school";
	public static final String SCHOOL_DATES_ATTENDED	= "schoolDatesAttended";
	public static final String SCHOOL_DEGREE			= "schoolDegree";
	public static final String SCHOOL_FIELD_OF_STUDY	= "schoolFieldOfStudy";
	public static final String SCHOOL_GRADE				= "schoolGrade";
	public static final String SCHOOL_ACTIVITIES_SOCIETIES = "schoolActivitiesSocieties";
	public static final String SCHOOL_DESCRIPTION		= "schoolDescription";
	public static final String STATUS					= "status";
	public static final String PK_COURSE				= "studentCourses.course.pkCourse";
	public static final String PK_STUDENT_COURSE		= "studentCourses.pkStudentCourse";
	
	public static Student getInstance() {
		return new Student();
	}
	
	public static Student getInstance(Long pkStudent) {
		Student s = getInstance();
			s.setPkStudent(pkStudent);
		return s;
	}
	
	@Id
	@SequenceGenerator(name="STUDENT_PK_STUDENT_SEQ", sequenceName="STUDENT_PK_STUDENT_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="STUDENT_PK_STUDENT_SEQ")
	@Column(name = "PK_STUDENT", unique = true ,nullable = false)
	private Long pkStudent;

	@Column(name="NAME")
	private String name;
	
	@Column(name="BIRTH_DATE")
	private Date birthDate;
	
	@Column(name="BIRTH_PLACE")
	private String birthPlace;
	
	@ManyToOne
	@JoinColumn(name="FK_LOOKUP_GENDER")
	private Lookup genderLookup;
	
	@Column(name="PHONE_NUMBER")
	private String phoneNumber;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="ADDRESS")
	private String address;
	
	@ManyToOne
	@JoinColumn(name="FK_LOOKUP_STUDENT_STATUS")
	private Lookup studentStatusLookup;
	
	@Column(name="COMPANY")
	private String company;
	
	@Column(name="COM_POSITION")
	private String companyPosition;
	
	@Column(name="COM_CITY")
	private String companyCity;
	
	@Column(name="COM_DESCRIPTION")
	private String companyDescription;
	
	@Column(name="SCHOOL")
	private String school;
	
	@Column(name="SC_DATES_ATTENDED")
	private Date schoolDatesAttended;
	
	@Column(name="SC_DEGREE")
	private String schoolDegree;
	
	@Column(name="SC_FIELD_OF_STUDY")
	private String schoolFieldOfStudy;
	
	@Column(name="SC_GRADE")
	private String schoolGrade;
	
	@Column(name="SC_ACTIVITIES_SOCIETIES")
	private String schoolActivitiesSocieties;
	
	@Column(name="SC_DESCRIPTION")
	private String schoolDescription;
	
	@Column(name="STATUS")
	private Integer status;
	
	@OneToMany(mappedBy="student", cascade=CascadeType.DETACH)
	private List<StudentCourse> studentCourses;
	
	@Transient
	private String validationType;

	public Long getPkStudent() {
		return pkStudent;
	}
	public void setPkStudent(Long pkStudent) {
		this.pkStudent = pkStudent;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getBirthPlace() {
		return birthPlace;
	}
	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public Lookup getGenderLookup() {
		return genderLookup;
	}
	public void setGenderLookup(Lookup genderLookup) {
		this.genderLookup = genderLookup;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public Lookup getStudentStatusLookup() {
		return studentStatusLookup;
	}
	public void setStudentStatusLookup(Lookup studentStatusLookup) {
		this.studentStatusLookup = studentStatusLookup;
	}

	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompanyPosition() {
		return companyPosition;
	}
	public void setCompanyPosition(String companyPosition) {
		this.companyPosition = companyPosition;
	}

	public String getCompanyCity() {
		return companyCity;
	}
	public void setCompanyCity(String companyCity) {
		this.companyCity = companyCity;
	}

	public String getCompanyDescription() {
		return companyDescription;
	}
	public void setCompanyDescription(String companyDescription) {
		this.companyDescription = companyDescription;
	}

	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}

	public Date getSchoolDatesAttended() {
		return schoolDatesAttended;
	}
	public void setSchoolDatesAttended(Date schoolDatesAttended) {
		this.schoolDatesAttended = schoolDatesAttended;
	}

	public String getSchoolDegree() {
		return schoolDegree;
	}
	public void setSchoolDegree(String schoolDegree) {
		this.schoolDegree = schoolDegree;
	}

	public String getSchoolFieldOfStudy() {
		return schoolFieldOfStudy;
	}
	public void setSchoolFieldOfStudy(String schoolFieldOfStudy) {
		this.schoolFieldOfStudy = schoolFieldOfStudy;
	}

	public String getSchoolGrade() {
		return schoolGrade;
	}
	public void setSchoolGrade(String schoolGrade) {
		this.schoolGrade = schoolGrade;
	}

	public String getSchoolActivitiesSocieties() {
		return schoolActivitiesSocieties;
	}
	public void setSchoolActivitiesSocieties(String schoolActivitiesSocieties) {
		this.schoolActivitiesSocieties = schoolActivitiesSocieties;
	}

	public String getSchoolDescription() {
		return schoolDescription;
	}
	public void setSchoolDescription(String schoolDescription) {
		this.schoolDescription = schoolDescription;
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
		this.studentCourses = studentCourses;	
	}

	public String getValidationType() {
		return validationType;
	}
	public void setValidationType(String validationType) {
		this.validationType = validationType;
	}
	
}