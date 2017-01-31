package id.base.app.webMember.research;

import id.base.app.SystemConstant;
import id.base.app.exception.ErrorHolder;
import id.base.app.paging.PagingWrapper;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.rest.SpecificRestCaller;
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

	@Override
	protected List<SearchFilter> convertForFilter(HttpServletRequest request,
			Map<String, String> paramWrapper, DataTableCriterias columns) {
		return null;
	}

	@Override
	protected List<SearchOrder> getSearchOrder() {
		return null;
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
	
	//Time Planning
	@RequestMapping(method=RequestMethod.GET, value="/listTimePlanning")
	@ResponseBody
	public List<ResearchTimePlanning> showTimePlanningList(@RequestParam Map<String,String> paramWrapper, HttpServletRequest request){
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		filters.add(new SearchFilter(ResearchTimePlanning.FK_RESEARCH, Operator.EQUALS, paramWrapper.get("fkResearch")));
		List<SearchOrder> orders = new ArrayList<SearchOrder>();
		orders.add(new SearchOrder(ResearchTimePlanning.DATE_FROM, SearchOrder.Sort.ASC));
		RestCaller<ResearchTimePlanning> rs = new RestCaller<ResearchTimePlanning>(RestConstant.REST_SERVICE, RestServiceConstant.RESEARCH_SERVICE);
		List<ResearchTimePlanning> list = rs.findAll(filters, orders);
		return list;
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
