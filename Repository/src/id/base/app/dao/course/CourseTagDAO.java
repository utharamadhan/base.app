package id.base.app.dao.course;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import id.base.app.AbstractHibernateDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.course.Tag;

@Repository
public class CourseTagDAO extends AbstractHibernateDAO<Tag, Long> implements ICourseTagDAO {

	@Override
	public Tag findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(Tag anObject) throws SystemException {
		if (anObject.getPkTag()==null) {
			super.create(anObject);
		} else {
		    super.update(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		List<Tag> objectList = new ArrayList<>();
		for(int i=0;i<objectPKs.length;i++){
			Tag object = Tag.getInstance();
			object = findById(objectPKs[i]);
			objectList.add(object);
		}
		super.delete(objectList);
	}

	@Override
	public List<Tag> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<Tag> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

}
