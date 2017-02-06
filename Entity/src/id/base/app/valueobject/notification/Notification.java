package id.base.app.valueobject.notification;

import id.base.app.ILookupConstant;
import id.base.app.SystemConstant;
import id.base.app.util.DateTimeFunction;
import id.base.app.util.StringFunction;
import id.base.app.valueobject.Lookup;
import id.base.app.valueobject.contact.Contact;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

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
import javax.persistence.Transient;

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
	
	public static final String PK_NOTIFICATION = "pkNotification";
	
	public static Notification getInstance(Contact obj, Lookup actionTypeLookup) {
		Notification notif = new Notification();
			notif.setStatus(SystemConstant.NotificationConstant.UNREAD);
			notif.setEmailFrom(obj.getEmail());
			notif.setNameFrom(obj.getName());
			notif.setOverviewMessage(obj.getMessage());
			notif.setActionTypeLookup(actionTypeLookup);
			notif.setFkMaintenance(obj.getPkContact());
			notif.setActionDate(obj.getCreationTime());
		return notif;
	}
	
	@Id
	@SequenceGenerator(name="NOTIFICATION_PK_NOTIFICATION_SEQ", sequenceName="NOTIFICATION_PK_NOTIFICATION_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="NOTIFICATION_PK_NOTIFICATION_SEQ")
	@Column(name = "PK_NOTIFICATION", unique = true ,nullable = false)
	private Long pkNotification;
	
	@ManyToOne(cascade=CascadeType.DETACH)
	@JoinColumn(name="FK_LOOKUP_ACTION_TYPE")
	private Lookup actionTypeLookup;
	
	@Column(name="STATUS")
	private Integer status;
	
	@Column(name="EMAIL_FROM")
	private String emailFrom;
	
	@Column(name="NAME_FROM")
	private String nameFrom;
	
	@Column(name="OVERVIEW_MESSAGE")
	private String overviewMessage;
	
	@Column(name = "FK_MAINTENANCE")
	private Long fkMaintenance;
	
	@Column(name = "ACTION_DATE")
	private Date actionDate;
	
	@Column(name = "READ_BY")
	private String readBy;
	
	@Column(name = "READ_TIME")
	private Date readTime;
	
	@Transient
	private String actionAge;
	
	@Transient
	private String detailURL;
	
	@Transient
	private String statusDescr;

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

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	
	public String getOverviewMessage() {
		return overviewMessage;
	}
	public void setOverviewMessage(String overviewMessage) {
		if(StringFunction.isNotEmpty(overviewMessage) && overviewMessage.length() > 100) {
			overviewMessage = overviewMessage.substring(0, 100) + "...";
		}
		this.overviewMessage = overviewMessage;
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
	
	public String getReadBy() {
		return readBy;
	}
	public void setReadBy(String readBy) {
		this.readBy = readBy;
	}
	
	public Date getReadTime() {
		return readTime;
	}
	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}
	
	@Transient
	public String getActionAge() {
		if(actionDate != null) {
			try {
				return DateTimeFunction.calcDateDifferenceString(actionDate, Calendar.getInstance().getTime());	
			} catch (Exception e) {
				
			}
		}
		return actionAge;
	}
	@Transient
	public void setActionAge(String actionAge) {
		this.actionAge = actionAge;
	}
	
	@Transient
	public String getDetailURL() {
		if(this.actionTypeLookup != null && this.actionTypeLookup.getCode() != null) {
			return ILookupConstant.NotificationActionType.URL_MAP.get(this.actionTypeLookup.getCode());
		}
		return detailURL;
	}
	@Transient
	public void setDetailURL(String detailURL) {
		this.detailURL = detailURL;
	}
	
	@Transient
	public String getStatusDescr() {
		if(this.status != null) {
			return SystemConstant.NotificationConstant.NOTIFICATION_MAP.get(status);
		}
		return statusDescr;
	}
	@Transient
	public void setStatusDescr(String statusDescr) {
		this.statusDescr = statusDescr;
	}
	
}
