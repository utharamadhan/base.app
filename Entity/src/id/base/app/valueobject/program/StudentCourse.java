package id.base.app.valueobject.program;

import id.base.app.valueobject.BaseEntity;
import id.base.app.valueobject.Lookup;
import id.base.app.valueobject.party.Student;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
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
@Table(name = "STUDENT_COURSE")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="studentCourseJid", scope=StudentCourse.class)
public class StudentCourse extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2814197570520573697L;
	
	public static StudentCourse getInstance() {
		return new StudentCourse(); 
	}
	
	public static StudentCourse getInstance(Long pkStudent, Long pkCourse) {
		return getInstance(Student.getInstance(pkStudent), ProgramItem.getInstance(pkCourse));
	}
	
	public static StudentCourse getInstance(Student student, ProgramItem course) {
		StudentCourse sc = new StudentCourse();
			sc.setStudent(student);
			sc.setCourse(course);
		return sc;
	}
	
	public static StudentCourse getProcessInstance(Long pkStudentCourse, String actionType) {
		StudentCourse sc = StudentCourse.getInstance();
			sc.setPkStudentCourse(pkStudentCourse);
			sc.setActionType(actionType);
		return sc;
	}
	
	@Id
	@SequenceGenerator(name="STUDENT_COURSE_PK_STUDENT_COURSE_SEQ", sequenceName="STUDENT_COURSE_PK_STUDENT_COURSE_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="STUDENT_COURSE_PK_STUDENT_COURSE_SEQ")
	@Column(name = "PK_STUDENT_COURSE", unique = true ,nullable = false)
	private Long pkStudentCourse;
	
	@ManyToOne(cascade=CascadeType.DETACH)
	@JoinColumn(name="FK_STUDENT")
	private Student student;
	
	@ManyToOne(cascade=CascadeType.DETACH)
	@JoinColumn(name="FK_COURSE")
	private ProgramItem course;
	
	@Column(name="ENROLL_DATE")
	private Date enrollDate;
	
	@ManyToOne
	@JoinColumn(name="FK_LOOKUP_STUDENT_COURSE_STATUS")
	private Lookup studentCourseStatusLookup;
	
	@Column(name="LAST_ACTION_DATE")
	private Date lastActionDate;
	
	@Column(name="STATUS")
	private Integer status;
	
	@Transient
	private String actionType;

	public Long getPkStudentCourse() {
		return pkStudentCourse;
	}
	public void setPkStudentCourse(Long pkStudentCourse) {
		this.pkStudentCourse = pkStudentCourse;
	}

	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}

	public ProgramItem getCourse() {
		return course;
	}
	public void setCourse(ProgramItem course) {
		this.course = course;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public Lookup getStudentCourseStatusLookup() {
		return studentCourseStatusLookup;
	}
	public void setStudentCourseStatusLookup(Lookup studentCourseStatusLookup) {
		this.studentCourseStatusLookup = studentCourseStatusLookup;
	}

	public Date getLastActionDate() {
		return lastActionDate;
	}
	public void setLastActionDate(Date lastActionDate) {
		this.lastActionDate = lastActionDate;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	
}