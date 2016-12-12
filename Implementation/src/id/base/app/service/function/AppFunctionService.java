package id.base.app.service.function;

import id.base.app.dao.appfunction.IAppFunctionDAO;
import id.base.app.exception.SystemException;
import id.base.app.valueobject.AppFunction;
import id.base.app.valueobject.AppRole;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AppFunctionService implements IAppFunctionService  {

	@Autowired
	private IAppFunctionDAO appFunctionDao;
	
	public List<AppFunction> findAllAppFunction() throws SystemException {
		return appFunctionDao.findAllAppFunction();
	}
	
	
	
	public List<AppFunction> findAppFunctionMenuByUserRole(Long userRole) throws SystemException{
		return appFunctionDao.findAppFunctionMenuByUserRole(userRole);
	}

	public void setAppFunctionDao(IAppFunctionDAO appFunctionDao) {
		this.appFunctionDao = appFunctionDao;
	}

	@Override
	public List<AppFunction> findAppFunctionByPermission(Long appRole)throws SystemException {
		 return appFunctionDao.findAppFunctionByPermission(appRole);
	}
	
	@Override
	public List<AppFunction> findAppFunctionMenuByUserRoles(List<AppRole> userRole)throws SystemException {
		List<AppFunction> list = new ArrayList<AppFunction>();
		for( AppRole a: userRole){
			list.addAll(appFunctionDao.findAppFunctionMenuByUserRole(a.getPkAppRole()));
		}
		return list;
	}

	@Override
	public List<AppFunction> findAppFunctionByPermissionList(List<AppRole> appRoles)throws SystemException {
		List<AppFunction> list = new ArrayList<AppFunction>();
		for( AppRole a: appRoles){
			list.addAll(appFunctionDao.findAppFunctionByPermission(a.getPkAppRole()));
		}
		return list;
	}
	
	public List<AppFunction> findAppFunctionByAppRole(Long pkAppRole) throws SystemException {
		return appFunctionDao.findAppFunctionByAppRole(pkAppRole);
	}
}
