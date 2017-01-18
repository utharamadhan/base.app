package id.base.app.controller.party;

import id.base.app.ILookupConstant;
import id.base.app.ILookupGroupConstant;
import id.base.app.SystemConstant;
import id.base.app.controller.SuperController;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.lookup.ILookupService;
import id.base.app.service.party.IStudentService;
import id.base.app.util.DateTimeFunction;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.validation.InvalidRequestException;
import id.base.app.valueobject.UpdateEntity;
import id.base.app.valueobject.course.StudentCourse;
import id.base.app.valueobject.party.Student;
import id.base.app.valueobject.party.VWStudentList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;

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
	
	@RequestMapping(method=RequestMethod.PUT, value="/updateReturn")
	@ResponseBody
	@ResponseStatus( HttpStatus.OK )
	public Long updateReturn(@RequestBody @Validated(UpdateEntity.class) Student student, BindingResult bindingResult) throws SystemException {
		if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("Invalid validation", bindingResult);
        }
	    getMaintenanceService().saveOrUpdate(preUpdate(student));
	    return student.getPkStudent();
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
	
	@RequestMapping(method=RequestMethod.PUT, value="/processLearning")
	@ResponseStatus( HttpStatus.OK )
	public void processLearning(@RequestBody StudentCourse anObject, BindingResult bindingResult) throws SystemException {
		if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("Invalid validation", bindingResult);
        }
	    studentService.processLearning(preProcessLearning(anObject));
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/getListByFilter")
	@ResponseBody
	public PagingWrapper<VWStudentList> getListByFilter(
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="10") int offset,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson,
			@RequestParam(value="order", defaultValue="", required=false) String orderJson)
			throws SystemException {
		PagingWrapper<VWStudentList> pw = new PagingWrapper<>();
		try {
			List<SearchFilter> filter = new ArrayList<SearchFilter>();
			if(StringUtils.isNotEmpty(filterJson)){
				filter = mapper.readValue(filterJson, new TypeReference<List<SearchFilter>>(){});
			}
			List<SearchOrder> order = new ArrayList<SearchOrder>();
			if(StringUtils.isNotEmpty(orderJson)){
				order = mapper.readValue(orderJson, new TypeReference<List<SearchOrder>>(){});
			}
			pw = studentService.getListByFilter(startNo, offset, filter, order);
			LOGGER.debug(mapper.writeValueAsString(pw));
			return pw;
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error("error finding your data",e);
			throw new SystemException(new ErrorHolder("error finding your data"));
		}
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
	
	public StudentCourse preProcessLearning(StudentCourse anObject) throws SystemException {
		StudentCourse objDb = studentService.findStudentCourseById(anObject.getPkStudentCourse());
			objDb.setActionType(anObject.getActionType());
		return validateProcessLearning(objDb);
	}
	
	public StudentCourse validateProcessLearning(StudentCourse anObject) throws SystemException {
		return anObject;
	}
	
}