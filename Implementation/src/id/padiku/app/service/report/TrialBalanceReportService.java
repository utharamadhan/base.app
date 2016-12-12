package id.padiku.app.service.report;

import id.padiku.app.AbstractHibernateDAO;
import id.padiku.app.SystemConstant;
import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.report.JasperExporter;
import id.padiku.app.report.ReportUtils;
import id.padiku.app.service.ReportService;
import id.padiku.app.util.DateTimeFunction;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.business.report.ViewNewBusinessReport;
import id.padiku.app.valueobject.business.report.ViewTrialBalanceReport;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("trialBalanceReportService")
public class TrialBalanceReportService implements ReportService<ViewTrialBalanceReport>{
	@Autowired
	@Qualifier("trialBalanceReportDAO")
	AbstractHibernateDAO<ViewTrialBalanceReport, Long> reportDAO;
	
	public static final String TRIAL_BALANCE_REPORT_FILE = "TrialBalanceReport_";
	
	public static final String LINE_SEPARATOR = "\r\n"; // Windows always
	
	@Override
	public Collection<ViewTrialBalanceReport> findReportCollection(Object[] params) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateExportedObjects(List<ViewTrialBalanceReport> objects)
			throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PagingWrapper<ViewTrialBalanceReport> findAllWithPagingWrapper(
			int startIndex, int maxRow, List<SearchFilter> searchFilters,
			List<SearchOrder> searchOrders) throws SystemException {
		return reportDAO.findAllWithPagingWrapper(startIndex, maxRow, searchFilters, searchOrders, null);
	}

	@Override
	public List<ViewTrialBalanceReport> findAll(
			List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		return reportDAO.findAll(searchFilters, searchOrders, null);
	}
	
	private Map<String, Object> getReportParameter(ViewNewBusinessReport obj) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		return parameters;
	}

	@Override
	public String exportReport(List<ViewTrialBalanceReport> listBean, int exportTo) throws JRException, FileNotFoundException {
		return null;
	}

	@Override
	public void exportReport(String fileName, String jasperFile, List<ViewTrialBalanceReport> listBean, int exportTo, Map<String, Object> params) throws JRException, FileNotFoundException {
		try {
			JasperExporter jex = new JasperExporter(jasperFile);
			Map<String,Object> model = new HashMap<String,Object>();
			model.putAll(params);
			model.put(JasperExporter.DATA_SOURCE, listBean);
			if(exportTo == ReportUtils.EXPORT_TO_PDF){
				jex.exportPdf(model, fileName);
			}else if(exportTo == ReportUtils.EXPORT_TO_EXCEL){
				model.put(JRParameter.IS_IGNORE_PAGINATION, true);
				jex.exportXlsReport(model, fileName);
			}else{
//				model.put(JRParameter.IS_IGNORE_PAGINATION, true);
//				jex.exportTxtReport(model, fileName);
				exportTxtFile(model, fileName);
			}
		} catch (JRException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void exportTxtFile(Map<String, Object> params, String fileName) throws IOException {
		BufferedWriter writer = null;
		try{
			File file = new File(SystemConstant.LOCAL_TEMP_DIRECTORY_REPORT+ReportUtils.fixFileName(fileName,ReportUtils.TXT_EXTENSION));
			writer = new BufferedWriter(new FileWriter(file));
			String periodShort = params.get("periodShort").toString();
	        String[] arrayString = generateHeader(periodShort).split(System.lineSeparator());
			for (int i = 0; i < arrayString.length; i ++) {
				writer.write(arrayString[i].toString());
				if(i < arrayString.length-1){
					writer.write(LINE_SEPARATOR);
				}
			}
			
			String content = generateValue((List<ViewTrialBalanceReport>) params.get(JasperExporter.DATA_SOURCE));
			writer.write(content);
//			String[] valueString = generateValue((List<ViewTrialBalanceReport>) params.get(JasperExporter.DATA_SOURCE)).split(LINE_SEPARATOR);
//			for (int i = 0; i < valueString.length; i ++) {
//				writer.write(valueString[i].toString());
//				if(i < valueString.length-1){
//					writer.write(LINE_SEPARATOR);
//				}
//			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			writer.flush();
			writer.close();
		}
	}
	
	private String generateHeader(String periodShort){
		String header = "";
		header = header.concat(StringUtils.center("TB " + periodShort, ConfigPath.header.maxLength, " ").concat(LINE_SEPARATOR))
				.concat(String.format("%-8s %-3s %-15s %-100s %-10s %-15s", "Company", "1", "PT ASTRA AVIVA LIFE", " ", "Run Date", DateTimeFunction.date2String(DateTimeFunction.getCurrentDate(), SystemConstant.SYSTEM_DATE_MASK_2))).concat(LINE_SEPARATOR)
				.concat(String.format("%-8s %-3s %-15s %-104s %-10s %-15s", "Branch", "10", "Life Division", " ", "Time", DateTimeFunction.date2String(DateTimeFunction.getCurrentDate(), SystemConstant.HOUR_MINUTE_SECOND_MASK))).concat(LINE_SEPARATOR)
				.concat(String.format("%-8s %-3s %-15s", "Currency", "IDR", "Rupiah")).concat(LINE_SEPARATOR)
				.concat(StringUtils.center("Start From Account : TRL - BAL", ConfigPath.header.maxLength, " ")).concat(LINE_SEPARATOR).concat(LINE_SEPARATOR)
				.concat(String.format("%62s %s %s", " ", "<----------------Month to Date---------------->", "<----------------Year to Date----------------->")).concat(LINE_SEPARATOR)
				.concat(String.format("%-20s %-15s %-25s %-23s %-23s %-23s %-23s", "Account", "Sun GL Code", "Description", "Debit", "Credit", "Debit", "Credit")).concat(LINE_SEPARATOR);
		
		return header;
	}
	
	public String generateValue(List<ViewTrialBalanceReport> listValue){
		StringBuilder contentValue = new StringBuilder();//"\r\n";
		contentValue.append(LINE_SEPARATOR);
		for(ViewTrialBalanceReport value : listValue){
//			contentValue = contentValue.concat(String.format("%-20s %-15s %-25s %20s.00 %20s.00 %20s.00 %20s.00", value.getAccountName(), value.getSunGlCode(), value.getDescription(), value.getDebitMtd(), value.getCreditMtd(), value.getDebitYtd(), value.getDebitYtd())).concat(LINE_SEPARATOR);
			contentValue.append(String.format("%-20s %-15s %-25s %20s.00 %20s.00 %20s.00 %20s.00", value.getAccountName(), value.getSunGlCode(), value.getDescription(), value.getDebitMtd(), value.getCreditMtd(), value.getDebitYtd(), value.getCreditYtd()));
			contentValue.append(LINE_SEPARATOR);
			if (value.getLevel() != null && value.getLevel() == 2) {
				contentValue.append(LINE_SEPARATOR);
			}
		}
		contentValue.delete(contentValue.length()-LINE_SEPARATOR.length(),contentValue.length());
		
		return contentValue.toString();
	}
	
	public enum ConfigPath{
		header(100, 0);
		
		private int maxLength;
		private int blankPageLength;
		
		private ConfigPath(int maxLength, int blankPageLength) {
			this.maxLength = maxLength;
			this.blankPageLength = blankPageLength;
		}			
	}

	@Override
	public ViewTrialBalanceReport findById(Long id) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(ViewTrialBalanceReport anObject)
			throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ViewTrialBalanceReport> findObjects(Long[] objectPKs)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagingWrapper<ViewTrialBalanceReport> findAllByFilter(int startNo,
			int offset, List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}
}
