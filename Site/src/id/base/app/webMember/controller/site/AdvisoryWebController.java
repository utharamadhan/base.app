package id.base.app.webMember.controller.site;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.advisory.Advisory;
import id.base.app.valueobject.advisory.AdvisoryMenu;

@Scope(value="request")
@RequestMapping(value="/advisory")
@Controller
public class AdvisoryWebController {
	
	static Logger LOGGER = LoggerFactory.getLogger(AdvisoryWebController.class);
	
	protected RestCaller<Advisory> getRestCaller() {
		return new RestCaller<Advisory>(RestConstant.REST_SERVICE, RestServiceConstant.ADVISORY_SERVICE);
	}
	
	protected RestCaller<AdvisoryMenu> getRestCallerMenu() {
		return new RestCaller<AdvisoryMenu>(RestConstant.REST_SERVICE, RestServiceConstant.ADVISORY_MENU_SERVICE);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String view(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="6") int offset,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson
		){
		List<AdvisoryMenu> menus = getRestCallerMenu().findAll(new ArrayList<SearchFilter>(), new ArrayList<SearchOrder>());
		model.addAttribute("menus", menus);
		return "/advisory/main";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/list")
	public String list(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="10") int offset,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson
		){
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		PagingWrapper<Advisory> advisories = getRestCaller().findAllByFilter(startNo, offset, filter, order);
		model.addAttribute("advisories", advisories);
		return "/advisory/list";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/detail/{id}")
	public String detail(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable(value="id") Long id){
		Advisory detail = getRestCaller().findById(id);
		model.addAttribute("detail", detail);
		return "/advisory/detail";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/list/load")
	@ResponseBody
	public Map<String, Object> load(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="10") int offset,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson
		){
		Map<String, Object> resultMap = new HashMap<>();
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		PagingWrapper<Advisory> advisories = getRestCaller().findAllByFilter(startNo, offset, filter, order);
		resultMap.put("advisories", advisories);
		return resultMap;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/sub/stories")
	public String sub(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="6") int offset,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson
		){
		return "/advisory/stories";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/sub/advisor")
	public String advisor(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="6") int offset,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson
		){
		return "/advisory/advisor";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/sub/article")
	public String article(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="6") int offset,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson
		){
		return "/advisory/article";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/sub/article/detail")
	public String detailArticle(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="6") int offset,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson
		){
		return "/advisory/articleDetail";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/sub/consulting")
	public String consulting(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="6") int offset,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson
		){
		return "/advisory/consulting";
	}
	
}
