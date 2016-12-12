package id.padiku.app.service.report;

import id.padiku.app.AbstractHibernateDAO;
import id.padiku.app.SystemConstant;
import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.report.JasperExporter;
import id.padiku.app.report.ReportUtils;
import id.padiku.app.service.ReportService;
import id.padiku.app.service.master.ICompanyService;
import id.padiku.app.util.StringFunction;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.business.report.ViewCashFlow;
import id.padiku.app.valueobject.master.Company;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReportCashFlowService implements ReportService<ViewCashFlow> {
	@Autowired
	AbstractHibernateDAO<ViewCashFlow, Long> reportDAO;
	@Autowired
	private ICompanyService companyService;
	
	@Override
	public Collection<ViewCashFlow> findReportCollection(Object[] params) throws SystemException {
		return null;
	}

	@Override
	public void updateExportedObjects(List<ViewCashFlow> objects) throws SystemException {}

	@Override
	public PagingWrapper<ViewCashFlow> findAllWithPagingWrapper(
			int startIndex, int maxRow, List<SearchFilter> searchFilters,
			List<SearchOrder> searchOrders)
					throws SystemException {
		return reportDAO.findAllWithPagingWrapper(startIndex, maxRow, searchFilters, searchOrders, null);
	}

	@Override
	public List<ViewCashFlow> findAll(
			List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		return reportDAO.findAll(searchFilters, searchOrders, null);
	}

	private String getCompanyName (List<ViewCashFlow> beans) {
		String companyName = "";
		if(beans != null && beans.size() > 0) {
			try {				
				for(ViewCashFlow item : beans) {
					Long pkCompany = item.getFkCompany();
					Company c = companyService.findById(pkCompany);
					companyName = c.getName();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return companyName;
	}
	
	@Override
	public void exportReport(String fileName, String jasperFile, List<ViewCashFlow> listBean, int exportTo, Map<String, Object> params) throws JRException, FileNotFoundException {
		try {
			JasperExporter jex = new JasperExporter(jasperFile);
			Map<String,Object> model = new HashMap<String,Object>();
			model.put(JasperExporter.DATA_SOURCE, listBean);
			model.put("companyName", getCompanyName(listBean));
			model.putAll(params);
			if(exportTo == ReportUtils.EXPORT_TO_PDF){
				jex.exportPdf(model, fileName);
			}else{
				model.put(JRParameter.IS_IGNORE_PAGINATION, true);
				jex.exportXlsReport(model, fileName);
			}
		} catch (JRException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public String exportReport(List<ViewCashFlow> listBean, int exportTo) throws JRException, FileNotFoundException {
		return null;
	}

	@Override
	public ViewCashFlow findById(Long id) throws SystemException {
		return reportDAO.findByPK(id);
	}

	@Override
	public void saveOrUpdate(ViewCashFlow anObject) throws SystemException {}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {}

	@Override
	public List<ViewCashFlow> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<ViewCashFlow> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		for(SearchFilter itemFilter : filter) {
			if (itemFilter.getFieldName().equals(ViewCashFlow.TRANS_DATE)) {
				try{
					itemFilter.setValue(StringFunction.string2Date(itemFilter.getValue().toString(), SystemConstant.DATABASE_DATE_FORMAT_STD));
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return reportDAO.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}
	
}
