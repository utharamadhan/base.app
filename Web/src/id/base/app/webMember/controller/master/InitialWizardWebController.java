package id.base.app.webMember.controller.master;

import id.base.app.SystemConstant;
import id.base.app.paging.PagingWrapper;
import id.base.app.rest.PathInterfaceRestCaller;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.rest.SpecificRestCaller;
import id.base.app.valueobject.AppUser;
import id.base.app.valueobject.MasterFee;
import id.base.app.valueobject.master.Company;
import id.base.app.valueobject.master.CompanyLookup;
import id.base.app.valueobject.master.CompanyMachinery;
import id.base.app.valueobject.master.CompanyProduct;
import id.base.app.valueobject.master.CompanyWarehouse;
import id.base.app.valueobject.master.VWCompanyThirdParty;
import id.base.app.valueobject.party.Party;
import id.base.app.webMember.WebGeneralFunction;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Scope(value="request")
@Controller
@RequestMapping("/initialWizard")
public class InitialWizardWebController {
	
	@RequestMapping(method=RequestMethod.GET, value="showCompany")
	public String showCompany(ModelMap model, HttpServletRequest request){
		model.addAttribute("pagingWrapper", new PagingWrapper<Company>());
		return "/initialWizard/companyListInit";
	}
	
	public AppUser getUser(final Long pkAppUser) {
		return new RestCaller<AppUser>(RestConstant.REST_SERVICE, RestServiceConstant.USER_SERVICE).findById(pkAppUser);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showThirdParty")
	public String showThirdParty(ModelMap model, HttpServletRequest request){
		model.addAttribute("pagingWrapper", new PagingWrapper<VWCompanyThirdParty>());
		new SpecificRestCaller<AppUser>(RestConstant.REST_SERVICE, RestServiceConstant.USER_SERVICE).performPost("/updateInitialWizard/"+SystemConstant.InitialWizard.FIRST_COMPANY, getUser(WebGeneralFunction.getLogin(request).getPkAppUser()));
		return "/initialWizard/companyThirdPartyListInit";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showProduct")
	public String showProduct(ModelMap model, HttpServletRequest request){
		model.addAttribute("pagingWrapper", new PagingWrapper<CompanyProduct>());
		new SpecificRestCaller<AppUser>(RestConstant.REST_SERVICE, RestServiceConstant.USER_SERVICE).performPost("/updateInitialWizard/"+SystemConstant.InitialWizard.SECOND_THIRD_PARTY, getUser(WebGeneralFunction.getLogin(request).getPkAppUser()));
		return "/initialWizard/companyProductListInit";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showWarehouse")
	public String showWarehouse(ModelMap model, HttpServletRequest request){
		model.addAttribute("pagingWrapper", new PagingWrapper<CompanyWarehouse>());
		new SpecificRestCaller<AppUser>(RestConstant.REST_SERVICE, RestServiceConstant.USER_SERVICE).performPost("/updateInitialWizard/"+SystemConstant.InitialWizard.THIRD_PRODUCT, getUser(WebGeneralFunction.getLogin(request).getPkAppUser()));
		return "/initialWizard/companyWarehouseListInit";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showMachinery")
	public String showMachinery(ModelMap model, HttpServletRequest request){
		model.addAttribute("pagingWrapper", new PagingWrapper<CompanyMachinery>());
		new SpecificRestCaller<AppUser>(RestConstant.REST_SERVICE, RestServiceConstant.USER_SERVICE).performPost("/updateInitialWizard/"+SystemConstant.InitialWizard.FOURTH_WAREHOUSE, getUser(WebGeneralFunction.getLogin(request).getPkAppUser()));
		return "/initialWizard/companyMachineryListInit";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showTransporter")
	public String showTransporter(ModelMap model, HttpServletRequest request){
		model.addAttribute("pagingWrapper", new PagingWrapper<Party>());
		new SpecificRestCaller<AppUser>(RestConstant.REST_SERVICE, RestServiceConstant.USER_SERVICE).performPost("/updateInitialWizard/"+SystemConstant.InitialWizard.FIFTH_MACHINERY, getUser(WebGeneralFunction.getLogin(request).getPkAppUser()));
		return "/initialWizard/companyTransporterListInit";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showMasterFee")
	public String showMasterFee(ModelMap model, HttpServletRequest request){
		model.addAttribute("pagingWrapper", new PagingWrapper<MasterFee>());
		new SpecificRestCaller<AppUser>(RestConstant.REST_SERVICE, RestServiceConstant.USER_SERVICE).performPost("/updateInitialWizard/"+SystemConstant.InitialWizard.SIXTH_TRANSPORTER, getUser(WebGeneralFunction.getLogin(request).getPkAppUser()));
		return "/initialWizard/companyMasterFeeListInit";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showLookup")
	public String showLookup(ModelMap model, HttpServletRequest request){
		model.addAttribute("pagingWrapper", new PagingWrapper<CompanyLookup>());
		new SpecificRestCaller<AppUser>(RestConstant.REST_SERVICE, RestServiceConstant.USER_SERVICE).performPost("/updateInitialWizard/"+SystemConstant.InitialWizard.SIXTH_TRANSPORTER, getUser(WebGeneralFunction.getLogin(request).getPkAppUser()));
		return "/initialWizard/companyLookupListInit";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="finishWizard")
	public String finishWizard(ModelMap model, HttpServletRequest request) {
		new SpecificRestCaller<AppUser>(RestConstant.REST_SERVICE, RestServiceConstant.USER_SERVICE).performPost("/updateInitialWizard/"+SystemConstant.InitialWizard.DONE, getUser(WebGeneralFunction.getLogin(request).getPkAppUser()));
		return "redirect:/do/dashboard";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="validateNextStep")
	@ResponseBody
	public Map<String, Object> validateNextStep(ModelMap mode, HttpServletRequest request) {
		Map<String, Object> returnMap = new HashMap<>();
		final Long pkAppUser = WebGeneralFunction.getLogin(request).getPkAppUser();
		try {
			Company company = new SpecificRestCaller<Company>(RestConstant.REST_SERVICE, RestServiceConstant.COMPANY_SERVICE).executeGet(new PathInterfaceRestCaller() {
				@Override
				public String getPath() {
					return "/getCompanyByUser/{pkAppUser}";
				}
				
				@Override
				public Map<String, Object> getParameters() {
					Map<String, Object> map = new HashMap<>();
					map.put("pkAppUser", pkAppUser);
					return map;
				}
			});
			if(company == null || company.getPkCompany() == null) {
				returnMap.put("error", "Daftarkan Penggilingan Padi Dahulu (Min. 1) Sebelum Menuju Langkah Selanjutnya");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnMap;
	}
	
}