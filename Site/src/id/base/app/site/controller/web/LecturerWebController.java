package id.base.app.site.controller.web;

import id.base.app.paging.PagingWrapper;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.site.controller.BaseSiteController;
import id.base.app.util.dao.Operator;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.aboutUs.Tutor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Scope(value="request")
@RequestMapping(value="/lecturer")
@Controller
public class LecturerWebController extends BaseSiteController<Tutor>{
	
	static Logger LOGGER = LoggerFactory.getLogger(LecturerWebController.class);
	
	protected ObjectMapper mapper = new ObjectMapper();
	
	protected RestCaller<Tutor> getRestCaller() {
		return new RestCaller<Tutor>(RestConstant.REST_SERVICE, RestServiceConstant.TUTOR_SERVICE);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}/{name}")
	public String view(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@PathVariable(value="id") Long id,
			@PathVariable(value="title") String title){
		Tutor detail = Tutor.getInstance();
		detail = getRestCaller().findById(id);
		if(detail!=null){
			if(detail.getName()!=null){
				String dataTitle = detail.getName().replace(" ", "-");
				if(dataTitle.equals(title)){
					setMenu(model);
					model.addAttribute("detail", detail);
					return "/lecturer/detail";
				}
			}
		}
		LOGGER.error("ERROR DATA NOT VALID");
		return "redirect:/page/notfound";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String view(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="12") int offset,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson
		) throws JsonParseException, JsonMappingException, IOException{
		setMenu(model);
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		if(StringUtils.isNotEmpty(filterJson)){
			filter.add(new SearchFilter(Tutor.NAME, Operator.LIKE, filterJson));
		}
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		PagingWrapper<Tutor> tutors = getRestCaller().findAllByFilter(startNo, offset, filter, order);
		model.addAttribute("tutors", tutors);
		return "/lecturer/main";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/load")
	@ResponseBody
	public Map<String, Object> load(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="12") int offset,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson
		) throws JsonParseException, JsonMappingException, IOException{
		Map<String, Object> resultMap = new HashMap<>();
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		if(StringUtils.isNotEmpty(filterJson)){
			filter.add(new SearchFilter(Tutor.NAME, Operator.LIKE, filterJson));
		}
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		PagingWrapper<Tutor> tutors = getRestCaller().findAllByFilter(startNo, offset, filter, order);
		resultMap.put("tutors", tutors);
		return resultMap;
	}
	
	@ExceptionHandler(TypeMismatchException.class)
	public String handleTypeMismatchException(TypeMismatchException ex) {
		LOGGER.error("ERROR PATH VARIABLE NOT VALID");
		return "redirect:/page/notfound";
	}
	
}
