package id.base.app.web.controller.research;

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
import id.base.app.valueobject.Lookup;
import id.base.app.valueobject.research.ResearchTheme;
import id.base.app.web.DataTableCriterias;
import id.base.app.web.controller.BaseController;

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
@RequestMapping("/research/researchTheme")
public class ResearchThemeWebController extends BaseController<ResearchTheme> {

	private final String PATH_LIST = "/research/researchThemeList";
	private final String PATH_DETAIL = "/research/researchThemeDetail";
	
	@Override
	protected RestCaller<ResearchTheme> getRestCaller() {
		return new RestCaller<ResearchTheme>(RestConstant.REST_SERVICE, RestServiceConstant.RESEARCH_THEME_SERVICE);
	}

	@Override
	protected List<SearchFilter> convertForFilter(Map<String, String> paramWrapper) {
		return null;
	}
	
	private void setDefaultFilter(HttpServletRequest request, List<SearchFilter> filters) {
	}

	@Override
	protected List<SearchFilter> convertForFilter(HttpServletRequest request, Map<String, String> paramWrapper, DataTableCriterias columns) {
		List<SearchFilter> filters = new ArrayList<>();
		setDefaultFilter(request, filters);
		if(StringFunction.isNotEmpty(columns.getSearch().get(DataTableCriterias.SearchCriterias.value))){
			filters.add(new SearchFilter(ResearchTheme.TITLE, Operator.LIKE, columns.getSearch().get(DataTableCriterias.SearchCriterias.value)));
		}
		return filters;
	}

	@Override
	protected List<SearchOrder> getSearchOrder() {
		if(orders != null) {
			orders.clear();
		}
		orders.add(new SearchOrder(ResearchTheme.PK_RESEARCH_THEME, SearchOrder.Sort.DESC));
		return orders;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showList")
	public String showList(ModelMap model, HttpServletRequest request){
		model.addAttribute("pagingWrapper", new PagingWrapper<ResearchTheme>());
		return getListPath();
	}
	
	public void setDefaultData(ModelMap model) {
		List<Lookup> statusOptions = new ArrayList<Lookup>();
		statusOptions.add(Lookup.getInstanceShort(SystemConstant.ValidFlag.VALID.toString(), SystemConstant.ValidFlag.VALID_STR));
		statusOptions.add(Lookup.getInstanceShort(SystemConstant.ValidFlag.INVALID.toString(), SystemConstant.ValidFlag.INVALID_STR));
		model.addAttribute("statusOptions", statusOptions);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showAdd")
	public String showAdd(ModelMap model, HttpServletRequest request){
		setDefaultData(model);
		model.addAttribute("detail", ResearchTheme.getInstance());
		return PATH_DETAIL;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showEdit")
	public String showEdit(@RequestParam(value="maintenancePK") final Long maintenancePK, @RequestParam Map<String, String> paramWrapper, ModelMap model, HttpServletRequest request){
		setDefaultData(model);
		ResearchTheme detail = getRestCaller().findById(maintenancePK);
		model.addAttribute("detail", detail);
		return PATH_DETAIL;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="saveResearchTheme")
	@ResponseBody
	public Map<String, Object> saveResearchTheme(final ResearchTheme anObject, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		List<ErrorHolder> errors = new ArrayList<>();
		try{
			errors = new SpecificRestCaller<ResearchTheme>(RestConstant.REST_SERVICE, RestServiceConstant.RESEARCH_THEME_SERVICE).performPut("/update", anObject);
			if(errors != null && errors.size() > 0){
				resultMap.put(SystemConstant.ERROR_LIST, errors);
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return resultMap;
	}

	@Override
	protected String getListPath() {
		return PATH_LIST;
	}
}