package id.base.app.dao.party;

import id.base.app.IBaseDAO;
import id.base.app.exception.SystemException;
import id.base.app.valueobject.course.StudentCourse;
import id.base.app.valueobject.party.Student;

import java.util.List;

public interface IStudentDAO extends IBaseDAO<Student> {
	
	public StudentCourse findStudentCourseById(Long id) throws SystemException;
	
	public void enrollCourse(StudentCourse studentCourse) throws SystemException;
	
	public void processLearning(StudentCourse studentCourse) throws SystemException;
	
	public void deleteStudentCourse(StudentCourse studentCourse) throws SystemException;
	
	public List<StudentCourse> findAllStudentCourses(Long pkStudent) throws SystemException;
	
}
