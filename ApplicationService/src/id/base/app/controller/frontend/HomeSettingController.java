package id.base.app.controller.frontend;

import id.base.app.SystemConstant;
import id.base.app.controller.SuperController;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.frontEndDisplay.ISettingService;
import id.base.app.valueobject.frontend.HomeSettingHelper;
import id.base.app.valueobject.frontend.Setting;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_HOME_SETTING)
public class HomeSettingController extends SuperController<HomeSettingHelper>{
	
	@Autowired
	private ISettingService settingService;
	

	@Override
	public HomeSettingHelper validate(HomeSettingHelper anObject) throws SystemException {
		return anObject;
	}
	
	@Override
	public MaintenanceService<HomeSettingHelper> getMaintenanceService() {
		return null;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findAllSettingHome")
	@ResponseBody
	public HomeSettingHelper findAllSettingHome() throws SystemException {
		List<Setting> list = settingService.findAllSetting(SystemConstant.SettingType.HOME_LIST);
		return settingService.convertToHomeSetting(list);
	}
}