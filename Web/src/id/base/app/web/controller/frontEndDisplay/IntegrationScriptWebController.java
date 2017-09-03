package id.base.app.web.controller.frontEndDisplay;

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
import id.base.app.valueobject.frontend.IntegrationScript;
import id.base.app.valueobject.util.SelectHelper;
import id.base.app.web.DataTableCriterias;
import id.base.app.web.controller.BaseController;
import id.base.app.web.rest.LookupRestCaller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Scope(value="request")
@Controller
@RequestMapping("/fed/integrationScript")
public class IntegrationScriptWebController extends BaseController<IntegrationScript>{

	private final String PATH_LIST = "/fed/integrationScriptList";
	private final String PATH_DETAIL = "/fed/integrationScriptDetail";
	
	@Override
	protected RestCaller<IntegrationScript> getRestCaller() {
		return new RestCaller<IntegrationScript>(RestConstant.REST_SERVICE, RestServiceConstant.INTEGRATION_SCRIPT_SERVICE);
	}

	@Override
	protected List<SearchFilter> convertForFilter(Map<String, String> paramWrapper) {
		return null;
	}

	@Override
	protected List<SearchFilter> convertForFilter(HttpServletRequest request, Map<String, String> paramWrapper, DataTableCriterias columns) {
		List<SearchFilter> filters = new ArrayList<>();
		if(StringFunction.isNotEmpty(columns.getSearch().get(DataTableCriterias.SearchCriterias.value))){
			filters.add(new SearchFilter(IntegrationScript.URL, Operator.LIKE, columns.getSearch().get(DataTableCriterias.SearchCriterias.value)));
		}
		return filters;
	}

	@Override
	protected List<SearchOrder> getSearchOrder() {
		if(orders != null) {
			orders.clear();
		}
		orders.add(new SearchOrder(IntegrationScript.URL, SearchOrder.Sort.ASC));
		return orders;
	}

	@Override
	protected String getListPath() {
		return PATH_LIST;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showList")
	public String showList(ModelMap model, HttpServletRequest request){
		model.addAttribute("pagingWrapper", new PagingWrapper<IntegrationScript>());
		model.addAttribute("detail",findGlobalScript());
		return getListPath();
	}
	
	public void setDefaultData(ModelMap model) {
		LookupRestCaller lrc = new LookupRestCaller();
		model.addAttribute("statusOptions", lrc.findByLookupGroup(ILookupGroupConstant.STATUS));
		List<SelectHelper> positionOptions = new ArrayList<>();
		positionOptions.add(new SelectHelper().getInstanceValueInteger(SystemConstant.IntegrationScriptPosition.HEADER, "Header"));
		positionOptions.add(new SelectHelper().getInstanceValueInteger(SystemConstant.IntegrationScriptPosition.FOOTER, "Footer"));
		model.addAttribute("positionOptions", positionOptions);
		List<SelectHelper> typeOptions = new ArrayList<>();
		typeOptions.add(new SelectHelper().getInstanceValueInteger(SystemConstant.IntegrationScriptType.GLOBAL, SystemConstant.IntegrationScriptType.GLOBAL_STR));
		typeOptions.add(new SelectHelper().getInstanceValueInteger(SystemConstant.IntegrationScriptType.SPESIFIC, SystemConstant.IntegrationScriptType.SPESIFIC_STR));
		model.addAttribute("typeOptions", typeOptions);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showAdd")
	public String showAdd(ModelMap model, HttpServletRequest request){
		setDefaultData(model);
		model.addAttribute("detail", IntegrationScript.getInstance());
		return PATH_DETAIL;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showEdit")
	public String showEdit(@RequestParam(value="maintenancePK") final Long maintenancePK, @RequestParam Map<String, String> paramWrapper, ModelMap model, HttpServletRequest request){
		setDefaultData(model);
		IntegrationScript detail = getRestCaller().findById(maintenancePK);
		model.addAttribute("detail", detail);
		return PATH_DETAIL;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="saveScript")
	@ResponseBody
	public Map<String, Object> saveScript(final IntegrationScript anObject, final BindingResult bindingResult, final ModelMap model, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		List<ErrorHolder> errors = new ArrayList<>();
		try{
			errors = new SpecificRestCaller<IntegrationScript>(RestConstant.REST_SERVICE, RestServiceConstant.INTEGRATION_SCRIPT_SERVICE).performPut("/update", anObject);
			if(errors != null && errors.size() > 0){
				resultMap.put(SystemConstant.ERROR_LIST, errors);
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return resultMap;
	}
	
	private IntegrationScript findGlobalScript(){
		IntegrationScript detail = new IntegrationScript();
		try{
			detail = new SpecificRestCaller<IntegrationScript>(RestConstant.REST_SERVICE, RestConstant.RM_INTEGRATION_SCRIPT, IntegrationScript.class).executeGet(new PathInterfaceRestCaller() {	
				@Override
				public String getPath() {
					return "/findGlobalScript";
				}
				
				@Override
				public Map<String, Object> getParameters() {
					Map<String,Object> map = new HashMap<String, Object>();
					return map;
				}
			});
			
		}catch(Exception e){
			detail = null;
		}
		return detail;
	}
}
