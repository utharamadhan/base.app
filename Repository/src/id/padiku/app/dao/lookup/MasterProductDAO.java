package id.padiku.app.dao.lookup;

import id.padiku.app.AbstractHibernateDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.MasterProduct;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class MasterProductDAO extends AbstractHibernateDAO<MasterProduct,Long> implements IMasterProductDAO {

	@Override
	public MasterProduct findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(MasterProduct anObject) throws SystemException {
		if(anObject.getPkMasterProduct()==null)
			super.create(anObject);
		else
		    super.update(anObject);	
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
	}

	@Override
	public List<MasterProduct> findObjects(Long[] objectPKs)
			throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<MasterProduct> findAllByFilter(int startNo,
			int offset, List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

}
