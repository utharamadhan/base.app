package id.base.app.webMember.controller.site;

import java.util.ArrayList;
import java.util.List;

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

import id.base.app.paging.PagingWrapper;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.advisory.Advisory;
import id.base.app.valueobject.research.Research;
import id.base.app.valueobject.research.ResearchTopic;

@Scope(value="request")
@RequestMapping(value="/research-development")
@Controller
public class ResearchDevelopmentWebController {
	
	static Logger LOGGER = LoggerFactory.getLogger(ResearchDevelopmentWebController.class);
	
	protected RestCaller<Research> getRestCaller() {
		return new RestCaller<Research>(RestConstant.REST_SERVICE, RestServiceConstant.RESEARCH_SERVICE);
	}
	
	protected RestCaller<ResearchTopic> getRestCallerTopic() {
		return new RestCaller<ResearchTopic>(RestConstant.REST_SERVICE, RestServiceConstant.RESEARCH_TOPIC_SERVICE);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String view(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="6") int offset,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson){
		return this.list(model, request, response, startNo, offset, filterJson);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/list")
	public String list(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="6") int offset,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson){
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		PagingWrapper<Research> researches = getRestCaller().findAllByFilter(startNo, offset, filter, order);
		List<ResearchTopic> topics = getRestCallerTopic().findAll(null, null);
		model.addAttribute("researches", researches);
		model.addAttribute("topics", topics);
		return "/research-development/list";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/detail/{id}")
	public String detail(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable(value="id") Long id){
		Research detail = getRestCaller().findById(id);
		model.addAttribute("detail", detail);
		return "/research-development/detail";
	}
	
}
