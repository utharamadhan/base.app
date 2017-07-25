package id.base.app.site.controller.web;

import id.base.app.ILookupConstant;
import id.base.app.SystemConstant;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.site.controller.BaseSiteController;
import id.base.app.util.dao.Operator;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.Pages;

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
@RequestMapping(value="/main-program")
@Controller
public class MainProgramWebController extends BaseSiteController<Pages>{

	static Logger LOGGER = LoggerFactory.getLogger(MainProgramWebController.class);
	
	
	private RestCaller<Pages> getRestCaller() {
		return new RestCaller<Pages>(RestConstant.REST_SERVICE, RestServiceConstant.PAGES_SERVICE);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String view(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		setCommonData(model);
		model.addAttribute("programs", findData());
		return "/main-program/main";
	}
	
	private List<Pages> findData(){
		List<SearchFilter> filters = new ArrayList<>();
		List<String> arrType = new ArrayList<>();
		arrType.add(SystemConstant.PagesType.PROGRAM_LEARNING);
		arrType.add(SystemConstant.PagesType.PROGRAM_ADVISORY);
		arrType.add(SystemConstant.PagesType.PROGRAM_RESEARCH_DEVELOPMENT);
		filters.add(new SearchFilter(Pages.TYPE, Operator.IN, arrType, String.class));
		filters.add(new SearchFilter(Pages.STATUS, Operator.EQUALS, ILookupConstant.Status.PUBLISH, Integer.class));
		List<SearchOrder> orders = new ArrayList<>();
		orders.add(new SearchOrder(Pages.ORDER_NO, SearchOrder.Sort.ASC));
		return getRestCaller().findAll(filters, orders);
	}
	
}
