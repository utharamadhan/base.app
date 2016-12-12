package id.padiku.app.controller.master;

import id.padiku.app.controller.SuperController;
import id.padiku.app.exception.ErrorHolder;
import id.padiku.app.exception.SystemException;
import id.padiku.app.rest.RestConstant;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.service.master.ICompanyMachineryService;
import id.padiku.app.util.StringFunction;
import id.padiku.app.valueobject.master.CompanyMachinery;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_COMPANY_MACHINERY)
public class CompanyMachineryController extends SuperController<CompanyMachinery> {

	@Autowired
	private ICompanyMachineryService companyMachineryService;
	
	@Override
	public MaintenanceService<CompanyMachinery> getMaintenanceService() {
		return companyMachineryService;
	}
	
	public CompanyMachinery preCreate(CompanyMachinery anObject) throws SystemException{
		return validate(anObject);
	}
	
	public CompanyMachinery preUpdate(CompanyMachinery anObject) throws SystemException{
		return validate(anObject);
	}

	@Override
	public CompanyMachinery validate(CompanyMachinery anObject) throws SystemException {
		List<ErrorHolder> errors = new ArrayList<>();
		if(StringFunction.isEmpty(anObject.getName())){
			errors.add(new ErrorHolder(CompanyMachinery.NAME, messageSource.getMessage("error.message.machinery.mandatory.name", null, Locale.ENGLISH)));
		}
		if(anObject.getCapacity() == null){
			errors.add(new ErrorHolder(CompanyMachinery.CAPACITY, messageSource.getMessage("error.message.machinery.mandatory.capacity", null, Locale.ENGLISH)));
		}
		if(errors != null && errors.size() > 0) {
			throw new SystemException (errors);
		}
		return anObject;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findAllByPkCompany/{pkCompany}")
	@ResponseBody
	public List<CompanyMachinery> findAllByPkCompany(@PathVariable(value="pkCompany") Long pkCompany) throws SystemException {
		return companyMachineryService.findAllByPkCompany(pkCompany);
	}
	

}
