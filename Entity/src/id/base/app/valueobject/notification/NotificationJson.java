package id.base.app.valueobject.notification;

import id.base.app.ILookupConstant;
import id.base.app.SystemConstant;
import id.base.app.util.StringFunction;

import java.io.Serializable;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotificationJson implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8394470302128425376L;

	public static NotificationJson getInstance(Notification notif) {
		NotificationJson json = new NotificationJson();
			json.setPkNotification(notif.getPkNotification());
			json.setIsRead(notif.getIsRead());
			json.setEmailFrom(notif.getEmailFrom());
			json.setNameFrom(notif.getNameFrom());
			json.setNotificationCode(notif.getActionTypeLookup() != null ? notif.getActionTypeLookup().getCode() : null);
			json.setNotificationDescription(notif.getActionTypeLookup() != null ? notif.getActionTypeLookup().getDescr() : null);
			json.setFkMaintenance(notif.getFkMaintenance());
			json.setActionDate(notif.getActionDate() != null ? StringFunction.date2String(notif.getActionDate(), SystemConstant.SYSTEM_TIME_MASK_SECONDHAND) : null);
			json.setActionAge(notif.getActionAge());
			json.setOverviewMessage(notif.getOverviewMessage());
			if(StringFunction.isNotEmpty(json.getNotificationCode())) {				
				json.setDetailURL(ILookupConstant.NotificationActionType.URL_MAP.get(json.getNotificationCode()));
			}
		return json;
	}
	
	@JsonProperty("id")
	private Long pkNotification;
	
	@JsonProperty("notification_code")
	private String notificationCode;
	
	@JsonProperty("notification_description")
	private String notificationDescription;
	
	@JsonProperty("is_read")
	private Boolean isRead;
	
	@JsonProperty("email_from")
	private String emailFrom;
	
	@JsonProperty("name_from")
	private String nameFrom;
	
	@JsonProperty("fk_maintenance")
	private Long fkMaintenance;
	
	@JsonProperty("action_date")
	private String actionDate;
	
	@JsonProperty("action_age")
	private String actionAge;
	
	@JsonProperty("detail_url")
	private String detailURL;
	
	@JsonProperty("overview_message")
	private String overviewMessage;

	public Long getPkNotification() {
		return pkNotification;
	}
	public void setPkNotification(Long pkNotification) {
		this.pkNotification = pkNotification;
	}

	public String getNotificationCode() {
		return notificationCode;
	}
	public void setNotificationCode(String notificationCode) {
		this.notificationCode = notificationCode;
	}

	public String getNotificationDescription() {
		return notificationDescription;
	}
	public void setNotificationDescription(String notificationDescription) {
		this.notificationDescription = notificationDescription;
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

	public String getActionDate() {
		return actionDate;
	}
	public void setActionDate(String actionDate) {
		this.actionDate = actionDate;
	}
	
	public String getActionAge() {
		return actionAge;
	}
	public void setActionAge(String actionAge) {
		this.actionAge = actionAge;
	}
	
	@Transient
	public String getDetailURL() {
		return detailURL;
	}
	@Transient
	public void setDetailURL(String detailURL) {
		this.detailURL = detailURL;
	}
	
	public String getOverviewMessage() {
		return overviewMessage;
	}
	public void setOverviewMessage(String overviewMessage) {
		this.overviewMessage = overviewMessage;
	}
	
}
