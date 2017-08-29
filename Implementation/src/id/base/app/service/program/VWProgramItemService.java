package id.base.app.service.program;

import id.base.app.dao.program.IVWProgramItemDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.program.VWProgramItem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VWProgramItemService implements IVWProgramItemService {

	@Autowired
	private IVWProgramItemDAO vwProgramItemDAO;
	
	@Override
	public VWProgramItem findById(Long id) throws SystemException {
		return vwProgramItemDAO.findById(id);
	}

	@Override
	public void saveOrUpdate(VWProgramItem anObject) throws SystemException {
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
	}

	@Override
	public List<VWProgramItem> findObjects(Long[] objectPKs)
			throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<VWProgramItem> findAllByFilter(int startNo,
			int offset, List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return vwProgramItemDAO.findAllByFilter(startNo, offset, filter, order);
	}

	@Override
	public List<VWProgramItem> findAll(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		return vwProgramItemDAO.findAll(filter, order);
	}
}