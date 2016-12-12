package id.padiku.app.service;

import id.padiku.app.exception.SystemException;
import id.padiku.app.valueobject.AppUser;

import java.util.Date;

public interface AuthenticationService<T> {
	
	public T authenticateLogin(AppUser appUser) throws SystemException ;
	public T authenticateLogin(AppUser appUser, String pass) throws SystemException ;
	public abstract boolean isNotificationPeriod(Date expiredDate, Date currentDate)throws SystemException;

}
