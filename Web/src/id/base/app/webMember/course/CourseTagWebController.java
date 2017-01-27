package id.base.app.webMember.course;

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

import id.base.app.ILookupGroupConstant;
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
import id.base.app.valueobject.AppUser;
import id.base.app.valueobject.course.Course;
import id.base.app.valueobject.course.GroupCourse;
import id.base.app.valueobject.course.Tag;
import id.base.app.webMember.DataTableCriterias;
import id.base.app.webMember.controller.BaseController;
import id.base.app.webMember.rest.LookupRestCaller;

@Scope(value="request")
@Controller
@RequestMapping("/course/courseTag")
public class CourseTagWebController extends BaseController<Tag> {

	private final String PATH_LIST = "/course/courseTagList";
	private final String PATH_DETAIL = "/course/courseTagDetail";
	
	@Override
	protected RestCaller<Tag> getRestCaller() {
		return new RestCaller<Tag>(RestConstant.REST_SERVICE, RestServiceConstant.COURSE_TAG_SERVICE);
	}

	@Override
	protected List<SearchFilter> convertForFilter(Map<String, String> paramWrapper) {
		return null;
	}
	
	private void setDefaultFilter(HttpServletRequest request, List<SearchFilter> filters) {
		filters.add(new SearchFilter(Course.STATUS, Operator.EQUALS, SystemConstant.ValidFlag.VALID));
	}

	@Override
	protected List<SearchFilter> convertForFilter(HttpServletRequest request, Map<String, String> paramWrapper, DataTableCriterias columns) {
		List<SearchFilter> filters = new ArrayList<>();
		setDefaultFilter(request, filters);
		if(StringFunction.isNotEmpty(columns.getSearch().get(DataTableCriterias.SearchCriterias.value))){
			filters.add(new SearchFilter(Course.CODE, Operator.LIKE, columns.getSearch().get(DataTableCriterias.SearchCriterias.value)));
		}
		return filters;
	}

	@Override
	protected List<SearchOrder> getSearchOrder() {
		if(orders != null) {
			orders.clear();
		}
		orders.add(new SearchOrder(Course.PK_COURSE, SearchOrder.Sort.DESC));
		return orders;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showList")
	public String showList(ModelMap model, HttpServletRequest request){
		model.addAttribute("pagingWrapper", new PagingWrapper<AppUser>());
		return getListPath();
	}
	
	public void setDefaultData(ModelMap model) {
		LookupRestCaller lrc = new LookupRestCaller();
		model.addAttribute("categoryOptions", lrc.findByLookupGroup(ILookupGroupConstant.COURSE_CATEGORY));
		model.addAttribute("tagOptions", lrc.findByLookupGroup(ILookupGroupConstant.TAG_COURSE));
		model.addAttribute("groupCourseOptions", getAllCourseOptions());
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showAdd")
	public String showAdd(ModelMap model, HttpServletRequest request){
		model.addAttribute("detail", Course.getInstance());
		setDefaultData(model);
		return PATH_DETAIL;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showEdit")
	public String showEdit(@RequestParam(value="maintenancePK") final Long maintenancePK, @RequestParam Map<String, String> paramWrapper, ModelMap model, HttpServletRequest request){
		setDefaultData(model);
		Tag detail = getRestCaller().findById(maintenancePK);
		model.addAttribute("detail", detail);
		return PATH_DETAIL;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="saveCourse")
	@ResponseBody
	public Map<String, Object> saveCourse(final Course anObject, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		List<ErrorHolder> errors = new ArrayList<>();
		try{
			errors = new SpecificRestCaller<Course>(RestConstant.REST_SERVICE, RestServiceConstant.COURSE_SERVICE).performPut("/update", anObject);
			if(errors != null && errors.size() > 0){
				resultMap.put(SystemConstant.ERROR_LIST, errors);
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return resultMap;
	}
	
	private List<GroupCourse> getAllCourseOptions() throws SystemException {
		return new SpecificRestCaller<GroupCourse>(RestConstant.REST_SERVICE, RestConstant.RM_GROUP_COURSE, GroupCourse.class).executeGetList(new PathInterfaceRestCaller() {
			@Override
			public String getPath() {
				return "/findAllGroupCourseCodeName";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				return new HashMap<String, Object>();
			}
		});
	}

	@Override
	protected String getListPath() {
		return PATH_LIST;
	}

}
