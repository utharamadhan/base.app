package id.base.app.web.controller.research;

import id.base.app.SystemConstant;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
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
import id.base.app.valueobject.research.Research;
import id.base.app.valueobject.research.ResearchOfficer;
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
@RequestMapping("/research/researchManagement")
public class ResearchManagementWebController extends BaseController<Research> {

	private final String PATH_LIST = "/research/researchMgList";
	private final String PATH_DETAIL = "/research/researchMgTab";
	
	@Override
	protected RestCaller<Research> getRestCaller() {
		return new RestCaller<Research>(RestConstant.REST_SERVICE, RestServiceConstant.RESEARCH_SERVICE);
	}

	@Override
	protected List<SearchFilter> convertForFilter(
			Map<String, String> paramWrapper) {
		return null;
	}
	
	private void setDefaultFilter(HttpServletRequest request, List<SearchFilter> filters) {
		filters.add(new SearchFilter(Research.STATUS, Operator.NOT_EQUAL, SystemConstant.ValidFlag.INVALID));
	}

	@Override
	protected List<SearchFilter> convertForFilter(HttpServletRequest request,
			Map<String, String> paramWrapper, DataTableCriterias columns) {
		List<SearchFilter> filters = new ArrayList<>();
		setDefaultFilter(request, filters);
		if(StringFunction.isNotEmpty(columns.getSearch().get(DataTableCriterias.SearchCriterias.value))){
			filters.add(new SearchFilter(Research.TITLE, Operator.LIKE, columns.getSearch().get(DataTableCriterias.SearchCriterias.value)));
		}
		return filters;
	}

	@Override
	protected List<SearchOrder> getSearchOrder() {
		if(orders != null) {
			orders.clear();
		}
		orders.add(new SearchOrder(Research.PK_RESEARCH, SearchOrder.Sort.DESC));
		return orders;
	}
	
	public void setDefaultData(ModelMap model) {
		model.addAttribute("themeOptions", getThemeList());
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showList")
	public String showList(ModelMap model, HttpServletRequest request){
		model.addAttribute("pagingWrapper", new PagingWrapper<Research>());
		return getListPath();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showAdd")
	public String showAdd(ModelMap model, HttpServletRequest request){
		setDefaultData(model);
		Research obj = Research.getInstance();
		obj.setIsInternal(Boolean.TRUE);
		model.addAttribute("detail", obj);
		model.addAttribute("mode", "creation");
		return PATH_DETAIL;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showEdit")
	public String showEdit(@RequestParam(value="maintenancePK") final Long maintenancePK, @RequestParam Map<String, String> paramWrapper, ModelMap model, HttpServletRequest request){
		setDefaultData(model);
		Research detail = getRestCaller().findById(maintenancePK);
		model.addAttribute("detail", detail);
		return PATH_DETAIL;
	}

	@Override
	protected String getListPath() {
		return PATH_LIST;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="saveResearch")
	@ResponseBody
	public Map<String, Object> saveResearch(final Research anObject, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		List<ErrorHolder> errors = new ArrayList<>();
		try{
			Long pkResearch = (Long) new SpecificRestCaller<Research>(RestConstant.REST_SERVICE, RestServiceConstant.RESEARCH_SERVICE).performPutReturn("/updateReturn", anObject, Long.class);
			resultMap.put("maintenancePK", pkResearch);
		}catch(SystemException e){
			errors = e.getErrors();
			if(errors != null && errors.size() > 0){
				resultMap.put(SystemConstant.ERROR_LIST, errors);
			}
		}
		return resultMap;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/listOfficer")
	@ResponseBody
	public List<ResearchOfficer> listOfficer(@RequestParam(value="maintenancePK")final Long fkResearch, HttpServletRequest request){
		List<ResearchOfficer> list = new SpecificRestCaller<ResearchOfficer>(RestConstant.REST_SERVICE, RestConstant.RM_RESEARCH, ResearchOfficer.class).executeGetList(new PathInterfaceRestCaller() {
			
			@Override
			public String getPath() {
				return "/findOfficerByFkResearch/{fkResearch}";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				Map<String, Object> map = new HashMap<>();
				map.put("fkResearch", fkResearch);
				return map;
			}
		});
		return list;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="saveOfficer")
	@ResponseBody
	public Map<String, Object> saveOfficer(final ResearchOfficer anObject, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		List<ErrorHolder> errors = new ArrayList<>();
		try{
			errors = new SpecificRestCaller<ResearchOfficer>(RestConstant.REST_SERVICE, RestServiceConstant.RESEARCH_SERVICE).performPut("/saveOfficer", anObject);
			if(errors != null && errors.size() > 0){
				resultMap.put(SystemConstant.ERROR_LIST, errors);
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return resultMap;
	}
	
	private List<ResearchTheme> getThemeList(){
		List<SearchFilter> sf = new ArrayList<>();
		sf.add(new SearchFilter(ResearchTheme.STATUS,Operator.EQUALS,SystemConstant.ValidFlag.VALID));
		List<SearchOrder> so = new ArrayList<>();
		so.add(new SearchOrder(ResearchTheme.TITLE, SearchOrder.Sort.ASC));
		List<ResearchTheme> list = new RestCaller<ResearchTheme>(RestConstant.REST_SERVICE, RestServiceConstant.RESEARCH_THEME_SERVICE).findAll(sf, so);
		return list;
	}
}
