package id.base.app.dao.frontend;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import id.base.app.AbstractHibernateDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.advisory.AdvisoryConsulting;
import id.base.app.valueobject.frontend.HomeSetting;

@Repository
public class HomeSettingDAO extends AbstractHibernateDAO<HomeSetting, Long> implements IHomeSettingDAO {

	@Override
	public HomeSetting findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(HomeSetting anObject) throws SystemException {
		if (anObject.getPkHomeSetting()==null) {
			super.create(anObject);
		} else {
		    super.update(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		List<HomeSetting> objectList = new ArrayList<>();
		for(int i=0;i<objectPKs.length;i++){
			HomeSetting object = HomeSetting.getInstance();
			object = findById(objectPKs[i]);
			objectList.add(object);
		}
		super.delete(objectList);
	}

	@Override
	public List<HomeSetting> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<HomeSetting> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

}
