package id.base.app.controller.party;

import id.base.app.ILookupConstant;
import id.base.app.ILookupGroupConstant;
import id.base.app.SystemConstant;
import id.base.app.controller.SuperController;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.lookup.ILookupService;
import id.base.app.service.party.IStudentService;
import id.base.app.util.DateTimeFunction;
import id.base.app.validation.InvalidRequestException;
import id.base.app.valueobject.course.StudentCourse;
import id.base.app.valueobject.party.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value=RestConstant.RM_STUDENT)
public class StudentController extends SuperController<Student> {
	
	public StudentController() {
		super();
	}
	
	@Autowired
	private ILookupService lookupService;
	@Autowired
	private IStudentService studentService;

	@Override
	public MaintenanceService<Student> getMaintenanceService() {
		return studentService;
	}
	
	@Override
	public Student preUpdate(Student anObject) throws SystemException{
		anObject.setStatus(SystemConstant.ValidFlag.VALID);
		return validate(anObject);
	}

	@Override
	public Student validate(Student anObject) throws SystemException {
		return anObject;
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/enrollCourse")
	@ResponseStatus( HttpStatus.OK )
	public void enrollCourse(@RequestBody StudentCourse anObject, BindingResult bindingResult) throws SystemException {
		if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("Invalid validation", bindingResult);
        }
	    studentService.enrollCourse(preEnrollCourse(anObject));
	}
	
	public StudentCourse preEnrollCourse(StudentCourse anObject) throws SystemException{
			anObject.setStatus(SystemConstant.ValidFlag.VALID);
			anObject.setEnrollDate(DateTimeFunction.truncateDate(DateTimeFunction.getCalendar().getTime()));
			anObject.setStudentCourseStatusLookup(lookupService.findByCode(ILookupConstant.EnrollmentStatus.ENROLLED, ILookupGroupConstant.ENROLLMENT_STATUS));
		return validateEnrollCourse(anObject);
	}

	public StudentCourse validateEnrollCourse(StudentCourse anObject) throws SystemException {
		return anObject;
	}
	
}