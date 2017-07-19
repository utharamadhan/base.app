package id.base.app.dao.learning;

import id.base.app.AbstractHibernateDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.learning.VWLearningItem;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class VWLearningItemDAO extends AbstractHibernateDAO<VWLearningItem, Long> implements IVWLearningItemDAO {

	@Override
	public VWLearningItem findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(VWLearningItem anObject) throws SystemException {
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
	}

	@Override
	public List<VWLearningItem> findObjects(Long[] objectPKs)
			throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<VWLearningItem> findAllByFilter(int startNo,
			int offset, List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

}
