package id.base.app.web.controller.advisory;

import id.base.app.ILookupConstant;
import id.base.app.ILookupGroupConstant;
import id.base.app.SystemConstant;
import id.base.app.exception.ErrorHolder;
import id.base.app.paging.PagingWrapper;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.rest.SpecificRestCaller;
import id.base.app.util.StringFunction;
import id.base.app.util.dao.Operator;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.Category;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Scope(value="request")
@Controller
@RequestMapping("/advisory/category")
public class AdvisoryCategoryWebController extends BaseController<Category> {

	private final String PATH_LIST = "/advisory/advisoryCategoryList";
	private final String PATH_DETAIL = "/advisory/advisoryCategoryDetail";
	
	@Override
	protected RestCaller<Category> getRestCaller() {
		return new RestCaller<Category>(RestConstant.REST_SERVICE, RestServiceConstant.CATEGORY_SERVICE);
	}

	@Override
	protected List<SearchFilter> convertForFilter(Map<String, String> paramWrapper) {
		return null;
	}
	
	@Override
	protected List<SearchFilter> convertForFilter(HttpServletRequest request, Map<String, String> paramWrapper, DataTableCriterias columns) {
		List<SearchFilter> filters = new ArrayList<>();
		filters.add(new SearchFilter(Category.STATUS, Operator.NOT_EQUAL, ILookupConstant.Status.DELETE, Integer.class));
		filters.add(new SearchFilter(Category.TYPE, Operator.EQUALS, SystemConstant.CategoryType.ADVISORY, String.class));
		if(StringFunction.isNotEmpty(columns.getSearch().get(DataTableCriterias.SearchCriterias.value))){
			filters.add(new SearchFilter(Category.TITLE, Operator.LIKE, columns.getSearch().get(DataTableCriterias.SearchCriterias.value)));
		}
		return filters;
	}

	@Override
	protected List<SearchOrder> getSearchOrder() {
		if(orders != null) {
			orders.clear();
		}
		orders.add(new SearchOrder(Category.ORDER_NO, SearchOrder.Sort.ASC));
		return orders;
	}
	
	@Override
	protected String getListPath() {
		return PATH_LIST;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showList")
	public String showList(ModelMap model, HttpServletRequest request){
		model.addAttribute("pagingWrapper", new PagingWrapper<Category>());
		return getListPath();
	}
	
	public void setDefaultData(ModelMap model) {
		LookupRestCaller lrc = new LookupRestCaller();
		model.addAttribute("statusOptions", lrc.findByLookupGroup(ILookupGroupConstant.STATUS));
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showAdd")
	public String showAdd(ModelMap model, HttpServletRequest request){
		setDefaultData(model);
		model.addAttribute("detail", new Category());
		return PATH_DETAIL;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showEdit")
	public String showEdit(@RequestParam(value="maintenancePK") final Long maintenancePK, @RequestParam Map<String, String> paramWrapper, ModelMap model, HttpServletRequest request){
		setDefaultData(model);
		Category detail = getRestCaller().findById(maintenancePK);
		model.addAttribute("detail", detail);
		return PATH_DETAIL;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="saveCategory")
	@ResponseBody
	public Map<String, Object> saveCategory(final Category anObject, final BindingResult bindingResult, final ModelMap model, HttpServletRequest request, 
			@RequestParam final Map<String, Object> inputMap) {
		Map<String, Object> resultMap = new HashMap<>();
		List<ErrorHolder> errors = new ArrayList<>();
		try{
			anObject.setType(SystemConstant.CategoryType.ADVISORY);
			errors = new SpecificRestCaller<Category>(RestConstant.REST_SERVICE, RestServiceConstant.CATEGORY_SERVICE).performPut("/update", anObject);
			if(errors != null && errors.size() > 0){
				resultMap.put(SystemConstant.ERROR_LIST, errors);
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return resultMap;
	}
}