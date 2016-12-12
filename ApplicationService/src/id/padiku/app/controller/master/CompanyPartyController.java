package id.padiku.app.controller.master;

import id.padiku.app.controller.SuperController;
import id.padiku.app.exception.SystemException;
import id.padiku.app.rest.RestConstant;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.valueobject.party.Party;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_COMPANY_PARTY)
public class CompanyPartyController extends SuperController<Party> {

	@Override
	public MaintenanceService<Party> getMaintenanceService() {
		return null;
	}

	@Override
	public Party validate(Party anObject) throws SystemException {
		return null;
	}

}
