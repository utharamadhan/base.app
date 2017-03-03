package id.base.app.valueobject.course;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import id.base.app.SystemConstant;
import id.base.app.encryptor.EncodeDecode;
import id.base.app.valueobject.BaseEntity;

@Entity
@Table(name = "GROUP_COURSE")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="groupCourseJid", scope=GroupCourse.class)
public class GroupCourse extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2512890745160322636L;
	
	public static final String PK_GROUP_COURSE 	= "pkGroupCourse";
	public static final String ORDER_NO 		= "orderNo";
	public static final String TITLE 			= "title";
	public static final String BASIC_PICTURE_URL 	= "basicPictureURL";
	public static final String SHORT_DESCRIPTION	= "shortDesription";
	public static final String FULL_DESCRIPTION	= "fullDesription";
	public static final String STATUS			= "status";
	
	public static GroupCourse getInstance() {
		return new GroupCourse();
	}
	
	@Id
	@SequenceGenerator(name="GROUP_COURSE_PK_GROUP_COURSE_SEQ", sequenceName="GROUP_COURSE_PK_GROUP_COURSE_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="GROUP_COURSE_PK_GROUP_COURSE_SEQ")
	@Column(name = "PK_GROUP_COURSE", unique = true ,nullable = false)
	private Long pkGroupCourse;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="BASIC_PICTURE_URL")
	private String basicPictureURL;
	
	@Transient
	public String encodedPicture;
	
	@Column(name="SHORT_DESCRIPTION")
	private String shortDescription;
	
	@Column(name="FULL_DESCRIPTION")
	private String fullDescription;

	@Column(name="STATUS")
	private Integer status;
	
	@Column(name="ORDER_NO")
	private Integer orderNo;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, mappedBy="groupCourse")
	private List<GCBasicInformation> gcBasicInformationList = new ArrayList<>(); 
	
	public Long getPkGroupCourse() {
		return pkGroupCourse;
	}
	public void setPkGroupCourse(Long pkGroupCourse) {
		this.pkGroupCourse = pkGroupCourse;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getBasicPictureURL() {
		return basicPictureURL;
	}
	public void setBasicPictureURL(String basicPictureURL) {
		this.basicPictureURL = basicPictureURL;
	}
	
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	
	public String getFullDescription() {
		return fullDescription;
	}
	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public List<GCBasicInformation> getGcBasicInformationList() {
		return gcBasicInformationList;
	}
	public void setGcBasicInformationList(List<GCBasicInformation> gcBasicInformationList) {
		if(this.gcBasicInformationList == null) {
			this.gcBasicInformationList = new ArrayList<>();
		} else {
			this.gcBasicInformationList.clear();
		}
		if(null != gcBasicInformationList) {			
			this.gcBasicInformationList.addAll(gcBasicInformationList);
		}
	}
	public String getEncodedPicture() throws Exception {
		if(getBasicPictureURL()!=null && !"".equals(getBasicPictureURL())){
			encodedPicture = EncodeDecode.getBase64FromLink(getBasicPictureURL());
		}
		return encodedPicture;
	}
	public void setEncodedPicture(String encodedPicture) {
		this.encodedPicture = encodedPicture;
	}

}
