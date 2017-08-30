package id.base.app.controller.party;

import id.base.app.controller.SuperController;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.party.IVWUserService;
import id.base.app.valueobject.party.VWUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value=RestConstant.RM_VW_USER)
public class VWUserController extends SuperController<VWUser> {
	
	public VWUserController() {
		super();
	}
	
	@Autowired
	private IVWUserService vwTeacherService;

	@Override
	public MaintenanceService<VWUser> getMaintenanceService() {
		return vwTeacherService;
	}

	@Override
	public VWUser validate(VWUser anObject) throws SystemException {
		return null;
	}

}