package id.base.app.dao.aboutUs;

import id.base.app.AbstractHibernateDAO;
import id.base.app.ILookupConstant;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.aboutUs.Engagement;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
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
		crit.setProjection(Projections.projectionList().
				add(Projections.property(Engagement.PERMALINK)).
				add(Projections.property(Engagement.TITLE)));
		crit.setResultTransformer(new ResultTransformer() {
			@Override
			public Object transformTuple(Object[] tuple, String[] aliases) {
				Engagement obj = new Engagement();
				try {
					BeanUtils.copyProperty(obj, Engagement.PERMALINK, tuple[0]);
					BeanUtils.copyProperty(obj, Engagement.TITLE, tuple[1]);
				} catch (Exception e) {
					LOGGER.error(e.getMessage(), e);
				}
				return obj;
			}
			
			@Override
			public List transformList(List collection) {
				return collection;
			}
		});
		return crit.list();
	}
	
	@Override
	public List<String> getSamePermalink(Long pk, String permalink) throws SystemException {
		Criteria crit = getSession().createCriteria(Engagement.class);
		crit.add(Restrictions.like(Engagement.PERMALINK, permalink, MatchMode.START));
		if(pk!=null){
			crit.add(Restrictions.ne(Engagement.PK_ENGAGEMENT, pk));
		}
		crit.setProjection(Projections.projectionList().add(Projections.property("permalink")));
		crit.setResultTransformer(new ResultTransformer() {
			@Override
			public Object transformTuple(Object[] tuple, String[] aliases) {
				String r = null;
				try{
					r = tuple[0].toString();
				}catch(Exception e){
					LOGGER.error(e.getMessage(), e);
				}
				return r;
			}
			
			@Override
			public List<?> transformList(List collection) {
				return collection;
			}
		});
		return crit.list();
	}
	
	@Override
	public Engagement findByPermalink(String permalink) throws SystemException {
		Criteria criteria = getSession().createCriteria(Engagement.class);
		criteria.add(Restrictions.eq(Engagement.PERMALINK, permalink));
		criteria.add(Restrictions.eq(Engagement.STATUS, ILookupConstant.ArticleStatus.PUBLISH));
		return (Engagement) criteria.uniqueResult();
	}
}