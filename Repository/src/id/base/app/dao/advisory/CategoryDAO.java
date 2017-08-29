package id.base.app.dao.advisory;

import id.base.app.AbstractHibernateDAO;
import id.base.app.ILookupConstant;
import id.base.app.SystemConstant;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.Category;
import id.base.app.valueobject.program.ProgramItem;

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
import org.hibernate.sql.JoinType;
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
				add(Projections.property(Category.DESCRIPTION)).
				add(Projections.property(Category.IS_ITEMS_NEW_PAGE)).
				add(Projections.property(Category.IS_SHOW_FILTER)));
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
					BeanUtils.copyProperty(obj, Category.IS_ITEMS_NEW_PAGE, tuple[5]);
					BeanUtils.copyProperty(obj, Category.IS_SHOW_FILTER, tuple[6]);
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
	
	public static String QUERY_SIMPLE_DATA_ALL = "SELECT * FROM ( "
			+ "SELECT CL.PK_CATEGORY, CL.PERMALINK, 'Learning -> '||initcap(lower(CL.TITLE)) AS TITLE FROM CATEGORY CL "
			+ "WHERE CL.TYPE = 'LG' AND CL.STATUS = 2 "
			+ "GROUP BY CL.PK_CATEGORY, CL.TITLE "
			+ "ORDER BY CL.ORDER_NO ASC  "
			+ ")C1 "
			+ "UNION "
			+ "SELECT * FROM ( "
			+ "SELECT C.PK_CATEGORY, C.PERMALINK, 'Advisory -> '||initcap(lower(C.TITLE)) AS TITLE FROM PROGRAM_ITEM_CATEGORY LIC  "
			+ "INNER JOIN CATEGORY C ON C.PK_CATEGORY = LIC.FK_CATEGORY "
			+ "WHERE C.TYPE = 'ADV' AND C.STATUS = 2 "
			+ "GROUP BY C.PK_CATEGORY, C.TITLE "
			+ ")C2";
			
	private List<Category> findSimpleDataForSelectAll() throws SystemException {
		Query query = getSession().createSQLQuery(QUERY_SIMPLE_DATA_ALL);
		query.setResultTransformer(new ResultTransformer() {
			@Override
			public Object transformTuple(Object[] tuple, String[] aliases) {
				Category cat = new Category();
				try{
					cat.setPkCategory(Long.valueOf(tuple[0].toString()));
					cat.setPermalink(tuple[1].toString());
					cat.setTitle(tuple[2].toString());
				}catch(Exception e){
					LOGGER.error(e.getMessage(), e);
				}
				return cat;
			}
			
			@Override
			public List transformList(List collection) {
				return collection;
			}
		});
		return query.list();
	}
	
	@Override
	public List<Category> findSimpleDataForSelect(String type) throws SystemException {
		if(type.equalsIgnoreCase(SystemConstant.CategoryType.ALL)){
			return findSimpleDataForSelectAll();
		}else{
			Criteria crit = this.getSession().createCriteria(domainClass);
			crit.add(Restrictions.eq(Category.STATUS, ILookupConstant.Status.PUBLISH));
			crit.add(Restrictions.eq(Category.TYPE, type));
			crit.addOrder(Order.asc(Category.ORDER_NO));
			crit.setProjection(Projections.projectionList().
					add(Projections.property(Category.PK_CATEGORY)).
					add(Projections.property(Category.TITLE)).
					add(Projections.property(Category.PERMALINK)));
			crit.setResultTransformer(new ResultTransformer() {
				@Override
				public Object transformTuple(Object[] tuple, String[] aliases) {
					Category obj = new Category();
					try {
						BeanUtils.copyProperty(obj, Category.PK_CATEGORY, tuple[0]);
						BeanUtils.copyProperty(obj, Category.TITLE, tuple[1]);
						BeanUtils.copyProperty(obj, Category.PERMALINK, tuple[1]);
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
	
	@Override
	public void updateThumb(Long pkCategory, String thumbURL) throws SystemException {
		String updateQuery = "UPDATE CATEGORY SET IMAGE_THUMB_URL = :thumbURL WHERE PK_CATEGORY = :pkCategory";
		SQLQuery sqlQuery = getSession().createSQLQuery(updateQuery);
		sqlQuery.setLong("pkCategory", pkCategory);
		sqlQuery.setString("thumbURL", thumbURL);
		sqlQuery.executeUpdate();
	}
	
	@Override
	public Category findIsShowFilterByPermalink(String permalink) throws SystemException {
		Criteria crit = this.getSession().createCriteria(domainClass);
		crit.add(Restrictions.eq(Category.STATUS, ILookupConstant.Status.PUBLISH));
		crit.add(Restrictions.eq(Category.PERMALINK, permalink));
		crit.addOrder(Order.desc(Category.PK_CATEGORY));
		crit.setProjection(Projections.projectionList().
				add(Projections.property(Category.PK_CATEGORY)).
				add(Projections.property(Category.TITLE)).
				add(Projections.property(Category.IS_SHOW_FILTER)));
		crit.setResultTransformer(new ResultTransformer() {
			@Override
			public Object transformTuple(Object[] tuple, String[] aliases) {
				Category obj = new Category();
				try {
					BeanUtils.copyProperty(obj, Category.PK_CATEGORY, tuple[0]);
					BeanUtils.copyProperty(obj, Category.TITLE, tuple[1]);
					BeanUtils.copyProperty(obj, Category.IS_SHOW_FILTER, tuple[2]);
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
	public List<Category> findSimpleDataWithItemsForList(String type) throws SystemException {
		Criteria crit = this.getSession().createCriteria(domainClass);
		crit.createAlias("items", "items", JoinType.LEFT_OUTER_JOIN);
		crit.add(Restrictions.eq(Category.STATUS, ILookupConstant.Status.PUBLISH));
		crit.add(Restrictions.eq(Category.TYPE, type));
		crit.addOrder(Order.asc(Category.ORDER_NO));
		crit.setProjection(Projections.projectionList().
				add(Projections.property(Category.TITLE)).
				add(Projections.property(Category.PERMALINK)).
				add(Projections.property(Category.IMAGE_URL)).
				add(Projections.property(Category.IMAGE_THUMB_URL)).
				add(Projections.property(Category.DESCRIPTION)).
				add(Projections.property(Category.DETAIL_LINK_IMAGE_URL)).
				add(Projections.property("items.permalink")));
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
					BeanUtils.copyProperty(obj, Category.DETAIL_LINK_IMAGE_URL, tuple[5]);
					if(tuple[6]!=null){
						List<ProgramItem> liList = new ArrayList<>(); 
						ProgramItem li = new ProgramItem();
						BeanUtils.copyProperty(li, ProgramItem.PERMALINK, tuple[6]);
						liList.add(li);
						obj.setItems(liList);
					}
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