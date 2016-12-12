package id.padiku.app.webMember.controller.report;

import id.padiku.app.SystemConstant;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.rest.FileRestCaller;
import id.padiku.app.rest.RestCaller;
import id.padiku.app.rest.RestConstant;
import id.padiku.app.rest.RestServiceConstant;
import id.padiku.app.util.DateTimeFunction;
import id.padiku.app.util.dao.Operator;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.util.dao.SearchOrder.Sort;
import id.padiku.app.valueobject.business.report.ViewStock;
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
@RequestMapping("/reports/stock")
public class ReportStockWebController extends BaseController<ViewStock> {

	@Override
	protected RestCaller<ViewStock> getRestCaller() {
		return new RestCaller<ViewStock>(RestConstant.REST_SERVICE, RestServiceConstant.REPORT_STOCK_SERVICE);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showList")
	public String showList(ModelMap model, HttpServletRequest request, @RequestParam Map<String, String> paramWrapper){
		model.addAttribute("pagingWrapper", new PagingWrapper<ViewStock>());
		return getListPath();
	}
	
	@Override
	protected List<SearchFilter> convertForFilter(Map<String, String> paramWrapper) {
		return null;
	}
	
	@RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
	public void downloadExcel(HttpServletResponse response, @RequestParam Map<String,String> paramWrapper, HttpServletRequest request) {
		String fileName = "reportStock.xlsx";
		
		try {
			List<SearchFilter> searchFilter = convertForFilter(paramWrapper);
			fileName = ViewStock.REPORT_STOCK_FILE + DateTimeFunction.date2String(new Date(), SystemConstant.SYSTEM_REPORT_DATE);
			FileRestCaller frc = new FileRestCaller(RestConstant.REST_SERVICE+RestConstant.RM_REPORT_STOCK+"/exportExcel", fileName);
			frc.exportXlsFile(request, response, searchFilter, new ArrayList<SearchOrder>());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	@Override
	protected List<SearchFilter> convertForFilter(HttpServletRequest request, Map<String, String> paramWrapper, DataTableCriterias columns) {
		List<SearchFilter> filters = new ArrayList<>();
		setDefaultFilter(request, filters);
		return filters;
	} 
	
	private void setDefaultFilter(HttpServletRequest request, List<SearchFilter> filters) {
		filters.add(new SearchFilter("fkCompany", Operator.EQUALS, WebGeneralFunction.getLogin(request).getCompanySelected(), Long.class));
	}

	@Override
	protected List<SearchOrder> getSearchOrder() {
		return new ArrayList<SearchOrder>(Arrays.asList(new SearchOrder("pkStock", Sort.ASC)));
	}

	@Override
	protected String getListPath() {
		return "/report/reportStockList";
	}

}
