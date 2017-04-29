package id.base.app.site.controller.web;

import id.base.app.ILookupConstant;
import id.base.app.paging.PagingWrapper;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.util.dao.Operator;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.util.dao.SearchOrder.Sort;
import id.base.app.valueobject.aboutUs.ProgramPost;

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

@Scope(value="request")
@RequestMapping(value="/program")
@Controller
public class ProgramWebController {
	
	static Logger LOGGER = LoggerFactory.getLogger(ProgramWebController.class);
	
	protected RestCaller<ProgramPost> getRestCaller() {
		return new RestCaller<ProgramPost>(RestConstant.REST_SERVICE, RestServiceConstant.PROGRAM_POST_SERVICE);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String view(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="6") int offset,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson
		){
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		filter.add(new SearchFilter(ProgramPost.STATUS, Operator.EQUALS, ILookupConstant.ArticleStatus.PUBLISH, Integer.class));
		if(StringUtils.isNotEmpty(filterJson)){
			filter.add(new SearchFilter(ProgramPost.TITLE, Operator.LIKE, filterJson));
		}
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		order.add(new SearchOrder(ProgramPost.PK_PROGRAM_POST, Sort.DESC));
		PagingWrapper<ProgramPost> program = getRestCaller().findAllByFilter(startNo, offset, filter, order);
		model.addAttribute("program", program);
		return "/program/main";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/load")
	@ResponseBody
	public Map<String, Object> load(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="6") int offset,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson
		){
		Map<String, Object> resultMap = new HashMap<>();
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		filter.add(new SearchFilter(ProgramPost.STATUS, Operator.EQUALS, ILookupConstant.ArticleStatus.PUBLISH, Integer.class));
		if(StringUtils.isNotEmpty(filterJson)){
			filter.add(new SearchFilter(ProgramPost.TITLE, Operator.LIKE, filterJson));
		}
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		order.add(new SearchOrder(ProgramPost.PK_PROGRAM_POST, Sort.DESC));
		PagingWrapper<ProgramPost> program = getRestCaller().findAllByFilter(startNo, offset, filter, order);
		resultMap.put("program", program);
		return resultMap;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/detail/{id}/{title}")
	public String view(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@PathVariable(value="id") Long id,
			@PathVariable(value="title") String title){
		ProgramPost detail = ProgramPost.getInstance();
		detail = getRestCaller().findById(id);
		if(detail!=null){
			if(detail.getTitle()!=null){
				String dataTitle = detail.getTitle().replace(" ", "-");
				if(dataTitle.equals(title)){
					model.addAttribute("detail", detail);
					return "/program/detail";
				}
			}
		}
		LOGGER.error("ERROR DATA NOT VALID");
		return "redirect:/page/notfound";
	}
	
	@ExceptionHandler(TypeMismatchException.class)
	public String handleTypeMismatchException(TypeMismatchException ex) {
		LOGGER.error("ERROR PATH VARIABLE NOT VALID");
		return "redirect:/page/notfound";
	}
}