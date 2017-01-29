package id.base.app.dao.course;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

import id.base.app.AbstractHibernateDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingUtil;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.Lookup;
import id.base.app.valueobject.course.Course;

@Repository
public class CourseDAO extends AbstractHibernateDAO<Course, Long> implements ICourseDAO {

	@Override
	public Course findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(Course anObject) throws SystemException {
		if (anObject.getPkCourse()==null) {
			super.create(anObject);
		} else {
		    super.update(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		List<Course> objectList = new ArrayList<>();
		for(int i=0;i<objectPKs.length;i++){
			Course object = Course.getInstance();
			object = findById(objectPKs[i]);
			objectList.add(object);
		}
		super.delete(objectList);
	}

	@Override
	public List<Course> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<Course> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

	@Override
	public List<Course> findAllCourseCodeName() throws SystemException {
		Criteria crit = getSession().createCriteria(domainClass);
			crit.setProjection(Projections.projectionList().
					add(Projections.property("pkCourse")).
					add(Projections.property("name")));
			crit.setResultTransformer(new ResultTransformer() {
				@Override
				public Object transformTuple(Object[] tuple, String[] aliases) {
					Course course = new Course();
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
	public List<Course> findAllCourseAndTags(Map<String, Object> params) throws SystemException {
		List<Course> result = new ArrayList<Course>();
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
	public PagingWrapper<Course> findAllCourseAndTags(int startIndex, int maxRow, Map<String, Object> params)
			throws SystemException {
		List<Course> result = new ArrayList<Course>();
		StringBuilder select = new StringBuilder();
		select.append(" select co.pk_course, ");//1
		select.append(" co.code, ");//2
		select.append(" co.name, ");//3
		select.append(" co.basic_picture_url, ");//4
		select.append(" l.pk_lookup, ");//5
		select.append(" l.name as category, ");//6
		select.append(" string_agg(t.name, ',') as tags ");//7
		select.append(" from course co ");
		select.append(" inner join lookup l on l.pk_lookup = co.fk_lookup_course_category ");
		select.append(" left join course_tag ct on ct.fk_course = co.pk_course ");
		select.append(" left join tag t on t.pk_tag = ct.fk_tag ");
		select.append(" where co.status = 1 ");
		if(params.get("groupCourse")!=null){
			Long groupCourse = new Long((String)params.get("groupCourse"));
			select.append(" and co.fk_group_course = " + groupCourse + " ");
		}
		select.append(" group by co.pk_course, l.pk_lookup ");
		Query querySelect = getSession().createSQLQuery(select.toString());
		querySelect.setFirstResult(startIndex - 1).setMaxResults(maxRow);
		querySelect.setResultTransformer(new ResultTransformer() {
			
			@Override
			public Object transformTuple(Object[] tupple, String[] arg1) {
				Course course = new Course();
				course.setPkCourse(tupple[0]==null?null:Long.valueOf(tupple[0].toString()));
				course.setCode(tupple[0]==null?null:tupple[1].toString());
				course.setName(tupple[0]==null?null:tupple[2].toString());
				course.setBasicPictureURL(tupple[0]==null?null:tupple[3].toString());
				course.setTags(tupple[0]==null?null:tupple[6].toString());
				Lookup lookup = new Lookup();
				lookup.setPkLookup(tupple[0]==null?null:Long.valueOf(tupple[4].toString()));
				lookup.setName(tupple[0]==null?null:tupple[5].toString());
				course.setCategoryLookup(lookup);
				
				return course;
			}

			@SuppressWarnings("rawtypes")
			@Override
			public List transformList(List list) {
				return list;
			}
		});
		
		result = querySelect.list();
		
		
		StringBuilder count = new StringBuilder();
		count.append(" select count(*) from ( ");
		count.append(select.toString());
		count.append(" )x ");
		Query queryCount = getSession().createSQLQuery(count.toString());
		
		Long _totalRecords = new Long(queryCount.uniqueResult().toString());
		if(_totalRecords==null){
			_totalRecords = 0L;
		}
		// normalize startIndex (in case startIndex > totalRecords/maxRow)
		if (startIndex > _totalRecords) {
			startIndex = PagingUtil.getLastPageStartRow(_totalRecords, maxRow);
		}

		return new PagingWrapper<Course>(result, _totalRecords, startIndex, maxRow);
	}

}
