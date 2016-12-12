package id.padiku.app.business.job.task;

import id.padiku.app.mail.MailManager;
import id.padiku.app.valueobject.AppUser;


public class UserPasswordEmailNotificationTask implements Runnable {
	
	private MailManager<AppUser> mailManager;
	private AppUser user;
	
	public UserPasswordEmailNotificationTask(MailManager<AppUser> mailManager,AppUser user){
		this.mailManager=mailManager;
		this.user=user;
	}
	@Override
	public void run() {
//		mailManager.sendMail(user.getEmail(), MailTemplate.PASSWORD_EXPIRED_NOTIFICATION_MAIL, user);
	}

}
