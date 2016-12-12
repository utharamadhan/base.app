package id.base.app.webMember.controller.production;

import id.base.app.ILookupConstant;
import id.base.app.ILookupGroupConstant;
import id.base.app.SystemConstant;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.rest.PathInterfaceRestCaller;
import id.base.app.rest.QueryParamInterfaceRestCaller;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.rest.SpecificRestCaller;
import id.base.app.util.DateTimeFunction;
import id.base.app.util.dao.Operator;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.master.Company;
import id.base.app.valueobject.master.CompanyMachinery;
import id.base.app.valueobject.master.CompanyProduct;
import id.base.app.valueobject.master.CompanyWarehouse;
import id.base.app.valueobject.master.Stock;
import id.base.app.valueobject.party.Party;
import id.base.app.valueobject.production.TransProd;
import id.base.app.valueobject.production.TransProdFee;
import id.base.app.valueobject.production.TransProdMachinery;
import id.base.app.webMember.DataTableCriterias;
import id.base.app.webMember.WebGeneralFunction;
import id.base.app.webMember.controller.BaseController;
import id.base.app.webMember.rest.CompanyMasterFeeRestCaller;
import id.base.app.webMember.rest.LookupRestCaller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Scope(value="request")
@Controller
@RequestMapping("/pengolahan")
public class TransProdWebController extends BaseController<TransProd> {

	private final String PATH_CALENDAR = "/production/transProdCalendar";
	private final String PATH_LIST = "/production/transProdList";
	private final String PATH_DETAIL = "/production/transProdDetail";
	
	@Override
	protected RestCaller<TransProd> getRestCaller() {
		return new RestCaller<TransProd>(RestConstant.REST_SERVICE, RestServiceConstant.TRANS_PROD_SERVICE);
	}

	@Override
	protected List<SearchFilter> convertForFilter(
			Map<String, String> paramWrapper) {
		return null;
	}

	private void setDefaultFilter(HttpServletRequest request, List<SearchFilter> filters) {
		filters.add(new SearchFilter(TransProd.STATUS, Operator.EQUALS, SystemConstant.ValidFlag.VALID));
	}
	
	@Override
	protected List<SearchFilter> convertForFilter(HttpServletRequest request,
			Map<String, String> paramWrapper, DataTableCriterias columns) {
		setDefaultFilter(request, filters);
		return filters;
	}

	@Override
	protected List<SearchOrder> getSearchOrder() {
		if(orders != null) {
			orders.clear();
		}
		orders.add(new SearchOrder(TransProd.ID, SearchOrder.Sort.DESC));
		return orders;
	}

	@Override
	protected String getListPath() {
		return PATH_LIST;
	}

	private void setDefaultData(ModelMap model, Long pkCompany) {
		LookupRestCaller lrc = new LookupRestCaller();
		CompanyMasterFeeRestCaller cmf = new CompanyMasterFeeRestCaller();
		//transProd type
		model.addAttribute("actorMandiri", ILookupConstant.ActorProduction.MANDIRI);
		model.addAttribute("actorThirdParty", ILookupConstant.ActorProduction.THIRD_PARTY);
		//transIn Source Type
		model.addAttribute("inSourceTypeBuying", SystemConstant.TransInSourceType.BUYING);
		model.addAttribute("inSourceTypeMaklon", SystemConstant.TransInSourceType.MAKLON);
		//Third Party
		model.addAttribute("optionsProdusen", findProdusenByCompany(pkCompany));
		//options Product
		model.addAttribute("optionsProductBBP", findAllCompanyProductBBP(SystemConstant.UsageItemType.BAHAN_BAKU_PRODUKSI, pkCompany));
		model.addAttribute("optionsProductInStock", findAllExistInStock(pkCompany));
		//uom options
		model.addAttribute("optionsUOM", lrc.findByLookupGroup(ILookupGroupConstant.UOM));
		//machinery List
		model.addAttribute("machineryList", getMachinery(pkCompany));
		//Master Fee
		model.addAttribute("feeList", cmf.findAllByFeeType(pkCompany, ILookupConstant.MasterFeeType.PRODUKSI));
		//Warehouse
		model.addAttribute("optionsWarehouse", getAllWarehouse(pkCompany));
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showList")
	public String showList(ModelMap model, HttpServletRequest request){
		model.addAttribute("pagingWrapper", new PagingWrapper<CompanyMachinery>());
		return getListPath();
	}

	@RequestMapping(method=RequestMethod.GET, value="showCurrentMonth")
	public String showCurrentMonth(ModelMap model, HttpServletRequest request){
		model.addAttribute("month", DateTimeFunction.getCurrentMonth());
		model.addAttribute("year", DateTimeFunction.getCurrentYear());
		return PATH_CALENDAR;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="getDataCalendar/{month}/{year}")
	@ResponseBody
	public List<TransProd> getDataCalendar(@PathVariable(value="month") Integer month, @PathVariable(value="year") Integer year, ModelMap model, HttpServletRequest request){
		try{
			return findAllByYearMonth(year, month, request);
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="saveTransProd")
	@ResponseBody
	public Map<String, Object> saveTransProd(final TransProd anObject, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		List<ErrorHolder> errors = new ArrayList<>();
		Long pkCompany = WebGeneralFunction.getLogin(request).getCompanySelected();
		anObject.setCompany(Company.getInstance(pkCompany));
		try{
			if(anObject.getPkTransProd()==null){
				errors = new SpecificRestCaller<TransProd>(RestConstant.REST_SERVICE, RestServiceConstant.TRANS_PROD_SERVICE).performPost("/create", anObject);
			}else{
				errors = new SpecificRestCaller<TransProd>(RestConstant.REST_SERVICE, RestServiceConstant.TRANS_PROD_SERVICE).performPut("/update", anObject);
			}
			if(errors != null && errors.size() > 1){
				resultMap.put(SystemConstant.ERROR_LIST, errors);
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return resultMap;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showAdd")
	public String showAdd(@RequestParam(value="day") final Integer day, @RequestParam(value="month") final Integer month, 
			@RequestParam(value="year") final Integer year, ModelMap model, HttpServletRequest request){
		Long pkCompany = WebGeneralFunction.getLogin(request).getCompanySelected();
		setDefaultData(model, pkCompany);
		model.addAttribute("day", day);
		model.addAttribute("month", month);
		model.addAttribute("year", year);
		model.addAttribute("detail", TransProd.getInstance(pkCompany));
		return PATH_DETAIL;
	}
	
	private TransProd preparingForEdit(TransProd detail) {
		if(detail.getFees() != null && detail.getFees().size() > 0) {
			for(TransProdFee item : detail.getFees()) {
				item.setTransProd(null);
			}
		}
		if(detail.getMachineries() != null && detail.getMachineries().size() > 0) {
			for(TransProdMachinery item : detail.getMachineries()) {
				item.setTransProd(null);
			}
		}
		return detail;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showEdit")
	public String showEdit(@RequestParam(value="maintenancePK") final Long maintenancePK, @RequestParam Map<String, String> paramWrapper, ModelMap model, HttpServletRequest request){
		setDefaultData(model, WebGeneralFunction.getLogin(request).getCompanySelected());
		TransProd detail = getRestCaller().findById(maintenancePK);
		model.addAttribute("detail", preparingForEdit(detail));
		return PATH_DETAIL;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="getStockListByFilter")
	@ResponseBody
	public List<Stock> getStockListByFilter(@RequestParam(value="pkCompanyProduct") final Long pkCompanyProduct, @RequestParam(value="tiSourceType") final String tiSourceType, 
			 @RequestParam(value="pkTiThirdParty") final String pkTiThirdParty, final HttpServletRequest request) {
		try {
			return new SpecificRestCaller<Stock>(RestConstant.REST_SERVICE, RestConstant.RM_STOCK, Stock.class).executeGetList(new PathInterfaceRestCaller() {
				@Override
				public String getPath() {
					return "/getStockListByFilter/{pkCompany}/{pkCompanyProduct}/{tiSourceType}/{pkTiThirdParty}";
				}
				@Override
				public Map<String, Object> getParameters() {
					Map<String, Object> map = new HashMap<>();
						map.put("pkCompany", WebGeneralFunction.getLogin(request).getCompanySelected());
						map.put("pkCompanyProduct", pkCompanyProduct);
						map.put("tiSourceType", tiSourceType);
						map.put("pkTiThirdParty", pkTiThirdParty);
					return map;
				}
			});
		}catch(Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="getSuplierInStock")
	@ResponseBody
	public List<Party> getSuplierInStock(@RequestParam(value="pkCompanyProduct") final Long pkCompanyProduct, @RequestParam(value="tiSourceType") final String tiSourceType, 
			final HttpServletRequest request) {
		try {
			return new SpecificRestCaller<Party>(RestConstant.REST_SERVICE, RestConstant.RM_STOCK, Party.class).executeGetList(new PathInterfaceRestCaller() {
				@Override
				public String getPath() {
					return "/getSuplierInStock/{pkCompany}/{pkCompanyProduct}/{tiSourceType}";
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
	
	public List<TransProd> findAllByYearMonth(final Integer year, final Integer month, final HttpServletRequest request){
		List<TransProd> prodList = new ArrayList<TransProd>();
		prodList = (List<TransProd>) new SpecificRestCaller<TransProd>(RestConstant.REST_SERVICE, RestServiceConstant.TRANS_PROD_SERVICE).executeGetList(new QueryParamInterfaceRestCaller() {
			
			@Override
			public String getPath() {
				return "/findAllByYearMonth";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("pkCompany", WebGeneralFunction.getLogin(request).getCompanySelected());
				map.put("year", year);
				map.put("month", month);
				return map;
			}
		});
		return prodList;
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
	
	public List<CompanyMachinery> getMachinery(final Long pkCompany) throws SystemException {
		try{
			return new SpecificRestCaller<CompanyMachinery>(RestConstant.REST_SERVICE, RestServiceConstant.COMPANY_MACHINERY_SERVICE).executeGetList(new PathInterfaceRestCaller() {
				@Override
				public String getPath() {
					return "/findAllByPkCompany/{pkCompany}";
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
	
	public List<CompanyProduct> findAllCompanyProductBBP(final String usageItemType, final Long pkCompany) throws SystemException {
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
	
	public List<CompanyProduct> findAllExistInStock(final Long pkCompany) throws SystemException {
		try{
			return new SpecificRestCaller<CompanyProduct>(RestConstant.REST_SERVICE, RestServiceConstant.COMPANY_PRODUCT_SERVICE).executeGetList(new PathInterfaceRestCaller() {
				@Override
				public String getPath() {
					return "/findAllExistInStock/{pkCompany}";
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
	
	private List<Party> findProdusenByCompany(final Long pkCompany) {
		return new SpecificRestCaller<Party>(RestConstant.REST_SERVICE, RestServiceConstant.PARTY).executeGetList(new PathInterfaceRestCaller() {
			@Override
			public String getPath() {
				return "/findAllPartyByRole/{pkCompany}/{roleCode}";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				Map<String, Object> map = new HashMap<>();
					map.put("pkCompany", pkCompany);
					map.put("roleCode", ILookupConstant.PartyRole.PRODUSEN);
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
	
	
}