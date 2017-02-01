package id.base.app.webMember.research;

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
import id.base.app.valueobject.research.Research;
import id.base.app.valueobject.research.ResearchBudgeting;
import id.base.app.valueobject.research.ResearchGoalTarget;
import id.base.app.valueobject.research.ResearchMemo;
import id.base.app.valueobject.research.ResearchTimePlanning;
import id.base.app.webMember.DataTableCriterias;
import id.base.app.webMember.controller.BaseController;

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
		filters.add(new SearchFilter(Research.IS_MANAGEMENT, Operator.EQUALS, Boolean.TRUE, Boolean.class));
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
	
	@RequestMapping(method=RequestMethod.GET, value="showList")
	public String showList(ModelMap model, HttpServletRequest request){
		model.addAttribute("pagingWrapper", new PagingWrapper<Research>());
		return getListPath();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showAdd")
	public String showAdd(ModelMap model, HttpServletRequest request){
		model.addAttribute("detail", Research.getInstance());
		model.addAttribute("mode", "creation");
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
			errors = new SpecificRestCaller<Research>(RestConstant.REST_SERVICE, RestServiceConstant.RESEARCH_SERVICE).performPut("/update", anObject);
			if(errors != null && errors.size() > 0){
				resultMap.put(SystemConstant.ERROR_LIST, errors);
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return resultMap;
	}

	@RequestMapping(method=RequestMethod.GET, value="/listTimePlanning")
	@ResponseBody
	public List<ResearchTimePlanning> showTimePlanningList(@RequestParam(value="fkResearch")final Long fkResearch, HttpServletRequest request){
		List<ResearchTimePlanning> list = new SpecificRestCaller<ResearchTimePlanning>(RestConstant.REST_SERVICE, RestConstant.RM_RESEARCH, ResearchTimePlanning.class).executeGetList(new PathInterfaceRestCaller() {
			
			@Override
			public String getPath() {
				return "/findTimePlanningByFkResearch/{fkResearch}";
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
	
	@RequestMapping(method=RequestMethod.GET, value="/listBudgeting")
	@ResponseBody
	public List<ResearchBudgeting> showBudgetingList(@RequestParam(value="fkResearch")final Long fkResearch, HttpServletRequest request){
		List<ResearchBudgeting> list = new SpecificRestCaller<ResearchBudgeting>(RestConstant.REST_SERVICE, RestConstant.RM_RESEARCH, ResearchBudgeting.class).executeGetList(new PathInterfaceRestCaller() {
			
			@Override
			public String getPath() {
				return "/findBudgetingByFkResearch/{fkResearch}";
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
	
	@RequestMapping(method=RequestMethod.GET, value="/listGoalTarget")
	@ResponseBody
	public List<ResearchGoalTarget> showGoalTargetList(@RequestParam(value="fkResearch")final Long fkResearch, HttpServletRequest request){
		List<ResearchGoalTarget> list = new SpecificRestCaller<ResearchGoalTarget>(RestConstant.REST_SERVICE, RestConstant.RM_RESEARCH, ResearchGoalTarget.class).executeGetList(new PathInterfaceRestCaller() {
			
			@Override
			public String getPath() {
				return "/findGoalTargetByFkResearch/{fkResearch}";
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
	
	@RequestMapping(method=RequestMethod.GET, value="/listMemo")
	@ResponseBody
	public List<ResearchMemo> showMemoList(@RequestParam(value="fkResearch")final Long fkResearch, HttpServletRequest request){
		List<ResearchMemo> list = new SpecificRestCaller<ResearchMemo>(RestConstant.REST_SERVICE, RestConstant.RM_RESEARCH, ResearchMemo.class).executeGetList(new PathInterfaceRestCaller() {
			
			@Override
			public String getPath() {
				return "/findMemoByFkResearch/{fkResearch}";
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
	
	@RequestMapping(method=RequestMethod.GET, value="/findTimePlanningById")
	@ResponseBody
	public ResearchTimePlanning findTimePlanningById(@RequestParam(value="id")final Long id, HttpServletRequest request){
		ResearchTimePlanning obj = new SpecificRestCaller<ResearchTimePlanning>(RestConstant.REST_SERVICE, RestConstant.RM_RESEARCH, ResearchTimePlanning.class).executeGet(new PathInterfaceRestCaller() {
			
			@Override
			public String getPath() {
				return "/findTimePlanningById/{id}";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				Map<String, Object> map = new HashMap<>();
				map.put("id", id);
				return map;
			}
		});
		return obj;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="saveTimePlanning")
	@ResponseBody
	public Map<String, Object> saveTimePlanning(final ResearchTimePlanning anObject, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		List<ErrorHolder> errors = new ArrayList<>();
		try{
			errors = new SpecificRestCaller<ResearchTimePlanning>(RestConstant.REST_SERVICE, RestServiceConstant.RESEARCH_SERVICE).performPut("/saveTimePlanning", anObject);
			if(errors != null && errors.size() > 0){
				resultMap.put(SystemConstant.ERROR_LIST, errors);
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return resultMap;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="saveBudgeting")
	@ResponseBody
	public Map<String, Object> saveBudgeting(final ResearchBudgeting anObject, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		List<ErrorHolder> errors = new ArrayList<>();
		try{
			errors = new SpecificRestCaller<ResearchBudgeting>(RestConstant.REST_SERVICE, RestServiceConstant.RESEARCH_SERVICE).performPut("/saveBudgeting", anObject);
			if(errors != null && errors.size() > 0){
				resultMap.put(SystemConstant.ERROR_LIST, errors);
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return resultMap;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="saveGoalTarget")
	@ResponseBody
	public Map<String, Object> saveGoalTarget(final ResearchGoalTarget anObject, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		List<ErrorHolder> errors = new ArrayList<>();
		try{
			errors = new SpecificRestCaller<ResearchGoalTarget>(RestConstant.REST_SERVICE, RestServiceConstant.RESEARCH_SERVICE).performPut("/saveGoalTarget", anObject);
			if(errors != null && errors.size() > 0){
				resultMap.put(SystemConstant.ERROR_LIST, errors);
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return resultMap;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="saveMemo")
	@ResponseBody
	public Map<String, Object> saveMemo(final ResearchMemo anObject, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		List<ErrorHolder> errors = new ArrayList<>();
		try{
			errors = new SpecificRestCaller<ResearchMemo>(RestConstant.REST_SERVICE, RestServiceConstant.RESEARCH_SERVICE).performPut("/saveMemo", anObject);
			if(errors != null && errors.size() > 0){
				resultMap.put(SystemConstant.ERROR_LIST, errors);
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return resultMap;
	}

}
