package id.padiku.app.service.report;

import id.padiku.app.AbstractHibernateDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.report.JasperExporter;
import id.padiku.app.report.ReportUtils;
import id.padiku.app.service.ReportService;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.business.report.ViewNewBusinessReport;

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
public class NewBusinessReportService implements ReportService<ViewNewBusinessReport> {
	@Autowired
	AbstractHibernateDAO<ViewNewBusinessReport, Long> reportDAO;

	@Override
	public Collection<ViewNewBusinessReport> findReportCollection(
			Object[] params) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateExportedObjects(
			List<ViewNewBusinessReport> objects)
					throws SystemException {
		// TODO Auto-generated method stub

	}

	@Override
	public PagingWrapper<ViewNewBusinessReport> findAllWithPagingWrapper(
			int startIndex, int maxRow, List<SearchFilter> searchFilters,
			List<SearchOrder> searchOrders)
					throws SystemException {
		return reportDAO.findAllWithPagingWrapper(startIndex, maxRow, searchFilters, searchOrders, null);
	}

	@Override
	public List<ViewNewBusinessReport> findAll(
			List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		return reportDAO.findAll(searchFilters, searchOrders, null);
	}

	@Override
	public void exportReport(String fileName, String jasperFile, List<ViewNewBusinessReport> listBean, int exportTo, Map<String, Object> params) throws JRException, FileNotFoundException {
		try {
			JasperExporter jex = new JasperExporter(jasperFile);
			Map<String,Object> model = new HashMap<String,Object>();
			model.put(JasperExporter.DATA_SOURCE, listBean);
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
	public String exportReport(List<ViewNewBusinessReport> listBean,
			int exportTo) throws JRException, FileNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ViewNewBusinessReport findById(Long id) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(ViewNewBusinessReport anObject)
			throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ViewNewBusinessReport> findObjects(Long[] objectPKs)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagingWrapper<ViewNewBusinessReport> findAllByFilter(int startNo,
			int offset, List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
}
