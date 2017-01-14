package id.base.app.service.course;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.course.Course;

import java.util.List;

public interface ICourseService extends MaintenanceService<Course> {
	
	public List<Course> findAllCourseCodeName() throws SystemException;

}
