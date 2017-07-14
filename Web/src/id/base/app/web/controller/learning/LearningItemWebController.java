package id.base.app.web.controller.learning;

import id.base.app.ILookupConstant;
import id.base.app.ILookupGroupConstant;
import id.base.app.SystemConstant;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.rest.PathInterfaceRestCaller;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.rest.SpecificRestCaller;
import id.base.app.util.StringFunction;
import id.base.app.util.dao.Operator;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.AppUser;
import id.base.app.valueobject.Category;
import id.base.app.valueobject.Lookup;
import id.base.app.valueobject.learning.LearningItem;
import id.base.app.web.DataTableCriterias;
import id.base.app.web.controller.BaseController;
import id.base.app.web.rest.LookupRestCaller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Scope(value="request")
@Controller
@RequestMapping("/learning/item")
public class LearningItemWebController extends BaseController<LearningItem> {

	private final String PATH_LIST = "/learning/learningItemList";
	private final String PATH_DETAIL = "/learning/learningItemDetail";
	
	@Override
	protected RestCaller<LearningItem> getRestCaller() {
		return new RestCaller<LearningItem>(RestConstant.REST_SERVICE, RestServiceConstant.LEARNING_ITEM_SERVICE);
	}
	
	@Override
	protected List<SearchFilter> convertForFilter(Map<String, String> paramWrapper) {
		return null;
	}
	
	private void setDefaultFilter(HttpServletRequest request, List<SearchFilter> filters) {
		filters.add(new SearchFilter(LearningItem.STATUS, Operator.NOT_EQUAL, ILookupConstant.Status.DELETE, Integer.class));
	}

	@Override
	protected List<SearchFilter> convertForFilter(HttpServletRequest request, Map<String, String> paramWrapper, DataTableCriterias columns) {
		List<SearchFilter> filters = new ArrayList<>();
		setDefaultFilter(request, filters);
		if(StringFunction.isNotEmpty(columns.getSearch().get(DataTableCriterias.SearchCriterias.value))){
			filters.add(new SearchFilter(LearningItem.TITLE, Operator.LIKE, columns.getSearch().get(DataTableCriterias.SearchCriterias.value)));
		}
		return filters;
	}

	@Override
	protected List<SearchOrder> getSearchOrder() {
		if(orders != null) {
			orders.clear();
		}
		orders.add(new SearchOrder(LearningItem.PK_LEARNING_ITEM, SearchOrder.Sort.DESC));
		return orders;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showList")
	public String showList(ModelMap model, HttpServletRequest request){
		model.addAttribute("pagingWrapper", new PagingWrapper<AppUser>());
		return getListPath();
	}
	
	public void setDefaultData(ModelMap model) {
		LookupRestCaller lrc = new LookupRestCaller();
		model.addAttribute("categoryOptions", getCategory());
		model.addAttribute("methodOptions", lrc.findByLookupGroup(ILookupGroupConstant.LEARNING_METHOD));
		model.addAttribute("organizerOptions", lrc.findByLookupGroup(ILookupGroupConstant.LEARNING_ORGANIZER));
		model.addAttribute("paymentOptions", lrc.findByLookupGroup(ILookupGroupConstant.LEARNING_PAYMENT));
		model.addAttribute("statusOptions", lrc.findByLookupGroup(ILookupGroupConstant.STATUS));
		List<Lookup> booleanList = new ArrayList<>();
		booleanList.add(new Lookup().getInstanceShort("0", "No"));
		booleanList.add(new Lookup().getInstanceShort("1", "Yes"));
		model.addAttribute("booleanOptions", booleanList);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showAdd")
	public String showAdd(ModelMap model, HttpServletRequest request){
		model.addAttribute("detail", LearningItem.getInstance());
		setDefaultData(model);
		return PATH_DETAIL;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showEdit")
	public String showEdit(@RequestParam(value="maintenancePK") final Long maintenancePK, @RequestParam Map<String, String> paramWrapper, ModelMap model, HttpServletRequest request){
		setDefaultData(model);
		LearningItem detail = getRestCaller().findById(maintenancePK);
		model.addAttribute("detail", detail);
		return PATH_DETAIL;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="saveItem")
	@ResponseBody
	public Map<String, Object> saveItem(final LearningItem anObject, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		List<ErrorHolder> errors = new ArrayList<>();
		try{
			errors = new SpecificRestCaller<LearningItem>(RestConstant.REST_SERVICE, RestServiceConstant.LEARNING_ITEM_SERVICE).performPut("/update", anObject);
			if(errors != null && errors.size() > 0){
				resultMap.put(SystemConstant.ERROR_LIST, errors);
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return resultMap;
	}
	
	@Override
	protected String getListPath() {
		return PATH_LIST;
	}
	
	private List<Category> getCategory() throws SystemException {
		return new SpecificRestCaller<Category>(RestConstant.REST_SERVICE, RestConstant.RM_CATEGORY, Category.class).executeGetList(new PathInterfaceRestCaller() {
			@Override
			public String getPath() {
				return "/findByType/{type}";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("type", SystemConstant.CategoryType.LEARNING);
				return map;
			}
		});
	}

}
