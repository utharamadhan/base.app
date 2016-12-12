package id.padiku.app.controller;

import id.padiku.app.exception.SystemException;
import id.padiku.app.rest.RestConstant;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.service.lookup.IMasterFeeService;
import id.padiku.app.valueobject.MasterFee;

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
