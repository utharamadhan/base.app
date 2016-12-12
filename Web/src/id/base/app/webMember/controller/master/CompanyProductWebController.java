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
import id.base.app.valueobject.master.CompanyProduct;
import id.base.app.valueobject.party.Party;
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
@RequestMapping("/settings/product")
public class CompanyProductWebController extends BaseController<CompanyProduct> {

	private final String PATH_LIST = "/master/companyProductList";
	private final String PATH_DETAIL = "/master/companyProductDetail";
	
	@Override
	protected RestCaller<CompanyProduct> getRestCaller() {
		return new RestCaller<CompanyProduct>(RestConstant.REST_SERVICE, RestServiceConstant.COMPANY_PRODUCT_SERVICE);
	}
	
	private void setDefaultData(ModelMap model) {
		model.addAttribute("optionCategoryStockType", new LookupRestCaller().findByLookupGroup(ILookupGroupConstant.ITEM_TYPE));
		model.addAttribute("optionUOM", new LookupRestCaller().findByLookupGroup(ILookupGroupConstant.UOM));
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showList")
	public String showList(ModelMap model, HttpServletRequest request){
		model.addAttribute("pagingWrapper", new PagingWrapper<CompanyProduct>());
		return PATH_LIST;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showAdd")
	public String showAdd(ModelMap model, HttpServletRequest request){
		setDefaultData(model);
		model.addAttribute("detail", new CompanyProduct());
		return PATH_DETAIL;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showEdit")
	public String showEdit(@RequestParam(value="maintenancePK") final Long maintenancePK, @RequestParam Map<String, String> paramWrapper, ModelMap model){
		setDefaultData(model);
		model.addAttribute("detail", getRestCaller().findById(maintenancePK));
		if(paramWrapper.containsKey("afterSave") && StringFunction.compareString(paramWrapper.get("afterSave"), "true")){
			model.addAttribute("message", "Data Sukses Tersimpan");
		}
		return PATH_DETAIL;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="saveProduct")
	@ResponseBody
	public Map<String, Object> saveProduct(final CompanyProduct anObject, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		List<ErrorHolder> errors = new ArrayList<>();
		anObject.setCompany(Company.getInstance(WebGeneralFunction.getLogin(request).getCompanySelected()));
		anObject.setStatus(SystemConstant.ValidFlag.VALID);
		try{
			if(anObject.getPkCompanyProduct()==null){
				errors = new SpecificRestCaller<CompanyProduct>(RestConstant.REST_SERVICE, RestServiceConstant.COMPANY_PRODUCT_SERVICE).performPost("/create", anObject);
			}else{
				errors = new SpecificRestCaller<CompanyProduct>(RestConstant.REST_SERVICE, RestServiceConstant.COMPANY_PRODUCT_SERVICE).performPut("/update", anObject);
			}	
			if(errors != null && errors.size() > 0){
				resultMap.put(SystemConstant.ERROR_LIST, errors);
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return resultMap;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="deleteProduct")
	@ResponseBody
	public Map<String, Object> deleteProduct(final Long[] maintenancePKs) {
		Map<String, Object> resultMap = new HashMap<>();
		try{
			List<ErrorHolder> errors = new SpecificRestCaller<CompanyProduct>(RestConstant.REST_SERVICE, RestServiceConstant.COMPANY_PRODUCT_SERVICE).delete(maintenancePKs);
			if(errors != null && errors.size() > 0){
				resultMap.put("errorList", errors);
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return resultMap;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="searchProduct")
	@ResponseBody
	public List<Party> searchSeller(HttpServletRequest request, @RequestParam(value="keyword") final String keyword){
		List<Party> productList = null;
		final Long pkCompany = WebGeneralFunction.getLogin(request).getCompanySelected();
		try{
			productList = new SpecificRestCaller<Party>(RestConstant.REST_SERVICE, RestServiceConstant.COMPANY_PRODUCT_SERVICE).executeGetList(new PathInterfaceRestCaller() {
				@Override
				public String getPath() {
					return "/searchProduct/{pkCompany}/{keyword}";
				}
				
				@Override
				public Map<String, Object> getParameters() {
					Map<String, Object> map = new HashMap<>();
						map.put("pkCompany", pkCompany);
						map.put("keyword", keyword);
					return map;
				}
			});
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return productList;
	}

	@Override
	protected List<SearchFilter> convertForFilter(
			Map<String, String> paramWrapper) {
		return null;
	}

	@Override
	protected List<SearchFilter> convertForFilter(HttpServletRequest request, Map<String, String> paramWrapper, DataTableCriterias columns) {
		List<SearchFilter> filters = new ArrayList<>();
		setDefaultFilter(request, filters);
		if(StringFunction.isNotEmpty(columns.getSearch().get(DataTableCriterias.SearchCriterias.value))){
			filters.add(new SearchFilter("name", Operator.LIKE, columns.getSearch().get(DataTableCriterias.SearchCriterias.value)));
		}
		return filters;
	}
	
	private void setDefaultFilter(HttpServletRequest request, List<SearchFilter> filters) {
		filters.add(new SearchFilter("company.pkCompany", Operator.EQUALS, WebGeneralFunction.getLogin(request).getCompanySelected(),Long.class));
		filters.add(new SearchFilter("status", Operator.EQUALS, SystemConstant.ValidFlag.VALID));
	}

	@Override
	protected List<SearchOrder> getSearchOrder() {
		List<SearchOrder> orders = new ArrayList<>();
			orders.add(new SearchOrder("creationTime", SearchOrder.Sort.DESC));
		return orders;
	}

	@Override
	protected String getListPath() {
		return PATH_LIST;
	}
}