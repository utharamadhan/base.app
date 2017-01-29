package id.base.app.service.notification;

import id.base.app.ILookupConstant;
import id.base.app.ILookupGroupConstant;
import id.base.app.rest.RestConstant;
import id.base.app.rest.SpecificRestCaller;
import id.base.app.service.lookup.ILookupService;
import id.base.app.valueobject.contact.Contact;
import id.base.app.valueobject.notification.Notification;
import id.base.app.valueobject.notification.NotificationEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class NotificationListener {
	
	protected static Logger LOGGER = LoggerFactory.getLogger(NotificationListener.class);
	
	@Autowired
	private INotificationService notificationService;
	@Autowired
	private ILookupService lookupService;
	
	@TransactionalEventListener(phase=TransactionPhase.AFTER_COMMIT)
	public void notify(NotificationEvent event) {
		if(event.getSource() != null) {
			Notification newObj = null;
			if(event.getSource() instanceof Contact) {
				newObj = Notification.getInstance((Contact) event.getSource(), 
						lookupService.findByCode(ILookupConstant.NotificationActionType.CONTACT_US, ILookupGroupConstant.NOTIFICATION_ACTION_TYPE));
			}
			
			if(newObj != null) {
				notificationService.saveOrUpdate(newObj);
				pushNotif(newObj);
			}
		}
	}
	
	public void pushNotif(Notification obj) {
		try {
			new SpecificRestCaller<Notification>(RestConstant.WEB_ADMIN_SERVICE, RestConstant.RM_WEB_SOCKET, Notification.class).performPost("/notify", obj);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
}
