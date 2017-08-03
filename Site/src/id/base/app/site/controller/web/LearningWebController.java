package id.base.app.site.controller.web;

import id.base.app.ILookupConstant;
import id.base.app.ILookupGroupConstant;
import id.base.app.SystemConstant;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.rest.PathInterfaceRestCaller;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.rest.SpecificRestCaller;
import id.base.app.site.controller.BaseSiteController;
import id.base.app.site.rest.LookupRestCaller;
import id.base.app.util.dao.Operator;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.util.dao.SearchOrder.Sort;
import id.base.app.valueobject.Category;
import id.base.app.valueobject.Lookup;
import id.base.app.valueobject.learning.LearningItem;
import id.base.app.valueobject.learning.VWLearningItem;

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
@RequestMapping(value="/main-program/learning")
@Controller
public class LearningWebController extends BaseSiteController<LearningItem>{
	
	static Logger LOGGER = LoggerFactory.getLogger(LearningWebController.class);
	
	protected RestCaller<LearningItem> getRestCaller() {
		return new RestCaller<LearningItem>(RestConstant.REST_SERVICE, RestServiceConstant.LEARNING_ITEM_SERVICE);
	}
	protected RestCaller<VWLearningItem> getRestCallerView() {
		return new RestCaller<VWLearningItem>(RestConstant.REST_SERVICE, RestServiceConstant.VW_LEARNING_ITEM_SERVICE);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String view(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		return "redirect:/page/main-program/learning/"+getFirstPermalinkData();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{permalink}")
	public String main(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable(value="permalink") String permalink,
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="6") int offset,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson){
		setCommonData(model);
		LookupRestCaller lrc = new LookupRestCaller();
		model.addAttribute("permalink", permalink);
		List<Category> categoryList = getCategoryList();
		model.addAttribute("categories", categoryList);
		Boolean foundPermalink = false;
		for (Category category : categoryList) {
			if(permalink.equalsIgnoreCase(category.getPermalink())){
				model.addAttribute("category", category);
				foundPermalink = true;
				break;
			}
		}
		if(foundPermalink){
			List<Lookup> periodOptions = new ArrayList<>();
			periodOptions.add(Lookup.getInstanceShort(SystemConstant.Period.FUTURE, "Periode Yang Akan Datang"));
			periodOptions.add(Lookup.getInstanceShort(SystemConstant.Period.PAST, "Periode Yang Akan Terselenggara"));
			model.addAttribute("periodOptions", periodOptions);
			model.addAttribute("methodOptions", lrc.findByLookupGroup(ILookupGroupConstant.LEARNING_METHOD));
			model.addAttribute("organizerOptions", lrc.findByLookupGroup(ILookupGroupConstant.LEARNING_ORGANIZER));
			model.addAttribute("paymentOptions", lrc.findByLookupGroup(ILookupGroupConstant.LEARNING_PAYMENT));
			List<SearchFilter> filter = new ArrayList<SearchFilter>();
			filter.add(new SearchFilter(VWLearningItem.CATEGORY_PERMALINK, Operator.EQUALS, permalink, String.class));
			filter.add(new SearchFilter(VWLearningItem.STATUS, Operator.EQUALS, ILookupConstant.Status.PUBLISH, Integer.class));
			List<SearchOrder> order = new ArrayList<SearchOrder>();
			order.add(new SearchOrder(VWLearningItem.DATE_FROM, Sort.DESC));
			PagingWrapper<VWLearningItem> items = getRestCallerView().findAllByFilter(startNo, offset, filter, order);
			model.addAttribute("items", items);
			return "/learning/main";
		}else{
			LOGGER.error("ERROR DATA NOT FOUND");
			return "redirect:/page/notfound";
		}
	}
	
	private List<SearchFilter> convertForFilter(String permalink, Map<String, String> paramWrapper) {
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		filter.add(new SearchFilter(VWLearningItem.CATEGORY_PERMALINK, Operator.EQUALS, permalink, String.class));
		filter.add(new SearchFilter(VWLearningItem.STATUS, Operator.EQUALS, ILookupConstant.Status.PUBLISH, Integer.class));
		if(StringUtils.isNotEmpty(paramWrapper.get("period"))){	
			filter.add(new SearchFilter(VWLearningItem.PERIOD, Operator.EQUALS, paramWrapper.get("period"), String.class));
		}
		if(StringUtils.isNotEmpty(paramWrapper.get("method"))){	
			filter.add(new SearchFilter(VWLearningItem.FK_LOOKUP_METHOD, Operator.EQUALS, paramWrapper.get("method"), Long.class));
		}
		if(StringUtils.isNotEmpty(paramWrapper.get("organizer"))){	
			filter.add(new SearchFilter(VWLearningItem.FK_LOOKUP_ORGANIZER, Operator.EQUALS, paramWrapper.get("organizer"), Long.class));
		}
		if(StringUtils.isNotEmpty(paramWrapper.get("payment"))){	
			filter.add(new SearchFilter(VWLearningItem.FK_LOOKUP_PAYMENT, Operator.EQUALS, paramWrapper.get("payment"), Long.class));
		}
		return filter;
	}
	
	private List<SearchOrder> convertForOrder(Map<String, String> paramWrapper) {
		List<SearchOrder> orders = new ArrayList<SearchOrder>();
		orders.add(new SearchOrder(VWLearningItem.DATE_FROM, SearchOrder.Sort.DESC));
		return orders;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/load/{permalink}")
	@ResponseBody
	public Map<String, Object> load(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable(value="permalink") String permalink,
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="6") int offset,
			@RequestParam Map<String,String> paramWrapper
		){
		Map<String, Object> resultMap = new HashMap<>();
		PagingWrapper<VWLearningItem> items = getRestCallerView().findAllByFilter(startNo, offset, convertForFilter(permalink, paramWrapper), convertForOrder(paramWrapper));
		resultMap.put("items", items);
		return resultMap;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="detail/{permalink}")
	public String detail(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@PathVariable(value="permalink") String permalink){
		LearningItem detail = findItemByPermalink(permalink);
		if(detail!=null){
			setCommonData(model);
			model.addAttribute("detail", detail);
			return "/learning/detail";
		}
		LOGGER.error("ERROR DATA NOT FOUND");
		return "redirect:/page/notfound";
	}
	
	@ExceptionHandler(TypeMismatchException.class)
	public String handleTypeMismatchException(TypeMismatchException ex) {
		LOGGER.error("ERROR PATH VARIABLE NOT VALID");
		return "redirect:/page/notfound";
	}
	
	private String getFirstPermalinkData() throws SystemException {
		return new SpecificRestCaller<String>(RestConstant.REST_SERVICE, RestConstant.RM_CATEGORY, String.class).executeGet(new PathInterfaceRestCaller() {
			@Override
			public String getPath() {
				return "/getFirstPermalinkData/{type}";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("type", SystemConstant.CategoryType.LEARNING);
				return map;
			}
		});
	}
	
	private List<Category> getCategoryList(){
		SpecificRestCaller<Category> rc = new SpecificRestCaller<Category>(RestConstant.REST_SERVICE, RestServiceConstant.CATEGORY_SERVICE);
		List<Category> list = rc.executeGetList(new PathInterfaceRestCaller() {
			
			@Override
			public String getPath() {
				return "/findSimpleDataForList/{type}";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("type", SystemConstant.CategoryType.LEARNING);
				return map;
			}
		});
		return list;
	}
	
	private LearningItem findItemByPermalink(final String permalink){
		LearningItem detail = new LearningItem();
		try{
			detail = new SpecificRestCaller<LearningItem>(RestConstant.REST_SERVICE, RestConstant.RM_LEARNING_ITEM, LearningItem.class).executeGet(new PathInterfaceRestCaller() {	
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
	
}