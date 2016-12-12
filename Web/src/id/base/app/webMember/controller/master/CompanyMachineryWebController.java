package id.base.app.webMember.controller.master;

import id.base.app.ILookupGroupConstant;
import id.base.app.SystemConstant;
import id.base.app.exception.ErrorHolder;
import id.base.app.paging.PagingWrapper;
import id.base.app.rest.PathInterfaceRestCaller;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.rest.SpecificRestCaller;
import id.base.app.util.StringFunction;
import id.base.app.util.dao.Operator;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.master.Company;
import id.base.app.valueobject.master.CompanyMachinery;
import id.base.app.valueobject.master.CompanyProduct;
import id.base.app.webMember.DataTableCriterias;
import id.base.app.webMember.WebGeneralFunction;
import id.base.app.webMember.controller.BaseController;
import id.base.app.webMember.rest.LookupRestCaller;

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
@RequestMapping("/settings/machinery")
public class CompanyMachineryWebController extends BaseController<CompanyMachinery> {

	private final String PATH_LIST = "/master/companyMachineryList";
	private final String PATH_DETAIL = "/master/companyMachineryDetail";
	
	@Override
	protected RestCaller<CompanyMachinery> getRestCaller() {
		return new RestCaller<CompanyMachinery>(RestConstant.REST_SERVICE, RestServiceConstant.COMPANY_MACHINERY_SERVICE);
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
			SearchFilter sfCode = new SearchFilter(CompanyMachinery.CODE, Operator.LIKE, columns.getSearch().get(DataTableCriterias.SearchCriterias.value));
			SearchFilter sfName = new SearchFilter(CompanyMachinery.NAME, Operator.LIKE, columns.getSearch().get(DataTableCriterias.SearchCriterias.value));
			filters.add(new SearchFilter(sfCode,sfName));
		}
		return filters;
	}
	
	private void setDefaultFilter(HttpServletRequest request, List<SearchFilter> filters) {
		filters.add(new SearchFilter(CompanyMachinery.COMPANY_ID, Operator.EQUALS, WebGeneralFunction.getLogin(request).getCompanySelected(),Long.class));
		filters.add(new SearchFilter(CompanyMachinery.STATUS, Operator.EQUALS, SystemConstant.ValidFlag.VALID));
	}
	
	private void setDefaultData(ModelMap model, Long pkCompany) {
		model.addAttribute("modelList", new LookupRestCaller().findByLookupGroup(ILookupGroupConstant.MACHINE_MODEL));
		model.addAttribute("typeList", new LookupRestCaller().findByLookupGroup(ILookupGroupConstant.MACHINE_TYPE));
		model.addAttribute("weightingList", new LookupRestCaller().findByLookupGroup(ILookupGroupConstant.MACHINE_WEIGHTING));
		model.addAttribute("capacityUomList", new LookupRestCaller().findByLookupGroup(ILookupGroupConstant.MACHINE_CAPACITY_UOM));
		model.addAttribute("powerSourceList", new LookupRestCaller().findByLookupGroup(ILookupGroupConstant.MACHINE_POWER_SOURCE));
		model.addAttribute("ownershipsList", new LookupRestCaller().findByLookupGroup(ILookupGroupConstant.MACHINE_OWNERSHIPS));
		model.addAttribute("companyProductList", getProductListByCompany(pkCompany));
	}
	
	private List<CompanyProduct> getProductListByCompany(final Long pkCompany) {
		List<CompanyProduct> productList = null;
		try{
			productList = new SpecificRestCaller<CompanyProduct>(RestConstant.REST_SERVICE, RestServiceConstant.COMPANY_PRODUCT_SERVICE).executeGetList(new PathInterfaceRestCaller() {
				@Override
				public String getPath() {
					return "/findAllProductByUsageType/{pkCompany}/{usageItemType}";
				}
				
				@Override
				public Map<String, Object> getParameters() {
					Map<String, Object> map = new HashMap<>();
						map.put("pkCompany", pkCompany);
						map.put("usageItemType", SystemConstant.UsageItemType.BAHAN_BAKU_PRODUKSI);
					return map;
				}
			});
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return productList;
	}

	@Override
	protected List<SearchOrder> getSearchOrder() {
		List<SearchOrder> orders = new ArrayList<>();
			orders.add(new SearchOrder(CompanyMachinery.CODE, SearchOrder.Sort.ASC));
		return orders;
	}

	@Override
	protected String getListPath() {
		return PATH_LIST;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showList")
	public String showList(ModelMap model, HttpServletRequest request){
		model.addAttribute("pagingWrapper", new PagingWrapper<CompanyMachinery>());
		return getListPath();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showAdd")
	public String showAdd(ModelMap model, HttpServletRequest request){ 
		setDefaultData(model, WebGeneralFunction.getLogin(request).getCompanySelected());
		model.addAttribute("detail", new CompanyMachinery());
		return PATH_DETAIL;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showEdit")
	public String showEdit(@RequestParam(value="maintenancePK") final Long maintenancePK, @RequestParam Map<String, String> paramWrapper, ModelMap model, HttpServletRequest request){
		setDefaultData(model, WebGeneralFunction.getLogin(request).getCompanySelected());
		model.addAttribute("detail", getRestCaller().findById(maintenancePK));
		return PATH_DETAIL;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="saveMachinery")
	@ResponseBody
	public Map<String, Object> saveMachinery(final CompanyMachinery anObject, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		List<ErrorHolder> errors = new ArrayList<>();
		anObject.setCompany(Company.getInstance(WebGeneralFunction.getLogin(request).getCompanySelected()));
		anObject.setStatus(SystemConstant.ValidFlag.VALID);
		try{
			if(anObject.getPkCompanyMachinery()==null){
				errors = new SpecificRestCaller<CompanyMachinery>(RestConstant.REST_SERVICE, RestServiceConstant.COMPANY_MACHINERY_SERVICE).performPost("/create", anObject);
			}else{
				errors = new SpecificRestCaller<CompanyMachinery>(RestConstant.REST_SERVICE, RestServiceConstant.COMPANY_MACHINERY_SERVICE).performPut("/update", anObject);
			}
			if(errors != null && errors.size() > 0){
				resultMap.put(SystemConstant.ERROR_LIST, errors);
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return resultMap;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="deleteMachinery")
	@ResponseBody
	public Map<String, Object> deleteMachinery(final Long[] maintenancePKs) {
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
