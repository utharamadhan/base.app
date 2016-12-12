package id.base.app.controller.master;

import id.base.app.controller.SuperController;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.party.Party;

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
