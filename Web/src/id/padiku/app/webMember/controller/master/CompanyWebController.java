package id.padiku.app.webMember.controller.master;

import id.padiku.app.ILookupAddressGroupConstant;
import id.padiku.app.ILookupGroupConstant;
import id.padiku.app.SystemConstant;
import id.padiku.app.exception.ErrorHolder;
import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.rest.QueryParamInterfaceRestCaller;
import id.padiku.app.rest.RestCaller;
import id.padiku.app.rest.RestConstant;
import id.padiku.app.rest.RestServiceConstant;
import id.padiku.app.rest.SpecificRestCaller;
import id.padiku.app.util.StringFunction;
import id.padiku.app.util.dao.Operator;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.LookupAddress;
import id.padiku.app.valueobject.master.Company;
import id.padiku.app.valueobject.party.PartyCompany;
import id.padiku.app.webMember.DataTableCriterias;
import id.padiku.app.webMember.WebGeneralFunction;
import id.padiku.app.webMember.controller.BaseController;
import id.padiku.app.webMember.rest.LookupAddressRestCaller;
import id.padiku.app.webMember.rest.LookupRestCaller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
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
@RequestMapping("/settings/company")
public class CompanyWebController extends BaseController<Company> {
	
	private final String PATH_LIST = "/master/companyList";
	private final String PATH_DETAIL = "/master/companyDetail";
	
	@Override
	protected RestCaller<Company> getRestCaller() {
		return new RestCaller<Company>(RestConstant.REST_SERVICE, RestServiceConstant.COMPANY_SERVICE);
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
		setDefaultFilter(request, filters);
		if(StringFunction.isNotEmpty(columns.getSearch().get(DataTableCriterias.SearchCriterias.value))){
			SearchFilter sfCode = new SearchFilter(Company.CODE, Operator.LIKE, columns.getSearch().get(DataTableCriterias.SearchCriterias.value));
			SearchFilter sfName = new SearchFilter(Company.NAME, Operator.LIKE, columns.getSearch().get(DataTableCriterias.SearchCriterias.value));
			filters.add(new SearchFilter(sfCode,sfName));
		}
		return filters;
	}
	
	private void setDefaultFilter(HttpServletRequest request, List<SearchFilter> filters) {
		filters.add(new SearchFilter(Company.STATUS, Operator.EQUALS, SystemConstant.ValidFlag.VALID));
		filters.add(new SearchFilter(Company.PARTY_COMPANIES_PARTY_PK, Operator.EQUALS, getUser(WebGeneralFunction.getLogin(request).getPkAppUser()).getParty().getPkParty(), Long.class));
	}

	@Override
	protected List<SearchOrder> getSearchOrder() {
		List<SearchOrder> orders = new ArrayList<>();
			orders.add(new SearchOrder(Company.CODE, SearchOrder.Sort.ASC));
		return orders;
	}

	@Override
	protected String getListPath() {
		return PATH_LIST;
	}
	
	private void setDefaultData(ModelMap model, Company company) {
		model.addAttribute("contactTypeList", new LookupRestCaller().findByLookupGroup(ILookupGroupConstant.CONTACT_TYPE));
		model.addAttribute("provinsiOptions", new LookupAddressRestCaller().findByLookupAddressGroup(ILookupAddressGroupConstant.PROVINSI));
		if(company!=null && company.getCompanyAddresses().size()>0){
			model.addAttribute("kabKotaOptions", new LookupAddressRestCaller().findAddressByParent(ILookupAddressGroupConstant.PROVINSI, company.getCompanyAddresses().get(0).getProvinsi().getPkLookupAddress()));
			model.addAttribute("kecamatanOptions", new LookupAddressRestCaller().findAddressByParent(ILookupAddressGroupConstant.KAB_KOTA, company.getCompanyAddresses().get(0).getKabupatenKota().getPkLookupAddress()));
			model.addAttribute("kelurahanOptions", new LookupAddressRestCaller().findAddressByParent(ILookupAddressGroupConstant.KECAMATAN, company.getCompanyAddresses().get(0).getKecamatan().getPkLookupAddress()));
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showList")
	public String showList(ModelMap model, HttpServletRequest request){
		model.addAttribute("pagingWrapper", new PagingWrapper<Company>());
		return getListPath();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showAdd")
	public String showAdd(ModelMap model, HttpServletRequest request){
		model.addAttribute("detail", new Company());
		setDefaultData(model, null);
		return PATH_DETAIL;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showEdit")
	public String showEdit(@RequestParam(value="maintenancePK") final Long maintenancePK, @RequestParam Map<String, String> paramWrapper, ModelMap model){
		Company company = getRestCaller().findById(maintenancePK);
		model.addAttribute("detail", company);
		setDefaultData(model, company);
		return PATH_DETAIL;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="saveCompany")
	@ResponseBody
	public Map<String, Object> saveCompany(final Company anObject, final HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		preparingObject(anObject, getUser(WebGeneralFunction.getLogin(request).getPkAppUser()).getParty().getPkParty());
		try{
			SpecificRestCaller<Company> rc = new SpecificRestCaller<Company>(RestConstant.REST_SERVICE, RestServiceConstant.COMPANY_SERVICE);
			Long pkAppUser = WebGeneralFunction.getLogin(request).getPkAppUser();
			HashMap<Long, String> companyMap = new HashMap<Long, String>();
			if(anObject.getPkCompany() != null){
				companyMap = (HashMap) rc.performPutReturn("/updateCompany/"+pkAppUser, anObject, HashMap.class);
			}else{
				companyMap = (HashMap) rc.performPostReturn("/createCompany/"+pkAppUser, anObject, HashMap.class);
			}
			resultMap.put("companyMapList", companyMap);
		}catch(SystemException se){
			LOGGER.error(se.getMessage());
			resultMap.put(SystemConstant.ERROR_LIST, se.getErrors());
		}catch(Exception e){
			LOGGER.error(e.getMessage());
		}
		return resultMap;
	}
	
	private void preparingObject(Company company, Long pkParty) {
		company.setStatus(SystemConstant.ValidFlag.VALID);
		//preparing party company data
		if(company.getPkCompany()==null){
			company.setPartyCompanies(new HashSet<PartyCompany>(Arrays.asList(PartyCompany.getInstance(pkParty, company))));
		}
	}
	
	@RequestMapping(method=RequestMethod.POST, value="deleteCompany")
	@ResponseBody
	public Map<String, Object> deleteCompany(final Long[] maintenancePKs, final HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		try{
			List<ErrorHolder> errors = getRestCaller().delete(maintenancePKs);
			if(errors != null && errors.size() > 1){
				resultMap.put(SystemConstant.ERROR_LIST, errors);
			}
			Long pkAppUser = WebGeneralFunction.getLogin(request).getPkAppUser();
			resultMap.put("companyMapList", updateRuntimeUserLogin(pkAppUser));
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return resultMap;
	}
	
	public Map<Long, String> updateRuntimeUserLogin(final Long pkAppUser) {
		HashMap<Long, String> companyMap = new HashMap<Long, String>();
		try{
			SpecificRestCaller<Company> rc = new SpecificRestCaller<Company>(RestConstant.REST_SERVICE, RestServiceConstant.COMPANY_SERVICE);
			companyMap = (HashMap) rc.performPutReturn("/updateRuntimeUserLogin/"+pkAppUser, new Company(), HashMap.class);
		}catch(Exception e){
			LOGGER.error(e.getMessage());
		}
		return companyMap;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="updateCompanySelected")
	@ResponseBody
	public Map<String, Object> updateCompanySelected(final Long pkCompany, final HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		try{
			Long pkAppUser = WebGeneralFunction.getLogin(request).getPkAppUser();
			Company c = new Company();
			c.setPkCompany(pkCompany);
			List<ErrorHolder> errorList = new SpecificRestCaller<Company>(RestConstant.REST_SERVICE, RestConstant.RM_COMPANY, Company.class).performPost("/updateCompanySelected/"+pkAppUser, c);
			if(errorList != null && errorList.size() > 0){
				resultMap.put(SystemConstant.ERROR_LIST, errorList);
			}
		}catch(Exception e){
			resultMap.put(SystemConstant.ERROR_LIST, new ArrayList<ErrorHolder>(Arrays.asList(new ErrorHolder(e.getMessage()))));
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
