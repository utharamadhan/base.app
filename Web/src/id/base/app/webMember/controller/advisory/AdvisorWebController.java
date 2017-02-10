package id.base.app.webMember.controller.advisory;

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
import id.base.app.valueobject.AppUser;
import id.base.app.valueobject.advisory.Advisor;
import id.base.app.webMember.DataTableCriterias;
import id.base.app.webMember.controller.BaseController;

@Scope(value="request")
@Controller
@RequestMapping("/advisory/advisor")
public class AdvisorWebController extends BaseController<Advisor> {

	private final String PATH_LIST = "/advisory/advisorList";
	private final String PATH_DETAIL = "/advisory/advisorDetail";
	
	@Override
	protected RestCaller<Advisor> getRestCaller() {
		return new RestCaller<Advisor>(RestConstant.REST_SERVICE, RestServiceConstant.ADVISOR_SERVICE);
	}

	@Override
	protected List<SearchFilter> convertForFilter(Map<String, String> paramWrapper) {
		return null;
	}
	
	private void setDefaultFilter(HttpServletRequest request, List<SearchFilter> filters) {
		filters.add(new SearchFilter(Advisor.STATUS, Operator.EQUALS, SystemConstant.ValidFlag.VALID));
	}

	@Override
	protected List<SearchFilter> convertForFilter(HttpServletRequest request, Map<String, String> paramWrapper, DataTableCriterias columns) {
		List<SearchFilter> filters = new ArrayList<>();
		setDefaultFilter(request, filters);
		if(StringFunction.isNotEmpty(columns.getSearch().get(DataTableCriterias.SearchCriterias.value))){
			filters.add(new SearchFilter(Advisor.NAME, Operator.LIKE, columns.getSearch().get(DataTableCriterias.SearchCriterias.value)));
		}
		return filters;
	}

	@Override
	protected List<SearchOrder> getSearchOrder() {
		if(orders != null) {
			orders.clear();
		}
		orders.add(new SearchOrder(Advisor.PK_ADVISOR, SearchOrder.Sort.DESC));
		return orders;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showList")
	public String showList(ModelMap model, HttpServletRequest request){
		model.addAttribute("pagingWrapper", new PagingWrapper<Advisor>());
		return getListPath();
	}
	
	public void setDefaultData(ModelMap model) {}
	
	@RequestMapping(method=RequestMethod.GET, value="showAdd")
	public String showAdd(ModelMap model, HttpServletRequest request){
		model.addAttribute("detail", Advisor.getInstance());
		return PATH_DETAIL;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showEdit")
	public String showEdit(@RequestParam(value="maintenancePK") final Long maintenancePK, @RequestParam Map<String, String> paramWrapper, ModelMap model, HttpServletRequest request){
		setDefaultData(model);
		Advisor detail = getRestCaller().findById(maintenancePK);
		model.addAttribute("detail", detail);
		return PATH_DETAIL;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="saveAdvisor")
	@ResponseBody
	public Map<String, Object> saveAdvisor(final Advisor anObject, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		List<ErrorHolder> errors = new ArrayList<>();
		try{
			errors = new SpecificRestCaller<Advisor>(RestConstant.REST_SERVICE, RestServiceConstant.ADVISOR_SERVICE).performPut("/update", anObject);
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
