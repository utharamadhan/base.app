package id.padiku.app.controller.report;

import id.padiku.app.SystemConstant;
import id.padiku.app.controller.SuperController;
import id.padiku.app.exception.ErrorHolder;
import id.padiku.app.exception.SystemException;
import id.padiku.app.report.ReportUtils;
import id.padiku.app.rest.RestConstant;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.service.ReportService;
import id.padiku.app.service.lookup.ILookupService;
import id.padiku.app.service.parameter.IParameterService;
import id.padiku.app.util.DateTimeFunction;
import id.padiku.app.util.StringFunction;
import id.padiku.app.util.dao.Operator;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.Lookup;
import id.padiku.app.valueobject.business.report.ViewTransInReport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@RequestMapping(RestConstant.RM_REPORT_TRANS_IN)
public class ReportTransInController extends SuperController<ViewTransInReport> {

	@Autowired
	private ReportService<ViewTransInReport> reportTransInService;
	@Autowired
	private IParameterService parameterService;
	@Autowired
	private ILookupService lookupService;
	
	private static final String JASPER_FILE = "reportTransIn.jasper";
	
	@Override
	public List<ViewTransInReport> findAll(
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
			List<ViewTransInReport> list = reportTransInService.findAll(filter, order);
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
	
	private String findExportData(String filename, String filterJson, String orderJson, int exportTo) throws IOException, JRException, FileNotFoundException {
		if(StringFunction.isEmpty(filename)){
			filename = ViewTransInReport.FILE_NAME + DateTimeFunction.date2String(new Date(), SystemConstant.SYSTEM_REPORT_DATE);
		}
		List<SearchFilter> filters = convertToListFilter(filterJson);
		List<ViewTransInReport> list = reportTransInService.findAll(filters, convertToListOrder(orderJson));
		
		reportTransInService.exportReport(filename, JASPER_FILE, list, exportTo, preparingParams(filters));
		
		return filename;
	}
	
	private Map<String, Object> preparingParams(List<SearchFilter> filters) {
		Map<String, Object> map = new HashMap<>();
		//params
		Date inDateStart = null;
		Date inDateEnd = null;
		String sourceTypeFilter = null;
		String itemTypeFilter = null;
		//extract params from filter (if exist
		for(SearchFilter item : filters) {
			if(StringFunction.isNotEmpty(item.getFieldName()) && item.getValue() != null) {
				
				if(item.getFieldName().equals(ViewTransInReport.IN_DATE) && item.getOperand().equals(Operator.EQUALS_OR_GREATER_THAN)) {
					try {
						inDateStart = (Date) item.getValue();
					} catch (Exception e) {
						LOGGER.error(e.getMessage(), e);
					}
				}
				if(item.getFieldName().equals(ViewTransInReport.IN_DATE) && item.getOperand().equals(Operator.EQUALS_OR_LESS_THAN)) {
					try {
						inDateEnd = (Date) item.getValue();
					} catch (Exception e) {
						LOGGER.error(e.getMessage(), e);
					}
				}
				if(item.getFieldName().equals(ViewTransInReport.SOURCE_TYPE)) {
					sourceTypeFilter = item.getValue().toString();
				}
				if(item.getFieldName().equals(ViewTransInReport.FK_LOOKUP_ITEM_TYPE)) {
					try {
						Long pkLookup = Long.valueOf(item.getValue().toString());
						Lookup lo = lookupService.findById(pkLookup);
						itemTypeFilter = lo.getName();
					} catch (Exception e) {
						LOGGER.error(e.getMessage(), e);
					}
				}
			}
		}
		//put params to map
		map.put("inDateStart", inDateStart);
		map.put("inDateEnd", inDateEnd);
		map.put("sourceTypeFilter", StringFunction.isNotEmpty(sourceTypeFilter) ? sourceTypeFilter : "ALL");
		map.put("itemTypeFilter", StringFunction.isNotEmpty(itemTypeFilter) ? itemTypeFilter : "ALL");
		map.put("currentDate", new Date());
		return map;
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
		if(filters != null && filters.size() > 0) {
			for(SearchFilter filter : filters) {
				if (filter.getFieldName().equals(ViewTransInReport.IN_DATE)) {
					try{
						filter.setValue(StringFunction.string2Date(filter.getValue().toString(), SystemConstant.DATABASE_DATE_FORMAT_STD));
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		}
		return filters;
	}
	
	@Override
	public MaintenanceService<ViewTransInReport> getMaintenanceService() { return reportTransInService; }

	@Override
	public ViewTransInReport validate(ViewTransInReport anObject) throws SystemException { return anObject; }
	
}