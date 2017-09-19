package id.base.app.valueobject.program;

import id.base.app.valueobject.party.Party;

import java.io.Serializable;
import java.util.Comparator;

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
@Table(name = "PROGRAM_ITEM_TEACHER")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="programItemTeacherJid", scope=ProgramItemTeacher.class)
public class ProgramItemTeacher implements Serializable, Comparator<ProgramItemTeacher> {

	private static final long serialVersionUID = 3129700009491696648L;
	
	@Id
	@SequenceGenerator(name="PROGRAM_ITEM_TEACHER_PK_PROGRAM_ITEM_TEACHER_SEQ", sequenceName="PROGRAM_ITEM_TEACHER_PK_PROGRAM_ITEM_TEACHER_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PROGRAM_ITEM_TEACHER_PK_PROGRAM_ITEM_TEACHER_SEQ")
	@Column(name = "PK_PROGRAM_ITEM_TEACHER", unique = true ,nullable = false)
	private Long pkProgramItemTeacher;
	
	@ManyToOne(cascade=CascadeType.DETACH)
	@JoinColumn(name="FK_PROGRAM_ITEM")
	private ProgramItem programItem;
	
	@Column(name="TEACHER_NAME")
	private String teacherName;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_PARTY_TEACHER", nullable=true)
	private Party partyTeacher;
	
	@Column(name="ORDER_NO")
	private Integer orderNo;

	public Long getPkProgramItemTeacher() {
		return pkProgramItemTeacher;
	}

	public void setPkProgramItemTeacher(Long pkProgramItemTeacher) {
		this.pkProgramItemTeacher = pkProgramItemTeacher;
	}

	public ProgramItem getProgramItem() {
		return programItem;
	}

	public void setProgramItem(ProgramItem programItem) {
		this.programItem = programItem;
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

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	
	@Override
	public int compare(ProgramItemTeacher o1, ProgramItemTeacher o2) {
		Integer orderNoO1 = o1.getOrderNo();
		Integer orderNoO2 = o2.getOrderNo();
		if(orderNoO1==null){
			orderNoO1 = Integer.MAX_VALUE;
		}
		if(orderNoO2==null){
			orderNoO2 = Integer.MAX_VALUE;
		}
		return orderNoO1 - orderNoO2;
	}
	
}