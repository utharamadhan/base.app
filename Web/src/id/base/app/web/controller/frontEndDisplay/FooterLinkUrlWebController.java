package id.base.app.web.controller.frontEndDisplay;

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
import id.base.app.valueobject.Faq;
import id.base.app.valueobject.Lookup;
import id.base.app.valueobject.publication.LinkUrl;
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
@RequestMapping("/fed/footerLinkUrl")
public class FooterLinkUrlWebController extends BaseController<LinkUrl> {

	private final String PATH_LIST = "/fed/footerLinkUrlList";
	private final String PATH_DETAIL = "/fed/footerLinkUrlDetail";
	
	@Override
	protected RestCaller<LinkUrl> getRestCaller() {
		return new RestCaller<LinkUrl>(RestConstant.REST_SERVICE, RestServiceConstant.LINK_URL_SERVICE);
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
		filters.add(new SearchFilter(LinkUrl.TYPE, Operator.EQUALS, SystemConstant.LinkUrlType.FOOTER, String.class));
		if(StringFunction.isNotEmpty(columns.getSearch().get(DataTableCriterias.SearchCriterias.value))){
			filters.add(new SearchFilter(LinkUrl.TITLE, Operator.LIKE, columns.getSearch().get(DataTableCriterias.SearchCriterias.value)));
		}
		return filters;
	}
	
	@Override
	protected List<SearchOrder> getSearchOrder() {
		if(orders != null) {
			orders.clear();
		}
		orders.add(new SearchOrder(LinkUrl.ORDER_NO, SearchOrder.Sort.ASC));
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
	
	public void setDefaultData(ModelMap model, LinkUrl obj) {
		LookupRestCaller lrc = new LookupRestCaller();
		model.addAttribute("statusOptions", lrc.findByLookupGroup(ILookupGroupConstant.STATUS));
		List<Lookup> booleanList = new ArrayList<>();
		booleanList.add(new Lookup().getInstanceShort("0", "No"));
		booleanList.add(new Lookup().getInstanceShort("1", "Yes"));
		model.addAttribute("booleanOptions", booleanList);
		model.addAttribute("parentOptions", getDataParent(obj.getPkLinkUrl()));
	}
	
	private List<LinkUrl> getDataParent(Long pkLinkUrl){
		List<SearchFilter> f = new ArrayList<>();
		f.add(new SearchFilter(LinkUrl.TYPE, Operator.EQUALS, SystemConstant.LinkUrlType.FOOTER, String.class));
		f.add(new SearchFilter(LinkUrl.IS_PARENT, Operator.EQUALS, Boolean.TRUE, Boolean.class));
		f.add(new SearchFilter(LinkUrl.STATUS, Operator.EQUALS, ILookupConstant.Status.PUBLISH, Integer.class));
		if(pkLinkUrl!=null){
			f.add(new SearchFilter(LinkUrl.PK_LINK_URL, Operator.NOT_EQUAL, pkLinkUrl, Long.class));
		}
		List<SearchOrder> o = new ArrayList<>();
		o.add(new SearchOrder(LinkUrl.TITLE, SearchOrder.Sort.ASC));
		return getRestCaller().findAll(f, o);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showAdd")
	public String showAdd(ModelMap model, HttpServletRequest request){
		LinkUrl obj = LinkUrl.getInstance();
		setDefaultData(model, obj);
		model.addAttribute("detail", obj);
		return PATH_DETAIL;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showEdit")
	public String showEdit(@RequestParam(value="maintenancePK") final Long maintenancePK, @RequestParam Map<String, String> paramWrapper, ModelMap model, HttpServletRequest request){
		LinkUrl detail = getRestCaller().findById(maintenancePK);
		setDefaultData(model, detail);
		model.addAttribute("detail", detail);
		return PATH_DETAIL;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="saveFooterLinkUrl")
	@ResponseBody
	public Map<String, Object> saveFooterLinkUrl(final LinkUrl anObject, final BindingResult bindingResult, final ModelMap model, HttpServletRequest request, 
			@RequestParam final Map<String, Object> inputMap) {
		Map<String, Object> resultMap = new HashMap<>();
		List<ErrorHolder> errors = new ArrayList<>();
		try{
			anObject.setType(SystemConstant.LinkUrlType.FOOTER);
			errors = new SpecificRestCaller<LinkUrl>(RestConstant.REST_SERVICE, RestServiceConstant.LINK_URL_SERVICE).performPut("/update", anObject);
			if(errors != null && errors.size() > 0){
				resultMap.put(SystemConstant.ERROR_LIST, errors);
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return resultMap;
	}
	
}