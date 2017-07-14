package id.base.app.web.controller.student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import id.base.app.SystemConstant;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.rest.PathInterfaceRestCaller;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.rest.SpecificRestCaller;
import id.base.app.util.dao.Operator;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.learning.LearningItem;
import id.base.app.valueobject.learning.StudentCourse;
import id.base.app.valueobject.party.Student;
import id.base.app.valueobject.party.VWStudentList;
import id.base.app.web.DataTableCriterias;
import id.base.app.web.controller.BaseController;

@Scope(value="request")
@Controller
@RequestMapping("/student/updateLearningStatus")
public class UpdateLearningStatusWebController extends BaseController<Student> {

	private final String PATH_LIST = "/student/updateLearningStatusList";
	private final String PATH_DETAIL = "/student/updateLearningStatusList";
	@Override
	protected RestCaller<Student> getRestCaller() {
		return new RestCaller<Student>(RestConstant.REST_SERVICE, RestServiceConstant.STUDENT_SERVICE);
	}

	@Override
	protected List<SearchFilter> convertForFilter(
			Map<String, String> paramWrapper) {
		return null;
	}
	
	private void setDefaultFilter(HttpServletRequest request, List<SearchFilter> filters) {
	}
	
	public void setDefaultData(ModelMap model) {
		model.addAttribute("courseOptions", getAllLearning());
	}

	@Override
	protected List<SearchFilter> convertForFilter(HttpServletRequest request,
			Map<String, String> paramWrapper, DataTableCriterias columns) {
		List<SearchFilter> filters = new ArrayList<>();
		if(paramWrapper.get("course")!=null){
			Long fkCourse = new Long((String) paramWrapper.get("course"));
			filters.add(new SearchFilter(Student.PK_COURSE, Operator.EQUALS, fkCourse, Long.class));
		}
		setDefaultFilter(request, filters);
		return filters;
	}

	@Override
	protected List<SearchOrder> getSearchOrder() {
		if(orders != null) {
			orders.clear();
		}
		return orders;
	}

	@Override
	protected String getListPath() {
		return PATH_LIST;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showList")
	public String showList(ModelMap model, HttpServletRequest request){
		setDefaultData(model);
		model.addAttribute("pagingWrapper", new PagingWrapper<VWStudentList>());
		return getListPath();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/studentList")
	@ResponseBody
	public PagingWrapper<Student> studentList(final ModelMap model, @ModelAttribute DataTableCriterias columns, @RequestParam Map<String,String> paramWrapper,
			HttpServletRequest request){
		boolean emptyList = false;
		if(paramWrapper.containsKey("emptyList")){
			if(paramWrapper.get("emptyList")!=null){
				if("true".equalsIgnoreCase(paramWrapper.get("emptyList"))){
					emptyList = true; 
				}
			}
		}
		PagingWrapper<Student> pw = new PagingWrapper<>();
		if(!emptyList){
			pw = getStudentListPagingWrapper(request, paramWrapper, columns);
		}
		return pw;
	}
	
	public PagingWrapper<Student> getStudentListPagingWrapper(HttpServletRequest request, Map<String,String> paramWrapper, DataTableCriterias columns){
		PagingWrapper<Student> pw = new PagingWrapper<>();
		int[] soff = getStartAndOffset(paramWrapper);
		pw = getRestCaller().findAllByFilter(soff[0], soff[1], convertForFilter(request, paramWrapper, columns), getSearchOrder());
		filters.clear();
		orders.clear();
		return pw;
	}
	
	private List<LearningItem> getAllLearning() {
		return new SpecificRestCaller<LearningItem>(RestConstant.REST_SERVICE, RestConstant.RM_LEARNING_ITEM, LearningItem.class).executeGetList(new PathInterfaceRestCaller() {
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
	
	@RequestMapping(method=RequestMethod.POST, value="processLearning")
	@ResponseBody
	public Map<String, Object> processLearning(@RequestParam(value="itemPK") Long itemPK,@RequestParam(value="courseId") Long courseId, @RequestParam(value="actionType") String actionType) throws SystemException {
		Map<String, Object> resultMap = new HashMap<>();
		List<ErrorHolder> errors = new ArrayList<>();
		try {
			Student student = getRestCaller().findById(itemPK);
			if(student!=null && student.getStudentCourses()!=null){
				for(StudentCourse studentCourse : student.getStudentCourses()){
					if(studentCourse.getCourse()!=null && studentCourse.getCourse().getPkLearningItem()!=null && studentCourse.getCourse().getPkLearningItem().compareTo(courseId)==0){
						errors = new SpecificRestCaller<StudentCourse>(RestConstant.REST_SERVICE, RestConstant.RM_STUDENT, StudentCourse.class).performPut("/processLearning", StudentCourse.getProcessInstance(studentCourse.getPkStudentCourse(), actionType));
						if(errors != null && errors.size() > 0) {
							resultMap.put(SystemConstant.ERROR_LIST, errors);
						}
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return resultMap;
	}

}
