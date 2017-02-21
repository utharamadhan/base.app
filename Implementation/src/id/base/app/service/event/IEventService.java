package id.base.app.service.event;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.event.Event;

import java.util.List;

public interface IEventService extends MaintenanceService<Event> {

	public List<Event> findArchived(List<SearchFilter> filter, List<SearchOrder> order) throws SystemException;
	
	public List<Event> findUpcoming(List<SearchFilter> filter, List<SearchOrder> order) throws SystemException;

	public List<Event> findLatestEventUpcoming(int number) throws SystemException;
}
