package id.base.app.controller.advisory;

import id.base.app.SystemConstant;
import id.base.app.controller.SuperController;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.advisory.IAdvisoryService;
import id.base.app.valueobject.advisory.AdvisoryConsulting;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_ADVISORY)
public class AdvisoryController extends SuperController<AdvisoryConsulting>{
	
	@Autowired
	private IAdvisoryService advisoryService;
	

	@Override
	public AdvisoryConsulting validate(AdvisoryConsulting anObject) throws SystemException {
		List<ErrorHolder> errorList = new ArrayList<>();
		if(errorList.size() > 0) {
			throw new SystemException(errorList);
		}
		return anObject;
	}
	
	@Override
	public MaintenanceService<AdvisoryConsulting> getMaintenanceService() {
		return advisoryService;
	}
	
	@Override
	public AdvisoryConsulting preUpdate(AdvisoryConsulting anObject) throws SystemException{
		return validate(anObject);
	}
	
}
