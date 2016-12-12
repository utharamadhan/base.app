package id.padiku.app.dao.role;

import id.padiku.app.exception.SystemException;
import id.padiku.app.valueobject.UserGroupAccessNode;

import java.util.List;

public interface IAppRoleFunctionDAO {
	public List<UserGroupAccessNode> getAccessTree(long groupPK,int userType)
			throws SystemException;

	public void deleteAccessList(Long pkAppRole) throws SystemException;
	public void insertAccessList(Long pkAppRole, int[] pkAppFunction);
}
