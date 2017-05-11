package id.base.app.dao.publication;

import id.base.app.AbstractHibernateDAO;
import id.base.app.ILookupConstant;
import id.base.app.SystemConstant;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.DateTimeFunction;
import id.base.app.util.dao.Operator;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.util.dao.SearchOrder.Sort;
import id.base.app.valueobject.publication.Event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
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
		filter.add(new SearchFilter(Event.STATUS, Operator.EQUALS, ILookupConstant.ArticleStatus.PUBLISH, Integer.class));
		filter.add(new SearchFilter(Event.EVENT_DATE, Operator.LESS_THAN, currentDate));
		order.add(new SearchOrder(Event.EVENT_DATE, Sort.DESC));
		return this.findAll(filter, order);
	}

	@Override
	public List<Event> findUpcoming(List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		filter = filter == null ? new ArrayList<SearchFilter>() : filter;
		filter.add(new SearchFilter(Event.STATUS, Operator.EQUALS, ILookupConstant.ArticleStatus.PUBLISH, Integer.class));
		order = order == null ? new ArrayList<SearchOrder>() : order;
		order.add(new SearchOrder(Event.EVENT_DATE, Sort.DESC));
		return this.findAll(filter, order);
	}
	
	@Override
	public List<Event> findLatestEventUpcoming(int number) throws SystemException {
		Date currentDate = DateTimeFunction.getCurrentDate();
		Criteria crit = this.getSession().createCriteria(domainClass);
		crit.add(Restrictions.eq(Event.STATUS, ILookupConstant.ArticleStatus.PUBLISH));
		crit.add(Restrictions.ge(Event.EVENT_DATE, currentDate));
		crit.addOrder(Order.desc(Event.EVENT_DATE));
		crit.setMaxResults(number);
		crit.setProjection(Projections.projectionList().
				add(Projections.property(Event.PERMALINK)).
				add(Projections.property(Event.TITLE)).
				add(Projections.property(Event.COVER_IMAGE_URL)).
				add(Projections.property(Event.EVENT_DATE)));
		crit.setResultTransformer(new ResultTransformer() {
			@Override
			public Object transformTuple(Object[] tuple, String[] aliases) {
				Event obj = new Event();
				try {
					BeanUtils.copyProperty(obj, Event.PERMALINK, tuple[0]);
					BeanUtils.copyProperty(obj, Event.TITLE, tuple[1]);
					BeanUtils.copyProperty(obj, Event.COVER_IMAGE_URL, tuple[2]);
					BeanUtils.copyProperty(obj, Event.EVENT_DATE, tuple[3]);
				} catch (Exception e) {
					LOGGER.error(e.getMessage(), e);
				}
				return obj;
			}
			
			@Override
			public List transformList(List collection) {
				return collection;
			}
		});
		return crit.list();
	}
	
	@Override
	public List<String> getSamePermalink(Long pk, String permalink) throws SystemException {
		Criteria crit = getSession().createCriteria(Event.class);
		crit.add(Restrictions.like(Event.PERMALINK, permalink, MatchMode.START));
		if(pk!=null){
			crit.add(Restrictions.ne(Event.PK_EVENT, pk));
		}
		crit.setProjection(Projections.projectionList().add(Projections.property("permalink")));
		crit.setResultTransformer(new ResultTransformer() {
			@Override
			public Object transformTuple(Object[] tuple, String[] aliases) {
				String r = null;
				try{
					r = tuple[0].toString();
				}catch(Exception e){
					LOGGER.error(e.getMessage(), e);
				}
				return r;
			}
			
			@Override
			public List<?> transformList(List collection) {
				return collection;
			}
		});
		return crit.list();
	}
	
	@Override
	public Event findByPermalink(String permalink) throws SystemException {
		Criteria criteria = getSession().createCriteria(Event.class);
		criteria.add(Restrictions.eq(Event.PERMALINK, permalink));
		criteria.add(Restrictions.eq(Event.STATUS, ILookupConstant.ArticleStatus.PUBLISH));
		return (Event) criteria.uniqueResult();
	}
}