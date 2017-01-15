package id.base.app.service.course;

import id.base.app.dao.course.ICourseDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.course.Course;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CourseService implements ICourseService {

	@Autowired
	private ICourseDAO courseDAO;
    
	public PagingWrapper<Course> findAll(int startNo, int offset) throws SystemException {
		return null;
	}

	public Course findById(Long id) throws SystemException {
		return courseDAO.findById(id);
	}

	public void saveOrUpdate(Course anObject) throws SystemException {
		courseDAO.saveOrUpdate(anObject);
	}

	public void delete(Long[] objectPKs) throws SystemException {
		courseDAO.delete(objectPKs);
	}

	public PagingWrapper<Course> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return courseDAO.findAllByFilter(startNo, offset, filter, order);
	}
	
	@Override
	public List<Course> findObjects(Long[] objectPKs) throws SystemException {
		List<Course> objects = new ArrayList<>();
		Course object = null;
		for(Long l:objectPKs){
			object = courseDAO.findById(l);
			objects.add(object);
		}
		return objects;
	}

	@Override
	public List<Course> findAll(List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return courseDAO.findAll(filter, order);
	}

	@Override
	public List<Course> findAllCourseCodeName() throws SystemException {
		return courseDAO.findAllCourseCodeName();
	}

}
