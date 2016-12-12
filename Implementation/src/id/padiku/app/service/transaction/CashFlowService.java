package id.padiku.app.service.transaction;

import id.padiku.app.dao.report.IReportCashFlowDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.business.report.ViewCashFlow;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CashFlowService implements ICashFlowService{

	@Autowired
	private IReportCashFlowDAO cashFlowDAO;
	
	@Override
	public ViewCashFlow findById(Long id) throws SystemException {
		return null;
	}

	@Override
	public void saveOrUpdate(ViewCashFlow anObject) throws SystemException {
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
	}

	@Override
	public List<ViewCashFlow> findObjects(Long[] objectPKs)
			throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<ViewCashFlow> findAllByFilter(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return null;
	}

	@Override
	public List<ViewCashFlow> findAll(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		return null;
	}
	
	@Override
	public List<ViewCashFlow> getCashFlowFee(Long pkCompany) throws SystemException {
		return cashFlowDAO.getCashFlowFee(pkCompany); 
	}

}
