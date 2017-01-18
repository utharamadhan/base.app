package id.base.app.service.event;

import id.base.app.dao.event.IEventDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.event.Event;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EventService implements IEventService {

	@Autowired
	private IEventDAO eventDAO;
    
	public PagingWrapper<Event> findAll(int startNo, int offset) throws SystemException {
		return null;
	}

	public Event findById(Long id) throws SystemException {
		return eventDAO.findById(id);
	}

	public void saveOrUpdate(Event anObject) throws SystemException {
		eventDAO.saveOrUpdate(anObject);
	}

	public void delete(Long[] objectPKs) throws SystemException {
		eventDAO.delete(objectPKs);
	}

	public PagingWrapper<Event> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return eventDAO.findAllByFilter(startNo, offset, filter, order);
	}
	
	@Override
	public List<Event> findObjects(Long[] objectPKs) throws SystemException {
		List<Event> objects = new ArrayList<>();
		Event object = null;
		for(Long l:objectPKs){
			object = eventDAO.findById(l);
			objects.add(object);
		}
		return objects;
	}

	@Override
	public List<Event> findAll(List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return eventDAO.findAll(filter, order);
	}

	@Override
	public List<Event> findArchived(List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return eventDAO.findArchived(filter, order);
	}

	@Override
	public List<Event> findUpcoming(List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return eventDAO.findUpcoming(filter, order);
	}

}
