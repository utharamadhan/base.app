package id.base.app.service.report;

import id.base.app.AbstractHibernateDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.report.JasperExporter;
import id.base.app.report.ReportUtils;
import id.base.app.service.ReportService;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.business.report.ViewStock;

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
public class ReportStockService implements ReportService<ViewStock> {
	@Autowired
	AbstractHibernateDAO<ViewStock, Long> reportDAO;

	@Override
	public Collection<ViewStock> findReportCollection(Object[] params) throws SystemException {
		return null;
	}

	@Override
	public void updateExportedObjects(List<ViewStock> objects) throws SystemException {}

	@Override
	public PagingWrapper<ViewStock> findAllWithPagingWrapper(
			int startIndex, int maxRow, List<SearchFilter> searchFilters,
			List<SearchOrder> searchOrders)
					throws SystemException {
		return reportDAO.findAllWithPagingWrapper(startIndex, maxRow, searchFilters, searchOrders, null);
	}

	@Override
	public List<ViewStock> findAll(
			List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		return reportDAO.findAll(searchFilters, searchOrders, null);
	}

	@Override
	public void exportReport(String fileName, String jasperFile, List<ViewStock> listBean, int exportTo, Map<String, Object> params) throws JRException, FileNotFoundException {
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
	public String exportReport(List<ViewStock> listBean,
			int exportTo) throws JRException, FileNotFoundException {
		return null;
	}

	@Override
	public ViewStock findById(Long id) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(ViewStock anObject) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ViewStock> findObjects(Long[] objectPKs) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagingWrapper<ViewStock> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return reportDAO.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}
	
	
	
	
	
}
