package id.padiku.app.service.user;

import id.padiku.app.exception.SystemException;
import id.padiku.app.valueobject.AppUser;

import java.util.List;

public interface IUserNotificationService {
	public List<AppUser> findNotifiyingPasswordUser()throws SystemException;
}
