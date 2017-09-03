package id.base.app.setting;

import id.base.app.IBaseDAO;
import id.base.app.exception.SystemException;
import id.base.app.valueobject.frontend.Setting;

import java.util.List;

public interface ISettingDAO extends IBaseDAO<Setting> {

	public List<Setting> findAllSetting(List<Integer> typeList) throws SystemException;

}
