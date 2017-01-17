package id.base.app.service.party;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.course.StudentCourse;
import id.base.app.valueobject.party.Student;

public interface IStudentService extends MaintenanceService<Student>{

	public StudentCourse findStudentCourseById(Long id) throws SystemException;
	
	public void enrollCourse(StudentCourse studentCourse) throws SystemException;
	
	public void processLearning(StudentCourse studentCourse) throws SystemException;
	
}
