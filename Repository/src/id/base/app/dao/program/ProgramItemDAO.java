package id.base.app.dao.program;

import id.base.app.AbstractHibernateDAO;
import id.base.app.ILookupConstant;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.DateTimeFunction;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.program.ProgramItem;

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
public class ProgramItemDAO extends AbstractHibernateDAO<ProgramItem, Long> implements IProgramItemDAO {

	@Override
	public ProgramItem findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(ProgramItem anObject) throws SystemException {
		if (anObject.getPkProgramItem()==null) {
			super.create(anObject);
		} else {
		    super.update(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		List<ProgramItem> objectList = new ArrayList<>();
		for(int i=0;i<objectPKs.length;i++){
			ProgramItem object = ProgramItem.getInstance();
			object = findById(objectPKs[i]);
			objectList.add(object);
		}
		super.delete(objectList);
	}

	@Override
	public List<ProgramItem> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<ProgramItem> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

	@Override
	public List<ProgramItem> findAllCourseCodeName() throws SystemException {
		Criteria crit = getSession().createCriteria(domainClass);
			crit.setProjection(Projections.projectionList().
					add(Projections.property("pkCourse")).
					add(Projections.property("name")));
			crit.setResultTransformer(new ResultTransformer() {
				@Override
				public Object transformTuple(Object[] tuple, String[] aliases) {
					ProgramItem course = new ProgramItem();
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
	public List<ProgramItem> findAllCourseAndTags(Map<String, Object> params) throws SystemException {
		List<ProgramItem> result = new ArrayList<ProgramItem>();
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
		Criteria crit = getSession().createCriteria(ProgramItem.class);
		crit.add(Restrictions.like(ProgramItem.PERMALINK, permalink, MatchMode.START));
		if(pk!=null){
			crit.add(Restrictions.ne(ProgramItem.PK_PROGRAM_ITEM, pk));
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
	public ProgramItem findByPermalink(String permalink) throws SystemException {
		Criteria criteria = getSession().createCriteria(ProgramItem.class);
		criteria.add(Restrictions.eq(ProgramItem.PERMALINK, permalink));
		criteria.add(Restrictions.eq(ProgramItem.STATUS, ILookupConstant.Status.PUBLISH));
		return (ProgramItem) criteria.uniqueResult();
	}
	
	@Override
	public void updateAnyUrl(Long pkProgramItem, ProgramItem programItem) throws SystemException {
		String updateQuery = "UPDATE PROGRAM_ITEM SET IMAGE_THUMB_URL = :thumbURL, BROCHURE_URL = :brochureURL  WHERE PK_PROGRAM_ITEM = :pk";
		SQLQuery sqlQuery = getSession().createSQLQuery(updateQuery);
		sqlQuery.setLong("pk", pkProgramItem);
		sqlQuery.setString("thumbURL", programItem.getImageURL());
		sqlQuery.setString("brochureURL", programItem.getBrochureURL());
		sqlQuery.executeUpdate();
	}
	
	@Override
	public List<ProgramItem> findForSelectEligibleReg(Long pkCategory) throws SystemException {
		Criteria crit = this.getSession().createCriteria(domainClass);
		crit.createAlias("categories", "categories");
		crit.add(Restrictions.eq("categories.pkCategory", pkCategory));
		crit.add(Restrictions.eq(ProgramItem.STATUS, ILookupConstant.Status.PUBLISH));
		crit.add(Restrictions.ge(ProgramItem.CLOSING_DATE_REG, DateTimeFunction.getCurrentDateWithoutTime()));
		crit.addOrder(Order.asc(ProgramItem.TITLE));
		crit.setProjection(Projections.projectionList().
				add(Projections.property(ProgramItem.PK_PROGRAM_ITEM)).
				add(Projections.property(ProgramItem.TITLE)).
				add(Projections.property(ProgramItem.PERMALINK)));
		crit.setResultTransformer(new ResultTransformer() {
			@Override
			public Object transformTuple(Object[] tuple, String[] aliases) {
				ProgramItem obj = new ProgramItem();
				try {
					BeanUtils.copyProperty(obj, ProgramItem.PK_PROGRAM_ITEM, tuple[0]);
					BeanUtils.copyProperty(obj, ProgramItem.TITLE, tuple[1]);
					BeanUtils.copyProperty(obj, ProgramItem.PERMALINK, tuple[2]);
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
	public List<ProgramItem> findForSelectEligibleRegByCategoryPermalink(String categoryPermalink) throws SystemException {
		Criteria crit = this.getSession().createCriteria(domainClass);
		crit.createAlias("categories", "categories");
		crit.add(Restrictions.eq("categories.permalink", categoryPermalink));
		crit.add(Restrictions.eq(ProgramItem.STATUS, ILookupConstant.Status.PUBLISH));
		crit.add(Restrictions.ge(ProgramItem.CLOSING_DATE_REG, DateTimeFunction.getCurrentDateWithoutTime()));
		crit.addOrder(Order.asc(ProgramItem.TITLE));
		crit.setProjection(Projections.projectionList().
				add(Projections.property(ProgramItem.PK_PROGRAM_ITEM)).
				add(Projections.property(ProgramItem.TITLE)).
				add(Projections.property(ProgramItem.PERMALINK)));
		crit.setResultTransformer(new ResultTransformer() {
			@Override
			public Object transformTuple(Object[] tuple, String[] aliases) {
				ProgramItem obj = new ProgramItem();
				try {
					BeanUtils.copyProperty(obj, ProgramItem.PK_PROGRAM_ITEM, tuple[0]);
					BeanUtils.copyProperty(obj, ProgramItem.TITLE, tuple[1]);
					BeanUtils.copyProperty(obj, ProgramItem.PERMALINK, tuple[2]);
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
	public List<ProgramItem> findForSelectByType(String type) throws SystemException {
		Criteria crit = this.getSession().createCriteria(domainClass);
		crit.add(Restrictions.eq("type", type));
		crit.add(Restrictions.eq(ProgramItem.STATUS, ILookupConstant.Status.PUBLISH));
		crit.add(Restrictions.ge(ProgramItem.CLOSING_DATE_REG, DateTimeFunction.getCurrentDateWithoutTime()));
		crit.addOrder(Order.asc(ProgramItem.TITLE));
		crit.setProjection(Projections.projectionList().
				add(Projections.property(ProgramItem.PK_PROGRAM_ITEM)).
				add(Projections.property(ProgramItem.TITLE)));
		crit.setResultTransformer(new ResultTransformer() {
			@Override
			public Object transformTuple(Object[] tuple, String[] aliases) {
				ProgramItem obj = new ProgramItem();
				try {
					BeanUtils.copyProperty(obj, ProgramItem.PK_PROGRAM_ITEM, tuple[0]);
					BeanUtils.copyProperty(obj, ProgramItem.TITLE, tuple[1]);
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