package id.base.app.controller;

import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.frontEndDisplay.ISettingService;
import id.base.app.valueobject.frontend.Setting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_SETTING)
public class SettingController extends SuperController<Setting>{

	@Autowired
	private ISettingService settingService;
	
	@Override
	public MaintenanceService<Setting> getMaintenanceService() {
		return settingService;
	}

	@Override
	public Setting validate(Setting anObject) throws SystemException {
		return null;
	}
	
}
