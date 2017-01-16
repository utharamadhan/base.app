package id.base.app.webMember.controller.site;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import id.base.app.paging.PagingWrapper;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.util.dao.Operator;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.course.Course;
import id.base.app.valueobject.course.GroupCourse;
import id.base.app.valueobject.news.News;

@Scope(value="request")
@RequestMapping(value="/learning")
@Controller
public class LearningWebController {
	
	static Logger LOGGER = LoggerFactory.getLogger(LearningWebController.class);
	
	protected RestCaller<GroupCourse> getRestCaller() {
		return new RestCaller<GroupCourse>(RestConstant.REST_SERVICE, RestServiceConstant.GROUP_COURSE_SERVICE);
	}
	
	protected RestCaller<Course> getRestCallerCourse() {
		return new RestCaller<Course>(RestConstant.REST_SERVICE, RestServiceConstant.COURSE_SERVICE);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String view(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		List<GroupCourse> groups = getRestCaller().findAll(filter, order);
		model.addAttribute("groups", groups);
		return "/learning/main";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/detail/{id}")
	public String detail(ModelMap model, HttpServletRequest request, HttpServletResponse response,
				@PathVariable(value="id") Long id
			){
		GroupCourse detail = GroupCourse.getInstance();
		detail = getRestCaller().findById(id);
		model.addAttribute("detail", detail);
		return "/learning/detail";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/program/{title}/{id}")
	public String program(ModelMap model, HttpServletRequest request, HttpServletResponse response,
				@PathVariable(value="id") Long id,
				@PathVariable(value="title") String title,
				@RequestParam(value="startNo",defaultValue="1") int startNo, 
				@RequestParam(value="offset",defaultValue="1") int offset,
				@RequestParam(value="filter", defaultValue="", required=false) String filterJson
			){
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		filter.add(new SearchFilter(Course.PK_GROUP_COURSE, Operator.EQUALS, id, Long.class));
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		PagingWrapper<Course> courses = getRestCallerCourse().findAllByFilter(startNo, offset, filter, order);
		model.addAttribute("title", title.replace("-", " "));
		model.addAttribute("idGroup", id);
		model.addAttribute("courses", courses);
		return "/learning/program";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/programDetail/{titleGroup}/{idGroup}/{title}/{id}")
	public String programDetail(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@PathVariable(value="titleGroup") String titleGroup,
			@PathVariable(value="idGroup") Long idGroup,
			@PathVariable(value="id") Long id,
			@PathVariable(value="title") String title
		){
		Course detail = Course.getInstance();
		detail = getRestCallerCourse().findById(id);
		model.addAttribute("title", title.replace("-", " "));
		model.addAttribute("titleGroup", titleGroup);
		model.addAttribute("idGroup", idGroup);
		model.addAttribute("detail", detail);
		return "/learning/program_detail";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/program/{title}/{id}/load")
	@ResponseBody
	public Map<String, Object> load(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@PathVariable(value="id") Long id,
			@PathVariable(value="title") String title,
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="1") int offset,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson
		){
		Map<String, Object> resultMap = new HashMap<>();
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		filter.add(new SearchFilter(Course.PK_GROUP_COURSE, Operator.EQUALS, id, Long.class));
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		PagingWrapper<Course> courses = getRestCallerCourse().findAllByFilter(startNo, offset, filter, order);
		resultMap.put("courses", courses);
		return resultMap;
	}
	
}
