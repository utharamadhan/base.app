package id.base.app.dao.publication;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import id.base.app.AbstractHibernateDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.publication.DigitalBook;

@Repository
public class DigitalBookDAO extends AbstractHibernateDAO<DigitalBook, Long> implements IDigitalBookDAO {

	@Override
	public DigitalBook findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(DigitalBook anObject) throws SystemException {
		if (anObject.getPkDigitalBook()==null) {
			super.create(anObject);
		} else {
		    super.update(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		List<DigitalBook> objectList = new ArrayList<>();
		for(int i=0;i<objectPKs.length;i++){
			DigitalBook object = DigitalBook.getInstance();
			object = findById(objectPKs[i]);
			objectList.add(object);
		}
		super.delete(objectList);
	}

	@Override
	public List<DigitalBook> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<DigitalBook> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

	@Override
	public List<DigitalBook> findLatestEbook(int number) throws SystemException {
		Criteria crit = this.getSession().createCriteria(domainClass);
		crit.addOrder(Order.desc(DigitalBook.PK_DIGITAL_BOOK));
		crit.setMaxResults(number);
		return crit.list();
	}
	
	

}
