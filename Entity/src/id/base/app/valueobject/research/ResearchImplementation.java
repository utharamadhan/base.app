package id.base.app.valueobject.research;

import id.base.app.valueobject.party.Party;

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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="RESEARCH_IMPLEMENTATION")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="researchImplementationJid", scope=ResearchImplementation.class)
public class ResearchImplementation implements Serializable{

	private static final long serialVersionUID = -4470603713725902163L;
	
	public static final String PK_RESEARCH_IMPLEMENTATION	= "pkResearchImplementation";
	public static final String FK_RESEARCH	= "research.pkResearch";
	public static final String STEP_NO	= "stepNo";
	public static final String OUTPUT_DESC = "outputDesc";
	public static final String OUTPUT_FILE = "outputFile";
	public static final String EXECUTOR_NAME = "executorName";
	public static final String FK_PARTY_EXECUTOR = "partyExecutor.name";
	public static final String DEADLINE_DATE = "deadlineDate";
	public static final String STATUS = "status";

	public static final String[] MAINTENANCE_LIST_FIELDS = {
		PK_RESEARCH_IMPLEMENTATION, FK_RESEARCH, STEP_NO, OUTPUT_DESC, OUTPUT_FILE, EXECUTOR_NAME, DEADLINE_DATE, STATUS
	};
	
	public static ResearchImplementation getInstance() {
		return new ResearchImplementation();
	}
	
	@Id
	@SequenceGenerator(name="RESEARCH_IMPLEMENTATION_PK_RESEARCH_IMPLEMENTATION_SEQ", sequenceName="RESEARCH_IMPLEMENTATION_PK_RESEARCH_IMPLEMENTATION_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="RESEARCH_IMPLEMENTATION_PK_RESEARCH_IMPLEMENTATION_SEQ")
	@Column(name = "PK_RESEARCH_IMPLEMENTATION", unique = true ,nullable = false, precision = 10, scale = 0)
	private Long pkResearchImplementation;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_RESEARCH", nullable=true)
	private Research research;

	@Column(name="STEP_NO")
	private String stepNo;
	
	@Column(name="OUTPUT_DESC")
	private String outputDesc;	
	
	@Column(name="OUTPUT_FILE")
	private String outputFile;
	
	@Column(name="EXECUTOR_NAME")
	private String executorName;

	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_PARTY_EXECUTOR", nullable=true)
	private Party partyExecutor;
	
	@Column(name="DEADLINE_DATE")
	private Date deadlineDate;
	
	@Column(name="STATUS")
	private Integer status;

	public Long getPkResearchImplementation() {
		return pkResearchImplementation;
	}

	public void setPkResearchImplementation(Long pkResearchImplementation) {
		this.pkResearchImplementation = pkResearchImplementation;
	}

	public Research getResearch() {
		return research;
	}

	public void setResearch(Research research) {
		this.research = research;
	}
	
	public String getStepNo() {
		return stepNo;
	}

	public void setStepNo(String stepNo) {
		this.stepNo = stepNo;
	}

	public String getOutputDesc() {
		return outputDesc;
	}

	public void setOutputDesc(String outputDesc) {
		this.outputDesc = outputDesc;
	}

	public String getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}

	public String getExecutorName() {
		return executorName;
	}

	public void setExecutorName(String executorName) {
		this.executorName = executorName;
	}

	public Party getPartyExecutor() {
		return partyExecutor;
	}

	public void setPartyExecutor(Party partyExecutor) {
		this.partyExecutor = partyExecutor;
	}

	public Date getDeadlineDate() {
		return deadlineDate;
	}

	public void setDeadlineDate(Date deadlineDate) {
		this.deadlineDate = deadlineDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
