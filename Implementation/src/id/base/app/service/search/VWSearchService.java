package id.base.app.service.search;

import id.base.app.dao.search.IVWSearchDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.VWSearch;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VWSearchService implements IVWSearchService {

	@Autowired
	private IVWSearchDAO vwSearchDAO;

	@Override
	public VWSearch findById(Long id) throws SystemException {
		return vwSearchDAO.findById(id);
	}

	@Override
	public void saveOrUpdate(VWSearch anObject) throws SystemException {
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
	}

	@Override
	public List<VWSearch> findObjects(Long[] objectPKs) throws SystemException {
		List<VWSearch> objects = new ArrayList<>();
		VWSearch object = null;
		for(Long l:objectPKs){
			object = vwSearchDAO.findById(l);
			objects.add(object);
		}
		return objects;
	}

	@Override
	public PagingWrapper<VWSearch> findAllByFilter(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return vwSearchDAO.findAllByFilter(startNo, offset, filter, order);
	}

	@Override
	public List<VWSearch> findAll(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		return vwSearchDAO.findAll(filter, order);
	}
}