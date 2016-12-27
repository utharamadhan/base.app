package id.base.app.dao.course;

import id.base.app.IBaseDAO;
import id.base.app.exception.SystemException;
import id.base.app.valueobject.course.GroupCourse;

import java.util.List;


public interface IGroupCourseDAO extends IBaseDAO<GroupCourse> {

	public List<GroupCourse> findAllGroupCourseCodeName() throws SystemException;
	
}
