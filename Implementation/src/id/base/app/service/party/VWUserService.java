package id.base.app.service.party;

import id.base.app.dao.party.IVWUserDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.party.VWUser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VWUserService implements IVWUserService {

	@Autowired
	private IVWUserDAO vwTeacherDAO;
	
	@Override
	public VWUser findById(Long id) throws SystemException {
		return vwTeacherDAO.findById(id);
	}

	@Override
	public void saveOrUpdate(VWUser anObject) throws SystemException {
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
	}

	@Override
	public List<VWUser> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<VWUser> findAllByFilter(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return vwTeacherDAO.findAllByFilter(startNo, offset, filter, order);
	}

	@Override
	public List<VWUser> findAll(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		return vwTeacherDAO.findAll(filter, order);
	}
	
}