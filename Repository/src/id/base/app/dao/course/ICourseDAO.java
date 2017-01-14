package id.base.app.dao.course;

import id.base.app.IBaseDAO;
import id.base.app.exception.SystemException;
import id.base.app.valueobject.course.Course;

import java.util.List;


public interface ICourseDAO extends IBaseDAO<Course> {
	
	public List<Course> findAllCourseCodeName() throws SystemException;

}
