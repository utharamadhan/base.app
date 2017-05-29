package id.base.app.service.frontend;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.base.app.dao.frontend.IHomeSettingDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.frontend.HomeSetting;

@Service
@Transactional
public class HomeSettingService implements IHomeSettingService {

	@Autowired
	private IHomeSettingDAO homeSettingDAO;
    
	public PagingWrapper<HomeSetting> findAll(int startNo, int offset) throws SystemException {
		return null;
	}

	public HomeSetting findById(Long id) throws SystemException {
		return homeSettingDAO.findById(id);
	}

	public void saveOrUpdate(HomeSetting anObject) throws SystemException {
		homeSettingDAO.saveOrUpdate(anObject);
	}

	public void delete(Long[] objectPKs) throws SystemException {
		homeSettingDAO.delete(objectPKs);
	}

	public PagingWrapper<HomeSetting> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return homeSettingDAO.findAllByFilter(startNo, offset, filter, order);
	}
	
	@Override
	public List<HomeSetting> findObjects(Long[] objectPKs) throws SystemException {
		List<HomeSetting> objects = new ArrayList<>();
		HomeSetting object = null;
		for(Long l:objectPKs){
			object = homeSettingDAO.findById(l);
			objects.add(object);
		}
		return objects;
	}

	@Override
	public List<HomeSetting> findAll(List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return homeSettingDAO.findAll(filter, order);
	}

}
