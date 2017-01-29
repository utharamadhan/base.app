package id.base.app.valueobject.notification;

import id.base.app.valueobject.Lookup;
import id.base.app.valueobject.contact.Contact;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="NOTIFICATION")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="notificationJid", scope=Notification.class)
public class Notification implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7292981544332561500L;
	
	public static Notification getInstance(Contact obj) {
		Notification notif = new Notification();
		return notif;
	}
	
	@Id
	@SequenceGenerator(name="NOTIFICATION_PK_NOTIFICATION_SEQ", sequenceName="NOTIFICATION_PK_NOTIFICATION_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="NOTIFICATION_PK_NOTIFICATION_SEQ")
	@Column(name = "PK_NOTIFICATION_PK_NOTIFICATION_SEQ", unique = true ,nullable = false)
	private Long pkNotification;
	
	@Column(name="FK_LOOKUP_ACTION_TYPE")
	private Lookup actionTypeLookup;
	
	@Column(name="IS_READ")
	private Boolean isRead;
	
	@Column(name="EMAIL_FROM")
	private String emailFrom;
	
	@Column(name="NAME_FROM")
	private String nameFrom;
	
	@Column(name = "FK_MAINTENANCE")
	private Long fkMaintenance;
	
	@Column(name = "ACTION_DATE")
	private Date actionDate;

	public Long getPkNotification() {
		return pkNotification;
	}
	public void setPkNotification(Long pkNotification) {
		this.pkNotification = pkNotification;
	}

	public Lookup getActionTypeLookup() {
		return actionTypeLookup;
	}
	public void setActionTypeLookup(Lookup actionTypeLookup) {
		this.actionTypeLookup = actionTypeLookup;
	}

	public Boolean getIsRead() {
		return isRead;
	}
	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	public String getEmailFrom() {
		return emailFrom;
	}
	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}
	
	public String getNameFrom() {
		return nameFrom;
	}
	public void setNameFrom(String nameFrom) {
		this.nameFrom = nameFrom;
	}
	
	public Long getFkMaintenance() {
		return fkMaintenance;
	}
	public void setFkMaintenance(Long fkMaintenance) {
		this.fkMaintenance = fkMaintenance;
	}
	
	public Date getActionDate() {
		return actionDate;
	}
	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}
	
}
