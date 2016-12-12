package id.base.app.service.report;

import id.base.app.AbstractHibernateDAO;
import id.base.app.SystemConstant;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.report.JasperExporter;
import id.base.app.report.ReportUtils;
import id.base.app.service.ReportService;
import id.base.app.service.master.ICompanyService;
import id.base.app.util.StringFunction;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.business.report.ViewTransInReport;
import id.base.app.valueobject.master.Company;

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
public class ReportTransInService implements ReportService<ViewTransInReport> {
	@Autowired
	AbstractHibernateDAO<ViewTransInReport, Long> reportDAO;
	@Autowired
	private ICompanyService companyService;

	@Override
	public Collection<ViewTransInReport> findReportCollection(Object[] params) throws SystemException {
		return null;
	}

	@Override
	public void updateExportedObjects(List<ViewTransInReport> objects) throws SystemException {}

	@Override
	public PagingWrapper<ViewTransInReport> findAllWithPagingWrapper(
			int startIndex, int maxRow, List<SearchFilter> searchFilters,
			List<SearchOrder> searchOrders)
					throws SystemException {
		return reportDAO.findAllWithPagingWrapper(startIndex, maxRow, searchFilters, searchOrders, null);
	}

	@Override
	public List<ViewTransInReport> findAll(
			List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		return reportDAO.findAll(searchFilters, searchOrders, null);
	}
	
	private String getCompanyName (List<ViewTransInReport> beans) {
		String companyName = "";
		if(beans != null && beans.size() > 0) {
			try {				
				for(ViewTransInReport item : beans) {
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
	public void exportReport(String fileName, String jasperFile, List<ViewTransInReport> listBean, int exportTo, Map<String, Object> params) throws JRException, FileNotFoundException {
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
	public String exportReport(List<ViewTransInReport> listBean, int exportTo) throws JRException, FileNotFoundException {
		return null;
	}

	@Override
	public ViewTransInReport findById(Long id) throws SystemException {
		return reportDAO.findByPK(id);
	}

	@Override
	public void saveOrUpdate(ViewTransInReport anObject) throws SystemException {}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {}

	@Override
	public List<ViewTransInReport> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<ViewTransInReport> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		for(SearchFilter itemFilter : filter) {
			if (itemFilter.getFieldName().equals(ViewTransInReport.IN_DATE)) {
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
