package id.padiku.app.service.passwordhistory;

import id.padiku.app.exception.SystemException;
import id.padiku.app.valueobject.PasswordHistory;

import java.util.List;

public interface IPasswordHistoryService {
	
	public boolean isPasswordExistHistory(String Password,Long fkAppUser) throws SystemException;
	public void addHistory(String password, Long fkAppUser) throws SystemException;
	public abstract List<PasswordHistory> getTotalPasswordByfkAppuser(Long fkAppUser) throws SystemException;
}
