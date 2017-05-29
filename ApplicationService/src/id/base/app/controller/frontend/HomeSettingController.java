package id.base.app.controller.frontend;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.base.app.controller.SuperController;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.frontend.IHomeSettingService;
import id.base.app.valueobject.frontend.HomeSetting;

@RestController
@RequestMapping(RestConstant.RM_HOME_SETTING)
public class HomeSettingController extends SuperController<HomeSetting>{
	
	@Autowired
	private IHomeSettingService homeSettingService;
	

	@Override
	public HomeSetting validate(HomeSetting anObject) throws SystemException {
		List<ErrorHolder> errorList = new ArrayList<>();
		if(errorList.size() > 0) {
			throw new SystemException(errorList);
		}
		return anObject;
	}
	
	@Override
	public MaintenanceService<HomeSetting> getMaintenanceService() {
		return homeSettingService;
	}
	
	@Override
	public HomeSetting preUpdate(HomeSetting anObject) throws SystemException{
		return validate(anObject);
	}
	
}
