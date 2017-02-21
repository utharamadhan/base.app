package id.base.app.dao.aboutUs;

import id.base.app.AbstractHibernateDAO;
import id.base.app.ILookupConstant;
import id.base.app.SystemConstant;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.aboutUs.Engagement;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class EngagementDAO extends AbstractHibernateDAO<Engagement, Long> implements IEngagementDAO {

	@Override
	public Engagement findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(Engagement anObject) throws SystemException {
		if (anObject.getPkEngagement()==null) {
			super.create(anObject);
		} else {
		    super.update(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		List<Engagement> objectList = new ArrayList<>();
		for(int i=0;i<objectPKs.length;i++){
			Engagement object = new Engagement();
			object = findById(objectPKs[i]);
			objectList.add(object);
		}
		super.delete(objectList);
	}

	@Override
	public List<Engagement> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<Engagement> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

	@Override
	public List<Engagement> findLatest(int number) throws SystemException {
		Criteria crit = this.getSession().createCriteria(domainClass);
		crit.add(Restrictions.eq(Engagement.STATUS, ILookupConstant.ArticleStatus.PUBLISH));
		crit.addOrder(Order.desc(Engagement.PK_ENGAGEMENT));
		crit.setMaxResults(number);
		return crit.list();
	}
}