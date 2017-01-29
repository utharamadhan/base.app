package id.base.app.dao.notification;

import id.base.app.IBaseDAO;
import id.base.app.exception.SystemException;
import id.base.app.valueobject.notification.Notification;

import java.util.List;


public interface INotificationDAO extends IBaseDAO<Notification> {
	
	public Integer countUnreadNotifications() throws SystemException;
	
	public List<Notification> getLastFiveNotifications() throws SystemException;

}
