package id.base.app.webMember.controller.site;

import id.base.app.ILookupConstant;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.util.dao.Operator;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.util.dao.SearchOrder.Sort;
import id.base.app.valueobject.aboutUs.ProgramPost;

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

@Scope(value="request")
@RequestMapping(value="/program")
@Controller
public class ProgramWebController {
	
	static Logger LOGGER = LoggerFactory.getLogger(ProgramWebController.class);
	
	protected RestCaller<ProgramPost> getRestCaller() {
		return new RestCaller<ProgramPost>(RestConstant.REST_SERVICE, RestServiceConstant.PROGRAM_POST_SERVICE);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public String view(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@PathVariable(value="id") Long id){
		ProgramPost detail = ProgramPost.getInstance();
		detail = getRestCaller().findById(id);
		model.addAttribute("detail", detail);
		return "/program/main";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/list")
	public String list(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		filter.add(new SearchFilter(ProgramPost.STATUS, Operator.EQUALS, ILookupConstant.ArticleStatus.PUBLISH, Integer.class));
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		order.add(new SearchOrder(ProgramPost.PK_PROGRAM_POST, Sort.DESC));
		List<ProgramPost> postList = getRestCaller().findAll(filter, order);
		model.addAttribute("postList", postList);
		return "/program/list";
	}	
}