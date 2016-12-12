package id.base.app.service.lookup;

import id.base.app.SystemConstant;
import id.base.app.dao.lookup.IMasterMachineryDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.Operator;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.util.dao.SearchOrder.Sort;
import id.base.app.valueobject.MasterMachinery;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MasterMachineryService implements IMasterMachineryService {

	@Autowired
	private IMasterMachineryDAO masterMachineryDAO;
	
	@Override
	public MasterMachinery findById(Long id) throws SystemException {
		return masterMachineryDAO.findById(id);
	}

	@Override
	public void saveOrUpdate(MasterMachinery anObject) throws SystemException {
		masterMachineryDAO.saveOrUpdate(anObject);
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		masterMachineryDAO.delete(objectPKs);
	}

	@Override
	public List<MasterMachinery> findObjects(Long[] objectPKs)
			throws SystemException {
		return masterMachineryDAO.findObjects(objectPKs);
	}

	@Override
	public PagingWrapper<MasterMachinery> findAllByFilter(int startNo,
			int offset, List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return masterMachineryDAO.findAllByFilter(startNo, offset, filter, order);
	}

	@Override
	public List<MasterMachinery> findAll(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		return masterMachineryDAO.findAll(filter, order);
	}
	
	@Override
	public List<MasterMachinery> findAllActive(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		filter.add(new SearchFilter("status", Operator.EQUALS, SystemConstant.ValidFlag.VALID, Integer.class));
		order.add(new SearchOrder("name", Sort.ASC));
		return masterMachineryDAO.findAll(filter, order);
	}

}
