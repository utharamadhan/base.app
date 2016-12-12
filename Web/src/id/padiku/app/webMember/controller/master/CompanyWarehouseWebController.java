package id.padiku.app.webMember.controller.master;

import id.padiku.app.ILookupGroupConstant;
import id.padiku.app.SystemConstant;
import id.padiku.app.exception.ErrorHolder;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.rest.RestCaller;
import id.padiku.app.rest.RestConstant;
import id.padiku.app.rest.RestServiceConstant;
import id.padiku.app.rest.SpecificRestCaller;
import id.padiku.app.util.StringFunction;
import id.padiku.app.util.dao.Operator;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.master.Company;
import id.padiku.app.valueobject.master.CompanyWarehouse;
import id.padiku.app.webMember.DataTableCriterias;
import id.padiku.app.webMember.WebGeneralFunction;
import id.padiku.app.webMember.controller.BaseController;
import id.padiku.app.webMember.rest.LookupRestCaller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Scope(value="request")
@Controller
@RequestMapping("/settings/warehouse")
public class CompanyWarehouseWebController extends BaseController<CompanyWarehouse> {

	private final String PATH_LIST = "/master/companyWarehouseList";
	private final String PATH_DETAIL = "/master/companyWarehouseDetail";
	
	@Override
	protected RestCaller<CompanyWarehouse> getRestCaller() {
		return new RestCaller<CompanyWarehouse>(RestConstant.REST_SERVICE, RestServiceConstant.COMPANY_WAREHOUSE_SERVICE);
	}

	@Override
	protected List<SearchFilter> convertForFilter(
			Map<String, String> paramWrapper) {
		return null;
	}

	@Override
	protected List<SearchFilter> convertForFilter(HttpServletRequest request,
			Map<String, String> paramWrapper, DataTableCriterias columns) {
		List<SearchFilter> filters = new ArrayList<>();
		setDefaultFilter(request, filters);
		if(StringFunction.isNotEmpty(columns.getSearch().get(DataTableCriterias.SearchCriterias.value))){
			SearchFilter sfCode = new SearchFilter(CompanyWarehouse.CODE, Operator.LIKE, columns.getSearch().get(DataTableCriterias.SearchCriterias.value));
			SearchFilter sfName = new SearchFilter(CompanyWarehouse.NAME, Operator.LIKE, columns.getSearch().get(DataTableCriterias.SearchCriterias.value));
			filters.add(new SearchFilter(sfCode,sfName));
		}
		return filters;
	}
	
	private void setDefaultFilter(HttpServletRequest request, List<SearchFilter> filters) {
		filters.add(new SearchFilter("company.pkCompany", Operator.EQUALS, WebGeneralFunction.getLogin(request).getCompanySelected(),Long.class));
		filters.add(new SearchFilter("status", Operator.EQUALS, SystemConstant.ValidFlag.VALID));
	}
	
	private void setDefaultData(ModelMap model) {
		model.addAttribute("typeList", new LookupRestCaller().findByLookupGroup(ILookupGroupConstant.WAREHOUSE_TYPE));
	}

	@Override
	protected List<SearchOrder> getSearchOrder() {
		List<SearchOrder> orders = new ArrayList<>();
			orders.add(new SearchOrder(CompanyWarehouse.CODE, SearchOrder.Sort.ASC));
		return orders;
	}

	@Override
	protected String getListPath() {
		return PATH_LIST;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showList")
	public String showList(ModelMap model, HttpServletRequest request){
		model.addAttribute("pagingWrapper", new PagingWrapper<CompanyWarehouse>());
		return getListPath();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showAdd")
	public String showAdd(ModelMap model, HttpServletRequest request){
		setDefaultData(model);
		model.addAttribute("detail", new CompanyWarehouse());
		return PATH_DETAIL;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showEdit")
	public String showEdit(@RequestParam(value="maintenancePK") final Long maintenancePK, @RequestParam Map<String, String> paramWrapper, ModelMap model){
		setDefaultData(model);
		model.addAttribute("detail", getRestCaller().findById(maintenancePK));
		return PATH_DETAIL;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="saveWarehouse")
	@ResponseBody
	public Map<String, Object> saveCompanyWarehouse(final CompanyWarehouse anObject, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		List<ErrorHolder> errors = new ArrayList<>();
		anObject.setCompany(Company.getInstance(WebGeneralFunction.getLogin(request).getCompanySelected()));
		anObject.setStatus(SystemConstant.ValidFlag.VALID);
		try{
			if(anObject.getPkCompanyWarehouse()==null){
				errors = new SpecificRestCaller<CompanyWarehouse>(RestConstant.REST_SERVICE, RestServiceConstant.COMPANY_WAREHOUSE_SERVICE).performPost("/create", anObject);
			}else{
				errors = new SpecificRestCaller<CompanyWarehouse>(RestConstant.REST_SERVICE, RestServiceConstant.COMPANY_WAREHOUSE_SERVICE).performPut("/update", anObject);
			}
			if(errors != null && errors.size() > 1){
				resultMap.put(SystemConstant.ERROR_LIST, errors);
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return resultMap;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="deleteWarehouse")
	@ResponseBody
	public Map<String, Object> deleteCompanyWarehouse(final Long[] maintenancePKs) {
		Map<String, Object> resultMap = new HashMap<>();
		try{
			List<ErrorHolder> errors = getRestCaller().delete(maintenancePKs);
			if(errors != null && errors.size() > 1){
				resultMap.put(SystemConstant.ERROR_LIST, errors);
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return resultMap;
	}
}