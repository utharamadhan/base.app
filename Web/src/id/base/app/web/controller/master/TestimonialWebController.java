package id.base.app.web.controller.master;

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
import id.base.app.valueobject.Pages;
import id.base.app.valueobject.testimonial.Testimonial;
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
@RequestMapping("/master/testimonial")
public class TestimonialWebController extends BaseController<Testimonial> {

	private final String PATH_LIST = "/master/testimonialList";
	private final String PATH_DETAIL = "/master/testimonialDetail";
	
	@Override
	protected RestCaller<Testimonial> getRestCaller() {
		return new RestCaller<Testimonial>(RestConstant.REST_SERVICE, RestServiceConstant.TESTIMONIAL_SERVICE);
	}

	@Override
	protected List<SearchFilter> convertForFilter(
			Map<String, String> paramWrapper) {
		return null;
	}

	private void setDefaultFilter(HttpServletRequest request, List<SearchFilter> filters) {
		filters.add(new SearchFilter(Pages.STATUS, Operator.NOT_EQUAL, ILookupConstant.Status.DELETE, Integer.class));
	}
	
	@Override
	protected List<SearchFilter> convertForFilter(HttpServletRequest request,
			Map<String, String> paramWrapper, DataTableCriterias columns) {
		List<SearchFilter> filters = new ArrayList<>();
		setDefaultFilter(request, filters);
		if(StringFunction.isNotEmpty(columns.getSearch().get(DataTableCriterias.SearchCriterias.value))){
			filters.add(new SearchFilter(Testimonial.NAME, Operator.LIKE, columns.getSearch().get(DataTableCriterias.SearchCriterias.value)));
		}
		return filters;
	}
	
	@Override
	protected List<SearchOrder> getSearchOrder() {
		if(orders != null) {
			orders.clear();
		}
		orders.add(new SearchOrder(Testimonial.PK_TESTIMONIAL, SearchOrder.Sort.DESC));
		return orders;
	}

	@Override
	protected String getListPath() {
		return PATH_LIST;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showList")
	public String showList(ModelMap model, HttpServletRequest request){
		model.addAttribute("pagingWrapper", new PagingWrapper<Testimonial>());
		return getListPath();
	}
	
	public void setDefaultData(ModelMap model) {
		LookupRestCaller lrc = new LookupRestCaller();
		model.addAttribute("statusOptions", lrc.findByLookupGroup(ILookupGroupConstant.STATUS));
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showAdd")
	public String showAdd(ModelMap model, HttpServletRequest request){
		setDefaultData(model);
		model.addAttribute("detail", Testimonial.getInstance());
		return PATH_DETAIL;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showEdit")
	public String showEdit(@RequestParam(value="maintenancePK") final Long maintenancePK, @RequestParam Map<String, String> paramWrapper, ModelMap model, HttpServletRequest request){
		setDefaultData(model);
		Testimonial detail = getRestCaller().findById(maintenancePK);
		model.addAttribute("detail", detail);
		return PATH_DETAIL;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="saveTestimonial")
	@ResponseBody
	public Map<String, Object> saveTestimonial(final Testimonial anObject, final BindingResult bindingResult, final ModelMap model, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		List<ErrorHolder> errors = new ArrayList<>();
		try{
			errors = new SpecificRestCaller<Testimonial>(RestConstant.REST_SERVICE, RestServiceConstant.TESTIMONIAL_SERVICE).performPut("/update", anObject);
			if(errors != null && errors.size() > 0){
				resultMap.put(SystemConstant.ERROR_LIST, errors);
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return resultMap;
	}
}