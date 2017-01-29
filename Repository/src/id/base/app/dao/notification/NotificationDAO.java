package id.base.app.dao.notification;

import id.base.app.AbstractHibernateDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.notification.Notification;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationDAO extends AbstractHibernateDAO<Notification, Long> implements INotificationDAO {

	@Override
	public Notification findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(Notification anObject) throws SystemException {
		if (anObject.getPkNotification()==null) {
			super.create(anObject);
		} else {
		    super.update(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		List<Notification> objectList = new ArrayList<>();
		for(int i=0;i<objectPKs.length;i++){
			Notification object = new Notification();
			object = findById(objectPKs[i]);
			objectList.add(object);
		}
		super.delete(objectList);
	}

	@Override
	public List<Notification> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<Notification> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

	@Override
	public List<Notification> getLastFiveNotifications() throws SystemException {
		Criteria crit = getSession().createCriteria(domainClass);
			crit.addOrder(Order.desc("actionDate"));
			crit.setMaxResults(5);
		return crit.list();
	}

	@Override
	public Integer countUnreadNotifications() throws SystemException {
		Criteria crit = getSession().createCriteria(domainClass);
			crit.add(Restrictions.eq("isRead", Boolean.FALSE));
			crit.setProjection(Projections.rowCount());
		Long rowCount = (Long) crit.uniqueResult();
		return rowCount != null ? rowCount.intValue() : 0;
	}

}
