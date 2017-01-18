package id.base.app.valueobject.party;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="VW_STUDENT_LIST")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="VWStudentListJid", scope=VWStudentList.class)
public class VWStudentList implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8816668676143635627L;
	
	public static final String PK_STUDENT 					= "pkStudent";
	public static final String NAME							= "name";
	public static final String PHONE_NUMBER					= "phoneNumber";
	public static final String EMAIL 						= "email";
	public static final String STUDENT_STATUS_LOOKUP_PK		= "fkStudentStatusLookup";
	public static final String STUDENT_STATUS_LOOKUP_CODE	= "studentStatusLookupCode";
	public static final String STUDENT_STATUS_LOOKUP_NAME	= "studentStatusLookupName";
	public static final String STATUS						= "status";
	
	public static VWStudentList getInstance() {
		return new VWStudentList();
	}
	
	@Id
	@Column(name="PK_STUDENT")
	private Long pkStudent;

	@Column(name="NAME")
	private String name;
	
	@Column(name="PHONE_NUMBER")
	private String phoneNumber;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="FK_LOOKUP_STUDENT_STATUS")
	private Long fkStudentStatusLookup;
	
	@Column(name="STUDENT_STATUS_CODE")
	private String studentStatusLookupCode;
	
	@Column(name="STUDENT_STATUS_LOOKUP_NAME")
	private String studentStatusLookupName;
	
	@Column(name="STATUS")
	private Integer status;

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

	public Long getFkStudentStatusLookup() {
		return fkStudentStatusLookup;
	}
	public void setFkStudentStatusLookup(Long fkStudentStatusLookup) {
		this.fkStudentStatusLookup = fkStudentStatusLookup;
	}

	public String getStudentStatusLookupCode() {
		return studentStatusLookupCode;
	}
	public void setStudentStatusLookupCode(String studentStatusLookupCode) {
		this.studentStatusLookupCode = studentStatusLookupCode;
	}
	
	public String getStudentStatusLookupName() {
		return studentStatusLookupName;
	}
	public void setStudentStatusLookupName(String studentStatusLookupName) {
		this.studentStatusLookupName = studentStatusLookupName;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}	
	
}