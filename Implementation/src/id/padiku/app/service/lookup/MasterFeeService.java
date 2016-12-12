package id.padiku.app.service.lookup;

import id.padiku.app.SystemConstant;
import id.padiku.app.dao.lookup.IMasterFeeDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.util.dao.Operator;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.util.dao.SearchOrder.Sort;
import id.padiku.app.valueobject.MasterFee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import softtech.hong.hce.core.QueryTransformer;

@Service
@Transactional	
public class MasterFeeService extends QueryTransformer<MasterFee> implements IMasterFeeService {

	@Autowired
	private IMasterFeeDAO masterFeeDAO;
	
	@Override
	public MasterFee findById(Long id) throws SystemException {
		return masterFeeDAO.findById(id);
	}

	@Override
	public void saveOrUpdate(MasterFee anObject) throws SystemException {
		masterFeeDAO.saveOrUpdate(anObject);
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		masterFeeDAO.delete(objectPKs);
	}

	@Override
	public List<MasterFee> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<MasterFee> findAllByFilter(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return masterFeeDAO.findAllByFilter(startNo, offset, filter, order);
	}

	@Override
	public List<MasterFee> findAll(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		return null;
	}
	
	@Override
	public List<MasterFee> findAllActive(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		filter.add(new SearchFilter("status", Operator.EQUALS, SystemConstant.ValidFlag.VALID, Integer.class));
		order.add(new SearchOrder("descr", Sort.ASC));
		return masterFeeDAO.findAll(filter, order);
	}
}
