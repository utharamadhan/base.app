package id.base.app.dao.publication;

import id.base.app.AbstractHibernateDAO;
import id.base.app.ILookupConstant;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.publication.News;

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
public class NewsDAO extends AbstractHibernateDAO<News, Long> implements INewsDAO {

	@Override
	public News findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(News anObject) throws SystemException {
		if (anObject.getPkNews()==null) {
			super.create(anObject);
		} else {
		    super.update(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		List<News> objectList = new ArrayList<>();
		for(int i=0;i<objectPKs.length;i++){
			News object = new News();
			object = findById(objectPKs[i]);
			objectList.add(object);
		}
		super.delete(objectList);
	}

	@Override
	public List<News> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<News> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

	@Override
	public List<News> findLatestNews(int number) throws SystemException {
		Criteria crit = this.getSession().createCriteria(domainClass);
		crit.add(Restrictions.eq(News.STATUS, ILookupConstant.ArticleStatus.PUBLISH));
		crit.addOrder(Order.desc(News.PK_NEWS));
		crit.setMaxResults(number);
		crit.setProjection(Projections.projectionList().
				add(Projections.property(News.PERMALINK)).
				add(Projections.property(News.PUBLISH_DATE)).
				add(Projections.property(News.IMAGE_URL)).
				add(Projections.property(News.TITLE)));
		crit.setResultTransformer(new ResultTransformer() {
			@Override
			public Object transformTuple(Object[] tuple, String[] aliases) {
				News obj = new News();
				try {
					BeanUtils.copyProperty(obj, News.PERMALINK, tuple[0]);
					BeanUtils.copyProperty(obj, News.PUBLISH_DATE, tuple[1]);
					BeanUtils.copyProperty(obj, News.IMAGE_URL, tuple[2]);
					BeanUtils.copyProperty(obj, News.TITLE, tuple[3]);
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
		Criteria crit = getSession().createCriteria(News.class);
		crit.add(Restrictions.like(News.PERMALINK, permalink, MatchMode.START));
		if(pk!=null){
			crit.add(Restrictions.ne(News.PK_NEWS, pk));
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
	public News findByPermalink(String permalink) throws SystemException {
		Criteria criteria = getSession().createCriteria(News.class);
		criteria.add(Restrictions.eq(News.PERMALINK, permalink));
		criteria.add(Restrictions.eq(News.STATUS, ILookupConstant.ArticleStatus.PUBLISH));
		return (News) criteria.uniqueResult();
	}
}