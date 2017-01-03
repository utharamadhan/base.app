package id.base.app.valueobject.advisory;

import id.base.app.valueobject.BaseEntity;
import id.base.app.valueobject.aboutUs.Tutor;

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
@Table(name = "ADVISORY")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="advisoryJid", scope=Advisory.class)
public class Advisory extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5924514560093017520L;
	
	public static final String PK_ADVISORY 		= "pkAdvisory";
	public static final String STATUS			= "status";
	
	public static Advisory getInstance() {
		return new Advisory();
	}
	
	@Id
	@SequenceGenerator(name="ADVISORY_PK_ADVISORY_SEQ", sequenceName="ADVISORY_PK_ADVISORY_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ADVISORY_PK_ADVISORY_SEQ")
	@Column(name = "PK_ADVISORY", unique = true ,nullable = false)
	private Long pkAdvisory;
		
	@Column(name="FK_TUTOR")
	private Tutor tutor;
	
	@Column(name="QUESTION")
	private String question;
	
	@Column(name="ANSWER")
	private String answer;
	
	@Column(name="STATUS")
	private Integer status;

	public Long getPkAdvisory() {
		return pkAdvisory;
	}
	public void setPkAdvisory(Long pkAdvisory) {
		this.pkAdvisory = pkAdvisory;
	}

	public Tutor getTutor() {
		return tutor;
	}
	public void setTutor(Tutor tutor) {
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
	
}
