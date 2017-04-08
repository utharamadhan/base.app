package id.base.app.service.report;

import id.base.app.dao.report.IVWResearchDevelopmentReportDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.report.VWResearchDevelopmentReport;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VWResearchDevelopmentReportService implements IVWResearchDevelopmentReportService {

	@Autowired
	private IVWResearchDevelopmentReportDAO vwResearchDevelopmentReportDAO;
	
	@Override
	public VWResearchDevelopmentReport findById(Long id) throws SystemException {
		return null;
	}

	@Override
	public void saveOrUpdate(VWResearchDevelopmentReport anObject) throws SystemException {
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
	}

	@Override
	public List<VWResearchDevelopmentReport> findObjects(Long[] objectPKs)
			throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<VWResearchDevelopmentReport> findAllByFilter(int startNo,
			int offset, List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return vwResearchDevelopmentReportDAO.findAllByFilter(startNo, offset, filter, order);
	}

	@Override
	public List<VWResearchDevelopmentReport> findAll(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		return vwResearchDevelopmentReportDAO.findAll(filter, order);
	}
	
}