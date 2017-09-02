package id.base.app.service.frontEndDisplay;

import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.setting.ISettingDAO;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.frontend.Setting;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SettingService implements ISettingService {

	@Autowired
	private ISettingDAO settingDAO;
	
	@Override
	public Setting findById(Long id) throws SystemException {
		return settingDAO.findById(id);
	}

	@Override
	public void saveOrUpdate(Setting anObject) throws SystemException {
		settingDAO.saveOrUpdate(anObject);
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
	}

	@Override
	public List<Setting> findObjects(Long[] objectPKs) throws SystemException {
		List<Setting> objects = new ArrayList<>();
		Setting object = null;
		for(Long l:objectPKs){
			object = settingDAO.findById(l);
			objects.add(object);
		}
		return objects;
	}

	@Override
	public PagingWrapper<Setting> findAllByFilter(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return settingDAO.findAllByFilter(startNo, offset, filter, order);
	}

	@Override
	public List<Setting> findAll(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		return null;
	}

}
