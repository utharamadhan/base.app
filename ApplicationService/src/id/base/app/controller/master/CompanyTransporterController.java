package id.base.app.controller.master;

import id.base.app.ILookupConstant;
import id.base.app.ILookupGroupConstant;
import id.base.app.controller.SuperController;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.lookup.ILookupService;
import id.base.app.service.party.IPartyService;
import id.base.app.service.party.ITransporterService;
import id.base.app.util.StringFunction;
import id.base.app.valueobject.master.Company;
import id.base.app.valueobject.party.Party;
import id.base.app.valueobject.party.PartyAddress;
import id.base.app.valueobject.party.PartyCompany;
import id.base.app.valueobject.party.PartyContact;
import id.base.app.valueobject.party.PartyRole;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_COMPANY_TRANSPORTER)
public class CompanyTransporterController extends SuperController<Party> {

	@Autowired
	private ILookupService lookupService;
	@Autowired
	private IPartyService partyService;
	@Autowired
	private ITransporterService transporterService;
	
	@Override
	public MaintenanceService<Party> getMaintenanceService() {
		return transporterService;
	}
	
	public Party preCreate(Party anObject) throws SystemException{
		return validate(preparingObject(anObject));
	}
	
	public Party preUpdate(Party anObject) throws SystemException{
		return validate(preparingObject(anObject));
	}
	
	private Party preparingObject(Party anObject) throws SystemException {
		// relate company address to company itself
		if (anObject.getPartyAddresses() != null && anObject.getPartyAddresses().size() > 0) {
			for (PartyAddress addrs : anObject.getPartyAddresses()) {
				addrs.setParty(anObject);
			}
		}
		if (anObject.getPartyContacts() != null && anObject.getPartyContacts().size() > 0) {
			for (PartyContact conts : anObject.getPartyContacts()) {
				conts.setParty(anObject);
			}
		}
		// relate to its party role (transporter) if already exist, if not then create new one
		{
			if (anObject.getPkParty() == null) {
				anObject.addPartyRole(PartyRole.getInstance(lookupService.findByCode(ILookupConstant.PartyRole.TRANSPORTER, ILookupGroupConstant.PARTY_ROLE), anObject));
			} else {
				PartyRole pr = partyService.findPartyRole(ILookupConstant.PartyRole.TRANSPORTER, anObject.getPkParty());
				if(pr != null) {
					anObject.addPartyRole(pr);
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

	@Override
	public Party validate(Party anObject) throws SystemException {
		List<ErrorHolder> errors = new ArrayList<>();
		if(StringFunction.isEmpty(anObject.getName())) {
			errors.add(new ErrorHolder(Company.NAME, messageSource.getMessage("error.message.transporter.mandatory.name", null, Locale.ENGLISH)));
		}
		if(errors != null && errors.size() > 0) {
			throw new SystemException(errors);
		}
		return anObject;
	}

}
