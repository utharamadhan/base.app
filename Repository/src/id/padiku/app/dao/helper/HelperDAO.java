package id.padiku.app.dao.helper;

import id.padiku.app.AbstractHibernateDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.Helper;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class HelperDAO extends AbstractHibernateDAO<Helper,Long> implements IHelperDAO {

	public Helper findById(Long lookupId) throws SystemException {
		return super.findByPK(lookupId);
	}

	public void saveOrUpdate(Helper lookup) throws SystemException {
		if(lookup.getPkHelper()==null)
			super.create(lookup);
		else
		    super.update(lookup);
	}
	
	public PagingWrapper<Helper> findAllHelper(int startNo,
			int offset, List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

	@Override
	public List<Helper> findAll(List<SearchFilter> filters,
			List<SearchOrder> orders) throws SystemException {
		return super.findAll(filters, orders, null);
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
	}

	@Override
	public List<Helper> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<Helper> findAllByFilter(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return null;
	}
}
