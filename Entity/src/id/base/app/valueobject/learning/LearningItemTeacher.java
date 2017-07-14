package id.base.app.valueobject.learning;

import id.base.app.valueobject.party.Party;

import java.io.Serializable;

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
@Table(name = "LEARNING_ITEM_TEACHER")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="learningItemTeacherJid", scope=LearningItemTeacher.class)
public class LearningItemTeacher implements Serializable{

	private static final long serialVersionUID = 3129700009491696648L;
	
	@Id
	@SequenceGenerator(name="LEARNING_ITEM_TEACHER_PK_LEARNING_ITEM_TEACHER_SEQ", sequenceName="LEARNING_ITEM_TEACHER_PK_LEARNING_ITEM_TEACHER_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="LEARNING_ITEM_TEACHER_PK_LEARNING_ITEM_TEACHER_SEQ")
	@Column(name = "PK_LEARNING_ITEM_TEACHER", unique = true ,nullable = false)
	private Long pkLearningItemTeacher;
	
	@ManyToOne(cascade=CascadeType.DETACH)
	@JoinColumn(name="FK_LEARNING_ITEM")
	private LearningItem learningItem;
	
	@Column(name="TEACHER_NAME")
	private String teacherName;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_PARTY_TEACHER", nullable=true)
	private Party partyTeacher;

	public Long getPkLearningItemTeacher() {
		return pkLearningItemTeacher;
	}

	public void setPkLearningItemTeacher(Long pkLearningItemTeacher) {
		this.pkLearningItemTeacher = pkLearningItemTeacher;
	}

	public LearningItem getLearningItem() {
		return learningItem;
	}

	public void setLearningItem(LearningItem learningItem) {
		this.learningItem = learningItem;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Party getPartyTeacher() {
		return partyTeacher;
	}

	public void setPartyTeacher(Party partyTeacher) {
		this.partyTeacher = partyTeacher;
	}
	
}