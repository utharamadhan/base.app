package id.base.app.webMember.controller.procurement;

import id.base.app.IAuditTrailConstant;
import id.base.app.ILookupConstant;
import id.base.app.ILookupGroupConstant;
import id.base.app.SystemConstant;
import id.base.app.exception.ErrorHolder;
import id.base.app.paging.PagingWrapper;
import id.base.app.rest.PathInterfaceRestCaller;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.rest.SpecificInterfaceRestCaller;
import id.base.app.rest.SpecificRestCaller;
import id.base.app.util.StringFunction;
import id.base.app.util.dao.Operator;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.AppUser;
import id.base.app.valueobject.Lookup;
import id.base.app.valueobject.party.Party;
import id.base.app.valueobject.procurement.PurchaseOrder;
import id.base.app.webMember.DataTableCriterias;
import id.base.app.webMember.WebGeneralFunction;
import id.base.app.webMember.controller.BaseController;
import id.base.app.webMember.rest.AuditRestCaller;
import id.base.app.webMember.rest.CompanyLookupRestCaller;
import id.base.app.webMember.rest.LookupRestCaller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Scope(value="request")
@Controller
@RequestMapping(RestConstant.RM_PURCHASE_ORDER)
public class PurchaseOrderWebController extends BaseController<PurchaseOrder> {

	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	private AuditRestCaller auditRestCaller;
	
	private AuditRestCaller getAuditService() {
		if(null == auditRestCaller){
			auditRestCaller = new AuditRestCaller();
		}
		return auditRestCaller;
	}
	
	private void constructAudit(String userName, String remoteAddress, String description, String status, int subCode){
		int parentCode = IAuditTrailConstant.LOG_PARENT_MAPPING.get(subCode);
		getAuditService().createAuditWithSubCode(parentCode, description, userName, status, remoteAddress, subCode);
	}
	
	@Override
	protected RestCaller<PurchaseOrder> getRestCaller() {
		return new RestCaller<PurchaseOrder>(RestConstant.REST_SERVICE, RestServiceConstant.PURCHASE_ORDER_SERVICE);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showList")
	public String showList(ModelMap model){
		model.addAttribute("pagingWrapper", new PagingWrapper<PurchaseOrder>());
		return "/procurement/purchaseOrderList";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="openSellerDialogue")
	public String openRMUDialogue(ModelMap model){
		return "/procurement/searchSellerDialogue";
	}
	
	private void setDefaultData(Long pkCompany, ModelMap model) {
		LookupRestCaller lr = new LookupRestCaller();
			model.addAttribute("optionUOM", lr.findByLookupGroup(ILookupGroupConstant.UOM));
		CompanyLookupRestCaller rc = new CompanyLookupRestCaller(pkCompany);		
			model.addAttribute("optionCurrency", rc.findByLookupGroup(ILookupGroupConstant.CURRENCY));
			model.addAttribute("optionTermOfPayment", rc.findByLookupGroup(ILookupGroupConstant.TERM_OF_PAYMENT));
			model.addAttribute("optionSupplier", getAllSupplier(pkCompany));
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
	
	@RequestMapping(method=RequestMethod.GET, value="showAdd")
	public String showAdd(ModelMap model, HttpServletRequest request){
		Long pkCompany = WebGeneralFunction.getLogin(request).getCompanySelected();
		setDefaultData(pkCompany, model);
		model.addAttribute("detail", PurchaseOrder.getInstance(pkCompany));
		return "/procurement/purchaseOrderDetail";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showEdit")
	public String showEdit(@RequestParam(value="maintenancePK") final Long maintenancePK, ModelMap model, HttpServletRequest request){
		detail = getPurchaseOrderData(maintenancePK);
		setDefaultData(WebGeneralFunction.getLogin(request).getCompanySelected(), model);
		model.addAttribute("detail", detail);
		return "/procurement/purchaseOrderDetail";
	}
	
	public PurchaseOrder getPurchaseOrderData(final Long maintenancePK) {
		return new SpecificRestCaller<PurchaseOrder>(RestConstant.REST_SERVICE, RestServiceConstant.PURCHASE_ORDER_SERVICE).executeGet(new PathInterfaceRestCaller() {
			@Override
			public String getPath() {
				return "/getPurchaseOrderById/{maintenancePK}";
			}
			@Override
			public Map<String, Object> getParameters() {
				Map<String, Object> map = new HashMap<>();
					map.put("maintenancePK", maintenancePK);
				return map;
			}
		});
	}
	
	@RequestMapping(method=RequestMethod.POST, value="savePurchaseOrder")
	@ResponseBody
	public Map<String, Object> savePurchaseOrder(final PurchaseOrder anObject, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		try{
			List<ErrorHolder> errors = new SpecificRestCaller<PurchaseOrder>(RestConstant.REST_SERVICE, RestServiceConstant.PURCHASE_ORDER_SERVICE).performPost("/create", anObject);
			if(errors != null && errors.size() > 0){
				resultMap.put(SystemConstant.ERROR_LIST, errors);
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return resultMap;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="editBuying")
	@ResponseBody
	public Map<String, Object> edit(final AppUser anObject){
		Map<String, Object> resultMap = new HashMap<>();
		try{
			List<ErrorHolder> errors = new SpecificRestCaller<AppUser>(RestConstant.REST_SERVICE, RestServiceConstant.USER_SERVICE).performPut("/update", anObject);
			if(errors != null && errors.size() > 1){
				resultMap.put("error", errors);
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return resultMap;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="deleteBulk")
	@ResponseBody
	public Map<String, Object> deleteBulk(@RequestParam(value="pkDelete") final Long[] pkDelete){
		Map<String, Object> resultMap = new HashMap<>();
		try{
			new SpecificRestCaller<AppUser>(RestConstant.REST_SERVICE, RestServiceConstant.USER_SERVICE).executePost(new 	SpecificInterfaceRestCaller() {
				@Override
				public String getPath() {
					return "/deleteBulk";
				}
				
				@Override
				public Map<String, Object> getParameters() {
					Map<String, Object> map = new HashMap<>();
					map.put("pkDelete", pkDelete);
					return map;
				}
			});
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return resultMap;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/getRawMaterial")
	@ResponseBody
	public List<Lookup> getRawMaterial(@RequestParam(value="categoryCode") String categoryCode){
		/*return new LookupRestCaller().findByLookupGroupAndParentCode(ILookupGroupConstant.TYPE_STOCK, categoryCode);*/
		return null;
	}

	@RequestMapping(method=RequestMethod.GET, value="searchProduct")
	@ResponseBody
	public List<Party> searchSeller(HttpServletRequest request, @RequestParam(value="keyword") final String keyword){
		List<Party> productList = null;
		final Long pkCompany = WebGeneralFunction.getLogin(request).getCompanySelected();
		try{
			productList = new SpecificRestCaller<Party>(RestConstant.REST_SERVICE, RestServiceConstant.COMPANY_PRODUCT_SERVICE).executeGetList(new PathInterfaceRestCaller() {
				@Override
				public String getPath() {
					return "/searchProduct/{pkRmu}/{keyword}";
				}
				
				@Override
				public Map<String, Object> getParameters() {
					Map<String, Object> map = new HashMap<>();
						map.put("pkRmu", pkCompany);
						map.put("keyword", keyword);
					return map;
				}
			});
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return productList;
	}
	
	@Override
	protected List<SearchFilter> convertForFilter(Map<String, String> paramWrapper) {
		return filters;
	}

	@Override
	protected List<SearchOrder> getSearchOrder() {
		return orders;
	}

	@Override
	protected String getListPath() {
		return "/parameter/systemParameterList";
	}

	@Override
	protected List<SearchFilter> convertForFilter(HttpServletRequest request, Map<String, String> paramWrapper, DataTableCriterias columns) {
		List<SearchFilter> filters = new ArrayList<>();
		setDefaultFilter(request, filters);
		if(StringFunction.isNotEmpty(columns.getSearch().get(DataTableCriterias.SearchCriterias.value))){
			filters.add(new SearchFilter("poNumber", Operator.LIKE, columns.getSearch().get(DataTableCriterias.SearchCriterias.value)));
		}
		return filters;
	} 
	
	private void setDefaultFilter(HttpServletRequest request, List<SearchFilter> filters) {
		filters.add(new SearchFilter("company.pkCompany", Operator.EQUALS, WebGeneralFunction.getLogin(request).getCompanySelected(), Long.class));
		filters.add(new SearchFilter("status", Operator.EQUALS, SystemConstant.ValidFlag.VALID));
	}
	
}