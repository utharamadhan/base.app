package id.base.app.web.controller.publication;

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
import id.base.app.valueobject.Faq;
import id.base.app.valueobject.publication.HousingIndex;
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
@RequestMapping("/publication/housingIndex")
public class HousingIndexWebController extends BaseController<HousingIndex> {

	private final String PATH_LIST = "/publication/housingIndexList";
	private final String PATH_DETAIL = "/publication/housingIndexDetail";
	
	@Override
	protected RestCaller<HousingIndex> getRestCaller() {
		return new RestCaller<HousingIndex>(RestConstant.REST_SERVICE, RestServiceConstant.HOUSING_INDEX_SERVICE);
	}
	@Override
	protected List<SearchFilter> convertForFilter(
			Map<String, String> paramWrapper) {
		return null;
	}
	
	@Override
	protected List<SearchFilter> convertForFilter(HttpServletRequest request,
			Map<String, String> paramWrapper, DataTableCriterias columns) {
		List<SearchFilter> filters = new ArrayList<>();
		if(StringFunction.isNotEmpty(columns.getSearch().get(DataTableCriterias.SearchCriterias.value))){
			filters.add(new SearchFilter(HousingIndex.TITLE, Operator.LIKE, columns.getSearch().get(DataTableCriterias.SearchCriterias.value)));
		}
		return filters;
	}
	
	@Override
	protected List<SearchOrder> getSearchOrder() {
		if(orders != null) {
			orders.clear();
		}
		orders.add(new SearchOrder(HousingIndex.PK_HOUSING_INDEX, SearchOrder.Sort.DESC));
		return orders;
	}
	
	@Override
	protected String getListPath() {
		return PATH_LIST;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showList")
	public String showList(ModelMap model, HttpServletRequest request){
		model.addAttribute("pagingWrapper", new PagingWrapper<Faq>());
		return getListPath();
	}
	
	public void setDefaultData(ModelMap model, HousingIndex obj) {
		LookupRestCaller lrc = new LookupRestCaller();
		model.addAttribute("statusOptions", lrc.findByLookupGroup(ILookupGroupConstant.ARTICLE_STATUS));
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showAdd")
	public String showAdd(ModelMap model, HttpServletRequest request){
		HousingIndex obj = HousingIndex.getInstance();
		setDefaultData(model, obj);
		model.addAttribute("detail", obj);
		return PATH_DETAIL;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showEdit")
	public String showEdit(@RequestParam(value="maintenancePK") final Long maintenancePK, @RequestParam Map<String, String> paramWrapper, ModelMap model, HttpServletRequest request){
		HousingIndex detail = getRestCaller().findById(maintenancePK);
		setDefaultData(model, detail);
		model.addAttribute("detail", detail);
		return PATH_DETAIL;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="saveHousingIndex")
	@ResponseBody
	public Map<String, Object> saveHousingIndex(final HousingIndex anObject, final BindingResult bindingResult, final ModelMap model, HttpServletRequest request, 
			@RequestParam final Map<String, Object> inputMap) {
		Map<String, Object> resultMap = new HashMap<>();
		List<ErrorHolder> errors = new ArrayList<>();
		try{
			errors = new SpecificRestCaller<HousingIndex>(RestConstant.REST_SERVICE, RestServiceConstant.HOUSING_INDEX_SERVICE).performPut("/update", anObject);
			if(errors != null && errors.size() > 0){
				resultMap.put(SystemConstant.ERROR_LIST, errors);
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return resultMap;
	}
	
}