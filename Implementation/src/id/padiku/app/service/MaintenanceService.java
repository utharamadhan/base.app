package id.padiku.app.service;

import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;

import java.util.List;

public interface MaintenanceService<T> {
	
	public T findById(Long id) throws SystemException  ;
	
	public void saveOrUpdate(T anObject) throws SystemException ;
	
	public void delete(Long[] objectPKs) throws SystemException ;
	
	public List<T> findObjects(Long[] objectPKs)throws SystemException;
	
	public PagingWrapper<T> findAllByFilter(int startNo, int offset,List<SearchFilter> filter,List<SearchOrder> order) throws SystemException ;
	
	public List<T> findAll(List<SearchFilter> filter,List<SearchOrder> order) throws SystemException ;
	
}
