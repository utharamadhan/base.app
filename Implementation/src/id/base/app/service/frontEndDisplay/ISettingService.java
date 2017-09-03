package id.base.app.service.frontEndDisplay;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.frontend.HomeSettingHelper;
import id.base.app.valueobject.frontend.Setting;

import java.util.List;

public interface ISettingService extends MaintenanceService<Setting> {

	public List<Setting> findAllSetting(List<Integer> typeList) throws SystemException;

	public HomeSettingHelper convertToHomeSetting(List<Setting> settingList) throws SystemException;

}
