package id.base.app.site.controller.web;

import id.base.app.ILookupConstant;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.util.dao.Operator;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.util.dao.SearchOrder.Sort;
import id.base.app.valueobject.aboutUs.Engagement;

import java.util.ArrayList;
import java.util.List;

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

@Scope(value="request")
@RequestMapping(value="/engagement")
@Controller
public class EngagementWebController {
	
	static Logger LOGGER = LoggerFactory.getLogger(EngagementWebController.class);
	
	protected RestCaller<Engagement> getRestCaller() {
		return new RestCaller<Engagement>(RestConstant.REST_SERVICE, RestServiceConstant.ENGAGEMENT_SERVICE);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}/{title}")
	public String view(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@PathVariable(value="id") Long id,
			@PathVariable(value="title") String title){
		Engagement detail = getRestCaller().findById(id);
		if(detail!=null){
			if(detail.getTitle()!=null){
				String dataTitle = detail.getTitle().replace(" ", "-");
				if(dataTitle.equals(title)){
					model.addAttribute("detail", detail);
					return "/engagement/main";
				}
			}
		}
		LOGGER.error("ERROR DATA NOT VALID");
		return "redirect:/page/notfound";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/list")
	public String list(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		filter.add(new SearchFilter(Engagement.STATUS, Operator.EQUALS, ILookupConstant.ArticleStatus.PUBLISH, Integer.class));
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		order.add(new SearchOrder(Engagement.PK_ENGAGEMENT, Sort.DESC));
		List<Engagement> engagesList = getRestCaller().findAll(filter, order);
		model.addAttribute("engagesList", engagesList);
		return "/engagement/list";
	}	
	
	@ExceptionHandler(TypeMismatchException.class)
	public String handleTypeMismatchException(TypeMismatchException ex) {
		LOGGER.error("ERROR PATH VARIABLE NOT VALID");
		return "redirect:/page/notfound";
	}
}