package id.padiku.app.controller.report;

import id.padiku.app.SystemConstant;
import id.padiku.app.controller.SuperController;
import id.padiku.app.exception.ErrorHolder;
import id.padiku.app.exception.SystemException;
import id.padiku.app.report.ReportUtils;
import id.padiku.app.rest.RestConstant;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.service.ReportService;
import id.padiku.app.service.parameter.IParameterService;
import id.padiku.app.util.DateTimeFunction;
import id.padiku.app.util.StringFunction;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.business.report.ViewStock;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(RestConstant.RM_REPORT_STOCK)
public class ReportStockController extends SuperController<ViewStock> {

	@Autowired
	private ReportService<ViewStock> reportStockService;
	@Autowired
	private IParameterService parameterService;
	
	private static final String JASPER_FILE = "reportStock.jasper";
	
	@Override
	public List<ViewStock> findAll(
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson,
			@RequestParam(value="order", defaultValue="", required=false) String orderJson)
			throws SystemException {
		try {
			List<SearchFilter> filter = new ArrayList<SearchFilter>();
			if(StringUtils.isNotEmpty(filterJson)){
				filter = mapper.readValue(filterJson, new TypeReference<List<SearchFilter>>(){});
			}
			List<SearchOrder> order = new ArrayList<SearchOrder>();
			if(StringUtils.isNotEmpty(orderJson)){
				order = mapper.readValue(orderJson, new TypeReference<List<SearchOrder>>(){});
			}
			List<ViewStock> list = reportStockService.findAll(filter, order);
			return list;
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error("error finding your data",e);
			throw new SystemException(new ErrorHolder("error finding your data"));
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/exportExcel")
	public void exportExcel(
			@RequestParam(value="filename", defaultValue="", required=false) String filename,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson,
			@RequestParam(value="order", defaultValue="", required=false) String orderJson,
			HttpServletResponse response)
			throws SystemException {
		InputStream is = null;
		try {
			filename = findExportData(filename, filterJson, orderJson, ReportUtils.EXPORT_TO_EXCEL);
			is = new FileInputStream(new File(SystemConstant.LOCAL_TEMP_DIRECTORY_REPORT + ReportUtils.fixFileName(filename, ReportUtils.XLS_EXTENSION)));
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
		} catch (IOException | JRException e) {
			e.printStackTrace();
			LOGGER.error("error exporting file",e);
			throw new SystemException(new ErrorHolder("error exporting file"));
		} finally {
			if(is!=null){
				IOUtils.closeQuietly(is);
			}
		}
	}
	
	private String findExportData(String filename, String filterJson,
			String orderJson, int exportTo) throws IOException, JRException,
			FileNotFoundException {
		if(StringFunction.isEmpty(filename)){
			filename = ViewStock.REPORT_STOCK_FILE + DateTimeFunction.date2String(new Date(), SystemConstant.SYSTEM_REPORT_DATE);
		}
		List<ViewStock> list = reportStockService.findAll(convertToListFilter(filterJson),convertToListOrder(orderJson));
		reportStockService.exportReport(filename, JASPER_FILE, list, exportTo, new HashMap<String, Object>());
		
		return filename;
	}
	
	private List<SearchOrder> convertToListOrder(String orderJson) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		List<SearchOrder> orders = new ArrayList<SearchOrder>();
		if(StringUtils.isNotEmpty(orderJson)){
			orders = mapper.readValue(orderJson, new TypeReference<List<SearchOrder>>(){});
		}
		return orders;
	}
	
	private List<SearchFilter> convertToListFilter(String filterJson) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		if(StringUtils.isNotEmpty(filterJson)){
			filters = mapper.readValue(filterJson, new TypeReference<List<SearchFilter>>(){});
		}
		return filters;
	}
	
	@Override
	public MaintenanceService<ViewStock> getMaintenanceService() {
		return reportStockService;
	}

	@Override
	public ViewStock validate(ViewStock anObject) throws SystemException {
		return anObject;
	}
}