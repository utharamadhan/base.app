package id.base.app.valueobject.program;

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
@Table(name = "PROGRAM_ITEM_IMAGE")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="programItemImageJid", scope=ProgramItemImage.class)
public class ProgramItemImage implements Serializable, Comparator<ProgramItemImage> {

	private static final long serialVersionUID = -4352824404094265090L;
	
	public static ProgramItemImage getInstance() {
		ProgramItemImage obj = new ProgramItemImage();
		return obj;
	}
	
	@Id
	@SequenceGenerator(name="PROGRAM_ITEM_IMAGE_PK_PROGRAM_ITEM_IMAGE_SEQ", sequenceName="PROGRAM_ITEM_IMAGE_PK_PROGRAM_ITEM_IMAGE_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PROGRAM_ITEM_IMAGE_PK_PROGRAM_ITEM_IMAGE_SEQ")
	@Column(name = "PK_PROGRAM_ITEM_IMAGE", unique = true ,nullable = false)
	private Long pkProgramItemImage;

	@ManyToOne(cascade=CascadeType.DETACH)
	@JoinColumn(name="FK_PROGRAM_ITEM")
	private ProgramItem programItem;
	
	@Column(name="IMAGE_URL")
	private String imageURL;
	
	@Column(name="TYPE")
	private Integer type;
	
	@Column(name="ORDER_NO")
	private Integer orderNo;
	
	public Long getPkProgramItemImage() {
		return pkProgramItemImage;
	}

	public void setPkProgramItemImage(Long pkProgramItemImage) {
		this.pkProgramItemImage = pkProgramItemImage;
	}

	public ProgramItem getProgramItem() {
		return programItem;
	}

	public void setProgramItem(ProgramItem programItem) {
		this.programItem = programItem;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	
	@Override
	public int compare(ProgramItemImage o1, ProgramItemImage o2) {
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