package id.base.app.valueobject.contact;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import id.base.app.valueobject.BaseEntity;

@Entity
@Table(name = "CONTACT_SETTING")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="contactSettingJid", scope=ContactSetting.class)
public class ContactSetting extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3549763034875617469L;
	
	public static final String PK_CONTACT_SETTING 	= "pkContactSetting";
	
	public static ContactSetting getInstance() {
		return new ContactSetting();
	}
	
	@Id
	@SequenceGenerator(name="SETTING_PK_CONTACT_SETTING_SEQ", sequenceName="SETTING_PK_CONTACT_SETTING_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SETTING_PK_CONTACT_SETTING_SEQ")
	@Column(name = "PK_CONTACT_SETTING", unique = true ,nullable = false)
	private Long pkContactSetting;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="PHONE")
	private String phone;
	
	@Column(name="FAX")
	private String fax;
		
	@Column(name="WORKING_DAY1")
	private String workingDay1;
	
	@Column(name="WORKING_DAY2")
	private String workingDay2;
	
	@Column(name="WORKING_DAY3")
	private String workingDay3;
	
	@Column(name="LONGITUDE")
	private String longitude;
	
	@Column(name="LATITUDE")
	private String latitude;

	public Long getPkContactSetting() {
		return pkContactSetting;
	}

	public void setPkContactSetting(Long pkContactSetting) {
		this.pkContactSetting = pkContactSetting;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getWorkingDay1() {
		return workingDay1;
	}

	public void setWorkingDay1(String workingDay1) {
		this.workingDay1 = workingDay1;
	}

	public String getWorkingDay2() {
		return workingDay2;
	}

	public void setWorkingDay2(String workingDay2) {
		this.workingDay2 = workingDay2;
	}

	public String getWorkingDay3() {
		return workingDay3;
	}

	public void setWorkingDay3(String workingDay3) {
		this.workingDay3 = workingDay3;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	
	
}
