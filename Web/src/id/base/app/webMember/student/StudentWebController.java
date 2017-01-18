package id.base.app.webMember.student;

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
import id.base.app.valueobject.course.StudentCourse;
import id.base.app.valueobject.party.Student;
import id.base.app.valueobject.party.VWStudentList;
import id.base.app.webMember.DataTableCriterias;
import id.base.app.webMember.controller.BaseController;
import id.base.app.webMember.rest.LookupRestCaller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Scope(value="request")
@Controller
@RequestMapping("/student")
public class StudentWebController extends BaseController<Student> {

	private final String PATH_LIST = "/student/studentList";
	private final String PATH_DETAIL = "/student/studentTab";
	
	@Override
	protected RestCaller<Student> getRestCaller() {
		return new RestCaller<Student>(RestConstant.REST_SERVICE, RestServiceConstant.STUDENT_SERVICE);
	}

	@Override
	protected List<SearchFilter> convertForFilter(Map<String, String> paramWrapper) {
		return null;
	}
	
	private void setDefaultFilter(HttpServletRequest request, List<SearchFilter> filters) {
		filters.add(new SearchFilter(Student.STATUS, Operator.EQUALS, SystemConstant.ValidFlag.VALID));
	}

	@Override
	protected List<SearchFilter> convertForFilter(HttpServletRequest request, Map<String, String> paramWrapper, DataTableCriterias columns) {
		List<SearchFilter> filters = new ArrayList<>();
		setDefaultFilter(request, filters);
		if(StringFunction.isNotEmpty(columns.getCustomFilters().get("filterName"))){
			filters.add(new SearchFilter(VWStudentList.NAME, Operator.LIKE, columns.getCustomFilters().get("filterName")));
		}
		if(StringFunction.isNotEmpty(columns.getCustomFilters().get("filterPhoneNumber"))){
			filters.add(new SearchFilter(VWStudentList.PHONE_NUMBER, Operator.LIKE, columns.getCustomFilters().get("filterPhoneNumber")));
		}
		if(StringFunction.isNotEmpty(columns.getCustomFilters().get("filterEmail"))){
			filters.add(new SearchFilter(VWStudentList.EMAIL, Operator.LIKE, columns.getCustomFilters().get("filterEmail")));
		}
		if(StringFunction.isNotEmpty(columns.getCustomFilters().get("filterStatus"))){
			filters.add(new SearchFilter(VWStudentList.STUDENT_STATUS_LOOKUP_PK, Operator.EQUALS, columns.getCustomFilters().get("filterStatus"), Long.class));
		}
		return filters;
	}

	@Override
	protected List<SearchOrder> getSearchOrder() {
		if(orders != null) {
			orders.clear();
		}
		orders.add(new SearchOrder(Student.PK_STUDENT, SearchOrder.Sort.DESC));
		return orders;
	}
	
	private List<Course> getAllLearning() {
		return new SpecificRestCaller<Course>(RestConstant.REST_SERVICE, RestConstant.RM_COURSE, Course.class).executeGetList(new PathInterfaceRestCaller() {
			@Override
			public String getPath() {
				return "/findAllCourseCodeName";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				return new HashMap<String, Object>();
			}
		});
	}
	
	private void setFilterOptions(ModelMap model) {
		LookupRestCaller lrc = new LookupRestCaller();
		model.addAttribute("studentStatusOptions", lrc.findByLookupGroup(ILookupGroupConstant.STUDENT_STATUS));
		model.addAttribute("learningOptions", getAllLearning());
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showList")
	public String showList(ModelMap model, HttpServletRequest request){
		setFilterOptions(model);
		model.addAttribute("pagingWrapper", new PagingWrapper<AppUser>());
		return getListPath();
	}
	
	public void setDefaultData(ModelMap model) {
		LookupRestCaller lrc = new LookupRestCaller();
		model.addAttribute("studentStatusOptions", lrc.findByLookupGroup(ILookupGroupConstant.STUDENT_STATUS));
		model.addAttribute("genderOptions", lrc.findByLookupGroup(ILookupGroupConstant.GENDER));
		model.addAttribute("courseOptions", getAllLearning());
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showAdd")
	public String showAdd(ModelMap model, HttpServletRequest request){
		model.addAttribute("detail", Student.getInstance());
		model.addAttribute("mode", "creation");
		setDefaultData(model);
		return PATH_DETAIL;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showEdit")
	public String showEdit(@RequestParam(value="maintenancePK") final Long maintenancePK, @RequestParam Map<String, String> paramWrapper, ModelMap model, HttpServletRequest request){
		setDefaultData(model);
		Student detail = getRestCaller().findById(maintenancePK);
		model.addAttribute("mode", "edit");
		model.addAttribute("detail", detail);
		return PATH_DETAIL;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="reloadLearning")
	public String reloadLearning(@RequestParam(value="maintenancePK") final Long maintenancePK, @RequestParam Map<String, String> paramWrapper, ModelMap model, HttpServletRequest request){
		String path = showEdit(maintenancePK, paramWrapper, model, request); 
			model.addAttribute("mode", "learning");
		return path;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="saveStudent")
	@ResponseBody
	public Map<String, Object> saveStudent(final Student anObject, final BindingResult bindingResult, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		List<ErrorHolder> errors = new ArrayList<>();
		try{
			Long pkStudent = (Long) new SpecificRestCaller<Student>(RestConstant.REST_SERVICE, RestServiceConstant.STUDENT_SERVICE).performPutReturn("/updateReturn", anObject, Long.class);
			resultMap.put("maintenancePK", pkStudent);
			if(errors != null && errors.size() > 0){
				resultMap.put(SystemConstant.ERROR_LIST, errors);
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return resultMap;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="enrollCourse")
	@ResponseBody
	public Map<String, Object> enrollCourse(@RequestParam(value="pkStudent") Long pkStudent, @RequestParam(value="pkCourse") Long pkCourse) throws SystemException {
		Map<String, Object> resultMap = new HashMap<>();
		List<ErrorHolder> errors = new ArrayList<>();
		try {
			errors = new SpecificRestCaller<StudentCourse>(RestConstant.REST_SERVICE, RestConstant.RM_STUDENT, StudentCourse.class).performPut("/enrollCourse", StudentCourse.getInstance(pkStudent, pkCourse));
			if(errors != null && errors.size() > 0) {
				resultMap.put(SystemConstant.ERROR_LIST, errors);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return resultMap;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="processLearning")
	@ResponseBody
	public Map<String, Object> processLearning(@RequestParam(value="itemPK") Long itemPK, @RequestParam(value="actionType") String actionType) throws SystemException {
		Map<String, Object> resultMap = new HashMap<>();
		List<ErrorHolder> errors = new ArrayList<>();
		try {
			errors = new SpecificRestCaller<StudentCourse>(RestConstant.REST_SERVICE, RestConstant.RM_STUDENT, StudentCourse.class).performPut("/processLearning", StudentCourse.getProcessInstance(itemPK, actionType));
			if(errors != null && errors.size() > 0) {
				resultMap.put(SystemConstant.ERROR_LIST, errors);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return resultMap;
	}
	
	@Override
	protected String getListPath() {
		return PATH_LIST;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/studentList")
	@ResponseBody
	public PagingWrapper<VWStudentList> studentList(final ModelMap model, @ModelAttribute DataTableCriterias columns, @RequestParam Map<String,String> paramWrapper,
			HttpServletRequest request){
		boolean emptyList = false;
		if(paramWrapper.containsKey("emptyList")){
			if(paramWrapper.get("emptyList")!=null){
				if("true".equalsIgnoreCase(paramWrapper.get("emptyList"))){
					emptyList = true; 
				}
			}
		}
		PagingWrapper<VWStudentList> pw = new PagingWrapper<>();
		if(!emptyList){
			pw = getStudentListPagingWrapper(request, paramWrapper, columns);
		}
		return pw;
	}
	
	public PagingWrapper<VWStudentList> getStudentListPagingWrapper(HttpServletRequest request, Map<String,String> paramWrapper, DataTableCriterias columns){
		PagingWrapper<VWStudentList> pw = new PagingWrapper<>();
		int[] soff = getStartAndOffset(paramWrapper);
		SpecificRestCaller<VWStudentList> contractInternalRestCaller = new SpecificRestCaller<VWStudentList>(RestConstant.REST_SERVICE, RestConstant.RM_STUDENT, VWStudentList.class);
		pw = contractInternalRestCaller.findAllByFilter("/getListByFilter", soff[0], soff[1], convertForFilter(request, paramWrapper, columns), getSearchOrder());
		filters.clear();
		orders.clear();
		return pw;
	}

}
