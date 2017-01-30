package id.base.app.dao.event;

import id.base.app.AbstractHibernateDAO;
import id.base.app.SystemConstant;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.DateTimeFunction;
import id.base.app.util.dao.Operator;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.util.dao.SearchOrder.Sort;
import id.base.app.valueobject.event.Event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class EventDAO extends AbstractHibernateDAO<Event, Long> implements IEventDAO {

	@Override
	public Event findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(Event anObject) throws SystemException {
		if (anObject.getPkEvent()==null) {
			super.create(anObject);
		} else {
		    super.update(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		List<Event> objectList = new ArrayList<>();
		for(int i=0;i<objectPKs.length;i++){
			Event object = new Event();
			object = findById(objectPKs[i]);
			objectList.add(object);
		}
		super.delete(objectList);
	}

	@Override
	public List<Event> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<Event> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

	@Override
	public List<Event> findArchived(List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		filter = filter == null ? new ArrayList<SearchFilter>() : filter;
		order = order == null ? new ArrayList<SearchOrder>() : order;
		String now = DateTimeFunction.date2String(new Date(), SystemConstant.SYSTEM_TIME_MASK);
		Date currentDate = DateTimeFunction.string2Date(now, SystemConstant.SYSTEM_TIME_MASK);
		filter.add(new SearchFilter(Event.EVENT_DATE, Operator.LESS_THAN, currentDate));
		order.add(new SearchOrder(Event.EVENT_DATE, Sort.DESC));
		return this.findAll(filter, order);
	}

	@Override
	public List<Event> findUpcoming(List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		filter = filter == null ? new ArrayList<SearchFilter>() : filter;
		order = order == null ? new ArrayList<SearchOrder>() : order;
		order.add(new SearchOrder(Event.EVENT_DATE, Sort.DESC));
		return this.findAll(filter, order);
	}

}