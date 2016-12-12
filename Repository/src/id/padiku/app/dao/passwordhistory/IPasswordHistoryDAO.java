package id.padiku.app.dao.passwordhistory;

import id.padiku.app.exception.SystemException;
import id.padiku.app.valueobject.PasswordHistory;

import java.util.List;

public interface IPasswordHistoryDAO {
	
	public abstract void saveOrUpdate(PasswordHistory anObject)throws SystemException;	
	public abstract boolean isPasswordExistsHistory(String password,Long fkAppUser) throws SystemException;
	public abstract List<PasswordHistory> getTotalPasswordByfkAppuser(Long fkAppUser) throws SystemException;
	public abstract List<PasswordHistory> getTotalPasswordByPasswordAndfkAppuser(String password, Long fkAppUser)throws SystemException;
	public void delete(PasswordHistory history);

}
