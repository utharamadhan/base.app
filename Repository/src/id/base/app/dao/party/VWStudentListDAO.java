package id.base.app.dao.party;

import id.base.app.AbstractHibernateDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.party.VWStudentList;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class VWStudentListDAO extends AbstractHibernateDAO<VWStudentList, Long> implements IVWStudentListDAO {

	public void delete(Long[] objectPKs) throws SystemException {}
	
	public List<VWStudentList> findAll(){
		return super.findAll();
	}

	public PagingWrapper<VWStudentList> findAllParameterWithFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

	@Override
	public VWStudentList findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(VWStudentList anObject) throws SystemException {}

	@Override
	public List<VWStudentList> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<VWStudentList> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

}