package id.base.app.valueobject.notification;

import id.base.app.valueobject.contact.Contact;

import org.springframework.context.ApplicationEvent;

public class NotificationEvent extends ApplicationEvent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7286313007724222316L;
	
	public NotificationEvent(Contact source) {
		super(source);
	}
	
}
