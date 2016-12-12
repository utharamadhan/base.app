package id.padiku.app.controller;

import id.padiku.app.exception.ErrorHolder;
import id.padiku.app.exception.SystemException;
import id.padiku.app.rest.RestConstant;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.service.helper.IHelperService;
import id.padiku.app.valueobject.Helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_HELPER)
public class HelperController extends SuperController<Helper>{
	
	@Autowired
	private IHelperService helperService;
	
	@Override
	public MaintenanceService<Helper> getMaintenanceService() {
		return helperService;
	}

	@Override
	public Helper validate(Helper anObject) throws SystemException {
		List<ErrorHolder> errors = new ArrayList<>();
		if(anObject.getCode() == null){
			errors.add(new ErrorHolder("Code is Mandatory"));
		}
		if(anObject.getContent() == null){
			errors.add(new ErrorHolder("Content is Mandatory"));
		}
		if(errors != null && errors.size() > 0){
			throw new SystemException(errors);
		}
		return anObject;
	}
	
}
