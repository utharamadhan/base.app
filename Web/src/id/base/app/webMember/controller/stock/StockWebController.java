package id.base.app.webMember.controller.stock;

import id.base.app.ILookupGroupConstant;
import id.base.app.SystemConstant;
import id.base.app.paging.PagingWrapper;
import id.base.app.rest.MapRestCaller;
import id.base.app.rest.PathInterfaceRestCaller;
import id.base.app.rest.QueryParamInterfaceRestCaller;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.rest.SpecificRestCaller;
import id.base.app.util.StringFunction;
import id.base.app.util.dao.Operator;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.Lookup;
import id.base.app.valueobject.master.CompanyWarehouse;
import id.base.app.valueobject.master.Stock;
import id.base.app.valueobject.procurement.TransInItem;
import id.base.app.webMember.DataTableCriterias;
import id.base.app.webMember.WebGeneralFunction;
import id.base.app.webMember.controller.BaseController;
import id.base.app.webMember.rest.LookupRestCaller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Scope(value="request")
@Controller
@RequestMapping("/stock")
public class StockWebController extends BaseController<Stock> {

	private final String PATH_LIST = "/stock/stockList";
	private final String PATH_DETAIL = "/stock/stockDetail";
	
	@Override
	protected RestCaller<Stock> getRestCaller() {
		return new RestCaller<Stock>(RestConstant.REST_SERVICE, RestServiceConstant.STOCK_SERVICE);
	}

	@Override
	protected List<SearchFilter> convertForFilter(
			Map<String, String> paramWrapper) {
		return null;
	}

	@Override
	protected List<SearchFilter> convertForFilter(HttpServletRequest request,
			Map<String, String> paramWrapper, DataTableCriterias columns) {
		return null;
	}
	
	protected List<SearchFilter> defaultConvertForFilter(HttpServletRequest request,
			Map<String, String> paramWrapper, DataTableCriterias columns) {
		filters.add(new SearchFilter(TransInItem.COMPANY_PRODUCT_COMPANY_ALIAS_ID, Operator.EQUALS, WebGeneralFunction.getLogin(request).getCompanySelected(), Long.class));
		filters.add(new SearchFilter(TransInItem.STATUS, Operator.EQUALS, SystemConstant.ValidFlag.VALID));
		filters.add(new SearchFilter(TransInItem.TRANS_IN_STATUS, Operator.EQUALS, SystemConstant.StatusTransInItem.VALID));
		if(StringFunction.isNotEmpty(columns.getSearch().get(DataTableCriterias.SearchCriterias.value))){
			SearchFilter sf1 = new SearchFilter(TransInItem.TRANS_IN_IN_NO, Operator.LIKE, columns.getSearch().get(DataTableCriterias.SearchCriterias.value));
			SearchFilter sf2 = new SearchFilter(TransInItem.COMPANY_PRODUCT_NAME, Operator.LIKE, columns.getSearch().get(DataTableCriterias.SearchCriterias.value));
			SearchFilter sf3 = new SearchFilter(TransInItem.TRANS_IN_THIRD_PARTY_ALIAS_NAME, Operator.LIKE, columns.getSearch().get(DataTableCriterias.SearchCriterias.value));
			filters.add(new SearchFilter(sf1,new SearchFilter(sf2,sf3)));
		}
		return filters;
	}
	
	
	protected List<SearchFilter> convertForFilterBuy(HttpServletRequest request,
			Map<String, String> paramWrapper, DataTableCriterias columns) {
		filters.add(new SearchFilter(TransInItem.TRANS_IN_SOURCE_TYPE, Operator.EQUALS, SystemConstant.TransInSourceType.BUYING));
		defaultConvertForFilter(request, paramWrapper, columns);
		return filters;
	}
	
	protected List<SearchFilter> convertForFilterMakloon(HttpServletRequest request,
			Map<String, String> paramWrapper, DataTableCriterias columns) {
		filters.add(new SearchFilter(TransInItem.TRANS_IN_SOURCE_TYPE, Operator.EQUALS, SystemConstant.TransInSourceType.MAKLON));
		defaultConvertForFilter(request, paramWrapper, columns);
		return filters;
	}

	@Override
	protected List<SearchOrder> getSearchOrder() {
		return null;
	}

	@Override
	protected String getListPath() {
		return PATH_LIST;
	}
	
	private void setDefaultData(ModelMap model, Long pkCompany) {
		LookupRestCaller lrc = new LookupRestCaller();
		model.addAttribute("countBuying", countTransIn(pkCompany, SystemConstant.TransInSourceType.BUYING));
		model.addAttribute("countMaklon", countTransIn(pkCompany, SystemConstant.TransInSourceType.MAKLON));
		model.addAttribute("countProd", countTransProd(pkCompany));
		model.addAttribute("uomList", lrc.findByLookupGroupOrderBy(ILookupGroupConstant.UOM, Lookup.NAME_ID, false));
		model.addAttribute("optionsWarehouse", getAllWarehouse(pkCompany));	
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showList")
	public String showList(ModelMap model, HttpServletRequest request){
		setDefaultData(model, WebGeneralFunction.getLogin(request).getCompanySelected());
		return getListPath();
	}
	
	public PagingWrapper<TransInItem> showJsonCustomList(String inSourceType, final ModelMap model, @ModelAttribute DataTableCriterias columns, @RequestParam Map<String,String> paramWrapper,
			HttpServletRequest request){
		boolean emptyList = false;
		if(paramWrapper.containsKey("emptyList")){
			if(paramWrapper.get("emptyList")!=null){
				if("true".equalsIgnoreCase(paramWrapper.get("emptyList"))){
					emptyList = true; 
				}
			}
		}
		PagingWrapper<TransInItem> pw = new PagingWrapper<TransInItem>();
		if(!emptyList){
			int[] soff = getStartAndOffset(paramWrapper);
			List<SearchFilter> sfList = null;
			if(SystemConstant.TransInSourceType.BUYING.equalsIgnoreCase(inSourceType)){
				sfList = convertForFilterBuy(request, paramWrapper, columns);
			}else if(SystemConstant.TransInSourceType.MAKLON.equalsIgnoreCase(inSourceType)){
				sfList = convertForFilterMakloon(request, paramWrapper, columns);
			}
			pw = new RestCaller<TransInItem>(RestConstant.REST_SERVICE, RestServiceConstant.TRANS_IN_ITEM_SERVICE).findAllByFilter(soff[0], soff[1], sfList, getSearchOrder());
			filters.clear();
			orders.clear();
		}
		return pw;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/listBuy")
	@ResponseBody
	public PagingWrapper<TransInItem> showJsonListBuy(final ModelMap model, @ModelAttribute DataTableCriterias columns, @RequestParam Map<String,String> paramWrapper,
			HttpServletRequest request){
		return showJsonCustomList(SystemConstant.TransInSourceType.BUYING, model, columns, paramWrapper, request);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/listMaklon")
	@ResponseBody
	public PagingWrapper<TransInItem> showJsonListMaklon(final ModelMap model, @ModelAttribute DataTableCriterias columns, @RequestParam Map<String,String> paramWrapper,
			HttpServletRequest request){
		return showJsonCustomList(SystemConstant.TransInSourceType.MAKLON, model, columns, paramWrapper, request);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="addToStock")
	@ResponseBody
	public Map<String, Object> addToStock(@RequestParam(value="combValue") final String combValue,
			@RequestParam(value="warehouseId") final Long warehouseId, @RequestParam(value="currentSource") final String currentSource) {
		Map<String, Object> resultMap = new HashMap<>();
		final HashMap<String, Object> stockMap = new HashMap<String, Object>();
		stockMap.put("combValue", combValue);
		stockMap.put("pkCompanyWarehouse", warehouseId);
		stockMap.put("currentSource", currentSource);
		if(SystemConstant.TransInSourceType.BUYING.equalsIgnoreCase(currentSource) || 
				SystemConstant.TransInSourceType.MAKLON.equalsIgnoreCase(currentSource)){
			try{
				resultMap = new MapRestCaller<String, Object>(RestConstant.REST_SERVICE, RestServiceConstant.STOCK_SERVICE).executePostMap(new QueryParamInterfaceRestCaller() {
					@Override
					public String getPath() {
						return "/addToStock";
					}
					
					@Override
					public Map<String, Object> getParameters() {
						return stockMap;
					}
				}, HashMap.class);
			}catch(Exception e){
				LOGGER.error(e.getMessage(), e);
			}
		}
		return resultMap;
	}
	
	public Long countTransIn(final Long pkCompany, final String transInSourceType){
		Long ct = null;
		try{
			ct = new SpecificRestCaller<Long>(RestConstant.REST_SERVICE, RestConstant.RM_TRANS_IN, Long.class).executeGet(new PathInterfaceRestCaller() {	
				@Override
				public String getPath() {
					return "/countTransIn/{pkCompany}/{transInSourceType}";
				}
				@Override
				public Map<String, Object> getParameters() {
					Map<String,Object> map = new HashMap<String, Object>();
					map.put("pkCompany", pkCompany);
					map.put("transInSourceType", transInSourceType);
					return map;
				}
			});
		}catch(Exception e){
			e.printStackTrace();
		}
		return ct;
	}
	
	public Long countTransProd(final Long pkCompany){
		Long ct = null;
		try{
			ct = new SpecificRestCaller<Long>(RestConstant.REST_SERVICE, RestConstant.RM_TRANS_PROD, Long.class).executeGet(new PathInterfaceRestCaller() {	
				@Override
				public String getPath() {
					return "/countTransProd/{pkCompany}";
				}
				@Override
				public Map<String, Object> getParameters() {
					Map<String,Object> map = new HashMap<String, Object>();
					map.put("pkCompany", pkCompany);
					return map;
				}
			});
		}catch(Exception e){
			e.printStackTrace();
		}
		return ct;
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
