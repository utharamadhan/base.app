package id.base.app.valueobject.advisory;

import id.base.app.valueobject.AppUser;
import id.base.app.valueobject.BaseEntity;
import id.base.app.valueobject.Category;

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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "ADVISORY_CONSULTING")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="advisoryConsultingJid", scope=AdvisoryConsulting.class)
public class AdvisoryConsulting extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5924514560093017520L;
	
	public static final String PK_ADVISORY 		= "pkAdvisory";
	public static final String STATUS			= "status";
	public static final String TUTOR 			= "tutor";
	public static final String TUTOR_PK 		= "tutor.pkAppUser";
	public static final String CATEGORY 		= "category";
	public static final String CATEGORY_PK 		= "category.pkCategory";
	
	public static AdvisoryConsulting getInstance() {
		return new AdvisoryConsulting();
	}
	
	@Id
	@SequenceGenerator(name="ADVISORY_PK_ADVISORY_SEQ", sequenceName="ADVISORY_PK_ADVISORY_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ADVISORY_PK_ADVISORY_SEQ")
	@Column(name = "PK_ADVISORY", unique = true ,nullable = false)
	private Long pkAdvisory;
		
	@ManyToOne
	@JoinColumn(name="FK_TUTOR")
	private AppUser tutor;
	
	@ManyToOne
	@JoinColumn(name="FK_CATEGORY")
	private Category category;
	
	@Column(name="QUESTION")
	private String question;
	
	@Column(name="ANSWER")
	private String answer;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="TELP")
	private String telp;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="STATUS")
	private Integer status;

	public Long getPkAdvisory() {
		return pkAdvisory;
	}
	public void setPkAdvisory(Long pkAdvisory) {
		this.pkAdvisory = pkAdvisory;
	}

	public AppUser getTutor() {
		return tutor;
	}
	public void setTutor(AppUser tutor) {
		this.tutor = tutor;
	}

	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
}