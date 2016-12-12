package id.padiku.app.webMember.controller.inventory;

import id.padiku.app.ILookupConstant;
import id.padiku.app.ILookupGroupConstant;
import id.padiku.app.SystemConstant;
import id.padiku.app.exception.ErrorHolder;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.rest.PathInterfaceRestCaller;
import id.padiku.app.rest.RestCaller;
import id.padiku.app.rest.RestConstant;
import id.padiku.app.rest.RestServiceConstant;
import id.padiku.app.rest.SpecificRestCaller;
import id.padiku.app.util.StringFunction;
import id.padiku.app.util.dao.Operator;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.inventory.DispatchOrderNote;
import id.padiku.app.valueobject.master.CompanyWarehouse;
import id.padiku.app.valueobject.party.Party;
import id.padiku.app.webMember.DataTableCriterias;
import id.padiku.app.webMember.WebGeneralFunction;
import id.padiku.app.webMember.controller.BaseController;
import id.padiku.app.webMember.rest.CompanyLookupRestCaller;
import id.padiku.app.webMember.rest.LookupRestCaller;

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
@RequestMapping("/inventory/"+RestConstant.RM_DISPATCH_ORDER_NOTE)
public class DispatchOrderNoteWebController extends BaseController<DispatchOrderNote> {

	@Override
	protected RestCaller<DispatchOrderNote> getRestCaller() {
		return new RestCaller<DispatchOrderNote>(RestConstant.REST_SERVICE, RestServiceConstant.DISPATCH_ORDER_NOTE_SERVICE);
	}

	@Override
	protected List<SearchFilter> convertForFilter(Map<String, String> paramWrapper) {
		return new ArrayList<SearchFilter>();
	}

	@Override
	protected List<SearchFilter> convertForFilter(HttpServletRequest request, Map<String, String> paramWrapper, DataTableCriterias columns) {
		List<SearchFilter> filters = new ArrayList<>();
		setDefaultFilter(request, filters);
		if(StringFunction.isNotEmpty(columns.getSearch().get(DataTableCriterias.SearchCriterias.value))){
			filters.add(new SearchFilter("pickingNumber", Operator.LIKE, columns.getSearch().get(DataTableCriterias.SearchCriterias.value)));
		}
		return filters;
	} 
	
	private void setDefaultFilter(HttpServletRequest request, List<SearchFilter> filters) {
		filters.add(new SearchFilter("company.pkCompany", Operator.EQUALS, WebGeneralFunction.getLogin(request).getCompanySelected(), Long.class));
		filters.add(new SearchFilter("status", Operator.EQUALS, SystemConstant.ValidFlag.VALID));
	}

	@Override
	protected List<SearchOrder> getSearchOrder() {
		return new ArrayList<SearchOrder>();
	}

	@Override
	protected String getListPath() {
		return "/inventory/dispatchOrderNoteList";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showList")
	public String showList(ModelMap model){
		model.addAttribute("pw", new PagingWrapper<DispatchOrderNote>());
		return getListPath();
	}
	
	private List<Party> getAllCustomer(final Long pkCompany) {
		return new SpecificRestCaller<Party>(RestConstant.REST_SERVICE, RestServiceConstant.PARTY).executeGetList(new PathInterfaceRestCaller() {
			@Override
			public String getPath() {
				return "/findAllPartyByRole/{pkCompany}/{roleCode}";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				Map<String, Object> map = new HashMap<>();
					map.put("pkCompany", pkCompany);
					map.put("roleCode", ILookupConstant.PartyRole.CUSTOMER);
				return map;
			}
		});
	}
	
	private List<CompanyWarehouse> getAllWarehouse(final Long pkCompany) {
		return new SpecificRestCaller<CompanyWarehouse>(RestConstant.REST_SERVICE, RestServiceConstant.COMPANY_WAREHOUSE_SERVICE).executeGetList(new PathInterfaceRestCaller() {
			@Override
			public String getPath() {
				return "/findAllByCompany/{pkCompany}";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				Map<String, Object> map = new HashMap<>();
					map.put("pkCompany", pkCompany);
				return map;
			}
		});
	}
	
	private void setDefaultData(Long pkCompany, ModelMap model) {
		LookupRestCaller lr = new LookupRestCaller();
			model.addAttribute("optionUOM", lr.findByLookupGroup(ILookupGroupConstant.UOM));
		CompanyLookupRestCaller rc = new CompanyLookupRestCaller(pkCompany);		
			model.addAttribute("optionCurrency", rc.findByLookupGroup(ILookupGroupConstant.CURRENCY));
			model.addAttribute("optionCustomer", getAllCustomer(pkCompany));
			model.addAttribute("optionCompanyWarehouse", getAllWarehouse(pkCompany));
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showAdd")
	public String showAdd(ModelMap model, HttpServletRequest request){
		Long pkCompany = WebGeneralFunction.getLogin(request).getCompanySelected();
		setDefaultData(pkCompany, model);
		model.addAttribute("detail", DispatchOrderNote.getInstance(pkCompany));
		return "/inventory/dispatchOrderNoteDetail";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showEdit")
	public String showEdit(@RequestParam(value="maintenancePK") final Long maintenancePK, ModelMap model, HttpServletRequest request){
		detail = getDispatchOrderNoteData(maintenancePK);
		setDefaultData(WebGeneralFunction.getLogin(request).getCompanySelected(), model);
		model.addAttribute("detail", detail);
		return "/inventory/dispatchOrderNoteDetail";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="saveDispatchOrderNote")
	@ResponseBody
	public Map<String, Object> saveDispatchOrderNote(final DispatchOrderNote anObject, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		try{
			List<ErrorHolder> errors = new SpecificRestCaller<DispatchOrderNote>(RestConstant.REST_SERVICE, RestServiceConstant.DISPATCH_ORDER_NOTE_SERVICE).performPost("/create", anObject);
			if(errors != null && errors.size() > 0){
				resultMap.put(SystemConstant.ERROR_LIST, errors);
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return resultMap;
	}
	
	public DispatchOrderNote getDispatchOrderNoteData(final Long maintenancePK) {
		return new SpecificRestCaller<DispatchOrderNote>(RestConstant.REST_SERVICE, RestServiceConstant.DISPATCH_ORDER_NOTE_SERVICE).executeGet(new PathInterfaceRestCaller() {
			@Override
			public String getPath() {
				return "/getDispatchOrderNoteById/{maintenancePK}";
			}
			@Override
			public Map<String, Object> getParameters() {
				Map<String, Object> map = new HashMap<>();
					map.put("maintenancePK", maintenancePK);
				return map;
			}
		});
	}
	
}