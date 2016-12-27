package id.base.app.service.course;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.course.GroupCourse;

import java.util.List;

public interface IGroupCourseService extends MaintenanceService<GroupCourse> {
	
	public List<GroupCourse> findAllGroupCourseCodeName() throws SystemException;

}
