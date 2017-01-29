package id.base.app.dao.course;

import java.util.List;
import java.util.Map;

import id.base.app.IBaseDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.valueobject.course.Course;


public interface ICourseDAO extends IBaseDAO<Course> {
	
	public List<Course> findAllCourseCodeName() throws SystemException;

	public List<Course> findAllCourseAndTags(Map<String, Object> params) throws SystemException;
	
	public PagingWrapper<Course> findAllCourseAndTags(int startIndex,int maxRow,Map<String, Object> params) throws SystemException;
}
