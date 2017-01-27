package id.base.app.service.course;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.base.app.dao.course.ICourseTagDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.course.Course;
import id.base.app.valueobject.course.Tag;

@Service
@Transactional
public class CourseTagService implements ICourseTagService {

	@Autowired
	private ICourseTagDAO courseTagDAO;
    
	public PagingWrapper<Course> findAll(int startNo, int offset) throws SystemException {
		return null;
	}

	public Tag findById(Long id) throws SystemException {
		return courseTagDAO.findById(id);
	}

	public void saveOrUpdate(Tag anObject) throws SystemException {
		courseTagDAO.saveOrUpdate(anObject);
	}

	public void delete(Long[] objectPKs) throws SystemException {
		courseTagDAO.delete(objectPKs);
	}

	public PagingWrapper<Tag> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return courseTagDAO.findAllByFilter(startNo, offset, filter, order);
	}
	
	@Override
	public List<Tag> findObjects(Long[] objectPKs) throws SystemException {
		List<Tag> objects = new ArrayList<>();	
		Tag object = null;
		for(Long l:objectPKs){
			object = courseTagDAO.findById(l);
			objects.add(object);
		}
		return objects;
	}

	@Override
	public List<Tag> findAll(List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return courseTagDAO.findAll(filter, order);
	}


}
