package id.base.app.webMember.controller.inventory;

import id.base.app.ILookupConstant;
import id.base.app.ILookupGroupConstant;
import id.base.app.SystemConstant;
import id.base.app.exception.ErrorHolder;
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
import id.base.app.valueobject.inventory.GoodsReceiptNote;
import id.base.app.valueobject.master.CompanyWarehouse;
import id.base.app.valueobject.party.Party;
import id.base.app.webMember.DataTableCriterias;
import id.base.app.webMember.WebGeneralFunction;
import id.base.app.webMember.controller.BaseController;
import id.base.app.webMember.rest.CompanyLookupRestCaller;
import id.base.app.webMember.rest.LookupRestCaller;

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
@RequestMapping("/inventory/"+RestConstant.RM_GOODS_RECEIPT_NOTE)
public class GoodsReceiptNoteWebController extends BaseController<GoodsReceiptNote> {

	@Override
	protected RestCaller<GoodsReceiptNote> getRestCaller() {
		return new RestCaller<GoodsReceiptNote>(RestConstant.REST_SERVICE, RestServiceConstant.GOODS_RECEIPT_NOTE_SERVICE);
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
			filters.add(new SearchFilter("grnNumber", Operator.LIKE, columns.getSearch().get(DataTableCriterias.SearchCriterias.value)));
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
		return "/inventory/goodsReceiptNoteList";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showList")
	public String showList(ModelMap model){
		model.addAttribute("pw", new PagingWrapper<GoodsReceiptNote>());
		return getListPath();
	}
	
	private List<Party> getAllSupplier(final Long pkCompany) {
		return new SpecificRestCaller<Party>(RestConstant.REST_SERVICE, RestServiceConstant.PARTY).executeGetList(new PathInterfaceRestCaller() {
			@Override
			public String getPath() {
				return "/findAllPartyByRole/{pkCompany}/{roleCode}";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				Map<String, Object> map = new HashMap<>();
					map.put("pkCompany", pkCompany);
					map.put("roleCode", ILookupConstant.PartyRole.SUPPLIER);
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
			model.addAttribute("optionReceivedFrom", rc.findByLookupGroup(ILookupGroupConstant.GOODS_RECEIPT_FROM));
			model.addAttribute("optionSupplier", getAllSupplier(pkCompany));
			model.addAttribute("optionCompanyWarehouse", getAllWarehouse(pkCompany));
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showAdd")
	public String showAdd(ModelMap model, HttpServletRequest request){
		Long pkCompany = WebGeneralFunction.getLogin(request).getCompanySelected();
		setDefaultData(pkCompany, model);
		model.addAttribute("detail", GoodsReceiptNote.getInstance(pkCompany));
		return "/inventory/goodsReceiptNoteDetail";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showEdit")
	public String showEdit(@RequestParam(value="maintenancePK") final Long maintenancePK, ModelMap model, HttpServletRequest request){
		detail = getGoodsReceiptNoteData(maintenancePK);
		setDefaultData(WebGeneralFunction.getLogin(request).getCompanySelected(), model);
		model.addAttribute("detail", detail);
		return "/inventory/goodsReceiptNoteDetail";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="saveGoodsReceiptNote")
	@ResponseBody
	public Map<String, Object> saveGoodsReceiptNote(final GoodsReceiptNote anObject, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		try{
			List<ErrorHolder> errors = new SpecificRestCaller<GoodsReceiptNote>(RestConstant.REST_SERVICE, RestServiceConstant.GOODS_RECEIPT_NOTE_SERVICE).performPost("/create", anObject);
			if(errors != null && errors.size() > 0){
				resultMap.put(SystemConstant.ERROR_LIST, errors);
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return resultMap;
	}
	
	public GoodsReceiptNote getGoodsReceiptNoteData(final Long maintenancePK) {
		return new SpecificRestCaller<GoodsReceiptNote>(RestConstant.REST_SERVICE, RestServiceConstant.GOODS_RECEIPT_NOTE_SERVICE).executeGet(new PathInterfaceRestCaller() {
			@Override
			public String getPath() {
				return "/getGoodsReceiptNoteById/{maintenancePK}";
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