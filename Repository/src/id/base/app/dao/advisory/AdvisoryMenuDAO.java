package id.base.app.dao.advisory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import id.base.app.AbstractHibernateDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.advisory.AdvisoryMenu;

@Repository
public class AdvisoryMenuDAO extends AbstractHibernateDAO<AdvisoryMenu, Long> implements IAdvisoryMenuDAO {

	@Override
	public AdvisoryMenu findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(AdvisoryMenu anObject) throws SystemException {
		if (anObject.getPkAdvisoryMenu()==null) {
			super.create(anObject);
		} else {
		    super.update(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		List<AdvisoryMenu> objectList = new ArrayList<>();
		for(int i=0;i<objectPKs.length;i++){
			AdvisoryMenu object = AdvisoryMenu.getInstance();
			object = findById(objectPKs[i]);
			objectList.add(object);
		}
		super.delete(objectList);
	}

	@Override
	public List<AdvisoryMenu> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<AdvisoryMenu> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

}
