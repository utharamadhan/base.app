package id.base.app.site.controller.web;

import id.base.app.ILookupConstant;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.util.dao.Operator;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.Faq;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Scope(value="request")
@Controller
@RequestMapping(value="/faq")
public class FaqWebController {

	static Logger LOGGER = LoggerFactory.getLogger(FaqWebController.class);
	
	protected RestCaller<Faq> getRestCaller() {
		return new RestCaller<Faq>(RestConstant.REST_SERVICE, RestServiceConstant.FAQ_SERVICE);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String view(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		filter.add(new SearchFilter(Faq.STATUS, Operator.EQUALS, ILookupConstant.ArticleStatus.PUBLISH));
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		order.add(new SearchOrder(Faq.PK_FAQ, SearchOrder.Sort.ASC));
		model.addAttribute("faq", getRestCaller().findAll(filter, order));
		return "/faq/main";
	}
}
