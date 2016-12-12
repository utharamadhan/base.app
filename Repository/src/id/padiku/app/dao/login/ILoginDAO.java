package id.padiku.app.dao.login;

import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.RuntimeUserLogin;

import java.util.List;

public interface ILoginDAO {	
	public abstract void delete(Long[] objectPKs) throws SystemException;
	
	public abstract PagingWrapper<RuntimeUserLogin> findAllRuntimeUserLoginByFilter(
		int startNo, int offset,List<SearchFilter> filter,List<SearchOrder> searchOrder) throws SystemException;
	
	
	
	public abstract RuntimeUserLogin findRuntimeUserLoginById(Long id)
		throws SystemException;
	
	
	public abstract void saveOrUpdate(RuntimeUserLogin anObject)
		throws SystemException;

	public abstract void deleteAll();

	public abstract void deleteExpiredUsers();
	
	public RuntimeUserLogin findByAccessInfoId(String accessInfoId) throws SystemException;

	public abstract RuntimeUserLogin findByUserName(String userName) throws SystemException;
}
