package id.padiku.app.service.login;

import id.padiku.app.exception.SystemException;
import id.padiku.app.valueobject.RuntimeUserLogin;

public interface LoginDirectoryService {
	
	public void register(RuntimeUserLogin userLogin) throws SystemException ;
	public void unregister(Long userPK) throws SystemException ;
	public RuntimeUserLogin findById(Long id) throws SystemException;
	public void unregisterAll() throws SystemException;
	public abstract void unreqisterExpiredLoginSession()
			throws SystemException;
	public RuntimeUserLogin findByAccessInfoId(String accessInfoId) throws SystemException;
	public abstract RuntimeUserLogin findByUserName(String userName) throws SystemException;

}
