package id.base.app.dao.learning;

import id.base.app.AbstractHibernateDAO;
import id.base.app.ILookupConstant;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.DateTimeFunction;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.Category;
import id.base.app.valueobject.learning.LearningItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

@Repository
public class LearningItemDAO extends AbstractHibernateDAO<LearningItem, Long> implements ILearningItemDAO {

	@Override
	public LearningItem findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(LearningItem anObject) throws SystemException {
		if (anObject.getPkLearningItem()==null) {
			super.create(anObject);
		} else {
		    super.update(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		List<LearningItem> objectList = new ArrayList<>();
		for(int i=0;i<objectPKs.length;i++){
			LearningItem object = LearningItem.getInstance();
			object = findById(objectPKs[i]);
			objectList.add(object);
		}
		super.delete(objectList);
	}

	@Override
	public List<LearningItem> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<LearningItem> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

	@Override
	public List<LearningItem> findAllCourseCodeName() throws SystemException {
		Criteria crit = getSession().createCriteria(domainClass);
			crit.setProjection(Projections.projectionList().
					add(Projections.property("pkCourse")).
					add(Projections.property("name")));
			crit.setResultTransformer(new ResultTransformer() {
				@Override
				public Object transformTuple(Object[] tuple, String[] aliases) {
					LearningItem course = new LearningItem();
					try {
						BeanUtils.copyProperty(course, "pkCourse", tuple[0]);
						BeanUtils.copyProperty(course, "name", tuple[1]);
					} catch (Exception e) {
						LOGGER.error(e.getMessage(), e);
					}
					return course;
				}
				
				@Override
				public List transformList(List collection) {
					return collection;
				}
			});
		return crit.list();
	}

	@Override
	public List<LearningItem> findAllCourseAndTags(Map<String, Object> params) throws SystemException {
		List<LearningItem> result = new ArrayList<LearningItem>();
		StringBuilder select = new StringBuilder();
		select.append(" select co.*, string_agg(t.name, ',') as tags  ");
		select.append(" from course co ");
		select.append(" left join course_tag ct on ct.fk_course = co.pk_course ");
		select.append(" left join tag t on t.pk_tag = ct.fk_tag ");
		select.append(" where co.status = 1 ");
		if(params.get("groupCourse")!=null){
			Long groupCourse = (Long) params.get("groupCourse");
			select.append(" and co.fk_group_course = " + groupCourse + " ");
		}
		select.append(" group by co.pk_course ");
		Query querySelect = getSession().createSQLQuery(select.toString());
		result = querySelect.list();
		return result;
	}
	
	
	@Override
	public List<String> getSamePermalink(Long pk, String permalink) throws SystemException {
		Criteria crit = getSession().createCriteria(LearningItem.class);
		crit.add(Restrictions.like(LearningItem.PERMALINK, permalink, MatchMode.START));
		if(pk!=null){
			crit.add(Restrictions.ne(LearningItem.PK_LEARNING_ITEM, pk));
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
	public LearningItem findByPermalink(String permalink) throws SystemException {
		Criteria criteria = getSession().createCriteria(LearningItem.class);
		criteria.add(Restrictions.eq(LearningItem.PERMALINK, permalink));
		criteria.add(Restrictions.eq(LearningItem.STATUS, ILookupConstant.Status.PUBLISH));
		return (LearningItem) criteria.uniqueResult();
	}
	
	@Override
	public void updateThumb(Long pkLearningItem, String thumbURL) throws SystemException {
		String updateQuery = "UPDATE LEARNING_ITEM SET IMAGE_THUMB_URL = :thumbURL WHERE PK_LEARNING_ITEM = :pk";
		SQLQuery sqlQuery = getSession().createSQLQuery(updateQuery);
		sqlQuery.setLong("pk", pkLearningItem);
		sqlQuery.setString("thumbURL", thumbURL);
		sqlQuery.executeUpdate();
	}
	
	@Override
	public List<LearningItem> findForSelectEligibleReg(Long pkCategory) throws SystemException {
		Criteria crit = this.getSession().createCriteria(domainClass);
		crit.createAlias("categories", "categories");
		crit.add(Restrictions.eq("categories.pkCategory", pkCategory));
		crit.add(Restrictions.eq(LearningItem.STATUS, ILookupConstant.Status.PUBLISH));
		crit.add(Restrictions.ge(LearningItem.CLOSING_DATE_REG, DateTimeFunction.getCurrentDateWithoutTime()));
		crit.addOrder(Order.asc(LearningItem.TITLE));
		crit.setProjection(Projections.projectionList().
				add(Projections.property(LearningItem.PK_LEARNING_ITEM)).
				add(Projections.property(LearningItem.TITLE)));
		crit.setResultTransformer(new ResultTransformer() {
			@Override
			public Object transformTuple(Object[] tuple, String[] aliases) {
				LearningItem obj = new LearningItem();
				try {
					BeanUtils.copyProperty(obj, LearningItem.PK_LEARNING_ITEM, tuple[0]);
					BeanUtils.copyProperty(obj, LearningItem.TITLE, tuple[1]);
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
	
}
