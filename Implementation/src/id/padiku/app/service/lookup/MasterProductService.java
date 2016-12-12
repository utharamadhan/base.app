package id.padiku.app.service.lookup;

import id.padiku.app.SystemConstant;
import id.padiku.app.dao.lookup.IMasterProductDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.util.dao.Operator;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.util.dao.SearchOrder.Sort;
import id.padiku.app.valueobject.MasterProduct;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MasterProductService implements IMasterProductService {

	@Autowired
	private IMasterProductDAO masterProductDAO;
	
	@Override
	public MasterProduct findById(Long id) throws SystemException {
		return masterProductDAO.findById(id);
	}

	@Override
	public void saveOrUpdate(MasterProduct anObject) throws SystemException {
		masterProductDAO.saveOrUpdate(anObject);
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		masterProductDAO.delete(objectPKs);
	}

	@Override
	public List<MasterProduct> findObjects(Long[] objectPKs)
			throws SystemException {
		return masterProductDAO.findObjects(objectPKs);
	}

	@Override
	public PagingWrapper<MasterProduct> findAllByFilter(int startNo,
			int offset, List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return masterProductDAO.findAllByFilter(startNo, offset, filter, order);
	}

	@Override
	public List<MasterProduct> findAll(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		return masterProductDAO.findAll(filter, order);
	}
	
	@Override
	public List<MasterProduct> findAllActive(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		filter.add(new SearchFilter("status", Operator.EQUALS, SystemConstant.ValidFlag.VALID, Integer.class));
		order.add(new SearchOrder("name", Sort.ASC));
		return masterProductDAO.findAll(filter, order);
	}
}