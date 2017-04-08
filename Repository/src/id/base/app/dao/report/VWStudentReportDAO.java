package id.base.app.dao.report;

import id.base.app.AbstractHibernateDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.report.VWStudentReport;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class VWStudentReportDAO extends AbstractHibernateDAO<VWStudentReport, Long> implements IVWStudentReportDAO {

	public void delete(Long[] objectPKs) throws SystemException {}
	
	public List<VWStudentReport> findAll(){
		return super.findAll();
	}

	public PagingWrapper<VWStudentReport> findAllParameterWithFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

	@Override
	public VWStudentReport findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(VWStudentReport anObject) throws SystemException {}

	@Override
	public List<VWStudentReport> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<VWStudentReport> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

}