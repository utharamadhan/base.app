package id.padiku.app.webMember.controller.report;

import id.padiku.app.SystemConstant;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.rest.FileRestCaller;
import id.padiku.app.rest.RestCaller;
import id.padiku.app.rest.RestConstant;
import id.padiku.app.rest.RestServiceConstant;
import id.padiku.app.util.DateTimeFunction;
import id.padiku.app.util.StringFunction;
import id.padiku.app.util.dao.Operator;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.util.dao.SearchOrder.Sort;
import id.padiku.app.valueobject.business.report.ViewCashFlow;
import id.padiku.app.webMember.DataTableCriterias;
import id.padiku.app.webMember.WebGeneralFunction;
import id.padiku.app.webMember.controller.BaseController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Scope(value="request")
@Controller
@RequestMapping("/reports/cashflow")
public class ReportCashFlowWebController extends BaseController<ViewCashFlow> {

	@Override
	protected RestCaller<ViewCashFlow> getRestCaller() {
		return new RestCaller<ViewCashFlow>(RestConstant.REST_SERVICE, RestServiceConstant.REPORT_CASH_FLOW_SERVICE);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showList")
	public String showList(ModelMap model, HttpServletRequest request, @RequestParam Map<String, String> paramWrapper){
		model.addAttribute("pagingWrapper", new PagingWrapper<ViewCashFlow>());
		return getListPath();
	}

	@Override
	protected List<SearchFilter> convertForFilter(Map<String, String> paramWrapper) {
		List<SearchFilter> filters = new ArrayList<>();
		if (paramWrapper.containsKey(ViewCashFlow.FILTER_TRANS_DATE_START)) {
			String filter = paramWrapper.get(ViewCashFlow.FILTER_TRANS_DATE_START);
			if(StringFunction.isNotEmpty(filter)) {
				try{
					filters.add(new SearchFilter(ViewCashFlow.TRANS_DATE, Operator.EQUALS_OR_GREATER_THAN, filter));
				}catch(Exception e){
					LOGGER.error(e.getMessage(), e);
				}
			}
		}
		if (paramWrapper.containsKey(ViewCashFlow.FILTER_TRANS_DATE_END)) {
			String filter = paramWrapper.get(ViewCashFlow.FILTER_TRANS_DATE_END);
			if(StringFunction.isNotEmpty(filter)) {
				try{
					filters.add(new SearchFilter(ViewCashFlow.TRANS_DATE, Operator.EQUALS_OR_LESS_THAN, filter));
				}catch(Exception e){
					LOGGER.error(e.getMessage(), e);
				}
			}
		}
		return filters;
	}
	
	@Override
	protected List<SearchFilter> convertForFilter(HttpServletRequest request, Map<String, String> paramWrapper, DataTableCriterias columns) {
		List<SearchFilter> filters = new ArrayList<>();
		if (columns != null) {
			if (columns.getCustomFilters().containsKey(ViewCashFlow.FILTER_TRANS_DATE_START)) {
				String filter = columns.getCustomFilters().get(ViewCashFlow.FILTER_TRANS_DATE_START);
				if(StringFunction.isNotEmpty(filter)) {
					try{
						filters.add(new SearchFilter(ViewCashFlow.TRANS_DATE, Operator.EQUALS_OR_GREATER_THAN, filter));
					}catch(Exception e){
						LOGGER.error(e.getMessage(), e);
					}
				}
			}
			if (columns.getCustomFilters().containsKey(ViewCashFlow.FILTER_TRANS_DATE_END)) {
				String filter = columns.getCustomFilters().get(ViewCashFlow.FILTER_TRANS_DATE_END);
				if(StringFunction.isNotEmpty(filter)) {
					try{
						filters.add(new SearchFilter(ViewCashFlow.TRANS_DATE, Operator.EQUALS_OR_LESS_THAN, filter));
					}catch(Exception e){
						LOGGER.error(e.getMessage(), e);
					}
				}
			}
		} else {
			filters.addAll(convertForFilter(paramWrapper));
		}
		setDefaultFilter(request, filters);
		return filters;
	} 
	
	private void setDefaultFilter(HttpServletRequest request, List<SearchFilter> filters) {
		filters.add(new SearchFilter("fkCompany", Operator.EQUALS, WebGeneralFunction.getLogin(request).getCompanySelected(), Long.class));
	}
	
	@RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
	public void downloadExcel(HttpServletResponse response, @RequestParam Map<String,String> paramWrapper, HttpServletRequest request) {
		String fileName = "reportCashFlow.xlsx";
		try {
			List<SearchFilter> searchFilter = convertForFilter(request, paramWrapper, null);
			fileName = ViewCashFlow.FILE_NAME + DateTimeFunction.date2String(new Date(), SystemConstant.SYSTEM_REPORT_DATE);
			FileRestCaller frc = new FileRestCaller(RestConstant.REST_SERVICE+RestConstant.RM_REPORT_CASH_FLOW+"/exportExcel", fileName);
			frc.exportXlsFile(request, response, searchFilter, new ArrayList<SearchOrder>());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	@Override
	protected List<SearchOrder> getSearchOrder() {
		return new ArrayList<SearchOrder>(Arrays.asList(new SearchOrder(ViewCashFlow.TRANS_DATE, Sort.ASC)));
	}

	@Override
	protected String getListPath() {
		return "/report/reportCashFlowList";
	}

}
