package id.base.app.service.contact;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.base.app.dao.contact.IContactDAO;
import id.base.app.dao.lookup.ILookupDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.contact.Contact;

@Service
@Transactional
public class ContactService implements IContactService {
	
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

}