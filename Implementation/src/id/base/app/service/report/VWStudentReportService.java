package id.base.app.service.report;

import id.base.app.dao.report.IVWStudentReportDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.report.VWStudentReport;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VWStudentReportService implements IVWStudentReportService {

	@Autowired
	private IVWStudentReportDAO vwStudentReportDAO;
	
	@Override
	public VWStudentReport findById(Long id) throws SystemException {
		return null;
	}

	@Override
	public void saveOrUpdate(VWStudentReport anObject) throws SystemException {
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
	}

	@Override
	public List<VWStudentReport> findObjects(Long[] objectPKs)
			throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<VWStudentReport> findAllByFilter(int startNo,
			int offset, List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return vwStudentReportDAO.findAllByFilter(startNo, offset, filter, order);
	}

	@Override
	public List<VWStudentReport> findAll(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		return vwStudentReportDAO.findAll(filter, order);
	}
	
}