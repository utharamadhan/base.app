package id.padiku.app.dao.lookup;

import id.padiku.app.IBaseDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.LookupGroup;

import java.util.List;

public interface ILookupGroupDAO extends IBaseDAO<LookupGroup>{
	
	public PagingWrapper<LookupGroup> findAllLookupGroup(
			int startNo, int offset) throws SystemException ;

	PagingWrapper<LookupGroup> findAllLookupGroup(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException;
	
	public LookupGroup findByName(String name) throws SystemException;

	public abstract boolean checkUpdatableByLookupPK(Long pk)
			throws SystemException;

	public abstract boolean checkUpdatableByGroupName(String name)
			throws SystemException;

}
