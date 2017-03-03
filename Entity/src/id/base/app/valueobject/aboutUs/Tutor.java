package id.base.app.valueobject.aboutUs;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import id.base.app.encryptor.EncodeDecode;
import id.base.app.valueobject.BaseEntity;

@Entity
@Table(name = "TUTOR")
public class Tutor extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2886297042567028121L;
	
	public static final String PK_TUTOR = "pkTutor";
	public static final String NAME 	= "name";
	public static final String PROFILE_DESCRIPTION 	= "profileDescription";
	public static final String STATUS 	= "status";
	
	public static Tutor getInstance() {
		return new Tutor();
	}
	
	@Id
	@SequenceGenerator(name="TUTOR_PK_TUTOR_SEQ", sequenceName="TUTOR_PK_TUTOR_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TUTOR_PK_TUTOR_SEQ")
	@Column(name = "PK_TUTOR", unique = true ,nullable = false)
	private Long pkTutor;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="PROFILE_DESCRIPTION")
	private String profileDescription;
	
	@Column(name="BASIC_PICTURE_URL")
	private String basicPictureURL;
	
	@Transient
	public String encodedPicture;
	
	@Column(name="STATUS")
	private Integer status;

	public Long getPkTutor() {
		return pkTutor;
	}
	public void setPkTutor(Long pkTutor) {
		this.pkTutor = pkTutor;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getProfileDescription() {
		return profileDescription;
	}
	public void setProfileDescription(String profileDescription) {
		this.profileDescription = profileDescription;
	}
	
	public String getBasicPictureURL() {
		return basicPictureURL;
	}
	public void setBasicPictureURL(String basicPictureURL) {
		this.basicPictureURL = basicPictureURL;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
