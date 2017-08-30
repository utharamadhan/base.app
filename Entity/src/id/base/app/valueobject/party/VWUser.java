package id.base.app.valueobject.party;

import id.base.app.encryptor.EncodeDecode;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="VW_USER")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="VWuSEJid", scope=VWUser.class)
public class VWUser implements Serializable {
	
	private static final long serialVersionUID = -1914332810746831970L;
	
	public static final String PK_APP_USER 			= "pkAppUser";
	public static final String PK_PARTY 			= "pkParty";
	public static final String ROLE_PERMALINK 		= "rolePermalink";
	public static final String PERMALINK			= "permalink";
	public static final String NAME					= "name";
	public static final String BASIC_PICTURE_URL	= "basicPictureURL";
	
	@Id
	@Column(name="PK_APP_USER")
	private Long pkAppUser;
	
	@Column(name="PK_PARTY")
	private Long pkParty;
	
	@Column(name="ROLE_PERMALINK")
	private String rolePermalink;

	@Column(name="PERMALINK")
	private String permalink;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="BASIC_PICTURE_URL")
	private String basicPictureURL;
	
	@Transient
	public String encodedPicture;

	public Long getPkAppUser() {
		return pkAppUser;
	}

	public void setPkAppUser(Long pkAppUser) {
		this.pkAppUser = pkAppUser;
	}

	public Long getPkParty() {
		return pkParty;
	}

	public void setPkParty(Long pkParty) {
		this.pkParty = pkParty;
	}

	public String getRolePermalink() {
		return rolePermalink;
	}

	public void setRolePermalink(String rolePermalink) {
		this.rolePermalink = rolePermalink;
	}

	public String getPermalink() {
		return permalink;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBasicPictureURL() {
		return basicPictureURL;
	}

	public void setBasicPictureURL(String basicPictureURL) {
		this.basicPictureURL = basicPictureURL;
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