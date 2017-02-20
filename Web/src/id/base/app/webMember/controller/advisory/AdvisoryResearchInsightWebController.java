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

import id.base.app.LoginSession;
import id.base.app.SystemConstant;
import id.base.app.exception.ErrorHolder;
import id.base.app.paging.PagingWrapper;
import id.base.app.rest.LoginSessionUtil;
import id.base.app.rest.PathInterfaceRestCaller;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.rest.SpecificRestCaller;
import id.base.app.util.StringFunction;
import id.base.app.util.dao.Operator;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.AppRole;
import id.base.app.valueobject.AppUser;
import id.base.app.valueobject.aboutUs.Tutor;
import id.base.app.valueobject.advisory.Article;
import id.base.app.valueobject.advisory.Category;
import id.base.app.valueobject.course.Tag;
import id.base.app.webMember.DataTableCriterias;
import id.base.app.webMember.controller.BaseController;

@Scope(value="request")
@Controller
@RequestMapping("/advisory/researchInsight")
public class AdvisoryResearchInsightWebController extends BaseController<Article> {

	private final String PATH_LIST = "/advisory/articleList";
	private final String PATH_DETAIL = "/advisory/articleDetail";
	
	@Override
	protected RestCaller<Article> getRestCaller() {
		return new RestCaller<Article>(RestConstant.REST_SERVICE, RestServiceConstant.ADVISORY_ARTICLE_SERVICE);
	}

	protected RestCaller<Category> getRestCallerCategory() {
		return new RestCaller<Category>(RestConstant.REST_SERVICE, RestServiceConstant.ADVISORY_CATEGORY_SERVICE);
	}
	
	protected RestCaller<AppRole> getRestCallerRole() {
		return new RestCaller<AppRole>(RestConstant.REST_SERVICE, RestServiceConstant.ROLE_SERVICE);
	}
	
	protected RestCaller<AppUser> getRestCallerUser() {
		return new RestCaller<AppUser>(RestConstant.REST_SERVICE, RestServiceConstant.USER_SERVICE);
	}
	
	@Override
	protected List<SearchFilter> convertForFilter(Map<String, String> paramWrapper) {
		return null;
	}
	
	private void setDefaultFilter(HttpServletRequest request, List<SearchFilter> filters) {
		filters.add(new SearchFilter(Article.STATUS, Operator.EQUALS, SystemConstant.ValidFlag.VALID));
	}

	@Override
	protected List<SearchFilter> convertForFilter(HttpServletRequest request, Map<String, String> paramWrapper, DataTableCriterias columns) {
		List<SearchFilter> filters = new ArrayList<>();
		setDefaultFilter(request, filters);
		if(StringFunction.isNotEmpty(columns.getSearch().get(DataTableCriterias.SearchCriterias.value))){
			filters.add(new SearchFilter(Article.NAME, Operator.LIKE, columns.getSearch().get(DataTableCriterias.SearchCriterias.value)));
		}
		return filters;
	}

	@Override
	protected List<SearchOrder> getSearchOrder() {
		if(orders != null) {
			orders.clear();
		}
		orders.add(new SearchOrder(Article.PK_ARTICLE, SearchOrder.Sort.DESC));
		return orders;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showList")
	public String showList(ModelMap model, HttpServletRequest request){
		model.addAttribute("pagingWrapper", new PagingWrapper<AppUser>());
		return getListPath();
	}
	
	public void setDefaultData(ModelMap model) {
		List<Category> categories = new ArrayList<Category>();
		categories = getRestCallerCategory().findAll(new ArrayList<SearchFilter>(), new ArrayList<SearchOrder>());
		model.addAttribute("categories", categories);
		
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		LoginSession userLogin = LoginSessionUtil.getLogin();
		boolean exclude = false;
		if(userLogin!=null){
			if(userLogin.getUserRoles()!=null){
				for(Long pkRoles : userLogin.getUserRoles()){
					AppRole role = getRestCallerRole().findById(pkRoles);
					if(SystemConstant.UserRole.ADVISOR.equals(role.getCode())){
						exclude = true;
						break;
					}
				}
			}
			
			if(exclude){
				filter.add(new SearchFilter(AppUser.PK_APP_USER, Operator.NOT_EQUAL, userLogin.getPkAppUser()));
			}
		}
		
		filter.add(new SearchFilter(AppUser.APP_ROLES_CODE, Operator.EQUALS, SystemConstant.UserRole.ADVISOR));
		List<AppUser> advisors = getRestCallerUser().findAll(filter, order);
		model.addAttribute("advisors", advisors);
	}
	
	private List<Tutor> getAllTutorOptions() {
		return new SpecificRestCaller<Tutor>(RestConstant.REST_SERVICE, RestConstant.RM_TUTOR, Tutor.class).executeGetList(new PathInterfaceRestCaller() {
			@Override
			public String getPath() {
				return "/findAllTutorCodeAndName";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				return new HashMap<String, Object>();
			}
		});
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showAdd")
	public String showAdd(ModelMap model, HttpServletRequest request){
		model.addAttribute("detail", Article.getInstance());
		setDefaultData(model);
		return PATH_DETAIL;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showEdit")
	public String showEdit(@RequestParam(value="maintenancePK") final Long maintenancePK, @RequestParam Map<String, String> paramWrapper, ModelMap model, HttpServletRequest request){
		setDefaultData(model);
		Article detail = getRestCaller().findById(maintenancePK);
		model.addAttribute("detail", detail);
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		filter.add(new SearchFilter(AppUser.STATUS, Operator.EQUALS, SystemConstant.ValidFlag.VALID));
		filter.add(new SearchFilter(AppUser.ARTICLE_PK, Operator.EQUALS, maintenancePK, Long.class));
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		model.addAttribute("createdAdvisors", getRestCallerUser().findAll(filter, order));
		return PATH_DETAIL;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="saveArticle")
	@ResponseBody
	public Map<String, Object> saveArticle(final Article anObject, HttpServletRequest request) {
		includeUserLogin(anObject);
		Map<String, Object> resultMap = new HashMap<>();
		List<ErrorHolder> errors = new ArrayList<>();
		try{
			errors = new SpecificRestCaller<Article>(RestConstant.REST_SERVICE, RestServiceConstant.ADVISORY_ARTICLE_SERVICE).performPut("/update", anObject);
			if(errors != null && errors.size() > 0){
				resultMap.put(SystemConstant.ERROR_LIST, errors);
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return resultMap;
	}

	private void includeUserLogin(Article anObject){
		LoginSession userLogin = LoginSessionUtil.getLogin();
		boolean include = false;
		if(userLogin!=null){
			if(userLogin.getUserRoles()!=null){
				for(Long pkRoles : userLogin.getUserRoles()){
					AppRole role = getRestCallerRole().findById(pkRoles);
					if(SystemConstant.UserRole.ADVISOR.equals(role.getCode())){
						include = true;
						break;
					}
				}
			}
			
			if(include){
				AppUser user = getRestCallerUser().findById(userLogin.getPkAppUser());
				if(anObject.getAdvisor()==null){
					anObject.setAdvisor(new ArrayList<AppUser>());
				}
				anObject.getAdvisor().add(user);
			}
		}
	}
	
	@Override
	protected String getListPath() {
		return PATH_LIST;
	}

}
