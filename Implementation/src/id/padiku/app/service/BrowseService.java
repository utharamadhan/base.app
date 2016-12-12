package id.padiku.app.service;

import id.padiku.app.exception.SystemException;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.AppUser;

import java.util.List;

public interface BrowseService<T> {

	public abstract List<T> findDestinations(Long pk) throws SystemException;
	public abstract void save(Long browsePK,long[] destinationPKs) throws SystemException;
	public abstract List<T> findByFilter(List<SearchFilter> searchFilter , List<SearchOrder> searchOrders) ;
	public abstract List<AppUser> findAllByFilter(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException;
	
}
