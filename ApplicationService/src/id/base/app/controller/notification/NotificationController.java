package id.base.app.controller.notification;

import id.base.app.SystemConstant;
import id.base.app.controller.SuperController;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.notification.INotificationService;
import id.base.app.valueobject.UpdateEntity;
import id.base.app.valueobject.notification.Notification;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_NOTIFICATION)
public class NotificationController extends SuperController<Notification>{
	
	@Autowired
	private INotificationService notificationService;

	@Override
	public MaintenanceService<Notification> getMaintenanceService() {
		return notificationService;
	}
	
	@RequestMapping(value="/getLastFiveNotifications")
	@ResponseBody
	public List<Notification> getLastFiveNotifications() throws SystemException {
		return notificationService.getLastFiveNotifications();
	}
	
	@RequestMapping(value="/countUnreadNotifications")
	@ResponseBody
	public Integer countUnreadNotifications() throws SystemException {
		return notificationService.countUnreadNotifications();
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/updateNotificationToRead")
	@ResponseBody
	public void updateNotificationToRead(@RequestBody Notification anObject, BindingResult bindingResult) throws SystemException {
		Notification notif = notificationService.findByTypeAndId(anObject.getActionTypeLookup().getCode(), anObject.getFkMaintenance());
		if (notif != null && notif.getStatus() < SystemConstant.NotificationConstant.READ) {
			notif.setStatus(SystemConstant.NotificationConstant.READ);
			notificationService.saveOrUpdate(notif);
		}
	}
	
	@Override
	@Deprecated
	public Notification validate(Notification anObject) throws SystemException {return null;}
	
}
