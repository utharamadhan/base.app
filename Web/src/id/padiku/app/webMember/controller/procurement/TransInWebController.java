package id.padiku.app.webMember.controller.procurement;

import id.padiku.app.ILookupConstant;
import id.padiku.app.ILookupGroupConstant;
import id.padiku.app.SystemConstant;
import id.padiku.app.exception.ErrorHolder;
import id.padiku.app.exception.SystemException;
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
import id.padiku.app.valueobject.master.Company;
import id.padiku.app.valueobject.master.CompanyProduct;
import id.padiku.app.valueobject.party.Party;
import id.padiku.app.valueobject.procurement.TransIn;
import id.padiku.app.valueobject.procurement.TransInFee;
import id.padiku.app.valueobject.procurement.TransInItem;
import id.padiku.app.webMember.DataTableCriterias;
import id.padiku.app.webMember.WebGeneralFunction;
import id.padiku.app.webMember.controller.BaseController;
import id.padiku.app.webMember.rest.CompanyLookupRestCaller;
import id.padiku.app.webMember.rest.CompanyMasterFeeRestCaller;
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
@RequestMapping("/penerimaan")
public class TransInWebController extends BaseController<TransIn> {

	private final String PATH_LIST = "/procurement/transInList";
	private final String PATH_DETAIL = "/procurement/transInDetail";
	
	@Override
	protected RestCaller<TransIn> getRestCaller() {
		return new RestCaller<TransIn>(RestConstant.REST_SERVICE, RestServiceConstant.TRANS_IN_SERVICE);
	}

	@Override
	protected List<SearchFilter> convertForFilter(
			Map<String, String> paramWrapper) {
		return null;
	}

	private void setDefaultFilter(HttpServletRequest request, List<SearchFilter> filters) {
		filters.add(new SearchFilter(TransIn.COMPANY_ID, Operator.EQUALS, WebGeneralFunction.getLogin(request).getCompanySelected(),Long.class));
		filters.add(new SearchFilter(TransIn.STATUS, Operator.NOT_EQUAL, SystemConstant.ValidFlag.INVALID));
	}
	
	@Override
	protected List<SearchFilter> convertForFilter(HttpServletRequest request, Map<String, String> paramWrapper, DataTableCriterias columns) {
		List<SearchFilter> filters = new ArrayList<>();
		setDefaultFilter(request, filters);
		if(StringFunction.isNotEmpty(columns.getSearch().get(DataTableCriterias.SearchCriterias.value))){
			filters.add(new SearchFilter(TransIn.IN_NO, Operator.LIKE, columns.getSearch().get(DataTableCriterias.SearchCriterias.value)));
		}
		return filters;
	}

	@Override
	protected List<SearchOrder> getSearchOrder() {
		if(orders != null) {
			orders.clear();
		}
		orders.add(new SearchOrder(TransIn.ID, SearchOrder.Sort.DESC));
		return orders;
	}

	@Override
	protected String getListPath() {
		return PATH_LIST;
	}
	
	private void setDefaultData(ModelMap model, Long pkCompany) {
		LookupRestCaller lrc = new LookupRestCaller();
		CompanyMasterFeeRestCaller cmf = new CompanyMasterFeeRestCaller();
		//transIn type
		model.addAttribute("transInTypeBuying", SystemConstant.TransInSourceType.BUYING);
		model.addAttribute("transInTypeMaklon", SystemConstant.TransInSourceType.MAKLON);
		//supplier options
		model.addAttribute("optionsSupplier", findSupplierByCompany(pkCompany));
		//termOfPayment
		model.addAttribute("optionsTermOfPayment", new CompanyLookupRestCaller(pkCompany).findByLookupGroup(ILookupGroupConstant.TERM_OF_PAYMENT));
		//product type
		model.addAttribute("usageItemTypeBBP", SystemConstant.UsageItemType.BAHAN_BAKU_PRODUKSI);
		model.addAttribute("usageItemTypeBPP", SystemConstant.UsageItemType.BARANG_PENUNJANG_PRODUKSI);
		//UOM options
		model.addAttribute("optionsUOM", lrc.findByLookupGroup(ILookupGroupConstant.UOM));
		//Comp Product options
		model.addAttribute("optionsProductBPP", getProductOptions(SystemConstant.UsageItemType.BARANG_PENUNJANG_PRODUKSI, pkCompany));
		model.addAttribute("optionsProductBBP", getProductOptions(SystemConstant.UsageItemType.BAHAN_BAKU_PRODUKSI, pkCompany));
		//Master Fee Bahan Baku
		model.addAttribute("feeBBPList", cmf.findAllByFeeType(pkCompany, ILookupConstant.MasterFeeType.PEMBELIAN_BAHAN_BAKU_PRODUKSI));
		model.addAttribute("feeBPPList", cmf.findAllByFeeType(pkCompany, ILookupConstant.MasterFeeType.PEMBELIAN_BARANG_PENUNJANG_PRODUKSI));
	}
	
	private void setDefaultDataEdit(ModelMap model, TransIn transIn){
		if(transIn.getPkTransIn()!=null && 
				SystemConstant.UsageItemType.BAHAN_BAKU_PRODUKSI.equalsIgnoreCase(transIn.getItemTypeCategory())){
			model.addAttribute("bbpItem", transIn.getItems().get(0));
		}else{
			model.addAttribute("bbpItem", new TransInItem());
		}
	}
	
	private List<Party> findSupplierByCompany(final Long pkCompany) {
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
	
	@RequestMapping(method=RequestMethod.GET, value="showList")
	public String showList(ModelMap model, HttpServletRequest request){
		model.addAttribute("pagingWrapper", new PagingWrapper<Company>());
		return getListPath();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showAdd")
	public String showAdd(ModelMap model, HttpServletRequest request){
		Long pkCompany = WebGeneralFunction.getLogin(request).getCompanySelected();
		setDefaultData(model, pkCompany);
		setDefaultDataEdit(model, new TransIn());
		model.addAttribute("detail", new TransIn());
		return PATH_DETAIL;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showEdit")
	public String showEdit(@RequestParam(value="maintenancePK") final Long maintenancePK, @RequestParam Map<String, String> paramWrapper, ModelMap model, HttpServletRequest request){
		setDefaultData(model, WebGeneralFunction.getLogin(request).getCompanySelected());
		TransIn detail = getRestCaller().findById(maintenancePK);
		model.addAttribute("detail", preparingForEdit(detail));
		setDefaultDataEdit(model, detail);
		return PATH_DETAIL;
	}
	
	private TransIn preparingForEdit(TransIn detail) {
		if(detail.getItems() != null && detail.getItems().size() > 0) {
			for(TransInItem item : detail.getItems()) {
				item.setTransIn(null);
			}
		}
		if(detail.getFees() != null && detail.getFees().size() > 0) {
			for(TransInFee fee : detail.getFees()) {
				fee.setTransIn(null);
			}
		}
		return detail;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="saveTransIn")
	@ResponseBody
	public Map<String, Object> saveTransIn(final TransIn anObject, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		List<ErrorHolder> errors = new ArrayList<>();
		anObject.setCompany(Company.getInstance(WebGeneralFunction.getLogin(request).getCompanySelected()));
		try{
			if(anObject.getPkTransIn()==null){
				errors = new SpecificRestCaller<TransIn>(RestConstant.REST_SERVICE, RestServiceConstant.TRANS_IN_SERVICE).performPost("/create", anObject);
			}else{
				errors = new SpecificRestCaller<TransIn>(RestConstant.REST_SERVICE, RestServiceConstant.TRANS_IN_SERVICE).performPut("/update", anObject);
			}
			if(errors != null && errors.size() > 0){
				resultMap.put(SystemConstant.ERROR_LIST, errors);
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return resultMap;
	}

	@RequestMapping(method=RequestMethod.POST, value="deleteTransIn")
	@ResponseBody
	public Map<String, Object> deleteTransIn(final Long[] maintenancePKs) {
		Map<String, Object> resultMap = new HashMap<>();
		try{
			List<ErrorHolder> errors = getRestCaller().delete(maintenancePKs);
			if(errors != null && errors.size() > 0){
				resultMap.put(SystemConstant.ERROR_LIST, errors);
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return resultMap;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="getProductOptions")
	@ResponseBody
	public List<CompanyProduct> getProductOptions(@RequestParam(value="usage") final String usage, HttpServletRequest request) throws SystemException {
		try{
			return getProductOptions(usage, WebGeneralFunction.getLogin(request).getCompanySelected());
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
	
	public List<CompanyProduct> getProductOptions(final String usageItemType, final Long pkCompany) throws SystemException {
		try{
			return new SpecificRestCaller<CompanyProduct>(RestConstant.REST_SERVICE, RestServiceConstant.COMPANY_PRODUCT_SERVICE).executeGetList(new PathInterfaceRestCaller() {
				@Override
				public String getPath() {
					return "/findAllProductByUsageType/{pkCompany}/{usageItemType}";
				}
				
				@Override
				public Map<String, Object> getParameters() {
					Map<String, Object> map = new HashMap<>();
						map.put("pkCompany", pkCompany);
						map.put("usageItemType", usageItemType);
					return map;
				}
			});
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
}
