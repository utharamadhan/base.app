package id.base.app.valueobject.report;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="VW_STUDENT_REPORT")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="VWStudentReportJid", scope=VWStudentReport.class)
public class VWStudentReport implements Serializable {
	
	private static final long serialVersionUID = 4404917350645421531L;
	
	public static final String PK_STUDENT 					= "pkStudent";
	public static final String NAME							= "name";
	
	public static VWStudentReport getInstance() {
		return new VWStudentReport();
	}
	
	@Id
	@Column(name="PK_STUDENT")
	private Long pkStudent;

	@Column(name="NAME")
	private String name;
	
	@Column(name="BIRTH_DATE")
	private Date birthDate;
	
	@Column(name="BIRTH_PLACE")
	private String birthPlace;
	
	@Column(name="GENDER")
	private String gender;
	
	@Column(name="PHONE_NUMBER")
	private String phoneNumber;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="LATEST_WORK_COMPANY")
	private String latestWorkCompany;
	
	@Column(name="LATEST_WORK_POSITION")
	private String latestWorkPosition;
	
	@Column(name="LATEST_WORK_CITY")
	private String latestWorkCity;
	
	@Column(name="LATEST_WORK_DESCRIPTION")
	private String latestWorkDescription;
	
	@Column(name="LATEST_EDUCATION_SCHOOL")
	private String latestEducationSchool;
	
	@Column(name="LATEST_EDUCATION_DATES_ATTENDED")
	private Date latestEducationDatesAttended;
	
	@Column(name="LATEST_EDUCATION_DEGREE")
	private String latestEducationDegree;
	
	@Column(name="LATEST_EDUCATION_FIELD_OF_STUDY")
	private String latestEducationFieldOfStudy;
	
	@Column(name="LATEST_EDUCATION_GRADE")
	private String latestEducationGrade;
	
	@Column(name="LATEST_EDUCATION_ACTIVITIES")
	private String latestEducationActivities;
	
	@Column(name="LATEST_EDUCATION_DESCRIPTION")
	private String latestEducationDescription;
	
	@Column(name="LATEST_LEARNING")
	private String latestLearning;
	
	@Column(name="LATEST_LEARNING_STATUS")
	private String latestLearningStatus;

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLatestWorkCompany() {
		return latestWorkCompany;
	}

	public void setLatestWorkCompany(String latestWorkCompany) {
		this.latestWorkCompany = latestWorkCompany;
	}

	public String getLatestWorkPosition() {
		return latestWorkPosition;
	}

	public void setLatestWorkPosition(String latestWorkPosition) {
		this.latestWorkPosition = latestWorkPosition;
	}

	public String getLatestWorkCity() {
		return latestWorkCity;
	}

	public void setLatestWorkCity(String latestWorkCity) {
		this.latestWorkCity = latestWorkCity;
	}

	public String getLatestWorkDescription() {
		return latestWorkDescription;
	}

	public void setLatestWorkDescription(String latestWorkDescription) {
		this.latestWorkDescription = latestWorkDescription;
	}

	public String getLatestEducationSchool() {
		return latestEducationSchool;
	}

	public void setLatestEducationSchool(String latestEducationSchool) {
		this.latestEducationSchool = latestEducationSchool;
	}

	public Date getLatestEducationDatesAttended() {
		return latestEducationDatesAttended;
	}

	public void setLatestEducationDatesAttended(Date latestEducationDatesAttended) {
		this.latestEducationDatesAttended = latestEducationDatesAttended;
	}

	public String getLatestEducationDegree() {
		return latestEducationDegree;
	}

	public void setLatestEducationDegree(String latestEducationDegree) {
		this.latestEducationDegree = latestEducationDegree;
	}

	public String getLatestEducationFieldOfStudy() {
		return latestEducationFieldOfStudy;
	}

	public void setLatestEducationFieldOfStudy(String latestEducationFieldOfStudy) {
		this.latestEducationFieldOfStudy = latestEducationFieldOfStudy;
	}

	public String getLatestEducationGrade() {
		return latestEducationGrade;
	}

	public void setLatestEducationGrade(String latestEducationGrade) {
		this.latestEducationGrade = latestEducationGrade;
	}

	public String getLatestEducationActivities() {
		return latestEducationActivities;
	}

	public void setLatestEducationActivities(String latestEducationActivities) {
		this.latestEducationActivities = latestEducationActivities;
	}
	
	public String getLatestEducationDescription() {
		return latestEducationDescription;
	}

	public void setLatestEducationDescription(String latestEducationDescription) {
		this.latestEducationDescription = latestEducationDescription;
	}

	public String getLatestLearning() {
		return latestLearning;
	}

	public void setLatestLearning(String latestLearning) {
		this.latestLearning = latestLearning;
	}

	public String getLatestLearningStatus() {
		return latestLearningStatus;
	}

	public void setLatestLearningStatus(String latestLearningStatus) {
		this.latestLearningStatus = latestLearningStatus;
	}
}