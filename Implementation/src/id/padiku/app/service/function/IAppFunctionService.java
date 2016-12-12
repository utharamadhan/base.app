package id.padiku.app.service.function;

import id.padiku.app.exception.SystemException;
import id.padiku.app.valueobject.AppFunction;
import id.padiku.app.valueobject.AppRole;

import java.util.List;

public interface IAppFunctionService {
	
	public List<AppFunction> findAllAppFunction();

	public List<AppFunction> findAppFunctionByPermission(Long appRole) throws SystemException;
	
	public abstract List<AppFunction> findAppFunctionMenuByUserRole(Long userRole) 
	    throws SystemException;

	public List<AppFunction> findAppFunctionByPermissionList(List<AppRole> appRoles)throws SystemException;

	public List<AppFunction> findAppFunctionMenuByUserRoles(List<AppRole> appRoles)
			throws SystemException;
	
	public abstract List<AppFunction> findAppFunctionByAppRole(Long pkAppRole) throws SystemException;
}