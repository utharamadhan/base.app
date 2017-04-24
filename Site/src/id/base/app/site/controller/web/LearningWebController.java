package id.base.app.site.controller.web;

import id.base.app.ILookupGroupConstant;
import id.base.app.paging.PagingWrapper;
import id.base.app.rest.QueryParamInterfaceRestCaller;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.rest.SpecificRestCaller;
import id.base.app.site.rest.LookupRestCaller;
import id.base.app.util.dao.Operator;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.Lookup;
import id.base.app.valueobject.course.Course;
import id.base.app.valueobject.course.GroupCourse;
import id.base.app.valueobject.course.Tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	protected RestCaller<Tag> getRestTagCaller() {
		return new RestCaller<Tag>(RestConstant.REST_SERVICE, RestServiceConstant.COURSE_TAG_SERVICE);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String view(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		filter.add(new SearchFilter(GroupCourse.ORDER_NO, Operator.IS_NOT_NULL, null));
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		order.add(new SearchOrder(GroupCourse.ORDER_NO, SearchOrder.Sort.ASC));
		List<GroupCourse> groups = getRestCaller().findAll(filter, order);
		model.addAttribute("groups", groups);
		return "/learning/main";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/detail/{id}/{title}")
	public String detail(ModelMap model, HttpServletRequest request, HttpServletResponse response,
				@PathVariable(value="id") Long id,
				@PathVariable(value="title") String title
			){
		GroupCourse detail = GroupCourse.getInstance();
		detail = getRestCaller().findById(id);
		if(detail!=null){
			if(detail.getTitle()!=null){
				String dataTitle = detail.getTitle().replace(" ", "-");
				if(dataTitle.equals(title)){
					model.addAttribute("detail", detail);
					return "/learning/detail";
				}
			}
		}
		LOGGER.error("ERROR DATA NOT VALID");
		return "redirect:/page/notfound";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/program/{id}/{title}")
	public String program(ModelMap model, HttpServletRequest request, HttpServletResponse response,
				@PathVariable(value="id") final Long id,
				@PathVariable(value="title") String title,
				@RequestParam(value="startNo",defaultValue="1") int startNo, 
				@RequestParam(value="offset",defaultValue="10") int offset,
				@RequestParam(value="filter", defaultValue="", required=false) String filterJson,
				@RequestParam final Map<String, String> paramWrapper
			){
		GroupCourse detail = GroupCourse.getInstance();
		detail = getRestCaller().findById(id);
		if(detail!=null){
			if(detail.getTitle()!=null){
				String dataTitle = detail.getTitle().replace(" ", "-");
				if(dataTitle.equals(title)){
					SpecificRestCaller<Course> rcCourse = new SpecificRestCaller<Course>(RestConstant.REST_SERVICE, RestServiceConstant.COURSE_SERVICE);
					PagingWrapper<Course> courses = rcCourse.executeGetPagingWrapper(startNo, offset, new QueryParamInterfaceRestCaller() {
						
						@Override
						public String getPath() {
							return "/findAllCourseAndTagsPaging";
						}
						
						@Override
						public Map<String, Object> getParameters() {
							Map<String,Object> map = new HashMap<String, Object>();
							map.put("groupCourse", id);
							if(paramWrapper.get("category")!=null && !"".equals(paramWrapper.get("category"))){
								map.put("category", paramWrapper.get("category").toString());
							}
							if(paramWrapper.get("searchParam")!=null && !"".equals(paramWrapper.get("searchParam"))){
								map.put("searchParam", paramWrapper.get("searchParam").toString());
							}
							if(paramWrapper.get("areaParam")!=null && !"".equals(paramWrapper.get("areaParam"))){
								map.put("areaParam", paramWrapper.get("areaParam").toString());
							}
							return map;
						}
					});
					List<Lookup> categories = new LookupRestCaller().findByLookupGroup(ILookupGroupConstant.COURSE_CATEGORY);
					model.addAttribute("categories", categories);
					model.addAttribute("title", title.replace("-", " "));
					model.addAttribute("idGroup", id);
					model.addAttribute("courses", courses);
					return "/learning/program";
				}
			}
		}
		LOGGER.error("ERROR DATA NOT VALID");
		return "redirect:/page/notfound";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/program/detail/{idGroup}/{titleGroup}/{id}/{title}")
	public String programDetail(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@PathVariable(value="titleGroup") String titleGroup,
			@PathVariable(value="idGroup") Long idGroup,
			@PathVariable(value="id") Long id,
			@PathVariable(value="title") String title
		){
		Course detail = Course.getInstance();
		detail = getRestCallerCourse().findById(id);
		if(detail!=null){
			if(detail.getGroupCourse()!=null && detail.getGroupCourse().getPkGroupCourse().compareTo(idGroup)==0 && titleGroup.equals(detail.getGroupCourse().getTitle().replace(" ", "-"))){
				if(detail.getName()!=null){
					String dataTitle = detail.getName().replace(" ", "-");
					if(dataTitle.equals(title)){
						model.addAttribute("title", title.replace("-", " "));
						model.addAttribute("titleGroup", titleGroup);
						model.addAttribute("idGroup", idGroup);
						model.addAttribute("detail", detail);
						return "/learning/programDetail";
					}
				}
			}
		}
		LOGGER.error("ERROR DATA NOT VALID");
		return "redirect:/page/notfound";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/program/{id}/{title}/load")
	@ResponseBody
	public Map<String, Object> load(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@PathVariable(value="id") final Long id,
			@PathVariable(value="title") String title,
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="10") int offset,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson,
			@RequestParam final Map<String, String> paramWrapper
		){
		Map<String, Object> resultMap = new HashMap<>();
		GroupCourse detail = GroupCourse.getInstance();
		detail = getRestCaller().findById(id);
		if(detail!=null){
			if(detail.getTitle()!=null){
				String dataTitle = detail.getTitle().replace(" ", "-");
				if(dataTitle.equals(title)){
					SpecificRestCaller<Course> rcCourse = new SpecificRestCaller<Course>(RestConstant.REST_SERVICE, RestServiceConstant.COURSE_SERVICE);
					PagingWrapper<Course> courses = rcCourse.executeGetPagingWrapper(startNo, offset, new QueryParamInterfaceRestCaller() {
						
						@Override
						public String getPath() {
							return "/findAllCourseAndTagsPaging";
						}
						
						@Override
						public Map<String, Object> getParameters() {
							Map<String,Object> map = new HashMap<String, Object>();
							map.put("groupCourse", id);
							if(paramWrapper.get("category")!=null && !"".equals(paramWrapper.get("category"))){
								map.put("category", paramWrapper.get("category").toString());
							}
							if(paramWrapper.get("searchParam")!=null && !"".equals(paramWrapper.get("searchParam"))){
								map.put("searchParam", paramWrapper.get("searchParam").toString());
							}
							if(paramWrapper.get("areaParam")!=null && !"".equals(paramWrapper.get("areaParam"))){
								map.put("areaParam", paramWrapper.get("areaParam").toString());
							}
							return map;
						}
					});
					resultMap.put("courses", courses);
					return resultMap;
				}
			}
		}
		resultMap.put("errors", "Data Not Found!");
		return resultMap;
	}
	
	@ExceptionHandler(TypeMismatchException.class)
	public String handleTypeMismatchException(TypeMismatchException ex) {
		LOGGER.error("ERROR PATH VARIABLE NOT VALID");
		return "redirect:/page/notfound";
	}
	
}
