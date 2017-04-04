package id.base.app.valueobject;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "FAQ")
public class Faq extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 5176188761567593942L;
	
	public static Faq getInstance() {
		return new Faq();
	}
	
	@Id
	@SequenceGenerator(name="FAQ_PK_FAQ_SEQ", sequenceName="FAQ_PK_FAQ_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FAQ_PK_FAQ_SEQ")
	@Column(name = "PK_FAQ", unique = true ,nullable = false)
	private Long pkFaq;
	
	@OneToOne(cascade=CascadeType.DETACH)
	@JoinColumn(name="FK_LOOKUP_FAQ_CATEGORY")
	private Lookup faqCategoryLookup;
	
	@Column(name="QUESTION")
    private String question;
	
	@Column(name="ANSWER")
    private String answer;
	
	@Column(name="STATUS")
    private Integer status;
	
	@Transient
	private String statusStr;
	
	public static final String PK_FAQ = "pkFaq" ;
	public static final String QUESTION = "question" ;
	public static final String ANSWER = "answer" ;
	public static final String STATUS = "status" ;
	
	public Long getPkFaq() {
		return pkFaq;
	}
	public void setPkFaq(Long pkFaq) {
		this.pkFaq = pkFaq;
	}
	public Lookup getFaqCategoryLookup() {
		return faqCategoryLookup;
	}
	public void setFaqCategoryLookup(Lookup faqCategoryLookup) {
		this.faqCategoryLookup = faqCategoryLookup;
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
	public String getStatusStr() {
		return statusStr;
	}
	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}
}