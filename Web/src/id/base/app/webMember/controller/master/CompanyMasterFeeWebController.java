package id.base.app.webMember.controller.master;

import id.base.app.ILookupGroupConstant;
import id.base.app.SystemConstant;
import id.base.app.exception.ErrorHolder;
import id.base.app.paging.PagingWrapper;
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
import id.base.app.valueobject.master.CompanyMasterFee;
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
@RequestMapping("/settings/fee")
public class CompanyMasterFeeWebController extends BaseController<CompanyMasterFee> {

	private final String PATH_LIST = "/master/companyMasterFeeList";
	private final String PATH_DETAIL = "/master/companyMasterFeeDetail";
	
	@Override
	protected RestCaller<CompanyMasterFee> getRestCaller() {
		return new RestCaller<CompanyMasterFee>(RestConstant.REST_SERVICE, RestServiceConstant.COMPANY_MASTER_FEE_SERVICE);
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
			filters.add(new SearchFilter(CompanyMasterFee.DESCR, Operator.LIKE, columns.getSearch().get(DataTableCriterias.SearchCriterias.value)));
		}
		return filters;
	}
	
	private void setDefaultFilter(HttpServletRequest request, List<SearchFilter> filters) {
		filters.add(new SearchFilter(CompanyMasterFee.COMPANY_ID, Operator.EQUALS, WebGeneralFunction.getLogin(request).getCompanySelected(),Long.class));
		filters.add(new SearchFilter(CompanyMasterFee.STATUS, Operator.EQUALS, SystemConstant.ValidFlag.VALID));
	}
	
	private void setDefaultData(ModelMap model) {
		model.addAttribute("optionsFeeType", new LookupRestCaller().findByLookupGroup(ILookupGroupConstant.FEE_TYPE));
		model.addAttribute("optionsUOM", new LookupRestCaller().findByLookupGroup(ILookupGroupConstant.UOM));
	}

	@Override
	protected List<SearchOrder> getSearchOrder() {
		return null;
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
		setDefaultData(model);
		model.addAttribute("detail", new CompanyMasterFee());
		return PATH_DETAIL;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showEdit")
	public String showEdit(@RequestParam(value="maintenancePK") final Long maintenancePK, @RequestParam Map<String, String> paramWrapper, ModelMap model){
		setDefaultData(model);
		model.addAttribute("detail", getRestCaller().findById(maintenancePK));
		return PATH_DETAIL;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="saveMasterFee")
	@ResponseBody
	public Map<String, Object> saveFee(final CompanyMasterFee anObject, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		List<ErrorHolder> errors = new ArrayList<>();
		anObject.setCompany(Company.getInstance(WebGeneralFunction.getLogin(request).getCompanySelected()));
		anObject.setStatus(SystemConstant.ValidFlag.VALID);
		try{
			if(anObject.getPkCompanyMasterFee()==null){
				errors = new SpecificRestCaller<CompanyMasterFee>(RestConstant.REST_SERVICE, RestServiceConstant.COMPANY_MASTER_FEE_SERVICE).performPost("/create", anObject);
			}else{
				errors = new SpecificRestCaller<CompanyMasterFee>(RestConstant.REST_SERVICE, RestServiceConstant.COMPANY_MASTER_FEE_SERVICE).performPut("/update", anObject);
			}
			if(errors != null && errors.size() > 1){
				resultMap.put(SystemConstant.ERROR_LIST, errors);
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return resultMap;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="deleteMasterFee")
	@ResponseBody
	public Map<String, Object> deleteFee(final Long[] maintenancePKs) {
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