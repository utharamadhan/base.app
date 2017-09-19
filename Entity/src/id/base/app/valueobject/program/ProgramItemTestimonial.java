package id.base.app.valueobject.program;

import id.base.app.valueobject.testimonial.Testimonial;

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
@Table(name = "PROGRAM_ITEM_TESTIMONIAL")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="programItemTestimonialJid", scope=ProgramItemTestimonial.class)
public class ProgramItemTestimonial implements Serializable, Comparator<ProgramItemTestimonial> {

	private static final long serialVersionUID = -8816867952744980146L;

	@Id
	@SequenceGenerator(name="PROGRAM_ITEM_TESTIMONIAL_PK_PROGRAM_ITEM_TESTIMONIAL_SEQ", sequenceName="PROGRAM_ITEM_TESTIMONIAL_PK_PROGRAM_ITEM_TESTIMONIAL_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PROGRAM_ITEM_TESTIMONIAL_PK_PROGRAM_ITEM_TESTIMONIAL_SEQ")
	@Column(name = "PK_PROGRAM_ITEM_TESTIMONIAL", unique = true ,nullable = false)
	private Long pkProgramItemTestimonial;
	
	@ManyToOne(cascade=CascadeType.DETACH)
	@JoinColumn(name="FK_PROGRAM_ITEM")
	private ProgramItem programItem;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_TESTIMONIAL", nullable=true)
	private Testimonial testimonial;
	
	@Column(name="ORDER_NO")
	private Integer orderNo;

	public Long getPkProgramItemTestimonial() {
		return pkProgramItemTestimonial;
	}

	public void setPkProgramItemTestimonial(Long pkProgramItemTestimonial) {
		this.pkProgramItemTestimonial = pkProgramItemTestimonial;
	}

	public ProgramItem getProgramItem() {
		return programItem;
	}

	public void setProgramItem(ProgramItem programItem) {
		this.programItem = programItem;
	}

	public Testimonial getTestimonial() {
		return testimonial;
	}

	public void setTestimonial(Testimonial testimonial) {
		this.testimonial = testimonial;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	@Override
	public int compare(ProgramItemTestimonial o1, ProgramItemTestimonial o2) {
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