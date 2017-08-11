package id.base.app.valueobject.contact;

import id.base.app.util.DateTimeFunction;
import id.base.app.valueobject.BaseEntity;
import id.base.app.valueobject.Category;
import id.base.app.valueobject.Lookup;
import id.base.app.valueobject.learning.LearningItem;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

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
@Table(name="CONTACT")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="contactJid", scope=Contact.class)
public class Contact extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 727318892302903192L;
	
	public static final String PK_CONTACT 				= "pkContact";
	public static final String NAME 					= "name";
	public static final String EMAIL 					= "email";
	public static final String TELP 					= "telp";
	public static final String PHONE 					= "phone";
	public static final String ADDRESS 					= "address";
	public static final String INSTANSI 				= "instansi";
	public static final String MESSAGE 				    = "message";
	public static final String HELP_LOOKUP 				= "helpLookup";
	public static final String HELP_LOOKUP_DESCR		= "helpLookup.descr";
	public static final String TEMA_LOOKUP				= "temaLookup";
	public static final String TEMA_LOOKUP_NAME			= "temaLookup.name";
	public static final String COURSE					= "course";
	public static final String COURSE_NAME				= "course.name";
	
	public static Contact getInstance() {
		return new Contact();
	}
	
	public static Contact getInstance(Long pkContact) {
		Contact s = getInstance();
		return s;
	}
	
	@Id
	@SequenceGenerator(name="CONTACT_PK_CONTACT_SEQ", sequenceName="CONTACT_PK_CONTACT_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CONTACT_PK_CONTACT_SEQ")
	@Column(name = "PK_CONTACT", unique = true ,nullable = false)
	private Long pkContact;

	@ManyToOne
	@JoinColumn(name="FK_LOOKUP_CATEGORY_HELP")
	private Lookup helpLookup;
	
	@ManyToOne
	@JoinColumn(name="FK_CATEGORY")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name="FK_LEARNING_ITEM")
	private LearningItem learningItem;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="TELP")
	private String telp;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="BIRTH_PLACE")
	private String birthPlace;
	
	@Column(name="BIRTH_DATE")
	private Date birthDate;
	
	@Column(name="JOB")
	private String job;
	
	@Column(name="INSTANSI")
	private String instansi;
	
	@Column(name="MESSAGE")
	private String message;
	
	@Column(name="ANSWER")
	private String answer;
	
	@Column(name="ANSWERED_BY")
	private String answeredBy;
	
	@Column(name="STATUS")
	private Integer status;
	
	@Transient
	private Date activityDate;
	
	@Transient
	private String activityAge;

	public Long getPkContact() {
		return pkContact;
	}

	public void setPkContact(Long pkContact) {
		this.pkContact = pkContact;
	}

	public Lookup getHelpLookup() {
		return helpLookup;
	}

	public void setHelpLookup(Lookup helpLookup) {
		this.helpLookup = helpLookup;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public LearningItem getLearningItem() {
		return learningItem;
	}

	public void setLearningItem(LearningItem learningItem) {
		this.learningItem = learningItem;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelp() {
		return telp;
	}

	public void setTelp(String telp) {
		this.telp = telp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getInstansi() {
		return instansi;
	}

	public void setInstansi(String instansi) {
		this.instansi = instansi;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public String getAnsweredBy() {
		return answeredBy;
	}

	public void setAnsweredBy(String answeredBy) {
		this.answeredBy = answeredBy;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Transient
	public Date getActivityDate() {
		return activityDate;
	}
	@Transient
	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

	@Transient
	public String getActivityAge() {
		if(activityDate != null) {
			try {
				return DateTimeFunction.calcDateDifferenceString(activityDate, Calendar.getInstance().getTime());	
			} catch (Exception e) {
				
			}
		}
		return activityAge;
	}
	@Transient
	public void setActivityAge(String activityAge) {
		this.activityAge = activityAge;
	}
	
}