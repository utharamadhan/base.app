package id.base.app.valueobject.frontend;

import java.io.Serializable;

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

import id.base.app.valueobject.AppUser;
import id.base.app.valueobject.BaseEntity;

@Entity
@Table(name = "HOME_SETTING")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="homeSettingJid", scope=HomeSetting.class)
public class HomeSetting extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3549763034875617469L;
	
	public static final String PK_HOME_SETTING 	= "pkHomeSetting";
	
	public static HomeSetting getInstance() {
		return new HomeSetting();
	}
	
	@Id
	@SequenceGenerator(name="SETTING_PK_HOME_SETTING_SEQ", sequenceName="SETTING_PK_HOME_SETTING_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SETTING_PK_HOME_SETTING_SEQ")
	@Column(name = "PK_HOME_SETTING", unique = true ,nullable = false)
	private Long pkHomeSetting;
	
	@Column(name="CODE")
	private String code;
	
	@Column(name="BACKGROUND_IMAGE")
	private String backgroundImage;
	
	@Column(name="TITLE_TEXT")
	private String titleText;
	
	@Column(name="SUB_TITLE_TEXT")
	private String subTitleText;
		
	@Column(name="LINK_URL")
	private String linkUrl;

	public Long getPkHomeSetting() {
		return pkHomeSetting;
	}

	public void setPkHomeSetting(Long pkHomeSetting) {
		this.pkHomeSetting = pkHomeSetting;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage(String backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	public String getTitleText() {
		return titleText;
	}

	public void setTitleText(String titleText) {
		this.titleText = titleText;
	}

	public String getSubTitleText() {
		return subTitleText;
	}

	public void setSubTitleText(String subTitleText) {
		this.subTitleText = subTitleText;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	
	
	
}
