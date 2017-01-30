package id.base.app.dao.event;

import java.util.List;

import id.base.app.IBaseDAO;
import id.base.app.exception.SystemException;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.event.Event;


public interface IEventDAO extends IBaseDAO<Event> {

	public List<Event> findArchived(List<SearchFilter> filter, List<SearchOrder> order) throws SystemException;
	
	public List<Event> findUpcoming(List<SearchFilter> filter, List<SearchOrder> order) throws SystemException;
}