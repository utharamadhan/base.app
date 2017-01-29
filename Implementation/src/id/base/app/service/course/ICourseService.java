package id.base.app.service.course;

import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.course.Course;

import java.util.List;
import java.util.Map;

public interface ICourseService extends MaintenanceService<Course> {
	
	public List<Course> findAllCourseCodeName() throws SystemException;
	
	public List<Course> findAllCourseAndTags(Map<String, Object> params) throws SystemException;
	
	public PagingWrapper<Course> findAllCourseAndTags(int startIndex, int maxRow, Map<String, Object> params) throws SystemException;

}
