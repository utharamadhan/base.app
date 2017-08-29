package id.base.app.service.party;

import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.service.MaintenanceService;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.party.Student;
import id.base.app.valueobject.party.VWStudentList;
import id.base.app.valueobject.program.StudentCourse;

import java.util.List;

public interface IStudentService extends MaintenanceService<Student>{

	public StudentCourse findStudentCourseById(Long id) throws SystemException;
	
	public PagingWrapper<VWStudentList> getListByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException;
	
	public void enrollCourse(StudentCourse studentCourse) throws SystemException;
	
	public void processLearning(StudentCourse studentCourse) throws SystemException;
	
}
