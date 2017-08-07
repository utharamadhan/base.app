package id.base.app.dao.frontend;

import id.base.app.AbstractHibernateDAO;
import id.base.app.ILookupConstant;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.frontend.IntegrationScript;
import id.base.app.valueobject.publication.News;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class IntegrationScriptDAO extends AbstractHibernateDAO<IntegrationScript, Long> implements IIntegrationScriptDAO {

	@Override
	public IntegrationScript findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(IntegrationScript anObject) throws SystemException {
		if (anObject.getPkIntegrationScript()==null) {
			super.create(anObject);
		} else {
		    super.update(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		List<IntegrationScript> objectList = new ArrayList<>();
		for(int i=0;i<objectPKs.length;i++){
			IntegrationScript object = new IntegrationScript();
			object = findById(objectPKs[i]);
			objectList.add(object);
		}
		super.delete(objectList);
	}

	@Override
	public List<IntegrationScript> findObjects(Long[] objectPKs)
			throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<IntegrationScript> findAllByFilter(int startNo,
			int offset, List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}
	
	@Override
	public List<IntegrationScript> findByUrl(String url) throws SystemException {
		Criteria criteria = getSession().createCriteria(IntegrationScript.class);
		criteria.add(Restrictions.eq(IntegrationScript.URL, url));
		criteria.add(Restrictions.eq(News.STATUS, ILookupConstant.Status.PUBLISH));
		return criteria.list();
	}
}