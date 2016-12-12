package id.base.app.controller;

import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.lookup.IMasterFeeService;
import id.base.app.valueobject.MasterFee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_MASTER_FEE)
public class MasterFeeController extends SuperController<MasterFee>{

	@Autowired
	@Qualifier("masterFeeService")
	private IMasterFeeService masterFeeService;
	
	@Override
	public MaintenanceService<MasterFee> getMaintenanceService() {
		return masterFeeService;	
	}

	@Override
	public MasterFee validate(MasterFee anObject) throws SystemException {
		return null;
	}

}
