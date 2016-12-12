package id.padiku.app.controller.master;

import id.padiku.app.ILookupGroupConstant;
import id.padiku.app.controller.SuperController;
import id.padiku.app.exception.ErrorHolder;
import id.padiku.app.exception.SystemException;
import id.padiku.app.rest.RestConstant;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.service.lookup.ILookupService;
import id.padiku.app.service.party.IPartyService;
import id.padiku.app.service.party.IThirdPartyService;
import id.padiku.app.util.StringFunction;
import id.padiku.app.validation.InvalidRequestException;
import id.padiku.app.valueobject.CreateEntity;
import id.padiku.app.valueobject.UpdateEntity;
import id.padiku.app.valueobject.master.VWCompanyThirdParty;
import id.padiku.app.valueobject.party.Party;
import id.padiku.app.valueobject.party.PartyAddress;
import id.padiku.app.valueobject.party.PartyCompany;
import id.padiku.app.valueobject.party.PartyContact;
import id.padiku.app.valueobject.party.PartyRole;

import java.util.ArrayList;
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

@RestController
@RequestMapping(RestConstant.RM_COMPANY_THIRD_PARTY)
public class CompanyThirdPartyController extends SuperController<VWCompanyThirdParty> {

	@Autowired
	private ILookupService lookupService;
	@Autowired
	private IPartyService partyService;
	@Autowired
	private IThirdPartyService thirdPartyService;
	
	@Override
	public MaintenanceService<VWCompanyThirdParty> getMaintenanceService() {
		return thirdPartyService;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/createThirdParty")
	@ResponseStatus( HttpStatus.CREATED )
	public void createThirdParty(@RequestBody @Validated(CreateEntity.class) Party anObject, BindingResult bindingResult) throws SystemException {
		if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("Invalid validation", bindingResult);
        }
	    thirdPartyService.saveOrUpdateThirdParty(preCreate(anObject));
	}
	
	public Party preCreate(Party anObject) throws SystemException{
		return validateThirdParty(preparingObject(anObject));
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/updateThirdParty")
	@ResponseStatus( HttpStatus.OK )
	public void updateThirdParty(@RequestBody @Validated(UpdateEntity.class) Party anObject, BindingResult bindingResult) throws SystemException {
		if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("Invalid validation", bindingResult);
        }
		thirdPartyService.saveOrUpdateThirdParty(preUpdate(anObject));
	}
	
	public Party preUpdate(Party anObject) throws SystemException{
		return validateThirdParty(preparingObject(anObject));
	}
	
	private Party preparingObject(Party anObject) throws SystemException {
		// relate company address to company itself
		if (anObject.getPartyAddresses() != null && anObject.getPartyAddresses().size() > 0) {
			for (PartyAddress addrs : anObject.getPartyAddresses()) {
				addrs.setParty(anObject);
			}
		}
		// relate company address to company itself
		if (anObject.getPartyContacts() != null && anObject.getPartyContacts().size() > 0) {
			for (PartyContact conts : anObject.getPartyContacts()) {
				conts.setParty(anObject);
			}
		}
		// relate to its party role whether customer / supplier if already exist, if not then create new one
		{
			if (anObject.getPkParty() == null) {
				if (anObject.getPartyRoles() != null && anObject.getPartyRoles().size() > 0) {
					for (PartyRole role : anObject.getPartyRoles()) {
						if(role.getRole() != null && role.getRole().getCode() != null) {
							role.setRole(lookupService.findByCode(role.getRole().getCode(), ILookupGroupConstant.PARTY_ROLE));
						}
					}
				}
			} else {
				if (anObject.getPartyRoles() != null && anObject.getPartyRoles().size() > 0) {
					for(PartyRole role : anObject.getPartyRoles()) {
						PartyRole pr = partyService.findPartyRole(role.getRole().getCode(), anObject.getPkParty());
						if(pr == null) {
							//if null, it seems user change the party role
							pr = partyService.findPartyRole(anObject.getPkParty());
							role.setRole(lookupService.findByCode(role.getRole().getCode(), ILookupGroupConstant.PARTY_ROLE));
						}else{
							role.setRole(pr.getRole());							
						}
						role.setPkPartyRole(pr.getPkPartyRole());
					}
				}
			}
		}
		// not necessary to add new record about company party join record if there's already one
		{
			if (anObject.getPkParty() != null && 
					anObject.getPartyCompanies() != null && 
						anObject.getPartyCompanies().size() > 0){
				for(PartyCompany pc : anObject.getPartyCompanies()) {
					if(pc.getPkPartyCompany() == null) {
						PartyCompany pcDB = partyService.findPartyCompanyByPartyAndCompany(pc.getCompany().getPkCompany(), anObject.getPkParty());
						pc.setPkPartyCompany(pcDB.getPkPartyCompany());
						pc.setCompany(pcDB.getCompany());
						pc.setParty(pcDB.getParty());
					}
				}
			}
		}
		return anObject;
	}

	public Party validateThirdParty(Party anObject) throws SystemException {
		List<ErrorHolder> errors = new ArrayList<>();
		if(anObject.getPartyRoles()==null || anObject.getPartyRoles().size()==0) {
			errors.add(new ErrorHolder("thirdPartyType", messageSource.getMessage("error.message.third.party.mandatory.type", null, Locale.ENGLISH)));
		}
		if(StringFunction.isEmpty(anObject.getName())) {
			errors.add(new ErrorHolder(Party.NAME, messageSource.getMessage("error.message.third.party.mandatory.name", null, Locale.ENGLISH)));
		}
		if(errors != null && errors.size() > 0) {
			throw new SystemException(errors);
		}
		return anObject;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/getThirdCompanyParty/{maintenancePK}")
	@ResponseBody
	public Party getThirdCompanyParty(@PathVariable(value="maintenancePK") Long maintenancePK) {
		return thirdPartyService.getThirdCompanyParty(maintenancePK);
	}
	
	@Override
	@Deprecated
	public VWCompanyThirdParty validate(VWCompanyThirdParty anObject) throws SystemException { return null; }

}
