package id.base.app.valueobject.program;

import id.base.app.valueobject.Lookup;

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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "PROGRAM_ITEM_MENU")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="programItemMenuJid", scope=ProgramItemMenu.class)
public class ProgramItemMenu implements Serializable, Comparator<ProgramItemMenu> {

	private static final long serialVersionUID = -6102507969542032743L;
	
	@Id
	@SequenceGenerator(name="PROGRAM_ITEM_MENU_PK_PROGRAM_ITEM_MENU_SEQ", sequenceName="PROGRAM_ITEM_MENU_PK_PROGRAM_ITEM_MENU_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PROGRAM_ITEM_MENU_PK_PROGRAM_ITEM_MENU_SEQ")
	@Column(name = "PK_PROGRAM_ITEM_MENU", unique = true ,nullable = false)
	private Long pkProgramItemMenu;
	
	@ManyToOne(cascade=CascadeType.DETACH)
	@JoinColumn(name="FK_PROGRAM_ITEM")
	private ProgramItem programItem;
	
	@Column(name="TITLE")
	private String title;
	
	@OneToOne
	@JoinColumn(name="FK_LOOKUP_ITEM_MENU")
	private Lookup itemMenuLookup;
	
	@Column(name="ORDER_NO")
	private Integer orderNo;

	public Long getPkProgramItemMenu() {
		return pkProgramItemMenu;
	}

	public void setPkProgramItemMenu(Long pkProgramItemMenu) {
		this.pkProgramItemMenu = pkProgramItemMenu;
	}

	public ProgramItem getProgramItem() {
		return programItem;
	}

	public void setProgramItem(ProgramItem programItem) {
		this.programItem = programItem;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Lookup getItemMenuLookup() {
		return itemMenuLookup;
	}

	public void setItemMenuLookup(Lookup itemMenuLookup) {
		this.itemMenuLookup = itemMenuLookup;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	@Override
	public int compare(ProgramItemMenu o1, ProgramItemMenu o2) {
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