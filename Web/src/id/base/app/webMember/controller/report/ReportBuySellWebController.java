package id.base.app.webMember.controller.report;

import id.base.app.SystemConstant;
import id.base.app.rest.FileRestCaller;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.util.DateTimeFunction;
import id.base.app.util.StringFunction;
import id.base.app.util.dao.Operator;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.util.dao.SearchOrder.Sort;
import id.base.app.valueobject.business.report.ViewCostExpensesReport;
import id.base.app.valueobject.business.report.ViewTransInReport;
import id.base.app.webMember.DataTableCriterias;
import id.base.app.webMember.WebGeneralFunction;
import id.base.app.webMember.controller.BaseController;

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
@RequestMapping("/reports/analisajualbeli")
public class ReportBuySellWebController extends BaseController<ViewCostExpensesReport> {

	@Override
	protected RestCaller<ViewCostExpensesReport> getRestCaller() {
		return new RestCaller<ViewCostExpensesReport>(RestConstant.REST_SERVICE, RestServiceConstant.REPORT_COST_EXPENSES_SERVICE);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showList")
	public String showList(ModelMap model){
		return "/report/reportCostExpensesList";
	}
	
	@RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
	public void downloadExcel(HttpServletResponse response, @RequestParam Map<String,String> paramWrapper, HttpServletRequest request) {
		String fileName = "reportCostExpenses.xlsx";
		try {
			List<SearchFilter> searchFilter = convertForFilter(request, paramWrapper, null);
			fileName = ViewCostExpensesReport.FILE_NAME + DateTimeFunction.date2String(new Date(), SystemConstant.SYSTEM_REPORT_DATE);
			FileRestCaller frc = new FileRestCaller(RestConstant.REST_SERVICE+RestConstant.RM_REPORT_COST_EXPENSES+"/exportExcel", fileName);
			frc.exportXlsFile(request, response, searchFilter, new ArrayList<SearchOrder>());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	@Override
	protected List<SearchFilter> convertForFilter(Map<String, String> paramWrapper) {
		List<SearchFilter> filters = new ArrayList<>();
		if(paramWrapper.containsKey(ViewCostExpensesReport.FILTER_TRANS_DATE_START)) {
			String filter = paramWrapper.get(ViewCostExpensesReport.FILTER_TRANS_DATE_START);
			if(StringFunction.isNotEmpty(filter)) {
				try{
					filters.add(new SearchFilter(ViewCostExpensesReport.TRANS_DATE, Operator.EQUALS_OR_GREATER_THAN, filter));
				}catch(Exception e){
					LOGGER.error(e.getMessage(), e);
				}
			}
		}
		if(paramWrapper.containsKey(ViewCostExpensesReport.FILTER_TRANS_DATE_END)) {
			String filter = paramWrapper.get(ViewCostExpensesReport.FILTER_TRANS_DATE_END);
			if(StringFunction.isNotEmpty(filter)) {
				try{
					filters.add(new SearchFilter(ViewCostExpensesReport.TRANS_DATE, Operator.EQUALS_OR_LESS_THAN, filter));
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
		if(columns != null) {
			if (columns.getCustomFilters().containsKey(ViewCostExpensesReport.FILTER_TRANS_DATE_START)) {
				String filter = columns.getCustomFilters().get(ViewCostExpensesReport.FILTER_TRANS_DATE_START);
				if(StringFunction.isNotEmpty(filter)) {
					try{
						filters.add(new SearchFilter(ViewCostExpensesReport.TRANS_DATE, Operator.EQUALS_OR_GREATER_THAN, filter));
					}catch(Exception e){
						LOGGER.error(e.getMessage(), e);
					}
				}
			}
			if (columns.getCustomFilters().containsKey(ViewCostExpensesReport.FILTER_TRANS_DATE_END)) {
				String filter = columns.getCustomFilters().get(ViewCostExpensesReport.FILTER_TRANS_DATE_END);
				if(StringFunction.isNotEmpty(filter)) {
					try{
						filters.add(new SearchFilter(ViewCostExpensesReport.TRANS_DATE, Operator.EQUALS_OR_LESS_THAN, filter));
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
		filters.add(new SearchFilter(ViewCostExpensesReport.FK_COMPANY, Operator.EQUALS, WebGeneralFunction.getLogin(request).getCompanySelected(), Long.class));
		filters.add(new SearchFilter(ViewCostExpensesReport.STATUS, Operator.EQUALS, SystemConstant.ValidFlag.VALID));
	}

	@Override
	protected List<SearchOrder> getSearchOrder() {
		return new ArrayList<SearchOrder>(Arrays.asList(new SearchOrder(ViewCostExpensesReport.TRANS_DATE, Sort.ASC)));
	}

	@Override
	protected String getListPath() {
		return "/report/reportCostExpensesList";
	}
}