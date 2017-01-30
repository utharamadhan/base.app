package id.base.app.service.contact;

import id.base.app.dao.contact.IContactDAO;
import id.base.app.dao.lookup.ILookupDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.contact.Contact;
import id.base.app.valueobject.notification.NotificationEvent;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ContactService implements IContactService {
	
	@Autowired
    private ApplicationEventPublisher publisher;
	@Autowired
	private ILookupDAO lookupDAO;
	@Autowired
	private IContactDAO contactDAO;
	
	@Override
	@Transactional(readOnly=true)
	public Contact findById(Long id) throws SystemException {
		Contact contact = contactDAO.findById(id);
		return contact;
	}

	@Override
	public void saveOrUpdate(Contact anObject) throws SystemException {
		contactDAO.saveOrUpdate(anObject);
		publisher.publishEvent(new NotificationEvent(anObject));
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		contactDAO.delete(objectPKs);
	}

	@Override
	public List<Contact> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	@Deprecated
	public PagingWrapper<Contact> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return contactDAO.findAllByFilter(startNo, offset, filter, order);
	}
	
	@Override
	public List<Contact> findAll(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		return contactDAO.findAll(filter, order);
	}

	@Override
	public List<Contact> getLatestContactUs() throws SystemException {
		return contactDAO.getLatestContactUs();
	}

	@Override
	public Integer countUnreadMessage() throws SystemException {
		return contactDAO.countUnreadMessage();
	}

}