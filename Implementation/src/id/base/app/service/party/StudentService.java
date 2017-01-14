package id.base.app.service.party;

import id.base.app.dao.party.IStudentDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.course.StudentCourse;
import id.base.app.valueobject.party.Student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentService implements IStudentService {
	
	@Autowired
	private IStudentDAO studentDAO;
	
	@Override
	@Transactional(readOnly=true)
	public Student findById(Long id) throws SystemException {
		Student student = studentDAO.findById(id);
		student.setStudentCourses(studentDAO.findAllStudentCourses(student.getPkStudent()));
		return student;
	}

	@Override
	public void saveOrUpdate(Student anObject) throws SystemException {
		studentDAO.saveOrUpdate(anObject);
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
	}

	@Override
	public List<Student> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<Student> findAllByFilter(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return studentDAO.findAllByFilter(startNo, offset, filter, order);
	}

	@Override
	public List<Student> findAll(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		return null;
	}

	@Override
	public void enrollCourse(StudentCourse studentCourse) throws SystemException {
		studentDAO.enrollCourse(studentCourse);
	}

}