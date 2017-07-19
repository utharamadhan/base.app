package id.base.app.dao.advisory;

import id.base.app.AbstractHibernateDAO;
import id.base.app.ILookupConstant;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.Category;
import id.base.app.valueobject.Pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDAO extends AbstractHibernateDAO<Category, Long> implements ICategoryDAO {

	@Override
	public Category findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(Category anObject) throws SystemException {
		if (anObject.getPkCategory()==null) {
			super.create(anObject);
		} else {
		    super.update(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		List<Category> objectList = new ArrayList<>();
		for(int i=0;i<objectPKs.length;i++){
			Category object = new Category();
			object = findById(objectPKs[i]);
			objectList.add(object);
		}
		super.delete(objectList);
	}

	@Override
	public List<Category> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<Category> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}
	
	@Override
	public List<String> getSamePermalink(Long pk, String permalink) throws SystemException {
		Criteria crit = getSession().createCriteria(Category.class);
		crit.add(Restrictions.like(Category.PERMALINK, permalink, MatchMode.START));
		if(pk!=null){
			crit.add(Restrictions.ne(Category.PK_CATEGORY, pk));
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> findByType(String type) throws SystemException {
		Criteria crit = getSession().createCriteria(Category.class);
		crit.add(Restrictions.eq(Category.TYPE, type));
		crit.add(Restrictions.eq(Category.STATUS, ILookupConstant.Status.PUBLISH));
		ProjectionList pl = Projections.projectionList();
		pl.add(Projections.property(Category.PK_CATEGORY));
		pl.add(Projections.property(Category.TITLE));
		crit.setProjection(pl);
		crit.setResultTransformer(new ResultTransformer() {
			private static final long serialVersionUID = 1021195617174304873L;

			@Override
			public Object transformTuple(Object[] tuple, String[] aliases) {
				Category c = new Category();
				try{					
					c.setPkCategory(Long.valueOf(tuple[0].toString()));
					c.setTitle(tuple[1].toString());
				}catch(Exception e){
					LOGGER.error(e.getMessage());
				}
				return c;
			}
			
			@SuppressWarnings("rawtypes")
			@Override
			public List transformList(List collection) {
				return collection;
			}
		});
		return crit.list();	
	}
	
	@Override
	public String getFirstPermalinkData(String type) throws SystemException {
		Query query = getSession().createSQLQuery("SELECT PERMALINK FROM CATEGORY WHERE TYPE = :type AND STATUS = :status ORDER BY ORDER_NO ASC LIMIT 1");
		query.setString("type", type);
		query.setInteger("status", ILookupConstant.Status.PUBLISH);
		return (String) query.uniqueResult();
	}
	
	@Override
	public Category findSimpleDataByPermalink(String permalink) throws SystemException {
		Criteria crit = this.getSession().createCriteria(domainClass);
		crit.add(Restrictions.eq(Category.STATUS, ILookupConstant.Status.PUBLISH));
		crit.add(Restrictions.eq(Category.PERMALINK, permalink));
		crit.addOrder(Order.desc(Category.PK_CATEGORY));
		crit.setProjection(Projections.projectionList().
				add(Projections.property(Category.TITLE)).
				add(Projections.property(Category.PERMALINK)).
				add(Projections.property(Category.IMAGE_URL)).
				add(Projections.property(Category.IMAGE_THUMB_URL)).
				add(Projections.property(Category.DESCRIPTION)));
		crit.setResultTransformer(new ResultTransformer() {
			@Override
			public Object transformTuple(Object[] tuple, String[] aliases) {
				Pages obj = new Pages();
				try {
					BeanUtils.copyProperty(obj, Category.TITLE, tuple[0]);
					BeanUtils.copyProperty(obj, Category.PERMALINK, tuple[1]);
					BeanUtils.copyProperty(obj, Category.IMAGE_URL, tuple[2]);
					BeanUtils.copyProperty(obj, Category.IMAGE_THUMB_URL, tuple[3]);
					BeanUtils.copyProperty(obj, Category.DESCRIPTION, tuple[4]);
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
		List<Category> list = crit.list();
		if(list!=null && !list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	@Override
	public List<Category> findSimpleDataForList(String type) throws SystemException {
		Criteria crit = this.getSession().createCriteria(domainClass);
		crit.add(Restrictions.eq(Category.STATUS, ILookupConstant.Status.PUBLISH));
		crit.add(Restrictions.eq(Category.TYPE, type));
		crit.addOrder(Order.asc(Category.ORDER_NO));
		crit.setProjection(Projections.projectionList().
				add(Projections.property(Category.TITLE)).
				add(Projections.property(Category.PERMALINK)).
				add(Projections.property(Category.IMAGE_URL)).
				add(Projections.property(Category.IMAGE_THUMB_URL)).
				add(Projections.property(Category.DESCRIPTION)));
		crit.setResultTransformer(new ResultTransformer() {
			@Override
			public Object transformTuple(Object[] tuple, String[] aliases) {
				Category obj = new Category();
				try {
					BeanUtils.copyProperty(obj, Category.TITLE, tuple[0]);
					BeanUtils.copyProperty(obj, Category.PERMALINK, tuple[1]);
					BeanUtils.copyProperty(obj, Category.IMAGE_URL, tuple[2]);
					BeanUtils.copyProperty(obj, Category.IMAGE_THUMB_URL, tuple[3]);
					BeanUtils.copyProperty(obj, Category.DESCRIPTION, tuple[4]);
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
	public void updateThumb(Long pkCategory, String thumbURL) throws SystemException {
		String updateQuery = "UPDATE CATEGORY SET IMAGE_THUMB_URL = :thumbURL WHERE PK_CATEGORY = :pkCategory";
		SQLQuery sqlQuery = getSession().createSQLQuery(updateQuery);
		sqlQuery.setLong("pkCategory", pkCategory);
		sqlQuery.setString("thumbURL", thumbURL);
		sqlQuery.executeUpdate();
	}
}