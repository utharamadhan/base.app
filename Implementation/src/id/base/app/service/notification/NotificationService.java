package id.base.app.service.notification;

import id.base.app.dao.notification.INotificationDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.notification.Notification;
import id.base.app.valueobject.notification.NotificationEvent;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@Transactional
public class NotificationService implements INotificationService {

	@Autowired
	private INotificationDAO notificationDAO;
	
	@TransactionalEventListener(phase=TransactionPhase.AFTER_COMMIT)
	public void notify(NotificationEvent event) {
		System.out.println(event);
	}
    
	public PagingWrapper<Notification> findAll(int startNo, int offset) throws SystemException {
		return null;
	}

	public Notification findById(Long id) throws SystemException {
		return notificationDAO.findById(id);
	}

	public void saveOrUpdate(Notification anObject) throws SystemException {
		notificationDAO.saveOrUpdate(anObject);
	}

	public void delete(Long[] objectPKs) throws SystemException {
		notificationDAO.delete(objectPKs);
	}

	public PagingWrapper<Notification> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return notificationDAO.findAllByFilter(startNo, offset, filter, order);
	}
	
	@Override
	public List<Notification> findObjects(Long[] objectPKs) throws SystemException {
		List<Notification> objects = new ArrayList<>();
		Notification object = null;
		for(Long l:objectPKs){
			object = notificationDAO.findById(l);
			objects.add(object);
		}
		return objects;
	}

	@Override
	public List<Notification> findAll(List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return notificationDAO.findAll(filter, order);
	}

}
