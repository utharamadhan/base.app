package id.base.app.service.notification;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.notification.Notification;

import java.util.List;

public interface INotificationService extends MaintenanceService<Notification> {
	
	public Integer countUnreadNotifications() throws SystemException;

	public List<Notification> getLastFiveNotifications() throws SystemException;
	
}
