package id.padiku.app.webMember.controller.master;

import id.padiku.app.ILookupAddressGroupConstant;
import id.padiku.app.ILookupGroupConstant;
import id.padiku.app.SystemConstant;
import id.padiku.app.exception.ErrorHolder;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.rest.PathInterfaceRestCaller;
import id.padiku.app.rest.QueryParamInterfaceRestCaller;
import id.padiku.app.rest.RestCaller;
import id.padiku.app.rest.RestConstant;
import id.padiku.app.rest.RestServiceConstant;
import id.padiku.app.rest.SpecificRestCaller;
import id.padiku.app.util.StringFunction;
import id.padiku.app.util.dao.Operator;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.Lookup;
import id.padiku.app.valueobject.LookupAddress;
import id.padiku.app.valueobject.master.VWCompanyThirdParty;
import id.padiku.app.valueobject.party.Party;
import id.padiku.app.valueobject.party.PartyRole;
import id.padiku.app.webMember.DataTableCriterias;
import id.padiku.app.webMember.WebGeneralFunction;
import id.padiku.app.webMember.controller.BaseController;
import id.padiku.app.webMember.rest.LookupAddressRestCaller;
import id.padiku.app.webMember.rest.LookupRestCaller;

import java.util.ArrayList;
import java.util.Arrays;
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
@RequestMapping("/settings/thirdParty")
public class CompanyThirdPartyWebController extends BaseController<VWCompanyThirdParty> {

	private final String PATH_LIST = "/master/companyThirdPartyList";
	private final String PATH_DETAIL = "/master/companyThirdPartyDetail";
	
	@Override
	protected RestCaller<VWCompanyThirdParty> getRestCaller() {
		return new RestCaller<VWCompanyThirdParty>(RestConstant.REST_SERVICE, RestServiceConstant.COMPANY_THIRD_PARTY_SERVICE);
	}

	@Override
	protected List<SearchFilter> convertForFilter(Map<String, String> paramWrapper) {
		return null;
	}

	@Override
	protected List<SearchFilter> convertForFilter(HttpServletRequest request,
			Map<String, String> paramWrapper, DataTableCriterias columns) {
		List<SearchFilter> filters = new ArrayList<>();
		setDefaultFilter(request, filters);
		if(StringFunction.isNotEmpty(columns.getSearch().get(DataTableCriterias.SearchCriterias.value))){
			filters.add(new SearchFilter(Party.NAME, Operator.LIKE, columns.getSearch().get(DataTableCriterias.SearchCriterias.value)));
		}
		return filters;
	}
	
	private void setDefaultFilter(HttpServletRequest request, List<SearchFilter> filters) {
		filters.add(new SearchFilter("fkCompany", Operator.EQUALS, WebGeneralFunction.getLogin(request).getCompanySelected(),Long.class));
		filters.add(new SearchFilter("status", Operator.EQUALS, SystemConstant.ValidFlag.VALID));
	}

	@Override
	protected List<SearchOrder> getSearchOrder() {List<SearchOrder> orders = new ArrayList<>();
		orders.add(new SearchOrder(Party.NAME, SearchOrder.Sort.ASC));
		return orders;
	}

	@Override
	protected String getListPath() {
		return PATH_LIST;
	}
	
	private void setDefaultData(ModelMap model, Party party) {
		model.addAttribute("optionsThirdParty", new LookupRestCaller().findByLookupGroupAndUsage(ILookupGroupConstant.PARTY_ROLE, SystemConstant.USAGE_THIRD_PARTY));
		model.addAttribute("optionsContactType", new LookupRestCaller().findByLookupGroup(ILookupGroupConstant.CONTACT_TYPE));
		model.addAttribute("provinsiOptions", new LookupAddressRestCaller().findByLookupAddressGroup(ILookupAddressGroupConstant.PROVINSI));
		if(party!=null && party.getPartyAddresses().size()>0){
			model.addAttribute("kabKotaOptions", new LookupAddressRestCaller().findAddressByParent(ILookupAddressGroupConstant.PROVINSI, party.getPartyAddresses().get(0).getProvinsi().getPkLookupAddress()));
			model.addAttribute("kecamatanOptions", new LookupAddressRestCaller().findAddressByParent(ILookupAddressGroupConstant.KAB_KOTA, party.getPartyAddresses().get(0).getKabupatenKota().getPkLookupAddress()));
			model.addAttribute("kelurahanOptions", new LookupAddressRestCaller().findAddressByParent(ILookupAddressGroupConstant.KECAMATAN, party.getPartyAddresses().get(0).getKecamatan().getPkLookupAddress()));
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showList")
	public String showList(ModelMap model, HttpServletRequest request){
		model.addAttribute("pagingWrapper", new PagingWrapper<VWCompanyThirdParty>());
		return getListPath();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showAdd")
	public String showAdd(ModelMap model, HttpServletRequest request){
		model.addAttribute("detail", new Party());
		setDefaultData(model, null);
		return PATH_DETAIL;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showEdit")
	public String showEdit(@RequestParam(value="maintenancePK") final Long maintenancePK, @RequestParam Map<String, String> paramWrapper, ModelMap model){
		Party detail = getThirdCompanyParty(maintenancePK);
		preparingPartyRole(model, detail);
		model.addAttribute("detail", getThirdCompanyParty(maintenancePK));
		setDefaultData(model, detail);
		return PATH_DETAIL;
	}
	
	private Party getThirdCompanyParty(final Long maintenancePK) {
		return new SpecificRestCaller<Party>(RestConstant.REST_SERVICE, RestConstant.RM_COMPANY_THIRD_PARTY, Party.class).executeGet(new PathInterfaceRestCaller() {
			@Override
			public String getPath() {
				return "/getThirdCompanyParty/{maintenancePK}";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				Map<String, Object> map = new HashMap<>();
					map.put("maintenancePK", maintenancePK);
				return map;
			}
		});
	}
	
	private void preparingPartyRole(ModelMap model, Party detail) {
		if(detail != null && detail.getPartyRoles() != null && detail.getPartyRoles().size() > 0) {
			for(PartyRole pr : detail.getPartyRoles()){
				if(pr.getRole() != null && StringFunction.isNotEmpty(pr.getRole().getCode())){
					model.addAttribute("partyRoleCode", pr.getRole().getCode());
				}
			}
		}
	}
	
	private void preparingCompanyRelation(Party anObject, Map<String, String> paramWrapper, HttpServletRequest request) {
		Long pkCompany = WebGeneralFunction.getLogin(request).getCompanySelected();
		anObject.addPartyCompany(anObject, pkCompany);
		
		if(paramWrapper.containsKey("thirdPartyType") && StringFunction.isNotEmpty(paramWrapper.get("thirdPartyType"))) {
			String roleCode = paramWrapper.get("thirdPartyType");
			anObject.addPartyRole(PartyRole.getInstance(Lookup.getInstanceShort(roleCode, null), anObject));
		}
	}
	
	@RequestMapping(method=RequestMethod.POST, value="saveThirdParty")
	@ResponseBody
	public Map<String, Object> saveThirdParty(final Party anObject, @RequestParam Map<String, String> paramWrapper, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		List<ErrorHolder> errors = new ArrayList<>();
		preparingCompanyRelation(anObject, paramWrapper, request);
		anObject.setStatus(SystemConstant.ValidFlag.VALID);
		try{
			if(anObject.getPkParty()==null){
				errors = new SpecificRestCaller<Party>(RestConstant.REST_SERVICE, RestConstant.RM_COMPANY_THIRD_PARTY, Party.class).performPost("/createThirdParty", anObject);
			}else{
				errors = new SpecificRestCaller<Party>(RestConstant.REST_SERVICE, RestConstant.RM_COMPANY_THIRD_PARTY, Party.class).performPut("/updateThirdParty", anObject);
			}
			if(errors != null && errors.size() > 0){
				resultMap.put(SystemConstant.ERROR_LIST, errors);
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			resultMap.put(SystemConstant.ERROR_LIST, new ArrayList<ErrorHolder>(Arrays.asList(new ErrorHolder(e.getMessage()))));
		}
		return resultMap;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="deleteThirdParty")
	@ResponseBody
	public Map<String, Object> deleteThirdParty(final Long[] maintenancePKs) {
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
	
	@RequestMapping(method=RequestMethod.GET, value="/findAddressByParent")
	@ResponseBody
	public List<LookupAddress> findAddressByParent(@RequestParam("addressGroupSource") final String addressGroupSource,  
			@RequestParam("fkLookupAddressParent") final Long fkLookupAddressParent){
		return new LookupAddressRestCaller().findAddressByParent(addressGroupSource, fkLookupAddressParent);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/getKodepos")
	@ResponseBody
	public Integer getKodepos(@RequestParam("kelurahan") final Long fkLookupAddressKelurahan){
		Integer result = null;
		try{
			result = new SpecificRestCaller<Integer>(RestConstant.REST_SERVICE, RestConstant.RM_MASTER_ADDRESS, Integer.class).executeGet(new QueryParamInterfaceRestCaller() {
				@Override
				public String getPath() {
					return "/getKodepos";
				}
				
				@Override
				public Map<String, Object> getParameters() {
					Map<String, Object> map = new HashMap<>();
						map.put("fkLookupAddressKelurahan", fkLookupAddressKelurahan);
					return map;
				}
			});
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return result;
	}
}