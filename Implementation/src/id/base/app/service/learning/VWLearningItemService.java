package id.base.app.service.learning;

import id.base.app.dao.learning.IVWLearningItemDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.learning.VWLearningItem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VWLearningItemService implements IVWLearningItemService {

	@Autowired
	private IVWLearningItemDAO vwLearningItemDAO;
	
	@Override
	public VWLearningItem findById(Long id) throws SystemException {
		return vwLearningItemDAO.findById(id);
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
		return vwLearningItemDAO.findAllByFilter(startNo, offset, filter, order);
	}

	@Override
	public List<VWLearningItem> findAll(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		return vwLearningItemDAO.findAll(filter, order);
	}
}