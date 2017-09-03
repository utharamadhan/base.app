package id.base.app.controller.frontend;

import id.base.app.SystemConstant;
import id.base.app.controller.SuperController;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.frontEndDisplay.ISettingService;
import id.base.app.valueobject.frontend.Setting;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_SETTING)
public class SettingController extends SuperController<Setting>{
	
	@Autowired
	private ISettingService settingService;
	

	@Override
	public Setting validate(Setting anObject) throws SystemException {
		List<ErrorHolder> errorList = new ArrayList<>();
		if(errorList.size() > 0) {
			throw new SystemException(errorList);
		}
		return anObject;
	}
	
	@Override
	public MaintenanceService<Setting> getMaintenanceService() {
		return settingService;
	}
	
	@Override
	public Setting preUpdate(Setting anObject) throws SystemException{
		return validate(anObject);
	}	
	
	@RequestMapping(method=RequestMethod.GET, value="/findAllSettingMenuTop")
	@ResponseBody
	public List<Setting> findAllSettingMenuTop() throws SystemException {
		return settingService.findAllSetting(SystemConstant.SettingType.MENU_TOP_LIST);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findAllSettingMenuBottom")
	@ResponseBody
	public List<Setting> findAllSettingMenuBottom() throws SystemException {
		return settingService.findAllSetting(SystemConstant.SettingType.MENU_BOTTOM_LIST);
	}
	
}