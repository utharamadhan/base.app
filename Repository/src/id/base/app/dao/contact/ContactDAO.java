package id.base.app.dao.contact;

import java.util.List;

import org.springframework.stereotype.Repository;

import id.base.app.AbstractHibernateDAO;
import id.base.app.SystemParameter;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.AppParameter;
import id.base.app.valueobject.contact.Contact;

@Repository
public class ContactDAO extends AbstractHibernateDAO<Contact,Long> implements IContactDAO {

	public void delete(Long[] objectPKs) throws SystemException {
		for(Long objectPK : objectPKs){
			Contact obj = findById(objectPK);
			super.delete(obj);
		}
	}
	
	public List<Contact> findAll(){
		return super.findAll();
	}

	public void saveOrUpdate(AppParameter anObject) throws SystemException {
    	 super.update(anObject);
    	 SystemParameter.updateSystemEnvironment(anObject.getName(), anObject.getValue());
	}

	public PagingWrapper<Contact> findAllParameterWithFilter(int startNo,
			int offset, List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

	@Override
	public Contact findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(Contact anObject) throws SystemException {
		if(anObject.getPkContact()!=null){
			super.update(anObject);
		}else{
			super.create(anObject);
		}
	}

	@Override
	public List<Contact> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<Contact> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

}