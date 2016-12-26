package id.base.app.valueobject.course;

import id.base.app.valueobject.BaseEntity;
import id.base.app.valueobject.Lookup;

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
@Table(name = "GC_BASIC_INFO")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="gcBasicInformationJid", scope=GCBasicInformation.class)
public class GCBasicInformation extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6405725508325296495L;
	
	public static final String PK_GC_BASIC_INFORMATION 	= "pkGCBasicInformation";
	public static final String FIELD_LOOKUP 			= "fieldLookup";
	public static final String ORDER_NO					= "orderNo";
	
	public static GCBasicInformation getInstance() {
		return new GCBasicInformation();
	}
	
	@Id
	@SequenceGenerator(name="GC_BASIC_INFO_PK_GC_BASIC_INFO_SEQ", sequenceName="GC_BASIC_INFO_PK_GC_BASIC_INFO_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="GC_BASIC_INFO_PK_GC_BASIC_INFO_SEQ")
	@Column(name = "PK_GC_BASIC_INFO", unique = true ,nullable = false)
	private Long pkGCBasicInformation;
	
	@ManyToOne
	@JoinColumn(name="FK_LOOKUP_FIELD")
	private Lookup fieldLookup;
	
	@Column(name="VALUE")
	private String value;
	
	@Column(name="ORDER_NO")
	private Integer orderNo;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_GROUP_COURSE")
	private GroupCourse groupCourse;

	public Long getPkGCBasicInformation() {
		return pkGCBasicInformation;
	}
	public void setPkGCBasicInformation(Long pkGCBasicInformation) {
		this.pkGCBasicInformation = pkGCBasicInformation;
	}
	
	public Lookup getFieldLookup() {
		return fieldLookup;
	}
	public void setFieldLookup(Lookup fieldLookup) {
		this.fieldLookup = fieldLookup;
	}

	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	
	public GroupCourse getGroupCourse() {
		return groupCourse;
	}
	public void setGroupCourse(GroupCourse groupCourse) {
		this.groupCourse = groupCourse;
	}
	
}
