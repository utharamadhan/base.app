package id.base.app.dao.course;

import id.base.app.AbstractHibernateDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.course.GroupCourse;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

@Repository
public class GroupCourseDAO extends AbstractHibernateDAO<GroupCourse, Long> implements IGroupCourseDAO {

	@Override
	public GroupCourse findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(GroupCourse anObject) throws SystemException {
		if (anObject.getPkGroupCourse()==null) {
			super.create(anObject);
		} else {
		    super.update(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		List<GroupCourse> objectList = new ArrayList<>();
		for(int i=0;i<objectPKs.length;i++){
			GroupCourse object = GroupCourse.getInstance();
			object = findById(objectPKs[i]);
			objectList.add(object);
		}
		super.delete(objectList);
	}

	@Override
	public List<GroupCourse> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<GroupCourse> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

	@Override
	public List<GroupCourse> findAllGroupCourseCodeName() throws SystemException {
		Criteria crit = getSession().createCriteria(domainClass);
			crit.setProjection(Projections.projectionList().
					add(Projections.property("pkGroupCourse")).
					add(Projections.property("code")).
					add(Projections.property("name")));
			crit.setResultTransformer(new ResultTransformer() {
				@Override
				public Object transformTuple(Object[] tuple, String[] aliases) {
					GroupCourse gc = GroupCourse.getInstance();
					try {
						BeanUtils.copyProperty(gc, "pkGroupCourse", tuple[0]);
						BeanUtils.copyProperty(gc, "code", tuple[1]);
						BeanUtils.copyProperty(gc, "name", tuple[2]);
					} catch (Exception e) {}
					return gc;
				}
				
				@Override
				public List transformList(List collection) {
					return collection;
				}
			});
		return crit.list();
	}

}
