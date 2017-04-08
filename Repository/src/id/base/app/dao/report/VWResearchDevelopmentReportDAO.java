package id.base.app.dao.report;

import id.base.app.AbstractHibernateDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.report.VWResearchDevelopmentReport;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class VWResearchDevelopmentReportDAO extends AbstractHibernateDAO<VWResearchDevelopmentReport, Long> implements IVWResearchDevelopmentReportDAO {

	public void delete(Long[] objectPKs) throws SystemException {}
	
	public List<VWResearchDevelopmentReport> findAll(){
		return super.findAll();
	}

	public PagingWrapper<VWResearchDevelopmentReport> findAllParameterWithFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

	@Override
	public VWResearchDevelopmentReport findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(VWResearchDevelopmentReport anObject) throws SystemException {}

	@Override
	public List<VWResearchDevelopmentReport> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<VWResearchDevelopmentReport> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

}