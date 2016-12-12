package id.padiku.app.dao.parameter;

import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.AppParameter;

import java.util.List;
import java.util.Map;

public interface IAppParameterDAO {
	
	public abstract void delete(Long[] objectPKs) throws SystemException;
	
	public abstract PagingWrapper<AppParameter> findAllParameterWithFilter(int startNo,
			int offset,List<SearchFilter> filter,List<SearchOrder> order) throws SystemException;

	public abstract AppParameter findAppParameterById(Long id)
			throws SystemException;
	
	public abstract List<AppParameter> findAll()
	throws SystemException;

	public abstract void saveOrUpdate(AppParameter anObject)
			throws SystemException;

	public abstract AppParameter findAppParameterByName(String name)
			throws SystemException;

	public abstract Map<String,String> findParameterPairValue(String likeName)
			throws SystemException;

	public abstract List<AppParameter> findParametersByNames(List<SearchFilter> names)
			throws SystemException;
}