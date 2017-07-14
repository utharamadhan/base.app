package id.base.app.service.party;

import id.base.app.ILookupConstant;
import id.base.app.ILookupGroupConstant;
import id.base.app.dao.lookup.ILookupDAO;
import id.base.app.dao.party.IStudentDAO;
import id.base.app.dao.party.IVWStudentListDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.DateTimeFunction;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.learning.StudentCourse;
import id.base.app.valueobject.party.Student;
import id.base.app.valueobject.party.VWStudentList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentService implements IStudentService {
	
	@Autowired
	private ILookupDAO lookupDAO;
	@Autowired
	private IStudentDAO studentDAO;
	@Autowired
	private IVWStudentListDAO vwStudentListDAO;
	
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
		studentDAO.delete(objectPKs);
	}

	@Override
	public List<Student> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	@Deprecated
	public PagingWrapper<Student> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return studentDAO.findAllByFilter(startNo, offset, filter, order);
	}
	
	@Override
	public PagingWrapper<VWStudentList> getListByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return vwStudentListDAO.findAllByFilter(startNo, offset, filter, order);
	}

	@Override
	public List<Student> findAll(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		return studentDAO.findAll(filter, order);
	}

	@Override
	public void enrollCourse(StudentCourse studentCourse) throws SystemException {
		studentDAO.enrollCourse(studentCourse);
	}

	@Override
	public void processLearning(StudentCourse studentCourse) throws SystemException {
		if(studentCourse.getActionType().equals("cancel")) {
			studentDAO.deleteStudentCourse(studentCourse);
		} else {
			studentCourse.setStudentCourseStatusLookup(lookupDAO.findByCode(
					ILookupConstant.EnrollmentStatus.ENROLLMENT_STATUS_MAP.get(studentCourse.getActionType()), 
					ILookupGroupConstant.ENROLLMENT_STATUS));
			studentCourse.setLastActionDate(DateTimeFunction.getCurrentDate());
			studentDAO.processLearning(studentCourse);
		}
	}

	@Override
	public StudentCourse findStudentCourseById(Long id) throws SystemException {
		return studentDAO.findStudentCourseById(id);
	}

}