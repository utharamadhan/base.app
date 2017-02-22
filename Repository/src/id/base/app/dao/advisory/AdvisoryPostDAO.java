package id.base.app.dao.advisory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import id.base.app.AbstractHibernateDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.advisory.AdvisoryPost;

@Repository
public class AdvisoryPostDAO extends AbstractHibernateDAO<AdvisoryPost, Long> implements IAdvisoryPostDAO {

	@Override
	public AdvisoryPost findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(AdvisoryPost anObject) throws SystemException {
		if (anObject.getPkAdvisoryPost()==null) {
			super.create(anObject);
		} else {
		    super.update(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		List<AdvisoryPost> objectList = new ArrayList<>();
		for(int i=0;i<objectPKs.length;i++){
			AdvisoryPost object = AdvisoryPost.getInstance();
			object = findById(objectPKs[i]);
			objectList.add(object);
		}
		super.delete(objectList);
	}

	@Override
	public List<AdvisoryPost> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<AdvisoryPost> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

}
