package id.base.app.site.controller.web;

import id.base.app.ILookupConstant;
import id.base.app.SystemConstant;
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
import id.base.app.valueobject.Category;
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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Scope(value="request")
@RequestMapping(value="/galeri")
@Controller
public class GaleriWebController extends BaseSiteController<LearningItem>{

	static Logger LOGGER = LoggerFactory.getLogger(MainProgramWebController.class);
	
	protected RestCaller<VWLearningItem> getRestCallerView() {
		return new RestCaller<VWLearningItem>(RestConstant.REST_SERVICE, RestServiceConstant.VW_LEARNING_ITEM_SERVICE);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String main(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="6") int offset){
		setCommonData(request,model);
		List<Category> categoryList = getCategoryList();
		model.addAttribute("categories", categoryList);
		PagingWrapper<VWLearningItem> items = getRestCallerView().findAllByFilter(startNo, offset, getDefaultFilter(), getDefaultOrder());
		model.addAttribute("items", items);
		return "/galeri/main";
	}
	
	private List<SearchFilter> getDefaultFilter(){
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		filter.add(new SearchFilter(VWLearningItem.PERIOD, Operator.EQUALS, SystemConstant.Period.PAST, String.class));
		filter.add(new SearchFilter(VWLearningItem.STATUS, Operator.EQUALS, ILookupConstant.Status.PUBLISH, Integer.class));
		return filter;
	}
	
	private List<SearchFilter> convertForFilter(Map<String, String> paramWrapper) {
		List<SearchFilter> filter = getDefaultFilter();
		if(StringUtils.isNotEmpty(paramWrapper.get("category"))){
			filter.add(new SearchFilter(VWLearningItem.PK_CATEGORY, Operator.EQUALS, paramWrapper.get("category"), Long.class));
		}
		if(StringUtils.isNotEmpty(paramWrapper.get("title"))){	
			filter.add(new SearchFilter(VWLearningItem.TITLE, Operator.LIKE, "%"+paramWrapper.get("title")+"%", String.class));
		}
		return filter;
	}
	
	private List<SearchOrder> getDefaultOrder() {
		List<SearchOrder> orders = new ArrayList<SearchOrder>();
		orders.add(new SearchOrder(VWLearningItem.DATE_FROM, SearchOrder.Sort.DESC));
		return orders;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/load")
	@ResponseBody
	public Map<String, Object> load(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="6") int offset,
			@RequestParam Map<String,String> paramWrapper
		){
		Map<String, Object> resultMap = new HashMap<>();
		PagingWrapper<VWLearningItem> items = getRestCallerView().findAllByFilter(startNo, offset, convertForFilter(paramWrapper), getDefaultOrder());
		resultMap.put("items", items);
		return resultMap;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/detail")
	public String learning(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		setCommonData(request,model);
		return "/galeri/detail";
	}
	
	private List<Category> getCategoryList(){
		SpecificRestCaller<Category> rc = new SpecificRestCaller<Category>(RestConstant.REST_SERVICE, RestServiceConstant.CATEGORY_SERVICE);
		List<Category> list = rc.executeGetList(new PathInterfaceRestCaller() {
			
			@Override
			public String getPath() {
				return "/findSimpleDataForSelect/{type}";
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
}
