package id.base.app.webMember.controller.sales;

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
import id.base.app.valueobject.master.Company;
import id.base.app.valueobject.master.CompanyMasterFee;
import id.base.app.valueobject.master.CompanyProduct;
import id.base.app.valueobject.master.Stock;
import id.base.app.valueobject.party.Party;
import id.base.app.valueobject.procurement.TransIn;
import id.base.app.valueobject.sales.TransOut;
import id.base.app.valueobject.sales.TransOutItem;
import id.base.app.webMember.DataTableCriterias;
import id.base.app.webMember.WebGeneralFunction;
import id.base.app.webMember.controller.BaseController;
import id.base.app.webMember.rest.CompanyLookupRestCaller;

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
@RequestMapping("/penjualan")
public class TransOutWebController extends BaseController<TransOut> {

	private final String PATH_LIST = "/penjualan/transOutList";
	private final String PATH_DETAIL = "/penjualan/transOutDetail";
	
	@Override
	protected RestCaller<TransOut> getRestCaller() {
		return new RestCaller<TransOut>(RestConstant.REST_SERVICE, RestServiceConstant.TRANS_OUT_SERVICE);
	}

	@Override
	protected List<SearchFilter> convertForFilter(
			Map<String, String> paramWrapper) {
		return null;
	}

	private void setDefaultFilter(HttpServletRequest request, List<SearchFilter> filters) {
		filters.add(new SearchFilter(TransIn.STATUS, Operator.EQUALS, SystemConstant.ValidFlag.VALID));
	}
	
	@Override
	protected List<SearchFilter> convertForFilter(HttpServletRequest request, Map<String, String> paramWrapper, DataTableCriterias columns) {
		List<SearchFilter> filters = new ArrayList<>();
		setDefaultFilter(request, filters);
		if(StringFunction.isNotEmpty(columns.getSearch().get(DataTableCriterias.SearchCriterias.value))){
			filters.add(new SearchFilter(TransOut.OUT_NO, Operator.LIKE, columns.getSearch().get(DataTableCriterias.SearchCriterias.value)));
		}
		return filters;
	}

	@Override
	protected List<SearchOrder> getSearchOrder() {
		if(orders != null) {
			orders.clear();
		}
		orders.add(new SearchOrder(TransOut.ID, SearchOrder.Sort.DESC));
		return orders;
	}

	@Override
	protected String getListPath() {
		return PATH_LIST;
	}
	
	private void setDefaultData(ModelMap model, Long pkCompany) {
		//supplier options
		model.addAttribute("optionsCustomer", findAllPartyByRole(pkCompany, ILookupConstant.PartyRole.CUSTOMER));
		//pengangkut options
		model.addAttribute("optionsTransporter", findAllPartyByRole(pkCompany, ILookupConstant.PartyRole.TRANSPORTER));
		//termOfPayment
		model.addAttribute("optionsTermOfPayment", new CompanyLookupRestCaller(pkCompany).findByLookupGroup(ILookupGroupConstant.TERM_OF_PAYMENT));
		//Master Fee Bahan Baku
		model.addAttribute("optionsFeeBBP", getFee(ILookupConstant.MasterFeeType.PENJUALAN_BAHAN_BAKU_PRODUKSI, pkCompany));
		model.addAttribute("optionsFeeBPP", getFee(ILookupConstant.MasterFeeType.PENJUALAN_BARANG_PENUNJANG_PRODUKSI, pkCompany));
		//product options
		model.addAttribute("optionsCompanyProductStock", getCompanyProductAvailableStock(pkCompany));
	}
	
	private List<CompanyProduct> getCompanyProductAvailableStock(final Long pkCompany) {
		try{
			return new SpecificRestCaller<CompanyProduct>(RestConstant.REST_SERVICE, RestConstant.RM_STOCK, CompanyProduct.class).executeGetList(new PathInterfaceRestCaller() {
				@Override
				public String getPath() {
					return "/getAvailableCompanyProductByStock/{pkCompany}";
				}
				@Override
				public Map<String, Object> getParameters() {
					Map<String, Object> map = new HashMap<>();
						map.put("pkCompany", pkCompany);
					return map;
				}
			});
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
	
	private List<CompanyMasterFee> getFee(final String masterFeeType, final Long pkCompany) {
		try{
			return new SpecificRestCaller<CompanyMasterFee>(RestConstant.REST_SERVICE, RestServiceConstant.TRANS_OUT_SERVICE).executeGetList(new PathInterfaceRestCaller() {
				@Override
				public String getPath() {
					return "/findAllByFeeType/{pkCompany}/{masterFeeType}";
				}
				@Override
				public Map<String, Object> getParameters() {
					Map<String, Object> map = new HashMap<>();
						map.put("pkCompany", pkCompany);
						map.put("masterFeeType", masterFeeType);
					return map;
				}
			});
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
	
	private List<Party> findAllPartyByRole(final Long pkCompany, final String roleCode) {
		return new SpecificRestCaller<Party>(RestConstant.REST_SERVICE, RestServiceConstant.PARTY).executeGetList(new PathInterfaceRestCaller() {
			@Override
			public String getPath() {
				return "/findAllPartyByRole/{pkCompany}/{roleCode}";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				Map<String, Object> map = new HashMap<>();
					map.put("pkCompany", pkCompany);
					map.put("roleCode", roleCode);
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
		model.addAttribute("detail", TransOut.getInstance(pkCompany));
		return PATH_DETAIL;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showEdit")
	public String showEdit(@RequestParam(value="maintenancePK") final Long maintenancePK, @RequestParam Map<String, String> paramWrapper, ModelMap model, HttpServletRequest request){
		setDefaultData(model, WebGeneralFunction.getLogin(request).getCompanySelected());
		TransOut detail = getRestCaller().findById(maintenancePK);
		model.addAttribute("detail", preparingForEdit(detail));
		return PATH_DETAIL;
	}
	
	private TransOut preparingForEdit(TransOut detail) {
		if(detail.getItems() != null && detail.getItems().size() > 0) {
			for(TransOutItem item : detail.getItems()) {
				item.setTransOut(null);
			}
		}
		return detail;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="saveTransOut")
	@ResponseBody
	public Map<String, Object> saveTransOut(final TransOut anObject) {
		Map<String, Object> resultMap = new HashMap<>();
		List<ErrorHolder> errors = new ArrayList<>();
		try{
			if(anObject.getPkTransOut()==null){
				errors = new SpecificRestCaller<TransOut>(RestConstant.REST_SERVICE, RestServiceConstant.TRANS_OUT_SERVICE).performPost("/create", anObject);
			}else{
				errors = new SpecificRestCaller<TransOut>(RestConstant.REST_SERVICE, RestServiceConstant.TRANS_OUT_SERVICE).performPut("/update", anObject);
			}
			if(errors != null && errors.size() > 1){
				resultMap.put(SystemConstant.ERROR_LIST, errors);
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return resultMap;
	}

	@RequestMapping(method=RequestMethod.POST, value="deleteBuying")
	@ResponseBody
	public Map<String, Object> deleteBuying(final Long[] maintenancePKs) {
		Map<String, Object> resultMap = new HashMap<>();
		try{
			List<ErrorHolder> errors = getRestCaller().delete(maintenancePKs);
			if(errors != null && errors.size() > 1){
				resultMap.put(SystemConstant.ERROR_LIST, errors);
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return resultMap;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="getProductOptions")
	@ResponseBody
	public List<CompanyProduct> getProductOptions(@RequestParam(value="productCode") final String productTypeCode, HttpServletRequest request) throws SystemException {
		try{
			return getProductOptions(productTypeCode, WebGeneralFunction.getLogin(request).getCompanySelected());
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="getStockListByCompanyProduct")
	@ResponseBody
	public List<Stock> getStockListByCompanyProduct(@RequestParam(value="pkCompanyProduct") final Long pkCompanyProduct, @RequestParam(value="tiSourceType") final String tiSourceType, final HttpServletRequest request) {
		try {
			return new SpecificRestCaller<Stock>(RestConstant.REST_SERVICE, RestConstant.RM_STOCK, Stock.class).executeGetList(new PathInterfaceRestCaller() {
				@Override
				public String getPath() {
					return "/getStockListByCompanyProduct/{pkCompany}/{pkCompanyProduct}/{tiSourceType}";
				}
				@Override
				public Map<String, Object> getParameters() {
					Map<String, Object> map = new HashMap<>();
						map.put("pkCompany", WebGeneralFunction.getLogin(request).getCompanySelected());
						map.put("pkCompanyProduct", pkCompanyProduct);
						map.put("tiSourceType", tiSourceType);
					return map;
				}
			});
		}catch(Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
	
	public List<CompanyProduct> getProductOptions(final String productTypeCode, final Long pkCompany) throws SystemException {
		try{
			return new SpecificRestCaller<CompanyProduct>(RestConstant.REST_SERVICE, RestServiceConstant.COMPANY_PRODUCT_SERVICE).executeGetList(new PathInterfaceRestCaller() {
				@Override
				public String getPath() {
					return "/findAllProductByType/{pkCompany}/{productTypeCode}";
				}
				
				@Override
				public Map<String, Object> getParameters() {
					Map<String, Object> map = new HashMap<>();
						map.put("pkCompany", pkCompany);
						map.put("productTypeCode", productTypeCode);
					return map;
				}
			});
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
}
