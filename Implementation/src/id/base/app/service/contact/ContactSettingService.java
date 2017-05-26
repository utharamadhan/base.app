package id.base.app.service.contact;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.base.app.dao.contact.IContactSettingDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.contact.ContactSetting;

@Service
@Transactional
public class ContactSettingService implements IContactSettingService {

	@Autowired
	private IContactSettingDAO contactSettingDAO;
    
	public PagingWrapper<ContactSetting> findAll(int startNo, int offset) throws SystemException {
		return null;
	}

	public ContactSetting findById(Long id) throws SystemException {
		return contactSettingDAO.findById(id);
	}

	public void saveOrUpdate(ContactSetting anObject) throws SystemException {
		contactSettingDAO.saveOrUpdate(anObject);
	}

	public void delete(Long[] objectPKs) throws SystemException {
		contactSettingDAO.delete(objectPKs);
	}

	public PagingWrapper<ContactSetting> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return contactSettingDAO.findAllByFilter(startNo, offset, filter, order);
	}
	
	@Override
	public List<ContactSetting> findObjects(Long[] objectPKs) throws SystemException {
		List<ContactSetting> objects = new ArrayList<>();
		ContactSetting object = null;
		for(Long l:objectPKs){
			object = contactSettingDAO.findById(l);
			objects.add(object);
		}
		return objects;
	}

	@Override
	public List<ContactSetting> findAll(List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return contactSettingDAO.findAll(filter, order);
	}

}
