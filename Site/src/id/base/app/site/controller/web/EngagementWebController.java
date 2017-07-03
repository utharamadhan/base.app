package id.base.app.site.controller.web;

import id.base.app.ILookupConstant;
import id.base.app.paging.PagingWrapper;
import id.base.app.rest.PathInterfaceRestCaller;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.rest.SpecificRestCaller;
import id.base.app.site.controller.BaseSiteController;
import id.base.app.util.dao.Operator;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.util.dao.SearchOrder.Sort;
import id.base.app.valueobject.aboutUs.Engagement;

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
@RequestMapping(value="/engagement")
@Controller
public class EngagementWebController extends BaseSiteController<Engagement>{
	
	static Logger LOGGER = LoggerFactory.getLogger(EngagementWebController.class);
	
	protected RestCaller<Engagement> getRestCaller() {
		return new RestCaller<Engagement>(RestConstant.REST_SERVICE, RestServiceConstant.ENGAGEMENT_SERVICE);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String view(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="6") int offset,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson
		){
		setMenu(model);
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		filter.add(new SearchFilter(Engagement.STATUS, Operator.EQUALS, ILookupConstant.Status.PUBLISH, Integer.class));
		if(StringUtils.isNotEmpty(filterJson)){
			filter.add(new SearchFilter(Engagement.TITLE, Operator.LIKE, filterJson));
		}
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		order.add(new SearchOrder(Engagement.PK_ENGAGEMENT, Sort.DESC));
		PagingWrapper<Engagement> engages = getRestCaller().findAllByFilter(startNo, offset, filter, order);
		model.addAttribute("engagement", engages);
		return "/engagement/main";
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
		filter.add(new SearchFilter(Engagement.STATUS, Operator.EQUALS, ILookupConstant.Status.PUBLISH, Integer.class));
		if(StringUtils.isNotEmpty(filterJson)){
			filter.add(new SearchFilter(Engagement.TITLE, Operator.LIKE, filterJson));
		}
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		order.add(new SearchOrder(Engagement.PK_ENGAGEMENT, Sort.DESC));
		PagingWrapper<Engagement> engages = getRestCaller().findAllByFilter(startNo, offset, filter, order);
		resultMap.put("engagement", engages);
		return resultMap;
	}
	
	private Engagement findByPermalink(final String permalink){
		Engagement detail = new Engagement();
		try{
			detail = new SpecificRestCaller<Engagement>(RestConstant.REST_SERVICE, RestConstant.RM_ENGAGEMENT, Engagement.class).executeGet(new PathInterfaceRestCaller() {	
				@Override
				public String getPath() {
					return "/findByPermalink/{permalink}";
				}
				
				@Override
				public Map<String, Object> getParameters() {
					Map<String,Object> map = new HashMap<String, Object>();
					map.put("permalink", permalink);
					return map;
				}
			});
			
		}catch(Exception e){
			detail = null;
		}
		return detail;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{permalink}")
	public String detail(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@PathVariable(value="permalink") String permalink){
		Engagement detail = findByPermalink(permalink);
		if(detail!=null){
			setMenu(model);
			model.addAttribute("detail", detail);
			return "/engagement/detail";
		}
		LOGGER.error("ERROR DATA NOT FOUND");
		return "redirect:/page/notfound";
	}
	
	@ExceptionHandler(TypeMismatchException.class)
	public String handleTypeMismatchException(TypeMismatchException ex) {
		LOGGER.error("ERROR PATH VARIABLE NOT VALID");
		return "redirect:/page/notfound";
	}
}