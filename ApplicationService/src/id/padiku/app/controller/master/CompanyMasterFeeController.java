package id.padiku.app.controller.master;

import id.padiku.app.controller.SuperController;
import id.padiku.app.exception.ErrorHolder;
import id.padiku.app.exception.SystemException;
import id.padiku.app.rest.RestConstant;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.service.master.ICompanyMasterFeeService;
import id.padiku.app.util.StringFunction;
import id.padiku.app.valueobject.master.CompanyMasterFee;

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
@RequestMapping(RestConstant.RM_COMPANY_MASTER_FEE)
public class CompanyMasterFeeController extends SuperController<CompanyMasterFee>{

	@Autowired
	private ICompanyMasterFeeService companyMasterFeeService;
	
	@Override
	public MaintenanceService<CompanyMasterFee> getMaintenanceService() {
		return companyMasterFeeService;
	}
	
	public CompanyMasterFee preCreate(CompanyMasterFee anObject) throws SystemException{
		return validate(anObject);
	}
	
	public CompanyMasterFee preUpdate(CompanyMasterFee anObject) throws SystemException{
		return validate(anObject);
	}

	@Override
	public CompanyMasterFee validate(CompanyMasterFee anObject) throws SystemException {
		List<ErrorHolder> errors = new ArrayList<>();
		if(anObject.getFeeType() == null || anObject.getFeeType().getPkLookup() == null){
			errors.add(new ErrorHolder(CompanyMasterFee.FEE_TYPE_ID, messageSource.getMessage("error.message.master.fee.mandatory.feeType", null, Locale.ENGLISH)));
		}
		if(StringFunction.isEmpty(anObject.getDescr())){
			errors.add(new ErrorHolder(CompanyMasterFee.DESCR, messageSource.getMessage("error.message.master.fee.mandatory.descr", null, Locale.ENGLISH)));
		}
		if(anObject.getUnitFee() == null) {
			errors.add(new ErrorHolder(CompanyMasterFee.UNIT_FEE, messageSource.getMessage("error.message.master.fee.mandatory.unitFee", null, Locale.ENGLISH)));
		}
		if(anObject.getUom() == null || anObject.getUom().getPkLookup() == null) {
			errors.add(new ErrorHolder(CompanyMasterFee.UOM_ID, messageSource.getMessage("error.message.master.fee.mandatory.uom", null, Locale.ENGLISH)));
		}
		if(errors != null && errors.size() > 0) {
			throw new SystemException(errors);
		}
		return anObject;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findAllByFeeType/{pkCompany}/{feeType}")
	@ResponseBody
	public List<CompanyMasterFee> findAllByFeeType(@PathVariable("pkCompany") Long pkCompany, @PathVariable("feeType") String feeType) throws SystemException {
		return companyMasterFeeService.findAllByFeeType(pkCompany, feeType);
	}

}
