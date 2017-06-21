package id.base.app.web.controller.advisory;

import id.base.app.LoginSession;
import id.base.app.SystemConstant;
import id.base.app.exception.ErrorHolder;
import id.base.app.paging.PagingWrapper;
import id.base.app.rest.LoginSessionUtil;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.rest.SpecificRestCaller;
import id.base.app.util.dao.Operator;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.AppRole;
import id.base.app.valueobject.AppUser;
import id.base.app.valueobject.Category;
import id.base.app.valueobject.advisory.AdvisoryConsulting;
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
@RequestMapping("/advisory/consulting")
public class AdvisoryWebController extends BaseController<AdvisoryConsulting> {

	private final String PATH_LIST = "/advisory/consultingList";
	private final String PATH_DETAIL = "/advisory/consultingDetail";
	
	@Override
	protected RestCaller<AdvisoryConsulting> getRestCaller() {
		return new RestCaller<AdvisoryConsulting>(RestConstant.REST_SERVICE, RestServiceConstant.ADVISORY_SERVICE);
	}
	
	protected RestCaller<AppUser> getRestCallerUser() {
		return new RestCaller<AppUser>(RestConstant.REST_SERVICE, RestServiceConstant.USER_SERVICE);
	}
	
	protected RestCaller<AppRole> getRestCallerRole() {
		return new RestCaller<AppRole>(RestConstant.REST_SERVICE, RestServiceConstant.ROLE_SERVICE);
	}
	
	protected RestCaller<Category> getRestCallerCategory() {
		return new RestCaller<Category>(RestConstant.REST_SERVICE, RestServiceConstant.CATEGORY_SERVICE);
	}
	
	@Override
	protected List<SearchFilter> convertForFilter(Map<String, String> paramWrapper) {
		return null;
	}
	
	private void setDefaultFilter(HttpServletRequest request, List<SearchFilter> filters) {
		if(!isNotAdvisor()){
			LoginSession userLogin = LoginSessionUtil.getLogin();
			filters.add(new SearchFilter(AdvisoryConsulting.TUTOR_PK, Operator.EQUALS, userLogin.getPkAppUser(),Long.class));
		}
	}

	@Override
	protected List<SearchFilter> convertForFilter(HttpServletRequest request, Map<String, String> paramWrapper, DataTableCriterias columns) {
		List<SearchFilter> filters = new ArrayList<>();
		setDefaultFilter(request, filters);
		return filters;
	}

	@Override
	protected List<SearchOrder> getSearchOrder() {
		if(orders != null) {
			orders.clear();
		}
		orders.add(new SearchOrder(AdvisoryConsulting.PK_ADVISORY, SearchOrder.Sort.DESC));
		return orders;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showList")
	public String showList(ModelMap model, HttpServletRequest request){
		model.addAttribute("pagingWrapper", new PagingWrapper<AppUser>());
		model.addAttribute("isNotAdvisor", isNotAdvisor());
		return getListPath();
	}
	
	public void setDefaultData(ModelMap model) {
		model.addAttribute("advisorOptions", getAllAdvisorOptions());
		model.addAttribute("isNotAdvisor", isNotAdvisor());
		model.addAttribute("userLogin", LoginSessionUtil.getLogin().getPkAppUser());
		List<SearchFilter> filterCategory = new ArrayList<SearchFilter>();
		model.addAttribute("categoryOptions", getAllCategoryOptions(filterCategory));
	}
	
	private List<AppUser> getAllAdvisorOptions() {
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		return getRestCallerUser().findAll(filter, order);
	}
	
	private List<Category> getAllCategoryOptions(List<SearchFilter> filter){
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		return getRestCallerCategory().findAll(filter, order);
	}
	
	private boolean isNotAdvisor(){
		LoginSession userLogin = LoginSessionUtil.getLogin();
		if(userLogin!=null){
			if(userLogin.getUserRoles()!=null){
				for(Long pkRoles : userLogin.getUserRoles()){
					AppRole role = getRestCallerRole().findById(pkRoles);
					if(SystemConstant.UserRole.ADVISOR.equals(role.getCode())){
						return false;
					}
				}
			}
		}
		return true;
	}
	
	private void setStatusCanSeeByAllUser(AdvisoryConsulting anObject){
		if(!isNotAdvisor() && anObject!=null && anObject.getAnswer()!=null){
			anObject.setStatus(SystemConstant.StatusAdvisory.ANSWERED);
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showAdd")
	public String showAdd(ModelMap model, HttpServletRequest request){
		model.addAttribute("detail", AdvisoryConsulting.getInstance());
		setDefaultData(model);
		return PATH_DETAIL;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showEdit")
	public String showEdit(@RequestParam(value="maintenancePK") final Long maintenancePK, @RequestParam Map<String, String> paramWrapper, ModelMap model, HttpServletRequest request){
		setDefaultData(model);
		AdvisoryConsulting detail = getRestCaller().findById(maintenancePK);
		model.addAttribute("detail", detail);
		return PATH_DETAIL;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="saveAdvisory")
	@ResponseBody
	public Map<String, Object> saveAdvisory(final AdvisoryConsulting anObject, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		setStatusCanSeeByAllUser(anObject);
		List<ErrorHolder> errors = new ArrayList<>();
		try{
			errors = new SpecificRestCaller<AdvisoryConsulting>(RestConstant.REST_SERVICE, RestServiceConstant.ADVISORY_SERVICE).performPut("/update", anObject);
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
