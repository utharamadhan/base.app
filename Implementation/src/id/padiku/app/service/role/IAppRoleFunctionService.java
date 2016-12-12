package id.padiku.app.service.role;

import id.padiku.app.exception.SystemException;
import id.padiku.app.valueobject.UserGroupAccessNode;

import java.util.List;

public interface IAppRoleFunctionService {
	public List<UserGroupAccessNode> getAccessTree(Long pkAppRole,Integer userType) throws SystemException;
	public void updateAccessibilities(Long pkAppRole, int pkAppFunction[]);
}
