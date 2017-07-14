package id.base.app.dao.frontEndDisplay;

import id.base.app.AbstractHibernateDAO;
import id.base.app.ILookupConstant;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.Pages;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

@Repository
public class PagesDAO extends AbstractHibernateDAO<Pages, Long> implements IPagesDAO {

	@Override
	public Pages findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(Pages anObject) throws SystemException {
		if (anObject.getPkPages()==null) {
			super.create(anObject);
		} else {
		    super.update(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		List<Pages> objectList = new ArrayList<>();
		for(int i=0;i<objectPKs.length;i++){
			Pages object = new Pages();
			object = findById(objectPKs[i]);
			objectList.add(object);
		}
		super.delete(objectList);
	}

	@Override
	public List<Pages> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<Pages> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}
	
	@Override
	public Pages findByPermalink(String permalink) throws SystemException {
		Criteria criteria = getSession().createCriteria(Pages.class);
		criteria.add(Restrictions.eq(Pages.PERMALINK, permalink));
		criteria.add(Restrictions.eq(Pages.STATUS, ILookupConstant.Status.PUBLISH));
		return (Pages) criteria.uniqueResult();
	}
	
	@Override
	public List<Pages> findSimpleData(String type) throws SystemException {
		Criteria crit = this.getSession().createCriteria(domainClass);
		crit.add(Restrictions.eq(Pages.STATUS, ILookupConstant.Status.PUBLISH));
		crit.add(Restrictions.eq(Pages.TYPE, type));
		crit.addOrder(Order.asc(Pages.ORDER_NO));
		crit.setProjection(Projections.projectionList().
				add(Projections.property(Pages.TITLE)).
				add(Projections.property(Pages.PERMALINK)));
		crit.setResultTransformer(new ResultTransformer() {
			@Override
			public Object transformTuple(Object[] tuple, String[] aliases) {
				Pages obj = new Pages();
				try {
					BeanUtils.copyProperty(obj, Pages.TITLE, tuple[0]);
					BeanUtils.copyProperty(obj, Pages.PERMALINK, tuple[1]);
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
	public List<Pages> getLatestPages(List<String> types) throws SystemException {
		String query = "SELECT DISTINCT ON (TYPE)TYPE,TITLE,PERMALINK FROM PAGES "
				+ "WHERE STATUS = :status AND PERMALINK IS NOT NULL AND TYPE IN :types "
				+ "ORDER BY TYPE,ORDER_NO DESC";
		SQLQuery sqlQuery = getSession().createSQLQuery(query);
			sqlQuery.setParameterList("types", types);			
			sqlQuery.setParameter("status", ILookupConstant.Status.PUBLISH);
			sqlQuery.setResultTransformer(new ResultTransformer() {

				private static final long serialVersionUID = 6857506591081945427L;

				@Override
				public Object transformTuple(Object[] tuple, String[] aliases) {
					Pages p = new Pages();
					try {
						BeanUtils.copyProperty(p, "type", tuple[0]);
						BeanUtils.copyProperty(p, "title", tuple[1]);     
						BeanUtils.copyProperty(p, "permalink", tuple[2]);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
					return p;
				}
				
				@SuppressWarnings("rawtypes")
				@Override
				public List transformList(List collection) {
					return collection;
				}
			});

		return sqlQuery.list();
	}

}