package id.base.app.controller.master;

import id.base.app.controller.SuperController;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.login.LoginDirectoryService;
import id.base.app.service.master.ICompanyService;
import id.base.app.util.StringFunction;
import id.base.app.validation.InvalidRequestException;
import id.base.app.valueobject.CreateEntity;
import id.base.app.valueobject.RuntimeUserLogin;
import id.base.app.valueobject.UpdateEntity;
import id.base.app.valueobject.master.Company;
import id.base.app.valueobject.master.CompanyAddress;
import id.base.app.valueobject.master.CompanyContact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping(RestConstant.RM_COMPANY)
public class CompanyController extends SuperController<Company> {

	@Autowired
	private ICompanyService companyService;
	
	@Autowired
	private LoginDirectoryService loginService;
	
	@Override
	public MaintenanceService<Company> getMaintenanceService() {
		return this.companyService;
	}
	
	public Company preCreate(Company anObject) throws SystemException{
		return validate(preparingObject(anObject));
	}
	
	public Company preUpdate(Company anObject) throws SystemException{
		return validate(preparingObject(anObject));
	}
	
	private Company preparingObject(Company anObject) throws SystemException {
		// relate company address to company itself
		if (anObject.getCompanyAddresses() != null && anObject.getCompanyAddresses().size() > 0) {
			for (CompanyAddress addrs : anObject.getCompanyAddresses()) {
				addrs.setCompany(anObject);
			}
		}
		// relate company contact to company itself
		if (anObject.getCompanyContacts() != null && anObject.getCompanyContacts().size() > 0) {
			for(CompanyContact conts : anObject.getCompanyContacts()) {
				conts.setCompany(anObject);
			}
		}
		return anObject;
	}

	@Override
	public Company validate(Company anObject) throws SystemException {
		List<ErrorHolder> errors = new ArrayList<>();
		if(StringFunction.isEmpty(anObject.getName())) {
			errors.add(new ErrorHolder(Company.NAME, messageSource.getMessage("error.message.company.mandatory.name", null, Locale.ENGLISH)));
		}
		for (int i=0;i<anObject.getCompanyAddresses().size();i++) {
			CompanyAddress ca = anObject.getCompanyAddresses().get(i);
			if(ca.getProvinsi() == null || ca.getProvinsi().getPkLookupAddress() == null) {
				errors.add(new ErrorHolder(String.format(Company.COMPANY_ADDRESS_PROVINSI_VALFIELD, new Integer(i)), messageSource.getMessage("error.message.company.mandatory.provinsi", null, Locale.ENGLISH)));
			}
			if(ca.getKabupatenKota() == null || ca.getKabupatenKota().getPkLookupAddress() == null) {
				errors.add(new ErrorHolder(String.format(Company.COMPANY_ADDRESS_KABUPATEN_KOTA_VALFIELD, new Integer(i)), messageSource.getMessage("error.message.company.mandatory.kabupatenKota", null, Locale.ENGLISH)));
			}
			if(ca.getKecamatan() == null || ca.getKecamatan().getPkLookupAddress() == null) {
				errors.add(new ErrorHolder(String.format(Company.COMPANY_ADDRESS_KECAMATAN_VALFIELD, new Integer(i)), messageSource.getMessage("error.message.company.mandatory.kecamatan", null, Locale.ENGLISH)));
			}
			if(ca.getKelurahan() == null || ca.getKelurahan().getPkLookupAddress() == null) {
				errors.add(new ErrorHolder(String.format(Company.COMPANY_ADDRESS_KELURAHAN_VALFIELD, new Integer(i)), messageSource.getMessage("error.message.company.mandatory.kelurahan", null, Locale.ENGLISH)));
			}
			if(StringFunction.isEmpty(ca.getAlamat())) {
				errors.add(new ErrorHolder(String.format(Company.COMPANY_ADDRESS_ALAMAT_VALFIELD, new Integer(i)), messageSource.getMessage("error.message.company.mandatory.alamat", null, Locale.ENGLISH)));
			}
		}
		if(errors != null && errors.size() > 0) {
			throw new SystemException(errors);
		}
		return anObject;
	}
	
	private HashMap<Long, String> processUpdateRUL(Long pkAppUser) throws JsonProcessingException{
		HashMap<Long, String> companyMap = new HashMap<Long, String>();
		List<Company> companyList = getCompaniesByUser(pkAppUser);
		if(companyList != null) {
			for (Company company : companyList) {
				companyMap.put(company.getPkCompany(), company.getName());
			}
			String companyListCookies = mapper.writeValueAsString(companyList);
			RuntimeUserLogin runtimeUserLogin = loginService.findById(pkAppUser);
			runtimeUserLogin.setCompanyList(companyListCookies);
			if(companyList.size()==1){
				runtimeUserLogin.setCompanySelected(companyList.get(0).getPkCompany());
			}
			loginService.register(runtimeUserLogin);
		}
		return companyMap;
	}
	
	private HashMap<Long, String> saveCompany(Company anObject, Long pkAppUser) throws SystemException, JsonProcessingException {
		if(anObject.getPkCompany()==null){
			getMaintenanceService().saveOrUpdate(preCreate(anObject));
		}else{
			getMaintenanceService().saveOrUpdate(preUpdate(anObject));
		}
		return processUpdateRUL(pkAppUser);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/createCompany/{pkAppUser}")
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public HashMap<Long, String> createCompany(@RequestBody @Validated(CreateEntity.class) Company company, BindingResult bindingResult, @PathVariable(value="pkAppUser") Long pkAppUser) throws SystemException, JsonProcessingException{
		if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("Invalid validation", bindingResult);
        }
		return saveCompany(company, pkAppUser);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/updateCompany/{pkAppUser}")
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public HashMap<Long, String> updateCompany(@RequestBody @Validated(UpdateEntity.class) Company company, BindingResult bindingResult, @PathVariable(value="pkAppUser") Long pkAppUser) throws SystemException, JsonProcessingException{
		if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("Invalid validation", bindingResult);
        }
		return saveCompany(company, pkAppUser);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/updateRuntimeUserLogin/{pkAppUser}")
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public HashMap<Long, String> updateRuntimeUserLogin(@RequestBody Company company, @PathVariable(value="pkAppUser") Long pkAppUser) throws SystemException{
		HashMap<Long, String> companyMap = new HashMap<Long, String>();
		try {
			companyMap = processUpdateRUL(pkAppUser);
		} catch (Exception e) {
			throw new SystemException(new ErrorHolder(e.getMessage()));	
		}
		return companyMap;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/updateCompanySelected/{pkAppUser}")
	@ResponseStatus( HttpStatus.OK )
	public void updateCompanySelected(@PathVariable(value="pkAppUser") Long pkAppUser, @RequestBody Company anObject) throws SystemException {
		RuntimeUserLogin runtimeUserLogin = loginService.findById(pkAppUser);
		runtimeUserLogin.setCompanySelected(anObject.getPkCompany());
		loginService.register(runtimeUserLogin);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/getCompanyByUser/{pkAppUser}")
	@ResponseBody
	public Company getCompanyByUser(@PathVariable(value="pkAppUser") Long pkAppUser) throws SystemException {
		return companyService.getCompanyByUser(pkAppUser);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/getCompaniesByUser/{pkAppUser}")
	@ResponseBody
	public List<Company> getCompaniesByUser(@PathVariable(value="pkAppUser") Long pkAppUser) throws SystemException {
		return companyService.getCompaniesByUser(pkAppUser);
	}
	
}